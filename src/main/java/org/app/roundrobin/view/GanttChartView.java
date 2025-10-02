package org.app.roundrobin.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.app.roundrobin.model.Process;

import java.util.List;

/** Very simple Gantt chart placeholder: lists completed processes in order. */
public class GanttChartView {
    private final VBox root = new VBox();

    public GanttChartView() {
        root.setPadding(new Insets(8));
        root.setSpacing(6);
        root.getChildren().add(new Label("Gantt Chart"));
    }

    public Node getView() { return root; }

    public void update(List<Process> completed) {
        root.getChildren().removeIf(n -> !(n instanceof Label && ((Label)n).getText().equals("Gantt Chart")));
        for (Process p : completed) {
            root.getChildren().add(new Label(p.getProcessId() + ": burst=" + p.getBurstTime() + ", turnaround=" + p.getTurnaroundTime()));
        }
    }
}
