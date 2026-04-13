package org.example;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

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

    public String getArbolString() {
        if (arbol != null && parser != null) {
            return arbol.toStringTree(parser);
        }
        return "No se pudo generar el árbol.";
    }

    public Map<String, Object> getArbolJson() {
        if (arbol == null) return null;
        return construirNodo(arbol);
    }

    private Map<String, Object> construirNodo(org.antlr.v4.runtime.tree.ParseTree nodo) {
        Map<String, Object> map = new LinkedHashMap<>();
        String nombre = nodo.getClass().getSimpleName()
                .replace("Context", "")
                .replace("TerminalNodeImpl", "");

        if (nodo.getChildCount() == 0) {
            map.put("nombre", nodo.getText());
            map.put("hijos", new ArrayList<>());
            map.put("esHoja", true);
        } else {
            map.put("nombre", nombre.isEmpty() ? nodo.getText() : nombre);
            map.put("esHoja", false);
            List<Map<String, Object>> hijos = new ArrayList<>();
            for (int i = 0; i < nodo.getChildCount(); i++) {
                hijos.add(construirNodo(nodo.getChild(i)));
            }
            map.put("hijos", hijos);
        }
        return map;
    }
}