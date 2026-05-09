package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class EstadoSingletonService {

    private int valorActual;

    public void actualizar(int valor) {
        this.valorActual = valor;
    }

    public int obtener() {
        return valorActual;
    }

    public void reiniciar() {
        this.valorActual = 0;
    }
}
