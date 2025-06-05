package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class Modelo_UbicacnionVehiculo {
    private String id_Ubicacion;
    private boolean Ubi_Disponible;
    private Vehiculo vehiculo;
    private LocalDate hora_ingreso;
    private LocalDate hora_salida;
    
    public Modelo_UbicacnionVehiculo(){
    }
    
    public Modelo_UbicacnionVehiculo(String idUbi,boolean ubiDis, Vehiculo vehi , LocalDate Hoin, LocalDate HoSal){
        this.id_Ubicacion = idUbi;
        this.Ubi_Disponible = ubiDis = true;
        this.vehiculo = vehi = null;
        this.hora_ingreso = Hoin;
        this.hora_salida = HoSal;
    }    
    // set para modelo Ubicacion vehiculos
    public void setIdUbicacion(String idUbi){
        this.id_Ubicacion = idUbi;
    }
    public void setUbicacionDisponible(boolean ubiDis){
        this.Ubi_Disponible = ubiDis;
    }
    public void setVehiculo(Vehiculo vehi){
        this.vehiculo = vehi;
    }
    public void setHoraIngreso(LocalDate Hoin){
        this.hora_ingreso = Hoin;
    }
    /*public void setHoraSalida(LocalDate Hosal){
        this.hora_salida = Hosal;
    }*/
    // Modelos Get para Ubicacion de Vehiculos 
    public String getIdUbicacion(){
        return this.id_Ubicacion;
    }
    public boolean getUbicacionDisponible(){
        return this.Ubi_Disponible;
    }
    public Vehiculo getVehiculo(){
        return this.vehiculo;
    }
    public LocalDate getHoraIngreso(){
        return hora_ingreso;
    }
    
    // ocupa un puesto en la ubicacion
  
    public void ocupar(Vehiculo vehiculo, LocalDate horaIngreso) {
        this.vehiculo = vehiculo;
        this.Ubi_Disponible = false;
        this.hora_ingreso = vehiculo.getHoraIngreso();
    }
     
     //desocupa el puesto


    public void desocupar() {
        this.Ubi_Disponible = true;
        this.vehiculo = null;
        this.hora_ingreso = null;
    }
}
