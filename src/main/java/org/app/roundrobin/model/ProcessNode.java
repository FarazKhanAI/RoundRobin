package org.app.roundrobin.model;

public class ProcessNode {
    private Process process;
    private ProcessNode next;

    public ProcessNode(Process process) {
        this.process = process;
    }

    public Process getProcess() { return process; }
    public ProcessNode getNext() { return next; }
    public void setNext(ProcessNode next) { this.next = next; }
}
