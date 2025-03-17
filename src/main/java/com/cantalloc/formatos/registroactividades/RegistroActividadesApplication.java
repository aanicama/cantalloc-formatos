package com.cantalloc.formatos.registroactividades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cantalloc.formatos.registroactividades", "com.cantalloc.formatos.consultas"})
public class RegistroActividadesApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistroActividadesApplication.class, args);
    }
}