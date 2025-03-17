package com.cantalloc.formatos.consultas.repository;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class ConsultasRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> consultarPorRangoFechas(String fechaInicio, String fechaFin) {
        String sql = "SELECT * FROM registro_actividades WHERE fecha BETWEEN :fechaInicio AND :fechaFin";
        Query query = entityManager.createNativeQuery(sql, Map.class);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        return query.getResultList();
    }
}
