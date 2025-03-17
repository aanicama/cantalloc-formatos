package com.cantalloc.formatos.registroactividades.service;

import com.cantalloc.formatos.registroactividades.dto.RegistroActividadesRequest;
import com.cantalloc.formatos.registroactividades.repository.RegistroActividadesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroActividadesService {

    @Autowired
    private RegistroActividadesRepository registroActividadesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public RegistroActividadesResponse registrarActividad(RegistroActividadesRequest request) {
        try {
            String medidasJson = objectMapper.writeValueAsString(request.getMedidas());
            String participantesJson = objectMapper.writeValueAsString(request.getParticipantes());

            System.out.println("🔹 Parámetros enviados al SP:");
            System.out.println("Fecha: " + request.getFecha());
            System.out.println("Hora: " + request.getHora());
            System.out.println("Área: " + request.getId_area());
            System.out.println("Contratista: " + request.getId_contratista());
            System.out.println("Permiso Trabajo: " + request.getPermiso_trabajo());
            System.out.println("Responsable: " + request.getId_personal_responsable());
            System.out.println("Descripción Peligro: " + request.getDescripcion_peligro());
            System.out.println("Imagen: " + request.getImagen());
            System.out.println("Medidas: " + new Gson().toJson(request.getMedidas()));
            System.out.println("Participantes: " + new Gson().toJson(request.getParticipantes()));



            return registroActividadesRepository.ejecutarProcedimiento(request.getFecha(), request.getHora(),
                    request.getId_area(), request.getId_contratista(),request.getPermiso_trabajo(), request.getId_personal_responsable(),
                    request.getDescripcion_peligro(), request.getImagen(),medidasJson, participantesJson);
        } catch (JsonProcessingException e) {
            return new RegistroActividadesResponse(1, "Error al procesar JSON", null);
        }
    }
}
