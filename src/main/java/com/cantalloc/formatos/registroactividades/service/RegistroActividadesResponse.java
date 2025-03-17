package com.cantalloc.formatos.registroactividades.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class RegistroActividadesResponse {
    private Integer statusCode;
    private String statusMessage;
    private Integer idRegistro;
}
