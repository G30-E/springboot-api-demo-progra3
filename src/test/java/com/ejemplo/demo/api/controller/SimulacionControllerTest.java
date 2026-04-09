package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimulacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debe simular préstamo correctamente")
    void debeSimularPrestamoCorrectamente() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monto": 5000,
                                  "tasaAnual": 12,
                                  "meses": 12
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuotaMensual").exists())
                .andExpect(jsonPath("$.interesTotal").exists())
                .andExpect(jsonPath("$.totalPagar").exists());
    }

    @Test
    @DisplayName("Debe devolver 400 si el request es inválido")
    void debeDevolver400SiRequestEsInvalido() throws Exception {
        mockMvc.perform(post("/api/v1/simulaciones/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "monto": 0,
                                  "tasaAnual": 12,
                                  "meses": 0
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    }
}