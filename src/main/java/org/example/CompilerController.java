package org.example;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompilerController {

    @PostMapping("/compilar")
    public Map<String, Object> compilar(@RequestBody Map<String, String> body) {
        String codigo = body.get("codigo");
        Map<String, Object> resultado = new HashMap<>();

        try {
            Compiler compiler = new Compiler();
            compiler.compilarDesdeString(codigo);

            resultado.put("tokens", compiler.getTokens());
            resultado.put("errores", compiler.getErrores());
            resultado.put("arbol", compiler.getArbol());
            resultado.put("arbolJson", compiler.getArbolJson());
            resultado.put("exito", compiler.getErrores().isEmpty());

        } catch (Exception e) {
            resultado.put("exito", false);
            resultado.put("mensaje", e.getMessage());
        }

        return resultado;
    }
}