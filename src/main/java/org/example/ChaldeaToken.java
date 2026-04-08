package org.example;

public class ChaldeaToken {
    private int numero;
    private String valor;
    private String tipo;
    private int linea;
    private int columna;

    public ChaldeaToken(int numero, String valor, String tipo, int linea, int columna) {
        this.numero = numero;
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public int getNumero() { return numero; }
    public String getValor() { return valor; }
    public String getTipo() { return tipo; }
    public int getLinea() { return linea; }
    public int getColumna() { return columna; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-15s %-5d %-5d",
                numero, valor, tipo, linea, columna);
    }
}