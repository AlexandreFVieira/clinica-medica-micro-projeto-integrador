package br.edu.imepac.administrativo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * **@Configuration**: Indica que esta classe declara um ou mais beans do Spring.
 * É usada para definir configurações e beans para o contexto da aplicação.
 */
@Configuration
public class ConfigModelMapper {

    /**
     * **@Bean**: Marca este método como um produtor de bean do Spring.
     * Este método fornece uma instância de `ModelMapper`, que é uma biblioteca utilizada para mapeamento de objetos.
     * O `ModelMapper` simplifica o processo de conversão entre objetos, como transformar DTOs em entidades e vice-versa.
     *
     * @return Uma nova instância de `ModelMapper`.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}