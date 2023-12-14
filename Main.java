import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String args[]) {
        Queue<Process> Hold = new LinkedList<Process>();
        Queue<Process> Ready = new LinkedList<Process>();

        CreationThread Creation = new CreationThread(Hold);
        AllocationThread Allocation = new AllocationThread(Hold, Ready);
        ShortestJobFirstThread ShortestJobFirst = new ShortestJobFirstThread(Ready);

        // Set a signal between two threads
        Creation.setSignal(Allocation.getSignal());
        Allocation.setSignal(ShortestJobFirst.getSignal());

        Creation.start();
        Allocation.start();
        ShortestJobFirst.start();
    }
}
