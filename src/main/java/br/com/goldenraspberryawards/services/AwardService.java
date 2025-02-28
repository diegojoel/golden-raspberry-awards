package br.com.goldenraspberryawards.services;

import br.com.goldenraspberryawards.dtos.AwardIntervalDTO;
import br.com.goldenraspberryawards.entities.FilmEntity;
import br.com.goldenraspberryawards.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AwardService {

    private final FilmRepository repository;

    public AwardService(FilmRepository repository) {
        this.repository = repository;
    }

    public Map<String, List<AwardIntervalDTO>> getAwardIntervals() {
        List<FilmEntity> winners = repository.findByWinnerTrue();
        Map<String, List<Integer>> producerWins = new HashMap<>();

        for (FilmEntity film : winners) {
            String[] producers = film.getProducers().split(",");
            for (String producer : producers) {
                producer = producer.trim();
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(Integer.parseInt(film.getReleaseYear()));
            }
        }

        List<AwardIntervalDTO> minIntervals = new ArrayList<>();
        List<AwardIntervalDTO> maxIntervals = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                AwardIntervalDTO intervalObj = new AwardIntervalDTO(entry.getKey(), interval, years.get(i - 1), years.get(i));
                minIntervals.add(intervalObj);
                maxIntervals.add(intervalObj);
            }
        }

        minIntervals.sort(Comparator.comparingInt(AwardIntervalDTO::getInterval));
        maxIntervals.sort(Comparator.comparingInt(AwardIntervalDTO::getInterval).reversed());

        int minValue = minIntervals.get(0).getInterval();
        int maxValue = maxIntervals.get(0).getInterval();

        return Map.of(
                "min", minIntervals.stream().filter(ai -> ai.getInterval() == minValue).collect(Collectors.toList()),
                "max", maxIntervals.stream().filter(ai -> ai.getInterval() == maxValue).collect(Collectors.toList())
        );
    }
}
