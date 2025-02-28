package br.com.goldenraspberryawards.dtos;

import lombok.Getter;

@Getter
public class AwardIntervalDTO {

    private final String producer;
    private final int interval;
    private final int previousWin;
    private final int followingWin;

    public AwardIntervalDTO(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }
}
