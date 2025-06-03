package Modelo;

/**
 *
 * @author Daniel
 */
public class Vehiculo {
    private String placa;
    private String moto;
    private String carro;
    private String titular;
    // public vacio 
    public Vehiculo(){
    }
    
    public Vehiculo(String pla, String mo,String car, String tit){
        this.placa = pla;
        this.moto = mo;
        this.carro = car;
        this.titular = tit;
    }
    // metodos set para vehiculo
    public void setPlaca(String pla){
        this.placa = pla;
    }
    public void setCarro(String car){
        this.carro = car;
    }
    public void setMoto(String mo){
        this.moto = mo;
    }
    public void setDueño(String tit){
        this.titular = tit;
    }
    // metodos get para vehiculo
    public String getPlaca(){
        return this.placa;
    }
    public String getCarro(){
        return this.carro;
    }
    public String getMoto(){
        return this.moto;
    }
    public String getDueno(){
        return this.titular;
    }
}
