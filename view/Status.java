package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Status extends JTextArea {
    public Status(String info, int r, int g, int b, int size) {
        super(info);
        this.addArialFont(size);
        this.setOpaque(true);
        this.setEditable(false);
        this.addBackground(r, g, b);
        this.addBorder();
    }

    private void addArialFont(int size) {
        this.setFont(new Font("WindowsLookAndFeel", Font.BOLD, size));
    }

    private void addBackground(int r, int g, int b) {
        this.setBackground(new Color(r, g, b));
    }

    private void addBorder() {
        this.setBorder(new EmptyBorder(6, 2, 6, 2));
    }
}
