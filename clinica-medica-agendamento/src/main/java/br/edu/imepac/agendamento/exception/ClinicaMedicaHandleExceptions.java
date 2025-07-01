package br.edu.imepac.agendamento.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j // **@Slf4j**: Automatically generates a logger for the class, allowing logging of messages.
@ControllerAdvice
// **@ControllerAdvice**: Indicates that this class provides centralized exception handling for controllers.
public class ClinicaMedicaHandleExceptions {

    // **@ExceptionHandler**: Specifies the type of exception this method handles.
    // This method handles generic exceptions and returns a 500 Internal Server Error.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado, tente novamente!"); // Returns a response with status 500.
    }

    // **@ExceptionHandler**: Handles `AuthenticationClinicaMedicaException` and returns a 401 Unauthorized status.
    @ExceptionHandler(
            {
                    AuthenticationClinicaMedicaException.class,
                    ActionClinicaMedicaException.class
            })
    public ResponseEntity<String> handleUnauthorized(Exception e) {
        log.error("An error occurred: " + e.getMessage()); // Logs the error message.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Dados de acesso inválido!"); // Returns a response with status 401.
    }
}