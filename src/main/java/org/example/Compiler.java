package org.example;

import org.antlr.v4.runtime.*;
import java.nio.file.*;
import java.util.*;

public class Compiler {
    private ErrorHandler errorHandler;
    private LexerAnalyzer lexerAnalyzer;
    private ParserAnalyzer parserAnalyzer;

    public Compiler() {
        this.errorHandler = new ErrorHandler();
        this.lexerAnalyzer = new LexerAnalyzer(errorHandler);
        this.parserAnalyzer = new ParserAnalyzer(errorHandler);
    }

    // Para leer desde archivo .chs
    public void compilar(String rutaArchivo) throws Exception {
        String codigo = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        processar(codigo);
        imprimirResultados(rutaArchivo);
    }

    // Para recibir código desde la interfaz web
    public void compilarDesdeString(String codigo) {
        processar(codigo);
    }

    private void processar(String codigo) {
        errorHandler.limpiar();
        CommonTokenStream tokenStream = lexerAnalyzer.analizar(codigo);
        parserAnalyzer.analizar(tokenStream);
    }

    private void imprimirResultados(String archivo) {
        System.out.println("========================================");
        System.out.println("       CHALDEASCRIPT COMPILER          ");
        System.out.println("========================================");
        System.out.println("Archivo: " + archivo);
        lexerAnalyzer.imprimirTokens();
        parserAnalyzer.imprimirArbol();
        errorHandler.imprimirErrores();
        System.out.println("\n========================================");
        System.out.println("    Análisis completado - Chaldea       ");
        System.out.println("========================================");
    }

    // Getters para la API
    public List<ChaldeaToken> getTokens() {
        return lexerAnalyzer.getTokens();
    }

    public List<ErrorEntry> getErrores() {
        return errorHandler.getErrores();
    }

    public String getArbol() {
        return parserAnalyzer.getArbolString();
    }

    public Map<String, Object> getArbolJson() {
        return parserAnalyzer.getArbolJson();
    }
}