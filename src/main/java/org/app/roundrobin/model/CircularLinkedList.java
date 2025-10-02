package org.app.roundrobin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Simple circular singly linked list to model the ready queue.
 */
public class CircularLinkedList {
    private ProcessNode tail; // tail.next is head
    private int size = 0;

    public void add(Process process) {
        ProcessNode node = new ProcessNode(process);
        if (tail == null) {
            tail = node;
            tail.setNext(tail);
        } else {
            node.setNext(tail.getNext());
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public ProcessNode getHead() {
        return tail == null ? null : tail.getNext();
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public int size() { return size; }

    /**
     * Removes a node whose process is completed (search by object equality) and
     * returns the next node after removed node in circular order.
     */
    public ProcessNode remove(ProcessNode nodeToRemove) {
        if (tail == null) throw new NoSuchElementException("List empty");
        ProcessNode prev = tail;
        ProcessNode cur = tail.getNext();
        for (int i = 0; i < size; i++) {
            if (cur == nodeToRemove) {
                if (size == 1) {
                    tail = null;
                } else {
                    prev.setNext(cur.getNext());
                    if (cur == tail) {
                        tail = prev;
                    }
                }
                size--;
                return (size == 0) ? null : prev.getNext();
            }
            prev = cur;
            cur = cur.getNext();
        }
        throw new NoSuchElementException("Node not found");
    }

    /**
     * Moves tail one step forward (effectively rotating list).
     */
    public void rotate() {
        if (tail != null) tail = tail.getNext();
    }

    public List<Process> toList() {
        List<Process> list = new ArrayList<>();
        if (tail == null) return list;
        ProcessNode cur = tail.getNext();
        for (int i = 0; i < size; i++) {
            list.add(cur.getProcess());
            cur = cur.getNext();
        }
        return list;
    }
}
