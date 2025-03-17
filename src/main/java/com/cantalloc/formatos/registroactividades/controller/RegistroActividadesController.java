package com.cantalloc.formatos.registroactividades.controller;

import com.cantalloc.formatos.registroactividades.dto.RegistroActividadesRequest;
import com.cantalloc.formatos.registroactividades.service.RegistroActividadesService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;


import java.util.Map;

@RestController
@RequestMapping("/api/formatos")
public class RegistroActividadesController {


    private final RegistroActividadesService registroService;
    private final ObjectMapper objectMapper;

    public RegistroActividadesController(RegistroActividadesService registroService, ObjectMapper objectMapper) {
        this.registroService = registroService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarActividad(@RequestBody Map<String, Object> datos) {
        try {
            // Convertir el Map en un objeto RegistroActividadesRequest
            RegistroActividadesRequest request = objectMapper.convertValue(datos, RegistroActividadesRequest.class);

            System.out.println("🔹 Recibido en el controlador:");
            System.out.println(new Gson().toJson(request));

            return ResponseEntity.ok(registroService.registrarActividad(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}