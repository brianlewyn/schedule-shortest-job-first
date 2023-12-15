package controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

import model.Process;
import view.Status;

public class StatesManager {
    private String title;
    public Queue<Process> queue;
    public JPanel stack;
    private Stack<Process> temporal;

    public StatesManager(String title) {
        this.title = title;
        this.queue = new LinkedList<>();
        this.temporal = new Stack<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void addShort(Process process) {
        queue.add(process);
        stack.removeAll();

        for (Process p : queue) {
            temporal.add(p);
        }

        while (!temporal.isEmpty()) {
            Process p = temporal.peek();
            stack.add(new Status(p.toShort(), p.getRed(), p.getGreen(), p.getBlue(), 10));
            temporal.pop();
        }

        stack.revalidate();
        stack.repaint();
    }

    public void addLong(Process process) {
        queue.add(process);
        stack.removeAll();

        for (Process p : queue) {
            temporal.add(p);
        }

        while (!temporal.isEmpty()) {
            Process p = temporal.peek();
            stack.add(new Status(p.toLong(), p.getRed(), p.getGreen(), p.getBlue(), 10));
            temporal.pop();
        }

        stack.revalidate();
        stack.repaint();
    }

}
