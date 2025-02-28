package br.com.goldenraspberryawards.controllers;

import br.com.goldenraspberryawards.dtos.AwardIntervalDTO;
import br.com.goldenraspberryawards.services.AwardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/awards")
public class AwardController {

    private final AwardService awardService;

    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping("/intervals")
    public Map<String, List<AwardIntervalDTO>> getAwardIntervals() {
        return awardService.getAwardIntervals();
    }
}
