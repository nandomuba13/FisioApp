package com.fisioapp.backend.dto;

import java.util.List;

public class DtoRequest {
    /*Fisioterapeutas*/
    private String nombreFisio;
    private String cedulaProfesional;
    private String clinica;

    /*Paciente*/
    private String nombrePaciente;
    private String telefono;
    private int sexo;
    private String lateralidad;
    private String ocupacion;

    /*Diagnosticos*/
    private String motivoConsulta;
    private String padecimientoActual;
    private String diagnostico;
    private String tratamiento;
    private List<String> antecedentes;
    private String actividadFisica;
    private List<String> habitosToxicos;

    // CAMBIO 1: String para permitir texto libre (ej: "8 hrs aprox")
    private String horasSueño;

    private Integer nivelEstres;

    // Evaluación subjetiva del dolor
    private String localizacionDolor, patronIrradiacion;
    private List<String> tipoDolor;
    private List<String> ritmoDolor;
    private Integer dolorActual;
    private String dolorMaximo, dolorMinimo;

    // Exploración física y funcional
    private Integer frecuenciaCardiaca;
    private Integer frecuenciaRespiratoria;

    // CAMBIO 2: Double (con mayúscula) para permitir nulos
    private Double temperaturaCorporal;

    private Integer saturacionOxigeno;
    private Integer presionArterialSistolica, presionArterialDiastolica;

    /* --- GETTERS Y SETTERS --- */

    public Integer getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }

    public Double getTemperaturaCorporal() { return temperaturaCorporal; }
    public void setTemperaturaCorporal(Double temperaturaCorporal) { this.temperaturaCorporal = temperaturaCorporal; }

    public Integer getFrecuenciaRespiratoria() { return frecuenciaRespiratoria; }
    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) { this.frecuenciaRespiratoria = frecuenciaRespiratoria; }

    // OJO: Estos nombres deben coincidir en el Controller
    public Integer getPresionArterialSistolica() { return presionArterialSistolica; }
    public void setPresionArterialSistolica(Integer presionArterialSistolica) { this.presionArterialSistolica = presionArterialSistolica; }

    public Integer getPresionArterialDiastolica() { return presionArterialDiastolica; }
    public void setPresionArterialDiastolica(Integer presionArterialDiastolica) { this.presionArterialDiastolica = presionArterialDiastolica; }

    public Integer getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(Integer saturacionOxigeno) { this.saturacionOxigeno = saturacionOxigeno; }

    public String getLocalizacionDolor() { return localizacionDolor; }
    public void setLocalizacionDolor(String localizacionDolor) { this.localizacionDolor = localizacionDolor; }

    public String getPatronIrradiacion() { return patronIrradiacion; }
    public void setPatronIrradiacion(String patronIrradiacion) { this.patronIrradiacion = patronIrradiacion; }

    public List<String> getTipoDolor() { return tipoDolor; }
    public void setTipoDolor(List<String> tipoDolor) { this.tipoDolor = tipoDolor; }

    public Integer getDolorActual() { return dolorActual; }
    public void setDolorActual(Integer dolorActual) { this.dolorActual = dolorActual; }

    public String getDolorMaximo() { return dolorMaximo; }
    public void setDolorMaximo(String dolorMaximo) { this.dolorMaximo = dolorMaximo; }

    public String getDolorMinimo() { return dolorMinimo; }
    public void setDolorMinimo(String dolorMinimo) { this.dolorMinimo = dolorMinimo; }

    public List<String> getRitmoDolor() { return ritmoDolor; }
    public void setRitmoDolor(List<String> ritmoDolor) { this.ritmoDolor = ritmoDolor; }

    public Integer getNivelEstres() { return nivelEstres; }
    public void setNivelEstres(Integer nivelEstres) { this.nivelEstres = nivelEstres; }

    public List<String> getHabitosToxicos() { return habitosToxicos; }
    public void setHabitosToxicos(List<String> habitosToxicos) { this.habitosToxicos = habitosToxicos; }

    public String getHorasSueño() { return horasSueño; }
    public void setHorasSueño(String horasSueño) { this.horasSueño = horasSueño; }

    public String getActividadFisica() { return actividadFisica; }
    public void setActividadFisica(String actividadFisica) { this.actividadFisica = actividadFisica; }

    public String getNombreFisio() { return nombreFisio; }
    public void setNombreFisio(String nombreFisio) { this.nombreFisio = nombreFisio; }

    public String getCedulaProfesional() { return cedulaProfesional; }
    public void setCedulaProfesional(String cedulaProfesional) { this.cedulaProfesional = cedulaProfesional; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }

    public String getClinica() { return clinica; }
    public void setClinica(String clinica) { this.clinica = clinica; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getSexo() { return sexo; }
    public void setSexo(int sexo) { this.sexo = sexo; }

    public String getLateralidad() { return lateralidad; }
    public void setLateralidad(String lateralidad) { this.lateralidad = lateralidad; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getPadecimientoActual() { return padecimientoActual; }
    public void setPadecimientoActual(String padecimientoActual) { this.padecimientoActual = padecimientoActual; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public List<String> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<String> antecedentes) { this.antecedentes = antecedentes; }
}