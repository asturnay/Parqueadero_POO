package Modelo;

/**
 *
 * @author Daniel
 */
public class Parqueadero {
    private String nit;
    private double valorHora; 

    public Parqueadero() {
    }
    public Parqueadero(String nit, double valorHora) {
        this.nit = nit;
        this.valorHora = valorHora;
    }
    // get para parquedero 
    public String getNit() {
        return nit;
    }
    public double getValorHora() {
        return valorHora;
    }
    // set parquedero
    public void setNit(String nit) {
        this.nit = nit;
    }
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
}

    

