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
    private LinkedList<Vehiculo> registrosDiarios;
    private LinkedList<Vehiculo> vehiculo; 
    private boolean parquederoAbierto;
    private String caja_diaria;
    
    public Controlador_Parquedero(){
        this.ubicaciones = UbicacionesParquedero();
        this.registrosDiarios = new LinkedList<>();
        this.vehiculo = new LinkedList<>();
        this.parquederoAbierto = false;
    }

    private Map<String, Modelo_UbicacnionVehiculo> UbicacionesParquedero() {
        Map<String, Modelo_UbicacnionVehiculo> ubicacionesParquedero = new HashMap<>(); 
        for (int U = 1 ; U <= 10; U++ ){
            String NUbicaciones = "A" + U;
            ubicacionesParquedero.put(NUbicaciones, new Modelo_UbicacnionVehiculo());
        }
        return ubicacionesParquedero;
    }
    private Modelo_UbicacnionVehiculo asignarPuesto(String tipo, String placa, LocalDate horaIngreso) {
    for (Map.Entry<String, Modelo_UbicacnionVehiculo> entry : ubicaciones.entrySet()) {
        Modelo_UbicacnionVehiculo ubicacion = entry.getValue();
        if (ubicacion.getUbicacionDisponible()) {
            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setPlaca(placa);
            nuevoVehiculo.setTipo(tipo);
            nuevoVehiculo.setHoraIngreso(horaIngreso); // Asegúrate que exista este método en Vehiculo

            ubicacion.ocupar(nuevoVehiculo, horaIngreso);
            System.out.println("Vehículo asignado a la ubicación: " + entry.getKey());
            return ubicacion;
        }
    }

    System.out.println("No hay puestos disponibles para el tipo de vehículo: " + tipo);
    return null;
}
    public boolean registrarVehiculo(String placa, String tipo, LocalDate hora_ingreso) {
    for (Vehiculo registro : registrosDiarios) {
        if (registro.getPlaca().equalsIgnoreCase(placa) && registro.getHoraSalida() == null) {
            System.out.println("El vehículo con placa " + placa + " ya está en el parqueadero.");
            return false;
        }
    }

    Modelo_UbicacnionVehiculo ubicacionAsignada = asignarPuesto(tipo, placa, hora_ingreso);
    if (ubicacionAsignada == null) {
        System.out.println("Lo sentimos, no hay puestos disponibles.");
        return false;
    }

    Vehiculo nuevo = ubicacionAsignada.getVehiculo(); // Vehículo recién asignado
    registrosDiarios.add(nuevo);

    System.out.println("Vehículo con placa " + placa + " registrado exitosamente.");
    return true;
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
    private double CajaParquedero(){
         throw new UnsupportedOperationException("Not supported yet.");
    }


}
