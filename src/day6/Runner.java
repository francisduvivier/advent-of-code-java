package day6;

import day5.Mapping;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    private final long time;
    private final long distance;

    Runner(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    long getNbWinners() {
        long startLosers = 0;
        for (long i = 1; i < this.time; i++) {
            if (i * (this.time - i) > this.distance) {
                break;
            } else {
                startLosers++;
            }
        }
        return this.time - 2 * startLosers - 1;
    }}