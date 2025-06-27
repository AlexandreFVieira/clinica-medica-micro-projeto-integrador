package br.edu.imepac.agendamento.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Classe responsável por capturar e tratar exceções de forma global.
 * Ela intercepta exceções lançadas em qualquer parte da aplicação
 * e retorna uma resposta amigável.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata exceções genéricas (ex: RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "erro", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                )
        );
    }

    // Exemplo adicional: IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArg(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "erro", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                )
        );
    }

    // Você pode adicionar mais tipos de exceções se desejar.
}
