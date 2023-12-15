package model;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.StatesManager;
import controller.OS;

public class RandomGeneratorThread extends Thread {
    private Semaphore mutex;
    private Semaphore signal;
    private Random random;
    private StatesManager Hold;
    private short id;
    private short time;
    private long arrival;
    private short burst;
    private short memory;

    public RandomGeneratorThread(StatesManager Hold) {
        this.mutex = new Semaphore(1);
        this.random = new Random();
        this.Hold = Hold;
        this.id = 0;
        this.time = 0;
        this.arrival = 0;
    }

    public void setSignal(Semaphore signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            while (id < OS.NUMBER_PROCESSES) {
                // Lock
                mutex.acquire();

                // Create a process
                id++;
                arrival += time;
                burst = OS.getBurstTime(random);
                memory = OS.getMemorySize(random);
                Process process = new Process(id, arrival, burst, memory);

                // Add a process to the hold queue
                Hold.addShort(process);

                // Notify to ready queue (unlock signal)
                signal.release();

                // Simulate the arrival times of a process
                time = OS.getTimeBetweenProcesses(random);
                Thread.sleep(time * (long) OS.QUANTUM);

                // Unlock
                mutex.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
