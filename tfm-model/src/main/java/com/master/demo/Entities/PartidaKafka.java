package com.master.demo.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PartidaKafka {
    @JsonProperty("idVersion")
    private Integer idVersion;
    @JsonProperty("informacion")
    private String informacion;
    @JsonProperty("gastos")
    private String gastos;

    public PartidaKafka(Integer idVersion, String gastos, String informacion){
        this.idVersion = idVersion;
        this.gastos = gastos;
        this.informacion = informacion;

    }

    public PartidaKafka(){

    }
    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }


    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getGastos() {
        return gastos;
    }

    public void setGastos(String gastos) {
        this.gastos = gastos;
    }
}
