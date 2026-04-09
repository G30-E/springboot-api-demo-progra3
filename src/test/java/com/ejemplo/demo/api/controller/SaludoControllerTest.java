package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    @Test
    @DisplayName("Debe responder saludo por GET")
    void debeResponderSaludoPorGet() throws Exception {
        mockMvc.perform(get("/api/v1/saludos").param("nombre", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Ana. Bienvenido a Spring Boot 3!"));
    }

    @Test
    @DisplayName("Debe devolver 400 en POST inválido")
    void debeDevolver400EnPostInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/saludos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nombre": ""
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }

    @Test
    @DisplayName("Debe devolver 400 si hay error de negocio")
    void debeDevolver400SiHayErrorDeNegocio() throws Exception {
        mockMvc.perform(get("/api/v1/saludos").param("nombre", "Ana123"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("BUSINESS_RULE_ERROR"));
    }
}