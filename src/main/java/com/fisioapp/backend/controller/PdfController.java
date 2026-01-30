package com.fisioapp.backend.controller;

import com.fisioapp.backend.dto.DtoRequest; // Asegúrate que tu DTO se llame así o ConsultaDTO
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

            // --- 2. TABLA DE DATOS DEL PACIENTE ---
            PdfPTable tablaPaciente = new PdfPTable(2);
            tablaPaciente.setWidthPercentage(100);

            // Celda 1: Nombre
            PdfPCell celdaNombre = new PdfPCell(new Paragraph("Paciente: " + datos.getNombrePaciente(), fontTexto));
            celdaNombre.setPadding(10);
            tablaPaciente.addCell(celdaNombre);

            // Celda 2: Teléfono
            PdfPCell celdaTel = new PdfPCell(new Paragraph("Teléfono: " + datos.getTelefono(), fontTexto));
            celdaTel.setPadding(10);
            tablaPaciente.addCell(celdaTel);

            document.add(tablaPaciente);
            document.add(new Paragraph(" "));

            // --- 3. SECCIONES MÉDICAS ---

            // Motivo
            document.add(new Paragraph("Motivo de Consulta:", fontSubtitulo));
            document.add(new Paragraph(datos.getMotivoConsulta(), fontTexto));
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

            // Frecuencia Cardiaca
            document.add(new Paragraph("Frecuencia Cardiaca: " +
                    (datos.getFrecuenciaCardiaca() != null ? datos.getFrecuenciaCardiaca() + " lpm" : "No registrado"), fontTexto));

            // Frecuencia Respiratoria
            document.add(new Paragraph("Frecuencia Respiratoria: " +
                    (datos.getFrecuenciaRespiratoria() != null ? datos.getFrecuenciaRespiratoria() + " rpm" : "No registrado"), fontTexto));

            // Temperatura
            document.add(new Paragraph("Temperatura Corporal: " +
                    (datos.getTemperaturaCorporal() != null ? datos.getTemperaturaCorporal() + " °C" : "No registrado"), fontTexto));

            // Saturación
            document.add(new Paragraph("Saturación de Oxígeno (SpO2): " +
                    (datos.getSaturacionOxigeno() != null ? datos.getSaturacionOxigeno() + " %" : "No registrado"), fontTexto));

            // Presión Arterial (Corregido nombres de variables)
            String paTexto = "No registrado";
            if (datos.getPresionArterialSistolica() != null && datos.getPresionArterialDiastolica() != null){
                paTexto = datos.getPresionArterialSistolica() + "/" + datos.getPresionArterialDiastolica() + " mmHg";
            }
            document.add(new Paragraph("Presión Arterial (PA): " + paTexto, fontTexto));

            document.add(new Paragraph(" "));

            // --- DIAGNÓSTICO Y PLAN ---
            document.add(new Paragraph("Diagnóstico Fisioterapéutico:", fontSubtitulo));
            Paragraph diagnostico = new Paragraph(datos.getDiagnostico(), fontTexto);
            diagnostico.setSpacingAfter(10);
            document.add(diagnostico);

            // Plan de Tratamiento
            PdfPTable tablaPlan = new PdfPTable(1);
            tablaPlan.setWidthPercentage(100); // Asegurar que ocupe todo el ancho
            PdfPCell celdaPlan = new PdfPCell();
            celdaPlan.addElement(new Paragraph("PLAN DE TRATAMIENTO", fontNegrita));
            celdaPlan.addElement(new Paragraph(datos.getTratamiento(), fontTexto));
            celdaPlan.setPadding(15);
            celdaPlan.setBackgroundColor(java.awt.Color.LIGHT_GRAY);
            tablaPlan.addCell(celdaPlan);

            document.add(tablaPlan);

            // --- 4. FIRMA ---
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph firma = new Paragraph("__________________________\nFirma del Fisioterapeuta", fontTexto);
            firma.setAlignment(Element.ALIGN_CENTER);
            document.add(firma);

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