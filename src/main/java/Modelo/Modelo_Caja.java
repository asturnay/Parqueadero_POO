/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Daniel
 */
public class Modelo_Caja {
    private String caja_diaria;
     
    public Modelo_Caja(){
    }
    
    public Modelo_Caja(String caja){
        this.caja_diaria = caja;
    }
     public void setCaja(String caja){
        this.caja_diaria = caja;
    }
       public String getCaja(){
        return this.caja_diaria;
    }
    
}
