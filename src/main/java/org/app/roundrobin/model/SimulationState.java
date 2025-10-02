package org.app.roundrobin.model;

import java.util.ArrayList;
import java.util.List;

/** Holds state during a simulation run. */
public class SimulationState {
    private final CircularLinkedList readyQueue = new CircularLinkedList();
    private final List<Process> completed = new ArrayList<>();
    private int currentTime = 0;
    private int contextSwitches = 0;

    public CircularLinkedList getReadyQueue() { return readyQueue; }
    public List<Process> getCompleted() { return completed; }
    public int getCurrentTime() { return currentTime; }
    public void incrementTime(int delta) { currentTime += delta; }
    public void resetTime() { currentTime = 0; }
    public int getContextSwitches() { return contextSwitches; }
    public void incrementContextSwitches() { contextSwitches++; }
}
