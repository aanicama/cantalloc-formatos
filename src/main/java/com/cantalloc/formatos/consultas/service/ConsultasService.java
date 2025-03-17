package com.cantalloc.formatos.consultas.service;

import com.cantalloc.formatos.consultas.repository.ConsultasRepository;
import com.cantalloc.formatos.registroactividades.dto.RegistroActividadesRequest;
import com.cantalloc.formatos.registroactividades.repository.RegistroActividadesRepository;
import com.cantalloc.formatos.registroactividades.service.RegistroActividadesResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConsultasService {
    @Autowired
    private ConsultasRepository consultasRepository;

    public List<Map<String, Object>> obtenerPorRangoFechas(String fechaInicio, String fechaFin) {
        return consultasRepository.consultarPorRangoFechas(fechaInicio, fechaFin);
    }
}
