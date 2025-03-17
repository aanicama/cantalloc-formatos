package com.cantalloc.formatos.registroactividades.dto;

import java.sql.Blob;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import jakarta.annotation.PostConstruct;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistroActividadesRequest {
    private String fecha;
    private Time hora;
    private Integer id_area;
    private Integer id_contratista;
    private Integer permiso_trabajo;
    private Integer id_personal_responsable;
    private String descripcion_peligro;
    private Blob imagen;
    private List<DetalleMedida> medidas;
    private List<Participante> participantes;

    @PostConstruct
    public void debugRequest() {
        System.out.println("🔹 JSON recibido:");
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Área: " + id_area);
        System.out.println("Contratista: " + id_contratista);
        System.out.println("Permiso Trabajo: " + permiso_trabajo);
        System.out.println("Responsable: " + id_personal_responsable);
        System.out.println("Descripción Peligro: " + descripcion_peligro);
        System.out.println("Descripción Peligro: " + medidas);

    }


    @Getter @Setter
    public static class DetalleMedida {
        private int id_medida;
        private int id_peligro;
        private int checked;
    }

    @Getter @Setter
    public static class Participante {
        private int id_personal;
    }


}
