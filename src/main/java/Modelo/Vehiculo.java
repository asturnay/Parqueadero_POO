package Modelo;

/**
 *
 * @author Daniel
 */
public class Vehiculo {
    private String placa;
    private String tipoVehiculo;
    private String dueno;
    // public vacio 
    public Vehiculo(){
    }
    
    public Vehiculo(String pla, String tipV, String due){
        this.placa = pla;
        this.tipoVehiculo = tipV;
        this.dueno = due;
    }
    // metodos set para vehiculo
    public void setPlaca(String pla){
        this.placa = pla;
    }
    public void setTipoVehiculo(String tipV){
        this.tipoVehiculo = tipV;
    }
    public void setDueño(String due){
        this.dueno = due;
    }
    // metodos get para vehiculo
    public String getPlaca(){
        return this.placa;
    }
    public String getTipoVehiculo(){
        return this.tipoVehiculo;
    }
    public String getDueno(){
        return this.dueno;
    }
}
