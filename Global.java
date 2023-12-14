import java.util.Random;

public class Global {
    public static short NUMBER_PROCESSES = 100; // Nº Processes
    public static long RAM = 8000; // 1B, 1000B=1KB, 10000B=1MB

    public static short QUANTUM = 700; // == 1ms
    public static short BYTE = 1; // == 1B

    public static short MIN_QUANTUM = 1; // ms
    public static short MAX_QUANTUM = 10; // ms

    public static short MIN_TIME = 1; // ms
    public static short MAX_TIME = 10; // ms

    public static short MIN_BYTE = 1; // B
    public static short MAX_BYTE = 1000; // B

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
