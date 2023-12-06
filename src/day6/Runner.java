package day6;

import day5.Mapping;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private final int time;
    private final int distance;

    public Runner(int time, int distance) {
        this.time = time;
        this.distance = distance;
    }

    long getNbWinners() {
        int startLosers = 0;
        for (int i = 1; i < this.time; i++) {
            if (i * (this.time - i) > this.distance) {
                break;
            } else {
                startLosers++;
            }
        }
        return this.time - 2 * startLosers - 1;
    }}