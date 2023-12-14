package controller;

import model.Process;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class RandomGeneratorThread extends Thread {
    private short id, time, burst, memory;
    private long arrival;
    private Random random;
    private Semaphore mutex, signal;
    private Queue<Process> Hold;

    public RandomGeneratorThread(Queue<Process> Hold) {
        this.id = 0;
        this.time = 0;
        this.arrival = 0;
        this.random = new Random();
        this.mutex = new Semaphore(1);
        this.Hold = Hold;
    }

    public void setSignal(Semaphore signal) {
        this.signal = signal;
    }

    @Override
    public void run() {
        try {
            while (id < Global.NUMBER_PROCESSES) {
                // Lock
                mutex.acquire();

                // Create a process
                id++;
                arrival += time;
                burst = Global.Quantum(random);
                memory = Global.Byte(random);
                Process process = new Process(id, arrival, burst, memory);

                // Add a process to the hold queue
                Hold.add(process);

                // Notify to ready queue (unlock signal)
                signal.release();

                // Simulate the arrival times of a process
                time = Global.Time(random);
                Thread.sleep(time * (long) Global.QUANTUM);

                // Unlock
                mutex.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
