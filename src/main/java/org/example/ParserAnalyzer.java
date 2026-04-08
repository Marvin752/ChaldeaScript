package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ParserAnalyzer {
    private ErrorHandler errorHandler;
    private ParseTree arbol;
    private ChaldeaScriptParser parser;

    public ParserAnalyzer(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ParseTree analizar(CommonTokenStream tokenStream) {
        tokenStream.seek(0);

        parser = new ChaldeaScriptParser(tokenStream);

        // Capturar errores sintácticos
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine,
                                    String msg, RecognitionException e) {
                errorHandler.agregarError("SINTÁCTICO", line, charPositionInLine, msg);
            }
        });

        arbol = parser.program();
        return arbol;
    }

    public void imprimirArbol() {
        System.out.println("\n>>> ÁRBOL SINTÁCTICO:");
        if (arbol != null && parser != null) {
            System.out.println(arbol.toStringTree(parser));
        } else {
            System.out.println("No se pudo generar el árbol.");
        }
    }

    public ParseTree getArbol() {
        return arbol;
    }
}