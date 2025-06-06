import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public static void main(String[] args) {
        // JFrame 생성
        JFrame frame = new JFrame("Panel Switch Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 패널 생성
        JPanel loginPanel = createLoginPanel(frame);
        JPanel mainPanel = createMainPanel(frame);

        // 초기 패널 설정
        frame.setContentPane(loginPanel);
        frame.setVisible(true);
    }

    // 로그인 패널 생성
    private static JPanel createLoginPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("아이디:");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("비밀번호:");
        JPasswordField passField = new JPasswordField(20);
        JButton loginButton = new JButton("로그인");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passLabel);
        panel.add(passField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);

        // 로그인 버튼 클릭 시 메인 화면으로 전환
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(createMainPanel(frame));
                frame.revalidate(); // 레이아웃 재검증
                frame.repaint();    // 화면 갱신
            }
        });

        return panel;
    }

    // 메인 화면 패널 생성
    private static JPanel createMainPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("환영합니다!");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton logoutButton = new JButton("로그아웃");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(logoutButton);

        // 로그아웃 버튼 클릭 시 로그인 화면으로 전환
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(createLoginPanel(frame));
                frame.revalidate(); // 레이아웃 재검증
                frame.repaint();    // 화면 갱신
            }
        });

        return panel;
    }
}
