package Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Daniel
 */
public class Modelo_UbicacnionVehiculo {
    private String Ubicacion;
    private boolean Ubi_Disponible = true ;
    private Vehiculo vehiculo = null;
    private LocalDateTime hora_ingreso = null ; 
    
    public Modelo_UbicacnionVehiculo() { 
    }
    public Modelo_UbicacnionVehiculo(String idUbi) {
        this();
        this.Ubicacion = idUbi;
    }

    public Modelo_UbicacnionVehiculo(String Ubi, boolean ubiDis, Vehiculo vehi, LocalDateTime Hoin){
        this.Ubicacion = Ubi;
        this.Ubi_Disponible = ubiDis;
        this.vehiculo = vehi;     
        this.hora_ingreso = Hoin;
        
    }
    public void setUbicacion(String idUbi) {
        this.Ubicacion = idUbi;
    }

    public void setUbicacionDisponible(boolean ubiDis) {
        this.Ubi_Disponible = ubiDis;
    }

    public void setVehiculo(Vehiculo vehi) {
        this.vehiculo = vehi;
    }

    public void setHoraIngreso(LocalDateTime Hoin) {
        this.hora_ingreso = Hoin;
    }
    public String getUbicacion() {
        return this.Ubicacion;
    }

    public boolean getUbicacionDisponible() {
        return this.Ubi_Disponible;
    }

    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    public LocalDateTime getHoraIngreso() {
        return hora_ingreso;
    }
    // Ocupa un puesto en la ubicación
    public void ocupar(Vehiculo vehiculo, LocalDateTime horaIngreso) {
        this.vehiculo = vehiculo;
        this.Ubi_Disponible = false;
        this.hora_ingreso = horaIngreso;
    }

    // Desocupa el puesto
    public void desocupar() {
        this.Ubi_Disponible = true;
        this.vehiculo = null;
        this.hora_ingreso = null;
    }
}