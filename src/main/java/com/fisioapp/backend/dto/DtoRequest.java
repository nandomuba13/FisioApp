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
    private String sexo;
    private String lateralidad;
    private String ocupacion;
    private String edad;

    /*Diagnosticos*/
    private String motivoConsulta;
    private String padecimientoActual;
    private String diagnostico;
    private String tratamiento;
    private List<String> antecedentes;
    private String actividadFisica;
    private List<String> habitosToxicos;

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

    private Double temperaturaCorporal;
    private Integer saturacionOxigeno;
    private Integer presionArterialSistolica, presionArterialDiastolica;

    //Balance y coordinación
    private Integer equilibrioEstatico;
    private Integer equilibrioDinamico;
    private Integer coordinacionMotora;

    private Integer propiocepcion;

    //Evaluación Respiratoria
    private List<String> expansionToracica;
    private List<String> coloracionPiel;
    private List<String> llenadoCapilar;
    private String presenciaEdema;

    //Evaluación cutánea
    private String integridadCutanea; // Es String porque es Radio Button (solo uno)
    private List<String> hallazgosEspecificos;

    //Evaluación Muscular
    private String rangoMovimientoActivo;
    private String rangoMovimientoPasivo;
    private String fuerzaMuscular;
    private String perimetrosMusculares;
    private String tonoMuscular;
    //7. Evaluación De la postura
    private String patronMarcha;
    private String Traslados;
    private List<String> Postura;
    private String otraPosturaDetalle;
    //8. Valoración Funcional Global
    private Integer nivelIndependencia, transferencias,subirYbajarEscaleras,marchaFuncional,alcanceManual,toleranciaEsfuerzo;
    //9. Hallazgos Adicionales
    private String escalaEquilibrio, gradoLimitacion,pruebasEspeciales,observaciones,hallazgosPalpacion,limitacionesActividades;
    private boolean consentimientoInformado;
    private String firmaPaciente,firmaFisio;

    /* --- GETTERS Y SETTERS --- */

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setNivelIndependencia(Integer nivelIndependencia) {
        this.nivelIndependencia = nivelIndependencia;
    }

    public void setTransferencias(Integer transferencias) {
        this.transferencias = transferencias;
    }

    public void setSubirYbajarEscaleras(Integer subirYbajarEscaleras) {
        this.subirYbajarEscaleras = subirYbajarEscaleras;
    }

    public void setMarchaFuncional(Integer marchaFuncional) {
        this.marchaFuncional = marchaFuncional;
    }

    public void setAlcanceManual(Integer alcanceManual) {
        this.alcanceManual = alcanceManual;
    }

    public void setToleranciaEsfuerzo(Integer toleranciaEsfuerzo) {
        this.toleranciaEsfuerzo = toleranciaEsfuerzo;
    }

    public void setEscalaEquilibrio(String escalaEquilibrio) {
        this.escalaEquilibrio = escalaEquilibrio;
    }

    public void setGradoLimitacion(String gradoLimitacion) {
        this.gradoLimitacion = gradoLimitacion;
    }

    public void setPruebasEspeciales(String pruebasEspeciales) {
        this.pruebasEspeciales = pruebasEspeciales;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setHallazgosPalpacion(String hallazgosPalpacion) {
        this.hallazgosPalpacion = hallazgosPalpacion;
    }

    public void setLimitacionesActividades(String limitacionesActividades) {
        this.limitacionesActividades = limitacionesActividades;
    }

    public void setConsentimientoInformado(boolean consentimientoInformado) {
        this.consentimientoInformado = consentimientoInformado;
    }

    public void setFirmaPaciente(String firmaPaciente) {
        this.firmaPaciente = firmaPaciente;
    }

    public void setFirmaFisio(String firmaFisio) {
        this.firmaFisio = firmaFisio;
    }

    public boolean isConsentimientoInformado() {
        return consentimientoInformado;
    }

    public String getFirmaPaciente() {
        return firmaPaciente;
    }

    public String getFirmaFisio() {
        return firmaFisio;
    }

    public String getEdad() {
        return edad;
    }

    public String getEscalaEquilibrio() {
        return escalaEquilibrio;
    }

    public String getGradoLimitacion() {
        return gradoLimitacion;
    }

    public String getPruebasEspeciales() {
        return pruebasEspeciales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getHallazgosPalpacion() {
        return hallazgosPalpacion;
    }

    public String getLimitacionesActividades() {
        return limitacionesActividades;
    }

    public Integer getNivelIndependencia() {
        return nivelIndependencia;
    }

    public Integer getTransferencias() {
        return transferencias;
    }

    public Integer getSubirYbajarEscaleras() {
        return subirYbajarEscaleras;
    }

    public Integer getMarchaFuncional() {
        return marchaFuncional;
    }

    public Integer getAlcanceManual() {
        return alcanceManual;
    }

    public Integer getToleranciaEsfuerzo() {
        return toleranciaEsfuerzo;
    }

    public String getOtraPosturaDetalle() {
        return otraPosturaDetalle;
    }

    public void setOtraPosturaDetalle(String otraPosturaDetalle) {
        this.otraPosturaDetalle = otraPosturaDetalle;
    }

    public String getPatronMarcha() {
        return patronMarcha;
    }

    public void setPatronMarcha(String patronMarcha) {
        this.patronMarcha = patronMarcha;
    }

    public String getTraslados() {
        return Traslados;
    }

    public void setTraslados(String traslados) {
        Traslados = traslados;
    }

    public List<String> getPostura() {
        return Postura;
    }

    public void setPostura(List<String> postura) {
        Postura = postura;
    }
// Genera o actualiza estos getters/setters específicamente:

    public List<String> getHallazgosEspecificos() { return hallazgosEspecificos; }
    public void setHallazgosEspecificos(List<String> hallazgosEspecificos) { this.hallazgosEspecificos = hallazgosEspecificos; }

    public String getPerimetrosMusculares() { return perimetrosMusculares; }
    public void setPerimetrosMusculares(String perimetrosMusculares) { this.perimetrosMusculares = perimetrosMusculares; }

    public Integer getPropiocepcion() { return propiocepcion; }
    public void setPropiocepcion(Integer propiocepcion) { this.propiocepcion = propiocepcion; }

    public List<String> getExpansionToracica() { return expansionToracica; }
    public void setExpansionToracica(List<String> expansionToracica) { this.expansionToracica = expansionToracica; }

    public List<String> getColoracionPiel() { return coloracionPiel; }
    public void setColoracionPiel(List<String> coloracionPiel) { this.coloracionPiel = coloracionPiel; }

    public List<String> getLlenadoCapilar() { return llenadoCapilar; }
    public void setLlenadoCapilar(List<String> llenadoCapilar) { this.llenadoCapilar = llenadoCapilar; }

    // ... (Asegúrate de tener los Getters/Setters del resto de variables) ...

    public String getNombreFisio() { return nombreFisio; }
    public void setNombreFisio(String nombreFisio) { this.nombreFisio = nombreFisio; }
    public String getCedulaProfesional() { return cedulaProfesional; }
    public void setCedulaProfesional(String cedulaProfesional) { this.cedulaProfesional = cedulaProfesional; }
    public String getClinica() { return clinica; }
    public void setClinica(String clinica) { this.clinica = clinica; }
    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getLateralidad() { return lateralidad; }
    public void setLateralidad(String lateralidad) { this.lateralidad = lateralidad; }
    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }
    public String getPadecimientoActual() { return padecimientoActual; }
    public void setPadecimientoActual(String padecimientoActual) { this.padecimientoActual = padecimientoActual; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
    public List<String> getAntecedentes() { return antecedentes; }
    public void setAntecedentes(List<String> antecedentes) { this.antecedentes = antecedentes; }
    public String getActividadFisica() { return actividadFisica; }
    public void setActividadFisica(String actividadFisica) { this.actividadFisica = actividadFisica; }
    public List<String> getHabitosToxicos() { return habitosToxicos; }
    public void setHabitosToxicos(List<String> habitosToxicos) { this.habitosToxicos = habitosToxicos; }
    public String getHorasSueño() { return horasSueño; }
    public void setHorasSueño(String horasSueño) { this.horasSueño = horasSueño; }
    public Integer getNivelEstres() { return nivelEstres; }
    public void setNivelEstres(Integer nivelEstres) { this.nivelEstres = nivelEstres; }
    public String getLocalizacionDolor() { return localizacionDolor; }
    public void setLocalizacionDolor(String localizacionDolor) { this.localizacionDolor = localizacionDolor; }
    public String getPatronIrradiacion() { return patronIrradiacion; }
    public void setPatronIrradiacion(String patronIrradiacion) { this.patronIrradiacion = patronIrradiacion; }
    public List<String> getTipoDolor() { return tipoDolor; }
    public void setTipoDolor(List<String> tipoDolor) { this.tipoDolor = tipoDolor; }
    public List<String> getRitmoDolor() { return ritmoDolor; }
    public void setRitmoDolor(List<String> ritmoDolor) { this.ritmoDolor = ritmoDolor; }
    public Integer getDolorActual() { return dolorActual; }
    public void setDolorActual(Integer dolorActual) { this.dolorActual = dolorActual; }
    public String getDolorMaximo() { return dolorMaximo; }
    public void setDolorMaximo(String dolorMaximo) { this.dolorMaximo = dolorMaximo; }
    public String getDolorMinimo() { return dolorMinimo; }
    public void setDolorMinimo(String dolorMinimo) { this.dolorMinimo = dolorMinimo; }
    public Integer getFrecuenciaCardiaca() { return frecuenciaCardiaca; }
    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) { this.frecuenciaCardiaca = frecuenciaCardiaca; }
    public Integer getFrecuenciaRespiratoria() { return frecuenciaRespiratoria; }
    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) { this.frecuenciaRespiratoria = frecuenciaRespiratoria; }
    public Double getTemperaturaCorporal() { return temperaturaCorporal; }
    public void setTemperaturaCorporal(Double temperaturaCorporal) { this.temperaturaCorporal = temperaturaCorporal; }
    public Integer getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(Integer saturacionOxigeno) { this.saturacionOxigeno = saturacionOxigeno; }
    public Integer getPresionArterialSistolica() { return presionArterialSistolica; }
    public void setPresionArterialSistolica(Integer presionArterialSistolica) { this.presionArterialSistolica = presionArterialSistolica; }
    public Integer getPresionArterialDiastolica() { return presionArterialDiastolica; }
    public void setPresionArterialDiastolica(Integer presionArterialDiastolica) { this.presionArterialDiastolica = presionArterialDiastolica; }
    public Integer getEquilibrioEstatico() { return equilibrioEstatico; }
    public void setEquilibrioEstatico(Integer equilibrioEstatico) { this.equilibrioEstatico = equilibrioEstatico; }
    public Integer getEquilibrioDinamico() { return equilibrioDinamico; }
    public void setEquilibrioDinamico(Integer equilibrioDinamico) { this.equilibrioDinamico = equilibrioDinamico; }
    public Integer getCoordinacionMotora() { return coordinacionMotora; }
    public void setCoordinacionMotora(Integer coordinacionMotora) { this.coordinacionMotora = coordinacionMotora; }
    public String getPresenciaEdema() { return presenciaEdema; }
    public void setPresenciaEdema(String presenciaEdema) { this.presenciaEdema = presenciaEdema; }
    public String getIntegridadCutanea() { return integridadCutanea; }
    public void setIntegridadCutanea(String integridadCutanea) { this.integridadCutanea = integridadCutanea; }
    public String getRangoMovimientoActivo() { return rangoMovimientoActivo; }
    public void setRangoMovimientoActivo(String rangoMovimientoActivo) { this.rangoMovimientoActivo = rangoMovimientoActivo; }
    public String getRangoMovimientoPasivo() { return rangoMovimientoPasivo; }
    public void setRangoMovimientoPasivo(String rangoMovimientoPasivo) { this.rangoMovimientoPasivo = rangoMovimientoPasivo; }
    public String getFuerzaMuscular() { return fuerzaMuscular; }
    public void setFuerzaMuscular(String fuerzaMuscular) { this.fuerzaMuscular = fuerzaMuscular; }
    public String getTonoMuscular() { return tonoMuscular; }
    public void setTonoMuscular(String tonoMuscular) { this.tonoMuscular = tonoMuscular; }
}