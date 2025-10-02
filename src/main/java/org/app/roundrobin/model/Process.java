package org.app.roundrobin.model;

public class Process {
    private final String processId;
    private final int arrivalTime;
    private final int burstTime;
    private int remainingTime;
    private int waitingTime;
    private int turnaroundTime;

    public Process(String processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }

    public String getProcessId() { return processId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }

    public void execute(int time) {
        remainingTime = Math.max(0, remainingTime - time);
    }

    public void updateWaitingTime(int delta) {
        waitingTime += delta;
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }

    public void setTurnaroundTime(int t) {
        this.turnaroundTime = t;
    }

    @Override
    public String toString() {
        return processId + "(burst=" + burstTime + ", rem=" + remainingTime + ")";
    }
}
