package org.app.roundrobin.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.app.roundrobin.view.QueueVisualization;
import org.app.roundrobin.view.GanttChartView;
import org.app.roundrobin.algorithm.RoundRobinScheduler;
import org.app.roundrobin.model.Process;
import org.app.roundrobin.model.SimulationState;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Controls a running simulation with a Timeline for animation.
 * This is a simplified controller to demonstrate integration with model.
 */
public class SimulationController {
    private final QueueVisualization queueView;
    private final GanttChartView ganttView;
    private final Label currentTimeLabel, completedLabel, avgWaitLabel;
    private final SimulationState state = new SimulationState();
    private RoundRobinScheduler scheduler;
    private Timeline timeline;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public SimulationController(QueueVisualization queueView, GanttChartView ganttView,
                                Label currentTimeLabel, Label completedLabel, Label avgWaitLabel) {
        this.queueView = queueView;
        this.ganttView = ganttView;
        this.currentTimeLabel = currentTimeLabel;
        this.completedLabel = completedLabel;
        this.avgWaitLabel = avgWaitLabel;
    }

    public void addProcess(String id, int burst) {
        Process p = new Process(id, state.getCurrentTime(), burst);
        state.getReadyQueue().add(p);
        queueView.update(state.getReadyQueue());
    }

    public void start(int quantumMs) {
        if (running.get()) return;
        scheduler = new RoundRobinScheduler(state, quantumMs);
        timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            boolean finished = scheduler.step();
            queueView.update(state.getReadyQueue());
            ganttView.update(state.getCompleted());
            currentTimeLabel.setText("Current Time: " + state.getCurrentTime());
            completedLabel.setText("Completed: " + state.getCompleted().size());
            avgWaitLabel.setText(String.format("Avg Wait: %.2f", scheduler.calculateMetrics().averageWaitingTime()));
            if (finished) {
                timeline.stop();
                running.set(false);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        running.set(true);
    }

    public void pause() {
        if (timeline != null) {
            if (timeline.getStatus() == Timeline.Status.RUNNING) timeline.pause();
            else timeline.play();
        }
    }

    public void reset() {
        if (timeline != null) timeline.stop();
        state.getReadyQueue().toList().clear();
        state.getCompleted().clear();
        state.resetTime();
        queueView.update(state.getReadyQueue());
        ganttView.update(state.getCompleted());
        currentTimeLabel.setText("Current Time: 0");
        completedLabel.setText("Completed: 0");
        avgWaitLabel.setText("Avg Wait: 0.0");
    }
}
