package day20;

import java.util.List;

public interface IOModule {
    IOModule create(List<String> inputs, List<String> outputs, PCB siblings);
    void processPulse(String input, boolean isHigh);
}