package org.app.roundrobin.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.app.roundrobin.model.CircularLinkedList;
import org.app.roundrobin.model.Process;

import java.util.List;

/** Simple placeholder visualization (expand with canvas/animation later). */
public class QueueVisualization {
    private final VBox root = new VBox();
    private final FlowPane pane = new FlowPane();

    public QueueVisualization() {
        root.setPadding(new Insets(8));
        root.setSpacing(6);
        Label title = new Label("Circular Queue");
        pane.setHgap(8);
        pane.setVgap(8);
        pane.setPrefHeight(140);
        root.getChildren().addAll(title, pane);
    }

    public Node getView() { return root; }

    public void update(CircularLinkedList q) {
        pane.getChildren().clear();
        List<Process> list = q.toList();
        if (list.isEmpty()) {
            pane.getChildren().add(new Label("Ready queue empty"));
            return;
        }
        for (Process p : list) {
            Label l = new Label(p.getProcessId() + "\nrem=" + p.getRemainingTime());
            l.setStyle("-fx-border-color: #34495E; -fx-padding: 8; -fx-background-color: white;");
            pane.getChildren().add(l);
        }
    }
}
