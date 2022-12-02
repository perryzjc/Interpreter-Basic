package App.Commands.Challenge0.BruceLoop.ImprovedBruce.MemoryMap;

import App.MemorySpace;

import java.util.HashMap;

public class MemoryMap {
    /**
     * key: a list of commands in string (separated by line)
     * e.g.
     * INC
     * INC
     * value: the memory space
     */
    private HashMap<String, MemorySpace> memMap;
}
