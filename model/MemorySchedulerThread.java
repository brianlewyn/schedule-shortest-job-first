package model;

import java.util.concurrent.Semaphore;

import controller.StatesManager;

public class MemorySchedulerThread extends Thread {
    private Semaphore mutex;
    private Semaphore signal;
    private StatesManager Hold;
    private StatesManager Fail;
    private StatesManager Ready;

    public MemorySchedulerThread(StatesManager Hold, StatesManager Fail, StatesManager Ready) {
        this.mutex = new Semaphore(0);
        this.Hold = Hold;
        this.Fail = Fail;
        this.Ready = Ready;
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

                // Find a process with valid memory
                if (OS.RAM > 0) {
                    while (!Hold.queue.isEmpty()) {
                        Process process = Hold.queue.remove();

                        // Allocation memory to a process
                        if (process.getMemorySize() <= OS.RAM) {
                            OS.RAM -= process.getMemorySize() * OS.BYTE;

                            // Add a process to the ready queue
                            Ready.addShort(process);

                            // Notify to SJF scheduling (unlock signal)
                            signal.release();

                            break;
                        }

                        // Move invalid memory process to fail queue
                        Fail.addShort(process);
                    }
                }

                // Remain Locked
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
