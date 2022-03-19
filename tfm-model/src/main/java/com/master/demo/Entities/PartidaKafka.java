package com.master.demo.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PartidaKafka {
    @JsonProperty("idPartida")
    private Integer idPartida;
    @JsonProperty("informacion")
    private String informacion;
    @JsonProperty("gastos")
    private String gastos;

    public PartidaKafka(Integer idPartida, String gastos, String informacion){
        this.idPartida = idPartida;
        this.gastos = gastos;
        this.informacion = informacion;

    }

    public PartidaKafka(){

    }
    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
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
