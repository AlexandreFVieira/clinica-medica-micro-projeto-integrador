package br.edu.imepac.comum.services;

import br.edu.imepac.comum.models.Perfil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionsApplicationService {

    public List<String> getActionsApplication() {
        // Extract attributes of the Perfil class using reflection
        return Arrays.stream(Perfil.class.getDeclaredFields())
                .filter(field -> field.getType().equals(boolean.class)) // Check for primitive boolean type
                .map(Field::getName) // Get the field name
                .collect(Collectors.toList()); // Collect as a list
    }
}