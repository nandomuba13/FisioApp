package com.fisioapp.backend.controller;

import com.fisioapp.backend.dto.DtoRequest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

@RestController // Esto le dice a Spring que esta clase responde a internet
@RequestMapping("/api/pdf") // La dirección base
@CrossOrigin(origins = "*") // IMPORTANTE: Permite que React (o cualquiera) se conecte
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

            // --- 2. TABLA DE DATOS DEL PACIENTE (Se ve más ordenado) ---
            PdfPTable tablaPaciente = new PdfPTable(2); // 2 Columnas
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
            document.add(new Paragraph(" ")); // Espacio

            // --- 3. SECCIONES MÉDICAS (Con títulos en negrita) ---

            // Motivo
            document.add(new Paragraph("Motivo de Consulta:", fontSubtitulo));
            document.add(new Paragraph(datos.getMotivoConsulta(), fontTexto));
            document.add(new Paragraph("------------------------------------------------"));

            document.add(new Paragraph("Antecedentes Médicos:", fontSubtitulo));

            if (datos.getAntecedentes() != null && !datos.getAntecedentes().isEmpty()) {
                // Unir todo con comas (Ej: "Diabetes, Alergias")
                String listaEnfermedades = String.join(", ", datos.getAntecedentes());
                document.add(new Paragraph(listaEnfermedades, fontTexto));
            } else {
                document.add(new Paragraph("Ninguno reportado.", fontTexto));
            }

            document.add(new Paragraph("Estilo de vida:", fontSubtitulo));
            document.add(new Paragraph("Nivel de Actividad: "+
                    (datos.getActividadFisica() != null ? datos.getActividadFisica() : "No especificado"), fontTexto ));

            document.add(new Paragraph("Hábitos tóxicos",fontSubtitulo));
            if (datos.getHabitosToxicos() != null && !datos.getHabitosToxicos().isEmpty()){
                String malosHabitos = String.join(", ", datos.getHabitosToxicos());
                document.add(new Paragraph(malosHabitos, fontTexto));
            }



            document.add(new Paragraph("Horas de sueño",fontSubtitulo));
            document.add(new Paragraph(String.valueOf(datos.getHorasSueño()), fontTexto));
            //Slider
            document.add(new Paragraph("Nivel de estrés percibido (1-10): " +
                    (datos.getNivelEstres() != null ? datos.getNivelEstres() : "No registrado"), fontTexto));
            document.add(new Paragraph("------------------------------------------------"));
            //EVA
            document.add(new Paragraph("Evaluación Subjetiva del Dolor (EVA)", fontSubtitulo));
            document.add(new Paragraph(datos.getLocalizacionDolor(),fontTexto));
            if (datos.getPatronIrradiacion() != null && !datos.getPatronIrradiacion().isEmpty()){
                document.add(new Paragraph("Patrón de Irradiación" ,fontSubtitulo));
                document.add(new Paragraph(datos.getPatronIrradiacion(),fontTexto));
                document.add(new Paragraph(" "));
            }
            document.add(new Paragraph("Características del dolor", fontNegrita));
            if (datos.getTipoDolor() != null && !datos.getTipoDolor().isEmpty()){
                String tipoDolor = String.join(", ", datos.getTipoDolor());
                document.add(new Paragraph(tipoDolor, fontTexto));
            }

            document.add(new Paragraph("------------------------------------------------"));
            // Diagnóstico
            document.add(new Paragraph("Diagnóstico Fisioterapéutico:", fontSubtitulo));
            Paragraph diagnostico = new Paragraph(datos.getDiagnostico(), fontTexto);
            diagnostico.setSpacingAfter(10); // Espacio abajo
            document.add(diagnostico);


            // Plan de Tratamiento (En un recuadro para resaltar)
            PdfPTable tablaPlan = new PdfPTable(1);
            PdfPCell celdaPlan = new PdfPCell();
            celdaPlan.addElement(new Paragraph("PLAN DE TRATAMIENTO", fontNegrita));
            celdaPlan.addElement(new Paragraph(datos.getTratamiento(), fontTexto));
            celdaPlan.setPadding(15);
            celdaPlan.setBackgroundColor(java.awt.Color.LIGHT_GRAY); // Fondo grisáceo
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

            // Devolver el PDF
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