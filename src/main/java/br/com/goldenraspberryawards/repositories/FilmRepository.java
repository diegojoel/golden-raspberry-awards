package br.com.goldenraspberryawards.repositories;

import br.com.goldenraspberryawards.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    List<FilmEntity> findByWinnerTrue();
}