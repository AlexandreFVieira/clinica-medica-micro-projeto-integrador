package br.edu.imepac.administrativo.utils;

import br.edu.imepac.administrativo.exception.ActionClinicaMedicaException;
import br.edu.imepac.administrativo.exception.AuthenticationClinicaMedicaException;
import br.edu.imepac.comum.services.AutenticadorService;
import br.edu.imepac.comum.services.AutorizadorService;
import br.edu.imepac.comum.services.PerfilService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    // **Injeção de Dependência**: The `PerfilService` is injected to verify user authorization.
    private final PerfilService perfilService;
    private final AutenticadorService autenticadorService;
    private final AutorizadorService autorizadorService;

    // **Construtor**: Receives the `PerfilService` dependency for initialization.
    public AuthorizationFilter(
            PerfilService perfilService,
            AutenticadorService autenticadorService,
            AutorizadorService autorizadorService
    ) {
        this.autenticadorService = autenticadorService;
        this.autorizadorService = autorizadorService;
        this.perfilService = perfilService;
    }

    // **doFilterInternal**: This method intercepts HTTP requests and applies the authorization logic.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // **Obtenção do Caminho da Requisição**: Retrieves the request URI to check if it matches specific paths.
            String path = request.getRequestURI();
            logger.debug("Iniciando validação de acesso para a requisição: {" + path + "}");
            identificarFluxoValidarCredencial(path, filterChain, request, response);
        } catch (Exception e) {
            logger.error("Erro ao prosseguir com a request de documentação: {" + e.getMessage() + "}");
            throw new RuntimeException("Erro ao prosseguir com a request de documentação! {" + e.getMessage() + "}");
        }

    }

    private void validarAcessoAplicacao(String path, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Iniciando validação de acesso para a aplicação");
        // **Optional.ofNullable**: Retrieves the "usuario" header or throws an exception if not found.
        String usuario = Optional.ofNullable(request.getHeader("usuario"))
                .orElseThrow(() -> new AuthenticationClinicaMedicaException("Usuario não encontrado!"));
        // **Optional.ofNullable**: Retrieves the "senha" header or throws an exception if not found.
        String senha = Optional.ofNullable(request.getHeader("senha"))
                .orElseThrow(() -> new AuthenticationClinicaMedicaException("Senha não encontrado!"));
        // **Optional.ofNullable**: Retrieves the "action" header or throws an exception if not found.
        String acao = Optional.ofNullable(request.getHeader("action"))
                .orElseThrow(() -> new ActionClinicaMedicaException("Ação não encontrado!"));

        //validar autenticação do usuário
        logger.debug("Validando autenticação do usuário: {" + usuario + "}");
    }

    private void identificarFluxoValidarCredencial(String path, FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Iniciando a identificação  do fluxo e validação de acesso... {" + path + "}");
        // **Verificação de Caminho**: Checks if the request path is for Swagger UI or API docs.
        if (!path.startsWith("/clinica-medica-administrativo/swagger-ui") && !path.startsWith("/clinica-medica-administrativo/v3/api-docs")) {
            logger.debug("Acesso permitido para a documentação da API: {" + path + "}");
            validarAcessoAplicacao(path, filterChain, request, response);
        }
        filterChain.doFilter(request, response);
    }
}