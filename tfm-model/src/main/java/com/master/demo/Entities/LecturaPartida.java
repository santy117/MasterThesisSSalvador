package com.master.demo.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LecturaPartida {
    @JsonProperty("idVersion")
    private Integer idVersion;
    @JsonProperty("usuario")
    private String usuario;


    public LecturaPartida(Integer idVersion, String usuario){
        this.idVersion = idVersion;
        this.usuario = usuario;

    }

    public LecturaPartida(){

    }
    public Integer getIdVersion() {
        return idVersion;
    }

    public void setIdVersion(Integer idVersion) {
        this.idVersion = idVersion;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
