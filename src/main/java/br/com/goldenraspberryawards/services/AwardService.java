package br.com.goldenraspberryawards.services;
import br.com.goldenraspberryawards.dtos.AwardIntervalDTO;
import br.com.goldenraspberryawards.entities.FilmEntity;
import br.com.goldenraspberryawards.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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
            String[] producers = film.getProducers().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                producerWins.computeIfAbsent(producer, k -> new ArrayList<>()).add(Integer.parseInt(film.getReleaseYear()));
            }
        }

        List<AwardIntervalDTO> intervals = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            List<Integer> years = entry.getValue();
            Collections.sort(years);
            for (int i = 1; i < years.size(); i++) {
                int interval = years.get(i) - years.get(i - 1);
                intervals.add(new AwardIntervalDTO(entry.getKey(), interval, years.get(i - 1), years.get(i)));
            }
        }

        if (intervals.isEmpty()) {
            return Map.of("min", Collections.emptyList(), "max", Collections.emptyList());
        }

        int minValue = intervals.stream().mapToInt(AwardIntervalDTO::getInterval).min().orElse(0);
        int maxValue = intervals.stream().mapToInt(AwardIntervalDTO::getInterval).max().orElse(0);

        List<AwardIntervalDTO> minIntervals = intervals.stream()
                .filter(ai -> ai.getInterval() == minValue)
                .toList();

        List<AwardIntervalDTO> maxIntervals = intervals.stream()
                .filter(ai -> ai.getInterval() == maxValue)
                .toList();

        Map<String, List<AwardIntervalDTO>> result = new LinkedHashMap<>();
        result.put("min", minIntervals);
        result.put("max", maxIntervals);

        return result;
    }
}

