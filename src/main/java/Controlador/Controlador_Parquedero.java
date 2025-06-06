package Controlador;

import Modelo.Modelo_UbicacnionVehiculo;
import Modelo.Vehiculo;
import Modelo.Parqueadero;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Controlador_Parquedero {
    private Map<String, Modelo_UbicacnionVehiculo> ubicaciones;
    private LinkedList<Vehiculo> registrosDiarios;
    private boolean parqueaderoAbierto;
    private double caja_diaria;
    private Parqueadero parqueadero;

    public Controlador_Parquedero() {
        this.ubicaciones = inicializarUbicacionesParqueadero();
        this.registrosDiarios = new LinkedList<>();
        this.parqueaderoAbierto = false;
        this.caja_diaria = 0.0;
        this.parqueadero = new Parqueadero();
    }

    public void AsignarParqueadero(String nit, double valorHora) {
        this.parqueadero.setNit(nit);
        this.parqueadero.setValorHora(valorHora);
        System.out.println("Información del parqueadero registrada: NIT=  " + nit + ", Valor por Hora=$  " + valorHora);
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public boolean ParqueaderoAbierto() {
        return parqueaderoAbierto;
    }

    private Map<String, Modelo_UbicacnionVehiculo> inicializarUbicacionesParqueadero() {
        Map<String, Modelo_UbicacnionVehiculo> ubicacionesParqueadero = new HashMap<>();
        for (int U = 1; U <= 10; U++) {
            String idUbicacion = "A" + U;
            ubicacionesParqueadero.put(idUbicacion, new Modelo_UbicacnionVehiculo(idUbicacion));
        }
        return ubicacionesParqueadero;
    }

    private Modelo_UbicacnionVehiculo asignarPuesto(String tipo, String placa, LocalDateTime horaIngreso) {
        for (Map.Entry<String, Modelo_UbicacnionVehiculo> entry : ubicaciones.entrySet()) {
            Modelo_UbicacnionVehiculo ubicacion = entry.getValue();
            if (ubicacion.getUbicacionDisponible()) {
                Vehiculo nuevoVehiculo = new Vehiculo();
                nuevoVehiculo.setPlaca(placa);
                nuevoVehiculo.setTipo(tipo);
                nuevoVehiculo.setHoraIngreso(horaIngreso);

                ubicacion.ocupar(nuevoVehiculo, horaIngreso);
                System.out.println("Vehículo asignado a la ubicación: " + entry.getKey());
                return ubicacion;
            }
        }
        System.out.println("No hay puestos disponibles.");
        return null;
    }

    public boolean registrarVehiculo(String placa, String tipo, LocalDate hora_ingreso) {
        if (!parqueaderoAbierto) {
            System.out.println("El parqueadero no está abierto. No se pueden registrar vehículos.");
            return false;
        }

        for (Modelo_UbicacnionVehiculo ubicacion : ubicaciones.values()) {
            if (!ubicacion.getUbicacionDisponible() && ubicacion.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("El vehículo con placa " + placa + " ya está en el parqueadero.");
                return false;
            }
        }

        Modelo_UbicacnionVehiculo ubicacionAsignada = asignarPuesto(tipo, placa, hora_ingreso);
        if (ubicacionAsignada == null) {
            System.out.println("Lo sentimos, no hay puestos disponibles.");
            return false;
        }

        Vehiculo nuevo = ubicacionAsignada.getVehiculo();
        registrosDiarios.add(nuevo);

        System.out.println("Vehículo con placa " + placa + " registrado exitosamente.");
        return true;
    }

     public boolean retirarVehiculo(String placa) {
        if (!parqueaderoAbierto) {
            System.out.println("El parqueadero no está abierto. No se pueden retirar vehículos.");
            return false;
        }
        Modelo_UbicacnionVehiculo ubicacionEncontrada = null;
        for (Modelo_UbicacnionVehiculo ubicacionR : ubicaciones.values()) {
            if (!ubicacionR.getUbicacionDisponible() && ubicacionR.getVehiculo() != null && ubicacionR.getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                ubicacionEncontrada = ubicacionR;
                break;
            }
        }
        if (ubicacionEncontrada == null) {
            System.out.println("No se encontró el vehículo con placa: " + placa );
            return false;
        }
        Vehiculo vehiculoRetirado = ubicacionEncontrada.getVehiculo();
        LocalDateTime horaIngreso = vehiculoRetirado.getHoraIngreso(); 
        LocalDateTime horaSalida = LocalDateTime.now(); 

        long totalMinutes = ChronoUnit.MINUTES.between(horaIngreso, horaSalida);
        long tiempoParqueaderoHoras = (long) Math.ceil((double) totalMinutes / 60.0);
        
        if (tiempoParqueaderoHoras == 0 && totalMinutes > 0) {
            tiempoParqueaderoHoras = 1;
        } else if (tiempoParqueaderoHoras == 0 && totalMinutes == 0) { 
            tiempoParqueaderoHoras = 1;
        }
        double cobro = calcularPago(vehiculoRetirado.getTipo(), tiempoParqueaderoHoras);

        vehiculoRetirado.setHoraSalida(horaSalida);

        this.caja_diaria += cobro;

        ubicacionEncontrada.desocupar();

        System.out.println("Vehículo con placa " + placa + " retirado de la ubicación " + ubicacionEncontrada.getUbicacion());
        System.out.println("Cobro total: $" + cobro);
        return true;
    }

    private double calcularPago(String tipoVehiculo, long tiempoParqueaderoHoras) {
        double tarifaBasePorHora = parqueadero.getValorHora();

        double multiplicadorTipo = 1.0;
        if (tipoVehiculo.equalsIgnoreCase("carro")) {
            multiplicadorTipo = 1.5;
        } else if (tipoVehiculo.equalsIgnoreCase("moto")) {
            multiplicadorTipo = 0.8;
        }
        return (tarifaBasePorHora * multiplicadorTipo) * tiempoParqueaderoHoras;
    }

    public double getCajaDiaria() {
        return caja_diaria;
    }

    public int getTotalVehiculosRegistradosHoy() {
        return registrosDiarios.size();
    }

    public void abrirParqueadero() {
        if (this.parqueadero.getNit() == null || this.parqueadero.getNit().isEmpty() || this.parqueadero.getValorHora() <= 0.0) {
            System.out.println(" No se puede abrir el parqueadero. Primero debe registrar la información del parqueadero (NIT y valor por hora válidos).");
            return;
        }
        this.parqueaderoAbierto = true;
        System.out.println("El parqueadero ha sido abierto con NIT: " + parqueadero.getNit() + " y Valor por Hora:$" + parqueadero.getValorHora());
    }

    public void cerrarParqueadero() {
        if (this.parqueaderoAbierto) {
            this.parqueaderoAbierto = false;
            System.out.println("El parqueadero ha sido cerrado.");
        } else {
            System.out.println("El parqueadero ya está cerrado.");
        }
    }

    public void mostrarEstadoPuestos() {
        System.out.println("\n--- ESTADO DEL PARQUEADERO ---");
        if (!parqueaderoAbierto) {
            System.out.println("El parqueadero está cerrado. No se puede mostrar el estado de los puestos.");
            return;
        }
        for (Map.Entry<String, Modelo_UbicacnionVehiculo> entry : ubicaciones.entrySet()) {
            String id = entry.getKey();
            Modelo_UbicacnionVehiculo ubicacion = entry.getValue();
            if (ubicacion.getUbicacionDisponible()) {
                System.out.println("Ubicación " + id + ": Disponible");
            } else {
                Vehiculo veh = ubicacion.getVehiculo();
                System.out.println("Ubicación " + id + ": Ocupado por [" + veh.getTipo() + "] - Placa: " + veh.getPlaca() + " (Ingreso: " + veh.getHoraIngreso() + ")");
            }
        }
    }

    public void realizarCierreDeCaja() {
        if (parqueaderoAbierto) {
            System.out.println("\n--- Cierre de Caja ---");
            System.out.println("Para realizar el cierre de caja final, el parqueadero debe estar cerrado.");
            return;
        }

        System.out.println("\n--- REPORTE DE CIERRE DE CAJA (" + LocalDate.now() + ") ---");
        System.out.println("----------------------------------------");
        System.out.println("NIT del Parqueadero: " + parqueadero.getNit());
        System.out.println("Valor por Hora Establecido: $" + String.format("%.2f", parqueadero.getValorHora()));
        System.out.println("----------------------------------------");

        double totalGanancias = getCajaDiaria();
        int vehiculosAtendidos = 0;
        int vehiculosPendientes = 0;

        System.out.println("--- Detalles de Vehículos ---");
        if (registrosDiarios.isEmpty()) {
            System.out.println("No se registraron movimientos de vehículos hoy.");
        } else {
            for (Vehiculo v : registrosDiarios) {
                System.out.print("Placa: " + v.getPlaca() + ", Tipo: " + v.getTipo() + ", Ingreso: " + v.getHoraIngreso());
                if (v.getHoraSalida() != null) {
                    System.out.println(", Salida: " + v.getHoraSalida() + " (Retirado)");
                    vehiculosAtendidos++;
                } else {
                    System.out.println(" (Aún en parqueadero)");
                    vehiculosPendientes++;
                }
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Total de vehículos que ingresaron hoy: " + getTotalVehiculosRegistradosHoy());
        System.out.println("Vehículos retirados hoy: " + vehiculosAtendidos);
        System.out.println("Vehículos que aún permanecen en el parqueadero: " + vehiculosPendientes);
        System.out.println("\n--- Dinero en Caja Esperado: $" + String.format("%.2f", totalGanancias) + " ---");
        System.out.println("----------------------------------------");
    }
}