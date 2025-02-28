package br.com.goldenraspberryawards.configs;

import br.com.goldenraspberryawards.entities.FilmEntity;
import br.com.goldenraspberryawards.repositories.FilmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

@Slf4j
@Configuration
public class DataLoaderConfig {

    @Bean
    CommandLineRunner initDatabase(FilmRepository repository) {
        return args -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/movielist.csv"))))) {
                reader.lines().skip(1).forEach(line -> {
                    String[] parts = line.split(";");
                    if (parts.length >= 4) {
                        boolean winner = parts.length > 4 && parts[4] != null && parts[4].equals("yes");
                        FilmEntity film = new FilmEntity(parts[0], parts[1], parts[2], parts[3], winner);
                        repository.save(film);
                    }
                });
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        };
    }
}
