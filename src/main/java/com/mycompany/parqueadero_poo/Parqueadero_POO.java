package com.mycompany.parqueadero_poo;

import Controlador.Controlador_Parquedero;
import java.time.LocalDate;
import java.util.Scanner;

public class Parqueadero_POO {

    // Variables estáticas para ser accesibles desde todos los métodos
    private static Scanner scanner = new Scanner(System.in);
    private static Controlador.Controlador_Parquedero controlador = null; // Se inicializará al "abrir" el parqueadero

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE PARQUEADERO ===");
        System.out.println("Bienvenido al sistema.\n");

        // Llamada al menú principal
        menuPrincipal();
    }

    // Método principal del menú
    public static void menuPrincipal() {
        String op; // Para leer la opción como String
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
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 7.");
                    break;
            }
        } while (!op.equals("6") && !op.equals("7")); 
    }

    

    private static void mostrarMenu() {
        System.out.println("\n----- MENÚ PRINCIPAL DE OPERACIONES -----");
        if (controlador != null && controlador.getParqueadero() != null) {
            String nit = controlador.getParqueadero().getNit();
            double valorHora = controlador.getParqueadero().getValorHora();
            boolean abierto = controlador.ParqueaderoAbierto();
            System.out.println("Parqueadero: " + (nit != null && !nit.isEmpty() ? nit : "No Configurado") +
                               " | Tarifa/Hora: $" + valorHora +
                               " | Estado: " + (abierto ? "ABIERTO" : "CERRADO"));
        } else {
            System.out.println("Parqueadero: No Configurado | Tarifa/Hora: N/A | Estado: CERRADO");
        }

        System.out.println("1. Configurar y Abrir Parqueadero");
        System.out.println("2. Registrar entrada de vehículo");
        System.out.println("3. Registrar salida de vehículo");
        System.out.println("4. Consultar estado del parqueadero");
        System.out.println("5. Generar reporte diario");
        System.out.println("6. Realizar Cierre de Caja ");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    

    private static void verificarControlador() {
        if (controlador == null) {
            System.out.println("El parqueadero no ha sido abierto.");
        }
    }
    public static void configurarYObrirParqueadero() {
        if (controlador == null) {
            controlador = new Controlador_Parquedero(); 
        }
        if (controlador.ParqueaderoAbierto()) {
            System.out.println("El parqueadero ya está abierto.");
            System.out.println("NIT: " + controlador.getParqueadero().getNit() +
                               ", Tarifa/Hora: $" +  controlador.getParqueadero().getValorHora());
            return;
        }

        System.out.println("\n--- ABRIR PARQUEADERO ---");
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

        System.out.println("\n--- REGISTRO DE ENTRADA ---");
        System.out.print("Ingrese placa del vehículo: ");
        String placa = scanner.nextLine();

        System.out.print("Tipo de vehículo (carro/moto/otro): ");
        String tipo = scanner.nextLine();

        LocalDate horaEntrada = LocalDate.now();

        if (controlador.registrarVehiculo(placa, tipo, horaEntrada)) {
            System.out.println("Vehículo registrado con éxito");
        } else {
            System.out.println("No se pudo registrar el vehículo Verifique disponibilidad.");
        }
    }

    public static void registrarSalidaVehiculo() {
        verificarControlador();
        if (controlador == null || !controlador.ParqueaderoAbierto()) return;

        System.out.println("\n--- REGISTRO DE SALIDA ---");
        System.out.print("Ingrese placa del vehículo: ");
        String placa = scanner.nextLine();

        if (controlador.retirarVehiculo(placa)) {
            System.out.println("Vehículo retirado correctamente.");
        } else {
            System.out.println("No se encontró el vehículo.");
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

        System.out.println("\n--- REPORTE DIARIO ---");
        System.out.println("NIT del Parqueadero: " + controlador.getParqueadero().getNit());
        System.out.println("Valor por Hora Establecido: $" + controlador.getParqueadero().getValorHora());
        System.out.println("Total vehículos que ingresaron hoy: " + controlador.getTotalVehiculosRegistradosHoy());
        System.out.println("Caja diaria actual: $" + controlador.getCajaDiaria());
    }
    
    public static void realizarCierreDeCajaYSalir() {
        if (controlador == null) {
            System.out.println("El parqueadero no ha sido configurado. No hay un cierre de caja para realizar.");
        } else {
            controlador.cerrarParqueadero();
            controlador.realizarCierreDeCaja();
        }
        salirSistema();
    }

    public static void salirSistema() {
        System.out.println("Saliendo del parqueadero");
        if (scanner != null) {
            scanner.close();
        }
        System.exit(0);
    }
}