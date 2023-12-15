import model.RandomGeneratorThread;
import model.MemorySchedulerThread;
import model.ProcessSchedulerThread;

import controller.StatesManager;
import controller.RunningManager;

import view.Window;

public class Main {
    public static void main(String args[]) {
        StatesManager Hold = new StatesManager("Hold State");
        StatesManager Fail = new StatesManager("Fail State");
        StatesManager Ready = new StatesManager("Ready State");
        StatesManager Finished = new StatesManager("Finished State");
        RunningManager Running = new RunningManager("Running State");

        // Grafic User Interfaz (GUI)
        Window GUI = new Window(Hold, Fail, Ready, Finished, Running);

        // Generate Random Processes
        RandomGeneratorThread RandomGenerator = new RandomGeneratorThread(Hold);

        // Memory Scheduler: First In First Out (FIFO)
        MemorySchedulerThread MemoryScheduler = new MemorySchedulerThread(Hold, Fail, Ready);

        // Process Scheduler: Shortest Job first (SFJ)
        ProcessSchedulerThread ProcessScheduler = new ProcessSchedulerThread(Ready, Finished, Running);

        // Set a signal between two threads
        RandomGenerator.setSignal(MemoryScheduler.getSignal());
        MemoryScheduler.setSignal(ProcessScheduler.getSignal());

        RandomGenerator.start();
        MemoryScheduler.start();
        ProcessScheduler.start();
    }
}
