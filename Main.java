import controller.*;
import model.Process;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String args[]) {
        Queue<Process> Hold = new LinkedList<Process>();
        Queue<Process> Ready = new LinkedList<Process>();

        // Generate Random Processes
        RandomGeneratorThread RandomGenerator = new RandomGeneratorThread(Hold);

        // Memory Scheduler: First In First Out (FIFO)
        MemorySchedulerThread MemoryScheduler = new MemorySchedulerThread(Hold, Ready);

        // Process Scheduler: Shortest Job first (SFJ)
        ProcessSchedulerThread ProcessScheduler = new ProcessSchedulerThread(Ready);

        // Set a signal between two threads
        RandomGenerator.setSignal(MemoryScheduler.getSignal());
        MemoryScheduler.setSignal(ProcessScheduler.getSignal());

        RandomGenerator.start();
        MemoryScheduler.start();
        ProcessScheduler.start();
    }
}
