package org.example;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    private List<ErrorEntry> errores = new ArrayList<>();

    public void agregarError(String tipo, int linea, int columna, String descripcion) {
        errores.add(new ErrorEntry(tipo, linea, columna, descripcion));
    }

    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    public List<ErrorEntry> getErrores() {
        return errores;
    }

    public void imprimirErrores() {
        String fmt = "║ %-10s ║ %-5d ║ %-5d ║ %-45s ║";
        String top = "╔════════════╦═══════╦═══════╦═══════════════════════════════════════════════╗";
        String hdr = "║ TIPO       ║ LÍNEA ║ COL   ║ DESCRIPCIÓN                                   ║";
        String mid = "╠════════════╬═══════╬═══════╬═══════════════════════════════════════════════╣";
        String bot = "╚════════════╩═══════╩═══════╩═══════════════════════════════════════════════╝";

        System.out.println("\n>>> TABLA DE ERRORES:");
        if (errores.isEmpty()) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║  No se encontraron errores.              ║");
            System.out.println("║  !Codigo ChaldeaScript valido!           ║");
            System.out.println("╚══════════════════════════════════════════╝");
        } else {
            System.out.println(top);
            System.out.println(hdr);
            System.out.println(mid);
            for (ErrorEntry error : errores) {
                // Truncar descripción si es muy larga
                String desc = error.getDescripcion();
                if (desc.length() > 45) desc = desc.substring(0, 42) + "...";
                System.out.println(String.format(fmt,
                        error.getTipo(), error.getLinea(),
                        error.getColumna(), desc));
            }
            System.out.println(bot);
        }
    }

    public void limpiar() {
        errores.clear();
    }
}