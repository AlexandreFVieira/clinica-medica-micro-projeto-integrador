package br.edu.imepac.atendimento.config;

@Configuration
public class ConfigModelMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
