package com.rutas;

import javax.swing.JOptionPane;

public class VentanaRuta extends javax.swing.JFrame {

    // 1. Declaras tu lista ligada personalizada aquí arriba
    private RutaListaLigada miRuta = new RutaListaLigada();

    public VentanaRuta() {
        initComponents(); // Inicializa los componentes visuales de la ventana
    }

    public void accionAgregar() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la parada:");
        if (nombre == null || nombre.isEmpty()) return;
        double lat = Double.parseDouble(JOptionPane.showInputDialog(this, "Latitud:"));
        double lon = Double.parseDouble(JOptionPane.showInputDialog(this, "Longitud:"));
        int tiempo = Integer.parseInt(JOptionPane.showInputDialog(this, "Tiempo estimado (min):"));
        String desc = JOptionPane.showInputDialog(this, "Descripción:");
        miRuta.agregarParada(nombre, lat, lon, tiempo, desc);
        JOptionPane.showMessageDialog(this, "¡Parada agregada con éxito!");
    }

    public void accionGuardar() {
        try {
            ArchivoJsonManager.guardarRutaJSON("ruta_paradas.json", miRuta);
            JOptionPane.showMessageDialog(this, "¡Ruta guardada en JSON correctamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }

    public void accionCargar() {
        try {
            ArchivoJsonManager.cargarRutaJSON("ruta_paradas.json", miRuta);
            JOptionPane.showMessageDialog(this, "¡Ruta cargada desde JSON con éxito!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage());
        }
    }

    public void accionCalcularDistancia() {
        double total = miRuta.calcularDistanciaTotal();
        JOptionPane.showMessageDialog(this, "Distancia total aproximada: " + String.format("%.2f", total) + " km");
    }

    private void initComponents() {
        // Aquí genera VS Code o tu entorno los botones por defecto
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaRuta().setVisible(true);
            }
        });
    }
}