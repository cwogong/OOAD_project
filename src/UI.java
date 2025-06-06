import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

public class UI {
    JFrame frame;
    JPanel mainPanel;

    public UI() {
        frame = new JFrame("main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        
        loginUI();

        frame.setVisible(true);
    }

    private void mainUI() {

    }

    private void loginUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(200, 30, 200, 30));
        mainPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JPanel idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel pwPanel = new JPanel();
        pwPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField idField = new JTextField(20);
        JPasswordField pwField = new JPasswordField(20);
        JButton backBtn = new JButton("뒤로 가기");

        idPanel.add(new JLabel("ID:"));
        idPanel.add(idField);
        
        pwPanel.add(new JLabel("PW:"));
        pwPanel.add(pwField);

        mainPanel.add(new JPanel());
        mainPanel.add(idPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(pwPanel);
        mainPanel.add(backBtn);
        mainPanel.add(new JPanel());

        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }
    
    public static void main(String[] args) {
        new UI();
    }
}
