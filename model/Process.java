package model;

public class Process {
    private short id;
    private long arrivalTime;
    private short burstTime;
    private long waitTime;
    private short memorySize;

    public Process(short id, long arrival, short burst, short memory) {
        this.id = id;
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.waitTime = 0;
        this.memorySize = memory;
    }

    public short getId() {
        return this.id;
    }

    public long getArrivalTime() {
        return this.arrivalTime;
    }

    public short getBurstTime() {
        return this.burstTime;
    }

    public long getWaitTime() {
        return this.waitTime;
    }

    public short getMemorySize() {
        return this.memorySize;
    }

    public void setWaitTime(long wait) {
        this.waitTime = wait;
    }

    public void addBurstTime(short burst) {
        this.burstTime += burst;
    }

    @Override
    public String toString() {
        return String.format("Pid: %d Arrival: %dQ Burst: %dQ Memory: %dB",
                id, arrivalTime, burstTime, memorySize);
    }

    public String toStringWitProgress(short burstTime) {
        return String.format("Process id: %d Arrival: %dQ Burst: %dQ Wait: %dQ Memory: %dB",
                id, arrivalTime, burstTime, waitTime, memorySize);
    }
}
