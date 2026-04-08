package org.example;

import org.antlr.v4.runtime.*;
import java.nio.file.*;

public class Compiler {
    private ErrorHandler errorHandler;
    private LexerAnalyzer lexerAnalyzer;
    private ParserAnalyzer parserAnalyzer;

    public Compiler() {
        this.errorHandler = new ErrorHandler();
        this.lexerAnalyzer = new LexerAnalyzer(errorHandler);
        this.parserAnalyzer = new ParserAnalyzer(errorHandler);
    }

    public void compilar(String rutaArchivo) throws Exception {
        String codigo = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

        System.out.println("========================================");
        System.out.println("       CHALDEASCRIPT COMPILER          ");
        System.out.println("========================================");
        System.out.println("Archivo: " + rutaArchivo);
        System.out.println("Código fuente:");
        System.out.println(codigo);
        System.out.println("========================================\n");

        // Análisis léxico
        CommonTokenStream tokenStream = lexerAnalyzer.analizar(codigo);
        lexerAnalyzer.imprimirTokens();

        // Análisis sintáctico
        parserAnalyzer.analizar(tokenStream);
        parserAnalyzer.imprimirArbol();

        // Mostrar errores
        errorHandler.imprimirErrores();

        System.out.println("\n========================================");
        System.out.println("    Análisis completado - Chaldea       ");
        System.out.println("========================================");
    }
}