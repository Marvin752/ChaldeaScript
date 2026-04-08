package org.example;

import org.antlr.v4.runtime.*;
import java.util.ArrayList;
import java.util.List;

public class LexerAnalyzer {
    private List<ChaldeaToken> tokens = new ArrayList<>();
    private ErrorHandler errorHandler;

    public LexerAnalyzer(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public CommonTokenStream analizar(String codigo) {
        CharStream input = CharStreams.fromString(codigo);
        ChaldeaScriptLexer lexer = new ChaldeaScriptLexer(input);

        // Capturar errores léxicos
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                errorHandler.agregarError("LÉXICO", line, charPositionInLine, msg);
            }
        });

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        tokenStream.fill();

        // Llenar lista de tokens
        int contador = 1;
        for (org.antlr.v4.runtime.Token t : tokenStream.getTokens()) {
            if (t.getType() == org.antlr.v4.runtime.Token.EOF) continue;
            String tipo = ChaldeaScriptLexer.VOCABULARY.getSymbolicName(t.getType());
            if (tipo == null) tipo = "DESCONOCIDO";
            tokens.add(new ChaldeaToken(contador++, t.getText(), tipo, t.getLine(), t.getCharPositionInLine()));
        }

        return tokenStream;
    }

    public void imprimirTokens() {
        String fmt = "║ %-4d ║ %-35s ║ %-15s ║ %-5d ║ %-5d ║";
        String top = "╔══════╦═════════════════════════════════════╦═════════════════╦═══════╦═══════╗";
        String hdr = "║ No.  ║ VALOR                               ║ TIPO            ║ LÍNEA ║ COL   ║";
        String mid = "╠══════╬═════════════════════════════════════╬═════════════════╬═══════╬═══════╣";
        String bot = "╚══════╩═════════════════════════════════════╩═════════════════╩═══════╩═══════╝";

        System.out.println("\n>>> TABLA DE TOKENS:");
        System.out.println(top);
        System.out.println(hdr);
        System.out.println(mid);
        for (ChaldeaToken t : tokens) {
            // Truncar valor si es muy largo
            String valor = t.getValor();
            if (valor.length() > 35) valor = valor.substring(0, 32) + "...";
            System.out.println(String.format(fmt,
                    t.getNumero(), valor, t.getTipo(),
                    t.getLinea(), t.getColumna()));
        }
        System.out.println(bot);
    }

    public List<ChaldeaToken> getTokens() {
        return tokens;
    }
}