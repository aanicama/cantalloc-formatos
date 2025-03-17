package com.cantalloc.formatos.registroactividades.repository;

import com.cantalloc.formatos.registroactividades.service.RegistroActividadesResponse;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.sql.Time;


@Repository
public class RegistroActividadesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RegistroActividadesResponse ejecutarProcedimiento(String fecha, Time hora,
                                                             int id_area, int id_contratista, int permiso_trabajo, int id_personal_responsable, String descripcion_peligro, Blob  imagen,
                                                             String medidas, String participantes) {

        System.out.println("🔹 Parámetros enviados al SP:");
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Área: " + id_area);
        System.out.println("Contratista: " + id_contratista);
        System.out.println("Permiso Trabajo: " + id_personal_responsable);
        System.out.println("Responsable: " + permiso_trabajo);
        System.out.println("Descripción Peligro: " + descripcion_peligro);
        System.out.println("Imagen: " + imagen);
        System.out.println("Medidas: " + new Gson().toJson(medidas));
        System.out.println("Participantes: " + new Gson().toJson(participantes));


        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("InsertarRegistroActividad");

        query.registerStoredProcedureParameter("p_fecha", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_hora", Time.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_area", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_contratista", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_permiso_trabajo", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_id_personal_responsable", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_descripcion_peligro", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_imagen", Blob.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_medidas", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_participantes", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_status_code", Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_status_message", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_id_registro", Integer.class, ParameterMode.OUT);




        query.setParameter("p_fecha", fecha);
        query.setParameter("p_hora", hora);
        query.setParameter("p_id_area", id_area);
        query.setParameter("p_id_contratista", id_contratista);
        query.setParameter("p_permiso_trabajo", permiso_trabajo);
        query.setParameter("p_id_personal_responsable", id_personal_responsable);
        query.setParameter("p_descripcion_peligro", descripcion_peligro);
        query.setParameter("p_imagen", imagen);
        query.setParameter("p_medidas", medidas != null ? medidas.toString() : "[]");
        query.setParameter("p_participantes", participantes != null ? participantes.toString() : "[]");

        query.execute();

        /*

        try {
            boolean result = query.execute();
            System.out.println("Resultado de ejecución del SP: " + result);
        } catch (Exception e) {
            System.out.println("Error ejecutando el SP: " + e.getMessage());
        }
        */
        Integer statusCode = (Integer) query.getOutputParameterValue("p_status_code");
        String statusMessage = (String) query.getOutputParameterValue("p_status_message");
        Integer idRegistro = (Integer) query.getOutputParameterValue("p_id_registro");

        System.out.println("Resultado SP - Código: " + statusCode + " Mensaje: " + statusMessage + " ID Registro: " + idRegistro);

        return new RegistroActividadesResponse(statusCode, statusMessage, idRegistro);
    }
}
