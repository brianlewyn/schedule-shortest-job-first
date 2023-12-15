package controller;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Process;
import view.Status;

public class RunningManager {
    private String title;
    public JLabel time;
    public JPanel box;
    public boolean io;

    public RunningManager(String title) {
        this.title = title;
        this.io = true;
    }

    public String getTitle() {
        return this.title;
    }

    public void add(Process p) {
        box.removeAll();
        box.add(new Status(p.toVertical(), p.getRed(), p.getGreen(), p.getBlue(), 18));
        box.revalidate();
        box.repaint();
    }

    public void updateProgress(short burstTime) {
        time.setText("Time: " + burstTime);
        box.revalidate();
        box.repaint();
    }
}
