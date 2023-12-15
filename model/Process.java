package model;

public class Process {
    private short id;
    private long arrivalTime;
    private short burstTime;
    private long waitTime;
    private short memorySize;
    private int red, green, blue;

    public Process(short id, long arrival, short burst, short memory) {
        this.id = id;
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.waitTime = 0;
        this.memorySize = memory;
        this.initPastelValues();
    }

    private void initPastelValues() {
        this.red = newPastelValue();
        this.green = newPastelValue();
        this.blue = newPastelValue();
    }

    private int newPastelValue() {
        return (int) (Math.random() * 100 + 150);
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

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public void setWaitTime(long wait) {
        this.waitTime = wait;
    }

    public void increaseBurstTime() {
        this.burstTime++;
    }

    public String toShort() {
        return String.format("PI: %d  AT: %d  BT: %d  MS: %d",
                id, arrivalTime, burstTime, memorySize);
    }

    public String toLong() {
        return String.format("PI: %d  AT: %d  BT: %d  WT: %d  MS: %d",
                id, arrivalTime, burstTime, waitTime, memorySize);
    }

    public String toVertical() {
        return String.format("Process ID: %d\nArrival Time: %d\nBurst Time: %d\nWait Time: %d\nMemory Size: %d",
                id, arrivalTime, burstTime, waitTime, memorySize);
    }
}
