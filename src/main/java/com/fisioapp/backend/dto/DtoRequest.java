package com.fisioapp.backend.dto;

import java.util.List;

public class DtoRequest {
    /*Fisioterapeutas*/


    private String nombreFisio;
    private String cedulaProfesional;
    private String clinica;
    /*Fin de variables fisioterapeutas*/
    /*Paciente*/
    private String nombrePaciente;
    private String telefono;
    private int sexo;
    private String lateralidad;
    private String ocupacion;
    /*Fin paciente*/
    /*Diagnosticos*/
    private String motivoConsulta;
    private String padecimientoActual;
    private String diagnostico;
    private String tratamiento;
    private List<String> antecedentes; //First checkbox
    private String actividadFisica;
    private List<String> habitosToxicos; //Second Checkbox
    private int horasSueño;

    /*Fin de variables diagnosticos*/

    public List<String> getHabitosToxicos() {
        return habitosToxicos;
    }

    public void setHabitosToxicos(List<String> habitosToxicos) {
        this.habitosToxicos = habitosToxicos;
    }

    public int getHorasSueño() {
        return horasSueño;
    }

    public void setHorasSueño(int horasSueño) {
        this.horasSueño = horasSueño;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public String getNombreFisio() {
        return nombreFisio;
    }

    public void setNombreFisio(String nombreFisio) {
        this.nombreFisio = nombreFisio;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getClinica() {
        return clinica;
    }

    public void setClinica(String clinica) {
        this.clinica = clinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(String lateralidad) {
        this.lateralidad = lateralidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPadecimientoActual() {
        return padecimientoActual;
    }

    public void setPadecimientoActual(String padecimientoActual) {
        this.padecimientoActual = padecimientoActual;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<String> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<String> antecedentes) {
        this.antecedentes = antecedentes;
    }
}

