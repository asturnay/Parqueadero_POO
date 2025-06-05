/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;



import Modelo.Modelo_UbicacnionVehiculo;
import Modelo.TipoVehiculo;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Caja_Vehiculos {

    
    private String nombreParqueadero;
    private LinkedList<Modelo_UbicacnionVehiculo> registrosDiarios;
    private final double PRECIO_POR_HORA_CARRO = 4000; 
    private final double PRECIO_POR_HORA_MOTO = 2000;
    private final double PRECIO_DIA_CARRO = 35000; 
    private final double PRECIO_DIA_MOTO = 18000; 

    
    private final int MAX_PUESTOS_CARRO = 10;
    private final int MAX_PUESTOS_MOTO = 10;
    private final boolean[] puestosCarro; 
    private final boolean[] puestosMoto;  

   
    public Caja_Vehiculos(String nombreParqueadero) {
        this.nombreParqueadero = nombreParqueadero;
        this.registrosDiarios = new LinkedList<>();
       
        this.puestosCarro = new boolean[MAX_PUESTOS_CARRO];
        this.puestosMoto = new boolean[MAX_PUESTOS_MOTO];
        for (int i = 0; i < MAX_PUESTOS_CARRO; i++) {
            puestosCarro[i] = false; 
        }
        
        for (int i = 0; i < MAX_PUESTOS_MOTO; i++) {
            puestosMoto[i] = false; 
        }
    }

    public String getNombreParqueadero() {
        return nombreParqueadero;
    }

    public void setNombreParqueadero(String nombreParqueadero) {
        this.nombreParqueadero = nombreParqueadero;
    }

    public LinkedList<Modelo_UbicacnionVehiculo> getRegistrosDiarios() {
        return registrosDiarios;
    }
    public int getPuestosDisponibles(TipoVehiculo tipo) {
        int disponibles = 0;
        if (tipo != TipoVehiculo.CARRO) { 
            for (boolean ocupado : puestosMoto) {
                if (!ocupado) {
                    disponibles++;
                }
            }
        } else {
            for (boolean ocupado : puestosCarro) {
                if (!ocupado) {
                    disponibles++;
                }
            }
        }
        return disponibles;
    }
    public void mostrarEstadoPuestos() {
        System.out.println(" Estado Actual de los Puestos");
        System.out.println("Puestos para Carros (" + getPuestosDisponibles(TipoVehiculo.CARRO) + 
                "/" + MAX_PUESTOS_CARRO + " disponibles):");
        for (int i = 0; i < MAX_PUESTOS_CARRO; i++) {
            System.out.print("  Puesto C" + (i + 1) + ": " + (puestosCarro[i] ? "OCUPADO" : "LIBRE") + "\t");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();

        System.out.println("Puestos para Motos (" + getPuestosDisponibles(TipoVehiculo.MOTO) + 
                "/" + MAX_PUESTOS_MOTO + " disponibles):");
        for (int i = 0; i < MAX_PUESTOS_MOTO; i++) {
            System.out.print("  Puesto M" + (i + 1) + ": " + (puestosMoto[i] ? "OCUPADO" : "LIBRE") + "\t");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("");
    }
    
    
    private int asignarPuesto(TipoVehiculo tipo) {
        if (tipo == TipoVehiculo.CARRO) {
            for (int i = 0; i < MAX_PUESTOS_CARRO; i++) {
                if (!puestosCarro[i]) {
                    puestosCarro[i] = true;
                    return i + 1; 
                }
            }
        } else { 
            for (int i = 0; i < MAX_PUESTOS_MOTO; i++) {
                if (!puestosMoto[i]) {
                    puestosMoto[i] = true;
                    return i + 1; 
                }
            }
        }
        return -1; 
    }

    private boolean liberarPuesto(TipoVehiculo tipo, int numeroPuesto) {
        if (numeroPuesto < 1) {
            return false; 
        }

        if (tipo == TipoVehiculo.CARRO) {
            if (numeroPuesto <= MAX_PUESTOS_CARRO) {
                puestosCarro[numeroPuesto - 1] = false;
                return true;
            }
        } else { 
            if (numeroPuesto <= MAX_PUESTOS_MOTO) {
                puestosMoto[numeroPuesto - 1] = false;
                return true;
            }
        }
        return false;
    }

    public boolean registrarEntradaVehiculo(String placa,
            
            TipoVehiculo tipoVehiculo, String nombreTitular) {
       
        for (Modelo_UbicacnionVehiculo registro : registrosDiarios) {
            if (registro.getPlaca().equalsIgnoreCase(placa) 
                    && registro.getHoraSalida() == null) {
                System.out.println("Error: El vehículo con placa " 
                        + placa + " ya se encuentra en el parqueadero.");
                return false;
            }
        }

        int puestoAsignado = asignarPuesto(tipoVehiculo);
        if (puestoAsignado == -1) {
            System.out.println("Lo sentimos, no hay puestos disponibles para " 
                    + tipoVehiculo.toString().toLowerCase() + "s en este momento.");
            return false;
        }

        LocalDateTime horaEntrada = LocalDateTime.now();
        Modelo_UbicacnionVehiculo nuevoRegistro = new Modelo_UbicacnionVehiculo(placa, tipoVehiculo, 
                nombreTitular, horaEntrada, puestoAsignado);
        this.registrosDiarios.add(nuevoRegistro);
        System.out.println("Entrada registrada para el vehículo con placa " + placa +
                " (Puesto: " + tipoVehiculo.toString().charAt(0) + puestoAsignado + 
                ") a las " + horaEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".");
        return true;
    }

   
    public double registrarSalidaVehiculo(String placa) {
        for (Modelo_UbicacnionVehiculo registro : registrosDiarios) {
            if (registro.getPlaca().equalsIgnoreCase(placa) && registro.getHoraSalida() == null) {
                registro.setHoraSalida(LocalDateTime.now());
                double costo = calcularCosto(registro);
                registro.setCostoTotal(costo);

               
                liberarPuesto(registro.getTipoVehiculo(), registro.getNumeroPuesto());

                System.out.println("Salida registrada para el vehículo con placa " + placa +
                        " (Puesto: " + registro.getTipoVehiculo().toString().charAt(0) + 
                        registro.getNumeroPuesto() + ") a las " + registro.getHoraSalida().
                                format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".");
                System.out.println("Costo total del parqueo: $" + String.format("%,.0f", costo) + " COP.");
                return costo;
            }
        }
        System.out.println("Error: No se encontró ningún vehículo con placa " 
                + placa + " o ya ha salido.");
        return -1; 
    }


    private double calcularCosto(Modelo_UbicacnionVehiculo registro) {
        LocalDateTime entrada = registro.getHoraEntrada();
        LocalDateTime salida = registro.getHoraSalida();

        if (entrada == null || salida == null) {
            return 0; 
        }

        Duration duracion = Duration.between(entrada, salida);
        long minutosTotales = duracion.toMinutes();

        double costoPorMinutoCarro = PRECIO_POR_HORA_CARRO / 60.0;
        double costoPorMinutoMoto = PRECIO_POR_HORA_MOTO / 60.0;

        long diasCompletos = minutosTotales / (24 * 60);
        long minutosRestantes = minutosTotales % (24 * 60);

        double costoTotal = 0;

        
        if (registro.getTipoVehiculo() == TipoVehiculo.CARRO) {
            costoTotal += diasCompletos * PRECIO_DIA_CARRO;
            costoTotal += minutosRestantes * costoPorMinutoCarro;
        } else { 
            costoTotal += diasCompletos * PRECIO_DIA_MOTO;
            costoTotal += minutosRestantes * costoPorMinutoMoto;
        }
        return Math.round(costoTotal / 100) * 100;
    }

    public void generarReporteDiario() {
        if (registrosDiarios.isEmpty()) {
            System.out.println("No hay registros de vehículos para mostrar.");
            return;
        }

        System.out.println("\n--- Reporte de Parqueadero (" + this.nombreParqueadero + ") ---");
        System.out.println("");
        System.out.printf("%-10s %-10s %-20s %-18s %-18s %-10s %-12s\n", "Placa",
                "Tipo", "Titular", "Entrada", "Salida", "Puesto", "Costo (COP)");
        System.out.println("");

        for (Modelo_UbicacnionVehiculo registro : registrosDiarios) {
            String horaEntradaStr = registro.getHoraEntrada().
                    format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
            String horaSalidaStr = registro.getHoraSalida()
                    != null ? registro.getHoraSalida().
                            format(DateTimeFormatter.ofPattern("dd/MM HH:mm")) : "ACTIVO";
            String costoStr = registro.getCostoTotal() 
                    != 0 ? String.format("%,.0f", registro.getCostoTotal()) : "N/A";
            String puestoStr = (registro.getTipoVehiculo() == TipoVehiculo.CARRO ? "C" : "M") + 
                    registro.getNumeroPuesto();

            System.out.printf("%-10s %-10s %-20s %-18s %-18s %-10s %-12s\n",
                    registro.getPlaca(),
                    registro.getTipoVehiculo().toString(),
                    registro.getNombreTitular(),
                    horaEntradaStr,
                    horaSalidaStr,
                    puestoStr,
                    costoStr
            );
        }
        System.out.println("");
    }
}
    
    