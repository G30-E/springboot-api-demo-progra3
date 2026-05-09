package com.ejemplo.demo.domain.service;

public class EstadoManualService {

    private int valorActual;

    public void actualizar(int valor) {
        this.valorActual = valor;
    }

    public int obtener() {
        return valorActual;
    }
}
