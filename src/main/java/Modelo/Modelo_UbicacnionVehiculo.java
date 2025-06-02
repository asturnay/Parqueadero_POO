/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class Modelo_UbicacnionVehiculo {
    private String id_Ubicacion;
    private String Ubi_Disponible;
    private Vehiculo vehiculo;
    private LocalDate hora_ingreso;
    private LocalDate hora_salida;
    
    public Modelo_UbicacnionVehiculo(){
    }
    
    public Modelo_UbicacnionVehiculo(String idUbi,String ubiDis, Vehiculo vehi , LocalDate Hoin, LocalDate HoSal){
        this.id_Ubicacion = idUbi;
        this.Ubi_Disponible = ubiDis;
        this.vehiculo = vehi;
        this.hora_ingreso = Hoin;
        this.hora_salida = HoSal;
    }
    // set para modelo Ubicacion vehiculos
    public void setIdUbicacion(String idUbi){
        this.id_Ubicacion = idUbi;
    }
    public void setUbicacionDisponible(String ubiDis){
        this.Ubi_Disponible = ubiDis;
    }
    public void setVehiculo(Vehiculo vehi){
        this.vehiculo = vehi;
    }
    public void setHoraIngreso(LocalDate Hoin){
        this.hora_ingreso = Hoin;
    }
    public void setHoraSalida(LocalDate Hosal){
        this.hora_salida = Hosal;
    }
    // Modelos Get para Ubicacion de Vehiculos 
    public String getIdUbicacion(){
        return this.id_Ubicacion;
    }
    public String getUbicacionDisponible(){
        return this.Ubi_Disponible;
    }
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    public LocalDate getHoraIngreso(){
        return this.hora_ingreso;
    }
    public LocalDate getHoraSalida(){
        return this.hora_salida;
    }
}
