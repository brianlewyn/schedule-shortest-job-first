package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Expression;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.RunningManager;
import controller.StatesManager;
import controller.OS;

public class Window extends JFrame {
    private StatesManager Hold;
    private StatesManager Fail;
    private StatesManager Ready;
    private StatesManager Finished;
    private RunningManager Running;
    private JButton IO;
    private JSlider QuantumSlider;
    private JSlider ByteSlider;
    private JSlider BurstSlider;
    private JSlider MemorySlider;
    private JSlider TimeSlider;

    public Window(StatesManager Hold, StatesManager Fail, StatesManager Ready,
            StatesManager Finished, RunningManager Running) {

        this.Hold = Hold;
        this.Fail = Fail;
        this.Ready = Ready;
        this.Finished = Finished;
        this.Running = Running;

        this.setTitle("Task Manager");
        this.setSize(1300, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.initComponenets();
        this.addStatusTitles();
        this.addStatusStacks();
        this.addRunningState();
        this.addSliders();

        IO.addActionListener(new WaitingListener());

        QuantumSlider.addChangeListener(new QuantumSliderListener());
        ByteSlider.addChangeListener(new ByteSliderListener());
        BurstSlider.addChangeListener(new BurstSliderListener());
        MemorySlider.addChangeListener(new MemorySliderListener());
        TimeSlider.addChangeListener(new TimeSliderListener());

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponenets() {
        this.Hold.stack = new JPanel();
        this.Fail.stack = new JPanel();
        this.Ready.stack = new JPanel();
        this.Finished.stack = new JPanel();
        this.Running.box = new JPanel();

        this.IO = new JButton("I/O");

        this.QuantumSlider = new JSlider(JSlider.HORIZONTAL, OS.MIN_QUANTUM, OS.MAX_QUANTUM, OS.QUANTUM);
        this.ByteSlider = new JSlider(JSlider.HORIZONTAL, OS.MIN_BYTE, OS.MAX_BYTE, OS.BYTE);
        this.BurstSlider = new JSlider(JSlider.HORIZONTAL, OS.MIN_BURST, OS.MAX_BURST, 1);
        this.MemorySlider = new JSlider(JSlider.HORIZONTAL, OS.MIN_MEMORY, OS.MAX_MEMORY, 1);
        this.TimeSlider = new JSlider(JSlider.HORIZONTAL, OS.MIN_TIME, OS.MAX_TIME, 1);
    }

    private void addStatusTitles() {
        JPanel grid = new JPanel(new GridLayout(1, 4));
        grid.setSize(860, 30);
        grid.setLocation(20, 10);

        String[] titles = { Hold.getTitle(), Fail.getTitle(), Ready.getTitle(), Finished.getTitle() };
        for (String title : titles) {
            JLabel caption = new JLabel(title);
            caption.setHorizontalAlignment(SwingConstants.CENTER);
            caption.setFont(new Font("Arial", Font.BOLD, 16));
            grid.add(caption);
        }
        this.add(grid);
    }

    private void addStatusStacks() {
        JPanel grid = new JPanel(new GridLayout(1, 4, 14, 0));
        grid.setSize(860, 600);
        grid.setLocation(20, 40);

        JPanel[] stacks = { Hold.stack, Fail.stack, Ready.stack, Finished.stack };
        for (JPanel stack : stacks) {
            stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
            grid.add(new JScrollPane(stack));
        }
        this.add(grid);
    }

    private void addRunningState() {
        JLabel caption = new JLabel(Running.getTitle());
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.BOLD, 16));
        caption.setSize(200, 30);
        caption.setLocation(970, 10);
        this.add(caption);

        JPanel grid = new JPanel(new BorderLayout());
        Running.box.setLayout(new BorderLayout());
        grid.add(new JScrollPane(Running.box));
        grid.setSize(360, 160);
        grid.setLocation(900, 40);
        this.add(grid);

        this.add(this.IO);
        Running.time = new JLabel();
        Running.time.setOpaque(true);
        Running.time.setBackground(Color.LIGHT_GRAY);
        Running.time.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.add(Running.time);

        grid.add(IO, BorderLayout.AFTER_LINE_ENDS);
        grid.add(Running.time, BorderLayout.AFTER_LAST_LINE);
    }

    private void addSliders() {
        JLabel caption = new JLabel("PROCESS");
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.BOLD, 16));
        caption.setSize(200, 30);
        caption.setLocation(970, 210);
        this.add(caption);

        JPanel grid = new JPanel(new GridLayout(10, 1, 0, 0));
        grid.setSize(360, 400);
        grid.setLocation(900, 240);

        JSlider[] sliders = { QuantumSlider, ByteSlider, BurstSlider, MemorySlider, TimeSlider };
        String[] titles = { "Quantum [1ms - 3000ms]", "Byte [1B-3000B]", "BurstTime [1Q - 10Q]",
                "MemorySize: [1B - 10B]", "TimeBetweenProcesses: [1Q - 10Q]" };

        for (int i = 0; i < sliders.length; i++) {
            JLabel title = new JLabel(titles[i]);
            sliders[i].setMajorTickSpacing(20);
            sliders[i].setMinorTickSpacing(5);
            sliders[i].setPaintTicks(false);
            sliders[i].setPaintLabels(false);
            grid.add(title);
            grid.add(sliders[i]);
        }

        this.add(grid);
    }

    class WaitingListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            Running.io = !Running.io;
        }
    }

    class QuantumSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            OS.QUANTUM = (short) QuantumSlider.getValue();
        }
    }

    class ByteSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            OS.BYTE = (short) ByteSlider.getValue();
        }
    }

    class BurstSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            OS.MIN_BURST = (short) BurstSlider.getValue();
        }
    }

    class MemorySliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            OS.MIN_MEMORY = (short) MemorySlider.getValue();
        }
    }

    class TimeSliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            OS.MIN_TIME = (short) TimeSlider.getValue();
        }
    }
}
