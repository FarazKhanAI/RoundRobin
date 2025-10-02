package org.app.roundrobin.algorithm;

import org.app.roundrobin.model.CircularLinkedList;
import org.app.roundrobin.model.Process;
import org.app.roundrobin.model.ProcessNode;
import org.app.roundrobin.model.SimulationState;

import java.util.List;

/**
 * Core Round-Robin scheduling algorithm (non-UI).
 * Simulated in discrete time units (ms).
 */
public class RoundRobinScheduler {

    private final SimulationState state;
    private int timeQuantum; // in ms

    public RoundRobinScheduler(SimulationState state, int timeQuantum) {
        this.state = state;
        setTimeQuantum(timeQuantum);
    }

    public void setTimeQuantum(int timeQuantum) {
        if (timeQuantum < 1) throw new IllegalArgumentException("Time quantum must be >=1");
        this.timeQuantum = timeQuantum;
    }

    /**
     * Run simulation until ready queue is empty. This is blocking (non-visual) and
     * useful for generating metrics and tests.
     */
    public void runToCompletion() {
        CircularLinkedList q = state.getReadyQueue();
        while (!q.isEmpty()) {
            ProcessNode node = q.getHead();
            Process p = node.getProcess();
            // simulate execution for min(quantum, remaining)
            int exec = Math.min(timeQuantum, p.getRemainingTime());
            p.execute(exec);
            state.incrementTime(exec);

            // update waiting times for others
            List<Process> others = q.toList();
            for (Process other : others) {
                if (!other.equals(p) && !other.isCompleted()) {
                    other.updateWaitingTime(exec);
                }
            }

            if (p.isCompleted()) {
                // compute turnaround time
                p.setTurnaroundTime(state.getCurrentTime() - p.getArrivalTime());
                state.getCompleted().add(p);
                // remove node from queue and continue
                q.remove(node);
            } else {
                // rotate to next process
                q.rotate();
            }
            state.incrementContextSwitches();
        }
    }

    /**
     * Run one quantum-step (useful for step-by-step UI).
     * Returns true if simulation finished after this step.
     */
    public boolean step() {
        CircularLinkedList q = state.getReadyQueue();
        if (q.isEmpty()) return true;
        ProcessNode node = q.getHead();
        Process p = node.getProcess();
        int exec = Math.min(timeQuantum, p.getRemainingTime());
        p.execute(exec);
        state.incrementTime(exec);
        // others waiting
        List<Process> others = q.toList();
        for (Process other : others) {
            if (!other.equals(p) && !other.isCompleted()) other.updateWaitingTime(exec);
        }
        if (p.isCompleted()) {
            p.setTurnaroundTime(state.getCurrentTime() - p.getArrivalTime());
            state.getCompleted().add(p);
            q.remove(node);
        } else {
            q.rotate();
        }
        state.incrementContextSwitches();
        return q.isEmpty();
    }

    public MetricsCalculator calculateMetrics() {
        return new MetricsCalculator(state.getCompleted(), state.getCurrentTime(), state.getContextSwitches());
    }
}
