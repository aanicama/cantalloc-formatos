package com.cantalloc.formatos.consultas.controller;

import com.cantalloc.formatos.consultas.service.ConsultasService;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/formatos")
public class ConsultasController {

    private final ConsultasService consultasService;

    public ConsultasController(ConsultasService consultasServices, ConsultasService consultasService, ObjectMapper objectMapper) {
        this.consultasService = consultasService;
    }

    @GetMapping("/consultar-fechas")
    public ResponseEntity<List<Map<String, Object>>> consultarPorRangoFechas(
            @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        try {
            List<Map<String, Object>> registros = consultasService.obtenerPorRangoFechas(fechaInicio, fechaFin);
            return ResponseEntity.ok(registros);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}