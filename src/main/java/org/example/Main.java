package org.example;

public class Main {
    public static void main(String[] args) throws Exception {

        String[] pruebas = {
                "src/main/resources/prueba1.chs",
                "src/main/resources/prueba2.chs",
                "src/main/resources/prueba3.chs",
                "src/main/resources/prueba4.chs",
                "src/main/resources/prueba5.chs",
                "src/main/resources/prueba6.chs",
                "src/main/resources/prueba7.chs"
        };

        for (int i = 0; i < pruebas.length; i++) {
            System.out.println("\n");
            System.out.println("╔══════════════════════════════════════════════════════════╗");
            System.out.printf( "║           PRUEBA %d DE %d                                  ║%n", i+1, pruebas.length);
            System.out.println("╚══════════════════════════════════════════════════════════╝");

            Compiler compiler = new Compiler();
            compiler.compilar(pruebas[i]);

            System.out.println("\n══════════════════════════════════════════════════════════");
        }

        System.out.println("\n");
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║         TODAS LAS PRUEBAS COMPLETADAS - CHALDEA          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}