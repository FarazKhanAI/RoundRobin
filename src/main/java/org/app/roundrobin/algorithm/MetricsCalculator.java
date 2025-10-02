package org.app.roundrobin.algorithm;

import org.app.roundrobin.model.Process;

import java.util.List;

/** Compute waiting time, turnaround, utilization, throughput, etc. */
public class MetricsCalculator {
    private final List<Process> completed;
    private final int totalTime;
    private final int contextSwitches;

    public MetricsCalculator(List<Process> completed, int totalTime, int contextSwitches) {
        this.completed = completed;
        this.totalTime = totalTime;
        this.contextSwitches = contextSwitches;
    }

    public double averageWaitingTime() {
        if (completed.isEmpty()) return 0.0;
        return completed.stream().mapToInt(Process::getWaitingTime).average().orElse(0.0);
    }

    public double averageTurnaroundTime() {
        if (completed.isEmpty()) return 0.0;
        return completed.stream().mapToInt(Process::getTurnaroundTime).average().orElse(0.0);
    }

    public double cpuUtilization() {
        int totalBurst = completed.stream().mapToInt(Process::getBurstTime).sum();
        if (totalTime == 0) return 0.0;
        return 100.0 * totalBurst / totalTime;
    }

    public double throughput() {
        if (totalTime == 0) return 0.0;
        return (double) completed.size() / totalTime;
    }

    public int getContextSwitches() { return contextSwitches; }
}
