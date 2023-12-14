import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ShortestJobFirstThread extends Thread {
    private long waitTime = 0;
    private Semaphore mutex;
    private Queue<Process> Ready;

    ShortestJobFirstThread(Queue<Process> Ready) {
        this.mutex = new Semaphore(0);
        this.Ready = Ready;
    }

    public Semaphore getSignal() {
        return this.mutex;
    }

    private Process getShortest() {
        Process shortest = Ready.peek();

        for (Process process : Ready) {
            if (process.getBurstTime() < shortest.getBurstTime()) {
                shortest = process;
            }
        }

        Ready.remove(shortest);
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
                if (Ready.isEmpty()) {
                    waitTime = 0;
                }

                // Simulate a process spending CPU time
                for (short burstTime = process.getBurstTime(); burstTime > 0; burstTime--) {

                    // Show job progress for each CPU time
                    System.out.println(process.toStringWitProgress(burstTime));
                    Thread.sleep(Global.QUANTUM);
                }
                System.out.println();

                // Add shortest first job burst time
                waitTime += process.getBurstTime();

                // Return borrowed memory to RAM
                Global.RAM += process.getMemorySize() * (long) Global.BYTE;

                // Remain Locked
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
