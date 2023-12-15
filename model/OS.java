package model;

import java.util.Random;

public class OS {
    public static short NUMBER_PROCESSES = 100;
    public static long RAM = 8000;

    public final static short QUANTUM = 700;
    public final static short BYTE = 1;

    public static short MIN_QUANTUM = 1;
    public static short MAX_QUANTUM = 10;

    public static short MIN_TIME = 1;
    public static short MAX_TIME = 10;

    public static short MIN_BYTE = 1;
    public static short MAX_BYTE = 1000;

    public static short Quantum(Random random) {
        return (short) (random.nextInt(MAX_QUANTUM - MIN_QUANTUM + 1) + MIN_QUANTUM);
    }

    public static short Time(Random random) {
        return (short) (random.nextInt(MAX_TIME - MIN_TIME + 1) + MIN_TIME);
    }

    public static short Byte(Random random) {
        return (short) (random.nextInt(MAX_BYTE - MIN_BYTE + 1) + MIN_BYTE);
    }
}
