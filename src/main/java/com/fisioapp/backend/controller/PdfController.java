package com.fisioapp.backend.controller;

import com.fisioapp.backend.dto.DtoRequest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "*")
public class PdfController {

    @PostMapping("/generar")
    public ResponseEntity<byte[]> generarPdf(@RequestBody DtoRequest datos) {
        // --- CÓDIGO ESPÍA (Debug) ---
        System.out.println("================ RECIBIENDO SOLICITUD ================");
        System.out.println("Nombre Paciente: " + datos.getNombrePaciente());
        System.out.println("Firma Paciente (Texto): " + datos.getFirmaPaciente()); 
        System.out.println("Firma Fisio (Texto): " + datos.getFirmaFisio());       
        System.out.println("======================================================");
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();

            // --- ESTILOS (Fuentes) ---
            Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font fontSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font fontTexto = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font fontNegrita = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

            // --- 1. ENCABEZADO ---
            Paragraph titulo = new Paragraph("Historia Clínica de Fisioterapia", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph("Fecha de Consulta: " + java.time.LocalDate.now(), fontTexto));
            document.add(new Paragraph(" ")); // Espacio vacío
            //1- Info del fisio
            PdfPTable tablaFisio = new PdfPTable(3);
            tablaFisio.setWidthPercentage(100);

            // Celda 1: Nombre
            PdfPCell celdaNombreF = new PdfPCell(new Paragraph("Fisioterapeuta: " + datos.getNombreFisio(), fontTexto));
            celdaNombreF.setPadding(10);
            tablaFisio.addCell(celdaNombreF);
            // Celda 2: Cedula
            PdfPCell celdacel = new PdfPCell(new Paragraph("Cédula Profesional: " + datos.getCedulaProfesional(), fontTexto));
            celdacel.setPadding(10);
            tablaFisio.addCell(celdacel);
            //Celda 3 Clinica
            PdfPCell celdaClinica = new PdfPCell(new Paragraph("Clínica: "+datos.getClinica(),fontTexto));
            celdaClinica.setPadding(10);
            tablaFisio.addCell(celdaClinica);

            document.add(tablaFisio);
            document.add(new Paragraph(" "));

            // --- 2. TABLA DE DATOS DEL PACIENTE ---
            PdfPTable tablaPaciente = new PdfPTable(2);
            tablaPaciente.setWidthPercentage(100);

            PdfPCell celdaNombre = new PdfPCell(new Paragraph("Paciente: " + datos.getNombrePaciente(), fontTexto));
            celdaNombre.setPadding(6);
            tablaPaciente.addCell(celdaNombre);

            PdfPCell celdaEdad = new PdfPCell(new Paragraph("Edad: " +
                    (datos.getEdad() != null ? datos.getEdad() + " años" : ""), fontTexto));
            celdaEdad.setPadding(6);
            tablaPaciente.addCell(celdaEdad);

            // Fila 2: Sexo y Teléfono
            PdfPCell celdaSexo = new PdfPCell(new Paragraph("Sexo: " +
                    (datos.getSexo() != null ? datos.getSexo() : ""), fontTexto));
            celdaSexo.setPadding(6);
            tablaPaciente.addCell(celdaSexo);

            PdfPCell celdaTel = new PdfPCell(new Paragraph("Teléfono: " + datos.getTelefono(), fontTexto));
            celdaTel.setPadding(6);
            tablaPaciente.addCell(celdaTel);

            // Fila 3: Ocupación y Lateralidad
            PdfPCell celdaOcupacion = new PdfPCell(new Paragraph("Ocupación: " +
                    (datos.getOcupacion() != null ? datos.getOcupacion() : ""), fontTexto));
            celdaOcupacion.setPadding(6);
            tablaPaciente.addCell(celdaOcupacion);

            PdfPCell celdaLateralidad = new PdfPCell(new Paragraph("Lateralidad: " +
                    (datos.getLateralidad() != null ? datos.getLateralidad() : ""), fontTexto));
            celdaLateralidad.setPadding(6);
            tablaPaciente.addCell(celdaLateralidad);

            document.add(tablaPaciente);
            document.add(new Paragraph(" "));

            // --- 3. SECCIONES MÉDICAS ---

            // Motivo
            document.add(new Paragraph("Motivo de Consulta:", fontSubtitulo));
            document.add(new Paragraph((datos.getMotivoConsulta() != null ? datos.getMotivoConsulta() : "No descrito"), fontTexto));
            document.add(new Paragraph("Padecimiento Actual Inicio, Mecanismo y Evolución",fontTexto));
            document.add(new Paragraph((datos.getPadecimientoActual() != null ? datos.getPadecimientoActual() : "No descrito"), fontTexto));
            document.add(new Paragraph("------------------------------------------------"));

            // Antecedentes
            document.add(new Paragraph("Antecedentes Médicos:", fontSubtitulo));
            if (datos.getAntecedentes() != null && !datos.getAntecedentes().isEmpty()) {
                String listaEnfermedades = String.join(", ", datos.getAntecedentes());
                document.add(new Paragraph(listaEnfermedades, fontTexto));
            } else {
                document.add(new Paragraph("Ninguno reportado.", fontTexto));
            }

            // Estilo de vida
            document.add(new Paragraph("Estilo de Vida:", fontSubtitulo));
            document.add(new Paragraph("Nivel de Actividad: " +
                    (datos.getActividadFisica() != null ? datos.getActividadFisica() : "No especificado"), fontTexto ));

            // Hábitos
            document.add(new Paragraph("Hábitos Tóxicos:", fontNegrita));
            if (datos.getHabitosToxicos() != null && !datos.getHabitosToxicos().isEmpty()){
                String malosHabitos = String.join(", ", datos.getHabitosToxicos());
                document.add(new Paragraph(malosHabitos, fontTexto));
            } else {
                document.add(new Paragraph("Niega toxicomanías.", fontTexto));
            }

            document.add(new Paragraph("Horas de Sueño: " +
                    (datos.getHorasSueño() != null ? datos.getHorasSueño() : "No especificado"), fontTexto));

            // Estrés
            document.add(new Paragraph("Nivel de Estrés Percibido (1-10): " +
                    (datos.getNivelEstres() != null ? datos.getNivelEstres() : "No registrado"), fontTexto));

            document.add(new Paragraph("------------------------------------------------"));

            // --- EVA y DOLOR ---
            document.add(new Paragraph("Evaluación Subjetiva del Dolor (EVA)", fontSubtitulo));

            document.add(new Paragraph("Localización:", fontNegrita));
            document.add(new Paragraph(datos.getLocalizacionDolor() != null ? datos.getLocalizacionDolor() : "No descrito", fontTexto));

            if (datos.getPatronIrradiacion() != null && !datos.getPatronIrradiacion().isEmpty()){
                document.add(new Paragraph("Patrón de Irradiación: " + datos.getPatronIrradiacion(), fontTexto));
            }

            document.add(new Paragraph("Características del Dolor:", fontNegrita));
            if (datos.getTipoDolor() != null && !datos.getTipoDolor().isEmpty()){
                String tipoDolor = String.join(", ", datos.getTipoDolor());
                document.add(new Paragraph("Tipo: " + tipoDolor, fontTexto));
            }

            if (datos.getRitmoDolor() != null && !datos.getRitmoDolor().isEmpty()){
                String ritmoDolor = String.join(", ", datos.getRitmoDolor());
                document.add(new Paragraph("Ritmo: " + ritmoDolor, fontTexto));
            }

            // Intensidad EVA
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Intensidad del Dolor:", fontNegrita));
            document.add(new Paragraph("Actual (0-10): " +
                    (datos.getDolorActual() != null ? datos.getDolorActual() : "-"), fontTexto));
            document.add(new Paragraph("Máximo (Peor): " +
                    (datos.getDolorMaximo() != null ? datos.getDolorMaximo() : "-"), fontTexto));
            document.add(new Paragraph("Mínimo (Mejor): " +
                    (datos.getDolorMinimo() != null ? datos.getDolorMinimo() : "-"), fontTexto));

            document.add(new Paragraph("------------------------------------------------"));

            // --- EXPLORACIÓN FÍSICA ---
            document.add(new Paragraph("Exploración Física y Funcional", fontSubtitulo));

            // Signos Vitales
            document.add(new Paragraph("Frecuencia Cardiaca: " +
                    (datos.getFrecuenciaCardiaca() != null ? datos.getFrecuenciaCardiaca() + " lpm" : "No registrado"), fontTexto));

            document.add(new Paragraph("Frecuencia Respiratoria: " +
                    (datos.getFrecuenciaRespiratoria() != null ? datos.getFrecuenciaRespiratoria() + " rpm" : "No registrado"), fontTexto));

            document.add(new Paragraph("Temperatura Corporal: " +
                    (datos.getTemperaturaCorporal() != null ? datos.getTemperaturaCorporal() + " °C" : "No registrado"), fontTexto));

            document.add(new Paragraph("Saturación de Oxígeno (SpO2): " +
                    (datos.getSaturacionOxigeno() != null ? datos.getSaturacionOxigeno() + " %" : "No registrado"), fontTexto));

            String paTexto = "No registrado";
            if (datos.getPresionArterialSistolica() != null && datos.getPresionArterialDiastolica() != null){
                paTexto = datos.getPresionArterialSistolica() + "/" + datos.getPresionArterialDiastolica() + " mmHg";
            }
            document.add(new Paragraph("Presión Arterial (PA): " + paTexto, fontTexto));
            document.add(new Paragraph(" "));

            // Balance y Coordinación
            document.add(new Paragraph("Balance y Coordinación (0-10)", fontNegrita));
            document.add(new Paragraph("Equilibrio Estático: " +
                    (datos.getEquilibrioEstatico() != null ? datos.getEquilibrioEstatico() : "-"),fontTexto));
            document.add(new Paragraph("Equilibrio Dinámico: " +
                    (datos.getEquilibrioDinamico() != null ? datos.getEquilibrioDinamico() : "-"),fontTexto));
            document.add(new Paragraph("Coordinación Motora: " +
                    (datos.getCoordinacionMotora() != null ? datos.getCoordinacionMotora() : "-"),fontTexto));
            document.add(new Paragraph("Propiocepción: " +
                    (datos.getPropiocepcion() != null ? datos.getPropiocepcion() : "-"),fontTexto));
            document.add(new Paragraph(" "));

            // Evaluación Respiratoria y Circulatoria
            document.add(new Paragraph("Evaluación Respiratoria y Circulatoria", fontSubtitulo));

            if (datos.getExpansionToracica() != null && !datos.getExpansionToracica().isEmpty()){
                String texto = String.join(", ", datos.getExpansionToracica());
                document.add(new Paragraph("Expansión Torácica: " + texto, fontTexto));
            }

            if (datos.getColoracionPiel() != null && !datos.getColoracionPiel().isEmpty()){
                String texto = String.join(", ", datos.getColoracionPiel());
                document.add(new Paragraph("Coloración de Piel: " + texto, fontTexto));
            }

            if (datos.getLlenadoCapilar() != null && !datos.getLlenadoCapilar().isEmpty()){
                String texto = String.join(", ", datos.getLlenadoCapilar());
                document.add(new Paragraph("Llenado Capilar: " + texto, fontTexto));
            }

            document.add(new Paragraph("Edema: " +
                    (datos.getPresenciaEdema() != null ? datos.getPresenciaEdema() : "No especificado"), fontTexto ));
            document.add(new Paragraph(" "));

            // Integridad Cutánea
            document.add(new Paragraph("Integridad Cutánea General:", fontSubtitulo));
            if (datos.getIntegridadCutanea() != null) {
                document.add(new Paragraph("Estado: " + datos.getIntegridadCutanea(), fontTexto));

                if (datos.getIntegridadCutanea().equals("Alterada")) {
                    document.add(new Paragraph("Hallazgos Específicos: ", fontNegrita));

                   
                    if (datos.getHallazgosEspecificos() != null && !datos.getHallazgosEspecificos().isEmpty()) {
                        String hallazgos = String.join(", ", datos.getHallazgosEspecificos());
                        document.add(new Paragraph(hallazgos, fontTexto));
                    } else {
                        document.add(new Paragraph("No especificados", fontTexto));
                    }
                }
            }

            document.add(new Paragraph(" "));

            // Evaluación Muscular y ROM
            document.add(new Paragraph("Evaluación Muscular y ROM", fontSubtitulo));

            document.add(new Paragraph("ROM ACTIVO (grados): " +
                    (datos.getRangoMovimientoActivo() != null ? datos.getRangoMovimientoActivo() : "-"), fontTexto));

            document.add(new Paragraph("ROM PASIVO (grados): " +
                    (datos.getRangoMovimientoPasivo() != null ? datos.getRangoMovimientoPasivo() : "-"), fontTexto));

            document.add(new Paragraph("Fuerza Muscular (Daniels): " +
                    (datos.getFuerzaMuscular() != null ? datos.getFuerzaMuscular() : "-"), fontTexto));

            document.add(new Paragraph("Perímetros / Edema: " +
                    (datos.getPerimetrosMusculares() != null ? datos.getPerimetrosMusculares() : "-"), fontTexto));

         //7 - Evaluación postura
            document.add(new Paragraph("Evaluación de la Postura y Marcha: ",fontSubtitulo));

            document.add(new Paragraph("Patrón de Marcha / Deambulación: " +
                    (datos.getPatronMarcha() != null ? datos.getPatronMarcha() : "No especificado"), fontTexto ));

            document.add(new Paragraph("Traslados: "+
                    (datos.getTraslados() != null ? datos.getTraslados() : "No evaluado"), fontTexto));

            if (datos.getPostura() != null && !datos.getPostura().isEmpty()) {
                // Unimos la lista (Ej: "Escoliosis, Otra")
                String textoPostura = String.join(", ", datos.getPostura());
                document.add(new Paragraph("Hallazgos: " + textoPostura, fontTexto));

                // Si seleccionó "Otra", imprimimos el detalle
                if (datos.getPostura().contains("Otra")) {
                    document.add(new Paragraph("Detalle de otra anomalía: " +
                            (datos.getOtraPosturaDetalle() != null ? datos.getOtraPosturaDetalle() : "-"), fontTexto));
                }
            } else {
                document.add(new Paragraph("Sin hallazgos posturales registrados.", fontTexto));
            }
            document.add(new Paragraph("------------------------------------------------"));
            // 8- Valoración Funcional Global
            document.add(new Paragraph("Valoración Funcional Global (0=Dependiente, 10=Independiente",fontSubtitulo));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Nivel de Independencia en AVDs: " +
                    (datos.getNivelIndependencia() != null ? datos.getNivelIndependencia() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Transferencias (Cama - Silla): " +
                    (datos.getTransferencias() != null ? datos.getTransferencias() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Subir y Bajar Escaleras: " +
                    (datos.getSubirYbajarEscaleras() != null ? datos.getSubirYbajarEscaleras() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Marcha Funcional (distancia, velocidad): " +
                    (datos.getMarchaFuncional() != null ? datos.getMarchaFuncional() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Alcance Manual (capacidad de agarre / mover): " +
                    (datos.getAlcanceManual() != null ? datos.getAlcanceManual() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Tolerancia al Esfuerzo (funcional) " +
                    (datos.getToleranciaEsfuerzo() != null ? datos.getToleranciaEsfuerzo() : "-"),fontTexto));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("------------------------------------------------"));
            //9. Hallazgos Adicionales
            document.add(new Paragraph("Hallazgos Adicionales",fontSubtitulo));
            document.add(new Paragraph("Escala de Equilibrio: ",fontTexto));
            document.add(new Paragraph(datos.getEscalaEquilibrio(),fontTexto));
            document.add(new Paragraph("Grado de Limitación Funcional: " +
                    (datos.getPatronMarcha() != null ? datos.getGradoLimitacion() : "No especificado"), fontTexto ));
            document.add(new Paragraph("Pruebas Especiales Positivas: ",fontTexto));
            document.add(new Paragraph(datos.getPruebasEspeciales(),fontTexto));
            document.add(new Paragraph("Observaciones Generales del Fisioterapeuta: ",fontTexto));
            document.add(new Paragraph(datos.getObservaciones(),fontTexto));
            document.add(new Paragraph("Hallazgos a la Palpacion: ",fontTexto));
            document.add(new Paragraph(datos.getHallazgosPalpacion(),fontTexto));
            document.add(new Paragraph("Limitaciones en Actividades de la Vida Diaria (AVDs) detallado: ",fontTexto));
            document.add(new Paragraph(datos.getLimitacionesActividades(),fontTexto));

            document.add(new Paragraph("------------------------------------------------"));

            // --- DIAGNÓSTICO Y PLAN ---
            document.add(new Paragraph("Diagnóstico Fisioterapéutico:", fontSubtitulo));
            Paragraph diagnostico = new Paragraph(
                    (datos.getDiagnostico() != null ? datos.getDiagnostico() : "Pendiente"), fontTexto);
            diagnostico.setSpacingAfter(10);
            document.add(diagnostico);

            // Plan de Tratamiento
            PdfPTable tablaPlan = new PdfPTable(1);
            tablaPlan.setWidthPercentage(100);
            PdfPCell celdaPlan = new PdfPCell();
            celdaPlan.addElement(new Paragraph("PLAN DE TRATAMIENTO", fontNegrita));
            celdaPlan.addElement(new Paragraph(
                    (datos.getTratamiento() != null ? datos.getTratamiento() : "Pendiente"), fontTexto));
            celdaPlan.setPadding(15);
            celdaPlan.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
            tablaPlan.addCell(celdaPlan);

            document.add(tablaPlan);

            // --- 4. FIRMA ---
            document.add(new Paragraph(" "));
         

            document.add(new Paragraph(" "));
            document.add(new Paragraph("------------------------------------------------"));

            // --- SECCIÓN CONSENTIMIENTO ---
            document.add(new Paragraph("Consentimiento y Conformidad", fontSubtitulo));
            document.add(new Paragraph(" "));

            // Cuadro de texto legal
            PdfPTable tablaLegal = new PdfPTable(1);
            tablaLegal.setWidthPercentage(100);
            PdfPCell celdaLegal = new PdfPCell();
            celdaLegal.addElement(new Paragraph(
                    "Otorgo mi consentimiento libre e informado para la valoración, diagnóstico y tratamiento de fisioterapia. " +
                            "Entiendo la naturaleza y los posibles riesgos/beneficios de los procedimientos. He recibido explicaciones sobre mi " +
                            "condición y el plan terapéutico. Acepto la toma de fotografías clínicas para fines de documentación de mi expediente.",
                    FontFactory.getFont(FontFactory.HELVETICA, 10, java.awt.Color.DARK_GRAY)));
            celdaLegal.setPadding(10);
            celdaLegal.setBorderColor(java.awt.Color.GREEN); 
            celdaLegal.setBorderWidth(1f);
            tablaLegal.addCell(celdaLegal);
            document.add(tablaLegal);

            document.add(new Paragraph(" "));

            // Estado del Checkbox
            String estadoConsentimiento = datos.isConsentimientoInformado() ? "[ X ] ACEPTADO" : "[   ] NO ACEPTADO";
            document.add(new Paragraph(estadoConsentimiento + " He leído y acepto el consentimiento informado.", fontNegrita));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            // --- TABLA DE FIRMAS (Lado a Lado) ---
            PdfPTable tablaFirmas = new PdfPTable(2);
            tablaFirmas.setWidthPercentage(100);
            tablaFirmas.setSpacingBefore(20f);

            // Firma Paciente
            PdfPCell celdaFirmaP = new PdfPCell();
            celdaFirmaP.setBorder(Rectangle.TOP); // Solo línea superior
            celdaFirmaP.setBorderWidthTop(1f);
            celdaFirmaP.setBorderColorTop(java.awt.Color.BLACK);
            celdaFirmaP.addElement(new Paragraph(
                    (datos.getFirmaPaciente() != null ? datos.getFirmaPaciente() : ""), fontTexto));
            celdaFirmaP.addElement(new Paragraph("Firma del Paciente o Responsable", fontNegrita));
            celdaFirmaP.setPaddingTop(10);
            celdaFirmaP.setPaddingRight(20);

            // Firma Fisio
            PdfPCell celdaFirmaF = new PdfPCell();
            celdaFirmaF.setBorder(Rectangle.TOP); // Solo línea superior
            celdaFirmaF.setBorderWidthTop(1f);
            celdaFirmaF.setBorderColorTop(java.awt.Color.BLACK);
            celdaFirmaF.addElement(new Paragraph(
                    (datos.getFirmaFisio() != null ? datos.getFirmaFisio() : ""), fontTexto));
            celdaFirmaF.addElement(new Paragraph("Firma del Fisioterapeuta Tratante", fontNegrita));
            celdaFirmaF.setPaddingTop(10);
            celdaFirmaF.setPaddingLeft(20);

            tablaFirmas.addCell(celdaFirmaP);
            tablaFirmas.addCell(celdaFirmaF);

            document.add(tablaFirmas);

            document.close();

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "historia_" + datos.getNombrePaciente() + ".pdf");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
