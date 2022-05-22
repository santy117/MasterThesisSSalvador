package com.master.demo.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensajeIA {
    @JsonProperty("usuario")
    private String usuario;
    @JsonProperty("versiones")
    private String versiones;

}
