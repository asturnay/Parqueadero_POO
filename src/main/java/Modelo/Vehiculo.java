package Modelo;

import java.time.LocalDateTime;


public class Vehiculo {
    private String placa;
    private String tipo;
    private LocalDateTime hora_ingreso;
    private LocalDateTime hora_salida;

    public Vehiculo() {
    }
    public Vehiculo(String pla, String tip, LocalDateTime Hoin, LocalDateTime HoSal) {
        this.placa = pla;
        this.tipo = tip;
        this.hora_ingreso = Hoin;
        this.hora_salida = HoSal;
    }
    // Métodos set para Vehiculo
    public void setPlaca(String pla) {
        this.placa = pla;
    }
    public void setTipo(String car) {
        this.tipo = car;
    }
    public void setHoraIngreso(LocalDateTime Hoin) {
        this.hora_ingreso = Hoin;
    }
    public void setHoraSalida(LocalDateTime HoSal) {
        this.hora_salida = HoSal; 
    }
    // Métodos get para Vehiculo
    public String getPlaca() {
        return this.placa;
    }
    public String getTipo() {
        return this.tipo;
    }
    public LocalDateTime getHoraIngreso() {
        return this.hora_ingreso;
    }
    public LocalDateTime getHoraSalida() {
        return this.hora_salida;
    }
}