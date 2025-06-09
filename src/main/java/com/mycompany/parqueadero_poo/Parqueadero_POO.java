package com.mycompany.parqueadero_poo;

import Controlador.Controlador_Parquedero;
import java.time.LocalDate;
import java.util.Scanner;

public class Parqueadero_POO {

    // Variables estáticas

private static Scanner scanner = new Scanner(System.in);
    private static Controlador.Controlador_Parquedero controlador = null; //

    public static void main(String[] args) {
        System.out.println("PARQUEDERO");

        menuPrincipal();
    }

    // Método principal del menú
    public static void menuPrincipal() {
        String op; 
        do {
            mostrarMenu();
            op = scanner.nextLine(); 

            switch (op) {
                case "1":
                    configurarYObrirParqueadero();
                    break;
                case "2":
                    registrarEntradaVehiculo();
                    break;
                case "3":
                    registrarSalidaVehiculo();
                    break;
                case "4":
                    consultarEstadoParqueadero();
                    break;
                case "5":
                    generarReporteDiario();
                    break;
                case "6":
                    realizarCierreDeCajaYSalir();
                    break;
                case "7":
                    salirSistema();
                    break;
                default:
                    System.out.println(" Por favor, ingrese un número del 1 al 7.");
                    break;
            }
        } while (!op.equals("6") && !op.equals("7")); 
    }

    

    private static void mostrarMenu() {
        System.out.println("Menu");
        if (controlador != null && controlador.getParqueadero() != null) {
            String nit = controlador.getParqueadero().getNit();
            double valorHora = controlador.getParqueadero().getValorHora();
            boolean abierto = controlador.ParqueaderoAbierto();
            System.out.println("Parqueadero: " + (nit != null && !nit.isEmpty() ? nit : "No Configurado") +
                               " | Tarifa/Hora: $" + valorHora +
                               " | Estado: " + (abierto ? "ABIERTO" : "CERRADO"));
       
        }

        System.out.println("1. Abrir Parqueadero");
        System.out.println("2. Ingresar vehiculo");
        System.out.println("3. Retirar parqueadero");
        System.out.println("4. Consultar estado del parqueadero");
        System.out.println("5. Generar reporte diario");
        System.out.println("6. Realizar Cierre de Caja ");
        System.out.println("7. Salir ");
        System.out.print("Seleccione una opcion: ");
    }

    

    private static void verificarControlador() {
        if (controlador == null) {
            System.out.println("Error: El parqueadero no ha sido configurado ni abierto.");
        }
    }
    public static void configurarYObrirParqueadero() {
        if (controlador == null) {
            controlador = new Controlador_Parquedero(); 
        }
        if (controlador.ParqueaderoAbierto()) {
            System.out.println("El parqueadero ya está configurado y abierto.");
            System.out.println("NIT: " + controlador.getParqueadero().getNit() +
                               ", Tarifa/Hora: $" +  controlador.getParqueadero().getValorHora());
            return;
        }

        System.out.println(" ABRIR PARQUEADERO ");
        System.out.print("Ingrese el NIT del parqueadero: ");
        String nit = scanner.nextLine();

        System.out.print("Ingrese el valor por hora : ");
        double valorHora = Double.parseDouble(scanner.nextLine());

        controlador.AsignarParqueadero(nit, valorHora);
        controlador.abrirParqueadero();
    }

    public static void registrarEntradaVehiculo() {
        verificarControlador();
        if (controlador == null || !controlador.ParqueaderoAbierto()) return;

        System.out.println(" REGISTRO DE ENTRADA ");
        System.out.print("Ingrese placa del vehiculo: ");
        String placa = scanner.nextLine();

        System.out.print("Tipo de vehiculo (carro/moto/otro): ");
        String tipo = scanner.nextLine();

        LocalDate horaEntrada = LocalDate.now();

        if (controlador.registrarVehiculo(placa, tipo, horaEntrada)) {
            System.out.println("¡Vehiculo registrado con exito!");
        } else {
            System.out.println(" Verifique disponibilidad o si ya esta en el parqueadero.");
        }
    }

    public static void registrarSalidaVehiculo() {
        verificarControlador();
        if (controlador == null || !controlador.ParqueaderoAbierto()) return;

        System.out.println("  REGISTRO DE SALIDA ");
        System.out.print("Ingrese placa del vehiculo: ");
        String placa = scanner.nextLine();

        if (controlador.retirarVehiculo(placa)) {
            System.out.println("Vehiculo retirado correctamente.");
        } else {
            System.out.println("No se encontro el vehiculo o ocurrio un error al retirar.");
        }
    }

    public static void consultarEstadoParqueadero() {
        verificarControlador();
        if (controlador == null || !controlador.ParqueaderoAbierto()) return;

        controlador.mostrarEstadoPuestos();
    }

    public static void generarReporteDiario() {
        verificarControlador();
        if (controlador == null || !controlador.ParqueaderoAbierto()) return;

        System.out.println("Reporte diario");
        System.out.println("NIT del Parqueadero: " + controlador.getParqueadero().getNit());
        System.out.println("Valor por hora" + controlador.getParqueadero().getValorHora());
        System.out.println("Total vehiculos que ingresaron hoy: " + controlador.getTotalVehiculosHoy());
        System.out.println("Caja diaria actual: $" + controlador.getCajaDiaria());
    }
    
    public static void realizarCierreDeCajaYSalir() {
        if (controlador == null) {
            System.out.println("El parqueadero no ha sido abierto.");
        } else {
            controlador.cerrarParqueadero();
            controlador.CierreDeCaja();
        }
        salirSistema();
    }

    public static void salirSistema() {
        System.out.println("Saliendo del sistema de gestión de parqueadero");
        if (scanner != null) {
            scanner.close();
        }
        System.exit(0);
    }
}