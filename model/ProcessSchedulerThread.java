package model;

import java.util.concurrent.Semaphore;

import controller.RunningManager;
import controller.StatesManager;
import controller.OS;

public class ProcessSchedulerThread extends Thread {
    private Semaphore mutex;
    private StatesManager Ready;
    private StatesManager Finished;
    private RunningManager Running;
    private long waitTime = 0;

    public ProcessSchedulerThread(StatesManager Ready, StatesManager Finished, RunningManager Running) {
        this.mutex = new Semaphore(0);
        this.Ready = Ready;
        this.Finished = Finished;
        this.Running = Running;
    }

    public Semaphore getSignal() {
        return this.mutex;
    }

    private Process getShortest() {
        Process shortest = Ready.queue.peek();

        for (Process process : Ready.queue) {
            if (process.getBurstTime() < shortest.getBurstTime()) {
                shortest = process;
            }
        }

        Ready.queue.remove(shortest);
        return shortest;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Lock (waiting for unlock signal)
                mutex.acquire();

                // Get the shortest job first
                Process process = getShortest();

                // Set current wait time
                process.setWaitTime(waitTime);

                // Restart wait time when ready queue is empty
                if (Ready.queue.isEmpty()) {
                    waitTime = 0;
                }

                // Add a process to the CPU
                Running.add(process);

                // Simulate a process spending CPU time
                short burstTime = process.getBurstTime();
                while (burstTime > 0) {
                    Running.updateProgress(burstTime);
                    Thread.sleep(OS.QUANTUM);

                    if (Running.io) {
                        burstTime--;
                    } else {
                        process.increaseBurstTime();
                    }
                }

                // Add shortest first job burst time
                waitTime += process.getBurstTime();

                // Return borrowed memory to RAM
                OS.RAM += process.getMemorySize() * (long) OS.BYTE;

                // Add a process to the finished queue
                Finished.addLong(process);

                // Remain Locked
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
