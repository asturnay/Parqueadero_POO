package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class Vehiculo {
    private String placa;
    private String tipo;
    private LocalDate hora_ingreso;
    private LocalDate hora_salida;
    // public vacio 
    public Vehiculo(){
    }
    
    public Vehiculo(String pla, String tip, LocalDate Hoin, LocalDate HoSal){
        this.placa = pla;
        this.tipo = tip;
        this.hora_ingreso = Hoin;
        this.hora_salida = HoSal; 
    }
    // metodos set para vehiculo
    public void setPlaca(String pla){
        this.placa = pla;
    }
    public void setTipo(String car){
        this.tipo = car;
    }
     public void setHoraIngreso(LocalDate Hoin){
        this.hora_ingreso = Hoin;
    }
      public void setHoraSalida(LocalDate HoSal){
        this.hora_ingreso = HoSal;
    }
    // metodos get para vehiculo
    public String getPlaca(){
        return this.placa;
    }
    public String getTipo(){
        return this.tipo;
    }
    public LocalDate getHoraIngreso(){
        return this.hora_ingreso;
        }
    public LocalDate getHoraSalida(){
        return this.hora_salida;
        }
    
    
}
