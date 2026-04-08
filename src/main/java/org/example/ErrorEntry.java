package org.example;

public class ErrorEntry {
    private String tipo;
    private int linea;
    private int columna;
    private String descripcion;

    public ErrorEntry(String tipo, int linea, int columna, String descripcion) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
        this.descripcion = descripcion;
    }

    public String getTipo() { return tipo; }
    public int getLinea() { return linea; }
    public int getColumna() { return columna; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return String.format("%-12s %-6d %-6d %s",
                tipo, linea, columna, descripcion);
    }
}