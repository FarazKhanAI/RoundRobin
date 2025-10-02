package org.app.roundrobin.controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.app.roundrobin.view.QueueVisualization;
import org.app.roundrobin.view.GanttChartView;
import org.app.roundrobin.model.SimulationState;
import org.app.roundrobin.algorithm.RoundRobinScheduler;

public class MainController {
    private BorderPane root;
    private SimulationController simController;

    public MainController() {
        buildUI();
    }

    public BorderPane getView() {
        return root;
    }

    private void buildUI() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #ECF0F1;");
        // Left control panel
        VBox left = new VBox(10);
        left.setPadding(new Insets(12));
        left.setPrefWidth(320);
        left.setStyle("-fx-background-color: #2C3E50;");
        Label title = new Label("Control Panel");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        // Process input
        TextField idField = new TextField();
        idField.setPromptText("Process ID");
        TextField burstField = new TextField();
        burstField.setPromptText("Burst Time (ms)");

        Button addBtn = new Button("Add Process");
        addBtn.setOnAction(e -> {
            try {
                String id = idField.getText().isEmpty() ? "P" + System.nanoTime() % 10000 : idField.getText();
                int burst = Integer.parseInt(burstField.getText());
                simController.addProcess(id, burst);
                idField.clear(); burstField.clear();
            } catch (NumberFormatException ex) {
                // quick feedback
                Alert a = new Alert(Alert.AlertType.WARNING, "Burst must be integer");
                a.showAndWait();
            }
        });

        Slider quantumSlider = new Slider(2, 10, 4);
        quantumSlider.setMajorTickUnit(1);
        quantumSlider.setMinorTickCount(0);
        quantumSlider.setSnapToTicks(true);
        Label qLabel = new Label("Time Quantum: 4 ms");
        quantumSlider.valueProperty().addListener((obs, oldV, newV) -> qLabel.setText("Time Quantum: " + newV.intValue() + " ms"));

        Button start = new Button("Start");
        Button pause = new Button("Pause");
        Button reset = new Button("Reset");

        left.getChildren().addAll(title, idField, burstField, addBtn, qLabel, quantumSlider, new HBox(8, start, pause, reset));

        // Center visualization
        VBox center = new VBox(8);
        center.setPadding(new Insets(12));
        QueueVisualization qv = new QueueVisualization();
        GanttChartView gantt = new GanttChartView();
        center.getChildren().addAll(qv.getView(), gantt.getView());

        // Right statistics
        VBox right = new VBox(10);
        right.setPadding(new Insets(12));
        right.setPrefWidth(300);
        right.setStyle("-fx-background-color: #fff;");
        Label statsTitle = new Label("Statistics");
        Label currentTime = new Label("Current Time: 0");
        Label completed = new Label("Completed: 0");
        Label avgWait = new Label("Avg Wait: 0.0");
        right.getChildren().addAll(statsTitle, currentTime, completed, avgWait);

        root.setLeft(left);
        root.setCenter(center);
        root.setRight(right);

        // Simulation controller hookup
        this.simController = new SimulationController(qv, gantt, currentTime, completed, avgWait);
        start.setOnAction(e -> simController.start((int) quantumSlider.getValue()));
        pause.setOnAction(e -> simController.pause());
        reset.setOnAction(e -> simController.reset());
    }
}
