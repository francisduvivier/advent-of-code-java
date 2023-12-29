package day20;

import java.util.HashMap;

public class PCB {
    HashMap<String, IOModule> moduleMap = new HashMap<>();

    IOModule getModule(String moduleId) {
        return moduleMap.get(moduleId);
    }

    void addModule(String moduleId, IOModule module) {
        moduleMap.put(moduleId, module);
    }
}