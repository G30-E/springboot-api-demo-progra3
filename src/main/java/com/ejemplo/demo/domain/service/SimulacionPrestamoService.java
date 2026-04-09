package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SimulacionPrestamoRequest;
import com.ejemplo.demo.api.dto.SimulacionPrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimulacionPrestamoService {

    public SimulacionPrestamoResponse simular(SimulacionPrestamoRequest request) {
        BigDecimal monto = request.monto();
        BigDecimal tasaAnual = request.tasaAnual();
        int meses = request.meses();

        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que 0");
        }

        if (tasaAnual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa anual debe ser mayor que 0");
        }

        if (meses < 1 || meses > 360) {
            throw new IllegalArgumentException("Los meses deben estar entre 1 y 360");
        }

        double p = monto.doubleValue();
        double r = tasaAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                .doubleValue();
        int n = meses;

        double cuota = p * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        BigDecimal cuotaMensual = BigDecimal.valueOf(cuota).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(monto).setScale(2, RoundingMode.HALF_UP);

        return new SimulacionPrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}