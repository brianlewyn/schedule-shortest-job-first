package controller;

import model.Process;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MemorySchedulerThread extends Thread {
    private Semaphore mutex, signal;
    private Queue<Process> Hold;
    private Queue<Process> Ready;
    private Queue<Process> temporal;

    public MemorySchedulerThread(Queue<Process> Hold, Queue<Process> Ready) {
        this.mutex = new Semaphore(0);
        this.Hold = Hold;
        this.Ready = Ready;
        this.temporal = new LinkedList<Process>();
    }

    public Semaphore getSignal() {
        return this.mutex;
    }

    public void setSignal(Semaphore signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Lock (waiting for unlock signal)
                mutex.acquire();

                // Check if there is RAM
                if (Global.RAM > 0) {

                    // Find a process with valid memory
                    while (!Hold.isEmpty()) {
                        Process process = Hold.remove();

                        // Allocation memory to a process
                        if (process.getMemorySize() <= Global.RAM) {
                            Global.RAM -= process.getMemorySize() * Global.BYTE;

                            // Add a process to the ready queue
                            Ready.add(process);

                            // Notify to SJF scheduling (unlock signal)
                            signal.release();

                            break;
                        }

                        temporal.add(process);
                    }

                    // Move invalid memory processes to the end
                    while (!temporal.isEmpty()) {
                        Hold.add(temporal.remove());
                    }
                }

                // Remain Locked
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
