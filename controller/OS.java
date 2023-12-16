package controller;

import java.util.Random;

public class OS {
    public static short NUMBER_PROCESSES = 100;
    public static short RAM = 8000;

    public static short QUANTUM = 700; // 700ms
    public static short MIN_QUANTUM = 1; // 1ms
    public static short MAX_QUANTUM = 3000; // 3000ms = 1s

    public static short BYTE = 1; // 1B
    public static short MIN_BYTE = 1; // 1B
    public static short MAX_BYTE = 3000; // 3000B = 1MB

    public static short MIN_BURST = 1;
    public static short MAX_BURST = 10;

    public static short MIN_MEMORY = 1;
    public static short MAX_MEMORY = 10;

    public static short MIN_TIME = 1;
    public static short MAX_TIME = 10;

    public static short getBurstTime(Random random) {
        return (short) (random.nextInt(MAX_BURST - MIN_BURST + 1) + MIN_BURST);
    }

    public static short getMemorySize(Random random) {
        return (short) (random.nextInt(MAX_MEMORY - MIN_MEMORY + 1) + MIN_MEMORY);
    }

    public static short getTimeBetweenProcesses(Random random) {
        return (short) (random.nextInt(MAX_TIME - MIN_TIME + 1) + MIN_TIME);
    }
}
