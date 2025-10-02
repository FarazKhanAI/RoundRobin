package org.app.roundrobin.utils;

import org.app.roundrobin.model.Process;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Process> importCsv(File f) throws IOException {
        List<Process> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                // expected: id,arrival,burst
                String id = parts[0].trim();
                int arrival = Integer.parseInt(parts[1].trim());
                int burst = Integer.parseInt(parts[2].trim());
                list.add(new Process(id, arrival, burst));
            }
        }
        return list;
    }

    public static void exportCsv(File f, List<Process> processes) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            for (Process p : processes) {
                pw.println(p.getProcessId() + "," + p.getArrivalTime() + "," + p.getBurstTime());
            }
        }
    }
}
