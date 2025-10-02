# 🖥️ Round-Robin CPU Scheduling Simulator

A **JavaFX desktop application** that simulates the **Round-Robin CPU Scheduling algorithm** using a **circular linked list**.  
This project is designed for the **Data Structures (Fourth Semester)** course and provides both **educational insights** and an **interactive visualization** of how process scheduling works in Operating Systems.

---

## 🚀 Features

### ✅ Core Functionality
- Round-Robin scheduling with **configurable time quantum (2–10 ms)**
- **Circular linked list** implementation for the ready queue
- Process management:
    - Add processes manually (ID, burst time)
    - Random process generation (future enhancement)
    - Bulk import/export via CSV

### 🎨 Visualization
- Animated **circular ready queue**
- Highlight current running process
- **Gantt chart timeline** of execution
- Process states: Ready / Running / Completed

### 📊 Statistics & Metrics
- **Average Waiting Time**
- **Average Turnaround Time**
- **CPU Utilization**
- **Throughput**
- **Context Switch Count**

### 🛠️ Additional Features
- Start / Pause / Reset simulation
- Step-by-step execution mode
- Speed control (1x, 2x, 5x planned)
- Export simulation results (CSV, PNG planned)



## 🏗️ Technology Stack

- **Java 21**
- **JavaFX 21** (Controls, FXML)
- **Maven** (build and dependency management)



## 📂 Project Structure


RoundRobin
├─ pom.xml # Maven build file
├─ module-info.java # Java module configuration
├─ .gitignore
├─ README.md
├─ src/main/java/org/app/roundrobin
│ ├─ MainApp.java # Application entry point
│ ├─ controller/ # GUI and simulation controllers
│ ├─ model/ # Data structures (Process, CircularLinkedList, etc.)
│ ├─ algorithm/ # Round-Robin scheduler + metrics calculator
│ ├─ view/ # Visualization components
│ └─ utils/ # Constants and File utilities
├─ src/main/resources/org/app/roundrobin
│ ├─ main.css # Modern UI styles
│ └─ main.fxml # FXML (placeholder for future use)
└─ src/test/java/org/app/roundrobin
├─ CircularLinkedListTest.java
└─ RoundRobinSchedulerTest.java




---

## ⚙️ Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/FarazKhanAI/RoundRobin.git

cd roundrobin-simulator



mvn clean install


mvn javafx:run

