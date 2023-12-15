package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.RunningManager;
import controller.StatesManager;

public class Window extends JFrame {
    private StatesManager Hold;
    private StatesManager Fail;
    private StatesManager Ready;
    private StatesManager Finished;
    private RunningManager Running;
    private Button IO;

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

        IO.addActionListener(new WaitingListener());

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void initComponenets() {
        Hold.stack = new JPanel();
        Fail.stack = new JPanel();
        Ready.stack = new JPanel();
        Finished.stack = new JPanel();
        Running.box = new JPanel();
        IO = new Button("I/O");
    }

    public void addStatusTitles() {
        JPanel grid = new JPanel(new GridLayout(1, 4));

        grid.setSize(860, 30);
        grid.setLocation(20, 20);

        String[] titles = { Hold.getTitle(), Fail.getTitle(), Ready.getTitle(), Finished.getTitle() };

        for (String title : titles) {
            JLabel caption = new JLabel(title);
            caption.setHorizontalAlignment(SwingConstants.CENTER);
            caption.setFont(new Font("Arial", Font.BOLD, 16));
            grid.add(caption);
        }

        this.add(grid);
    }

    public void addStatusStacks() {
        JPanel grid = new JPanel(new GridLayout(1, 4, 14, 0));

        grid.setSize(860, 500);
        grid.setLocation(20, 50);

        JPanel[] stacks = { Hold.stack, Fail.stack, Ready.stack, Finished.stack };

        for (JPanel stack : stacks) {
            stack.setLayout(new BoxLayout(stack, BoxLayout.Y_AXIS));
            grid.add(new JScrollPane(stack));
        }

        this.add(grid);
    }

    public void addRunningState() {
        JLabel caption = new JLabel(Running.getTitle());
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        caption.setFont(new Font("Arial", Font.BOLD, 16));
        caption.setSize(200, 30);
        caption.setLocation(970, 20);
        this.add(caption);

        JPanel grid = new JPanel(new BorderLayout());
        Running.box.setLayout(new BorderLayout());
        grid.add(new JScrollPane(Running.box));
        grid.setSize(340, 160);
        grid.setLocation(910, 50);
        this.add(grid);

        this.add(IO);
        Running.time = new JLabel();
        Running.time.setOpaque(true);
        Running.time.setBackground(Color.LIGHT_GRAY);
        Running.time.setBorder(new EmptyBorder(2, 2, 2, 2));
        this.add(Running.time);

        grid.add(IO, BorderLayout.AFTER_LINE_ENDS);
        grid.add(Running.time, BorderLayout.AFTER_LAST_LINE);
    }

    class WaitingListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            Running.io = !Running.io;
        }
    }
}
