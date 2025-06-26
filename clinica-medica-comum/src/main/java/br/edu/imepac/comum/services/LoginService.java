package br.edu.imepac.comum.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginService {
    private final AutenticadorService autenticadorService;
    private final AutorizadorService autorizadorService;

    public boolean validarLogin(String username, String password) {
        return false;
    }

}
