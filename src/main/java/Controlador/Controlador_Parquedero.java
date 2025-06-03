/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Modelo_UbicacnionVehiculo;
import Modelo.Vehiculo;
import Modelo.Modelo_Caja;// privisional


import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Daniel
 */
public class Controlador_Parquedero {
    private Map<String, Modelo_UbicacnionVehiculo>ubicaciones;
    private double valorHoraCarro;
    private double valorHoraMoto;
    private boolean parquederoAbierto;
    private String caja_diaria;
    
    public Controlador_Parquedero(){
        this.ubicaciones = UbicacionesParquedero();
        this.valorHoraCarro = 0.0;
        this.valorHoraMoto = 0.0;
        this.parquederoAbierto = false;
    }

    private Map<String, Modelo_UbicacnionVehiculo> UbicacionesParquedero() {
        Map<String, Modelo_UbicacnionVehiculo> ubicacionesParquedero = new HashMap<>(); 
        for (int U = 1 ; U <= 10; U++ ){
            String NUbicaciones = "A" + U;
            ubicacionesParquedero.put(NUbicaciones, new Modelo_UbicacnionVehiculo());
        }
        return ubicaciones;
    }
    public boolean retirarVehiculo(String placa, String caja){
        if(!parquederoAbierto){
            System.out.println("El parquedero no ha abierto");
            return false;
        }
        Modelo_UbicacnionVehiculo ubicacionE = null;
        // busr todas las pociciones por su placa 
        for (Modelo_UbicacnionVehiculo ubicaiconR : ubicaciones.values()){
            if(!ubicaiconR.getUbicacionDisponible()&& ubicaiconR.getVehiculo() != null && ubicaiconR.getVehiculo().getPlaca().equals(placa)){
                ubicacionE = ubicaiconR;
                break;
                
            }
        }
        if(ubicacionE == null){
            System.out.println("No se encontro la placa" );
            return false;
        }
        // si el vehiculo fue encontrado procedemos con el retiro y calcular el pago
        long tiempoParquedero = calcularTiempo(ubicacionE.getHoraIngreso());
        double cobro = calcularPago(ubicacionE.getVehiculo(),tiempoParquedero);
       
        this.caja_diaria += cobro;
        ubicacionE.desocupar();
        
        System.out.println("Vehículo con placa " + placa + " retirado de la ubicación " + ubicacionE.getIdUbicacion()+ ".");
        return true;
        
    }

    private long calcularTiempo(LocalDate horaIngreso) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private double calcularPago(Vehiculo vehiculo, long tiempoParquedero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
