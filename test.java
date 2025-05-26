import javax.swing.*;

public class test {
    public static void main(String[] args) {
        // 프레임 생성
        JFrame frame = new JFrame("로그인");

        // 라벨, 텍스트필드, 버튼 생성
        JLabel userLabel = new JLabel("아이디:");
        JTextField userText = new JTextField(15);

        JLabel passLabel = new JLabel("비밀번호:");
        JPasswordField passText = new JPasswordField(15);

        JButton loginButton = new JButton("로그인");
        JLabel messageLabel = new JLabel();

        // 로그인 버튼에 기능 추가
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            // 간단한 로그인 조건 (예: admin / 1234)
            if (username.equals("admin") && password.equals("1234")) {
                messageLabel.setText("로그인 성공!");
            } else {
                messageLabel.setText("아이디 또는 비밀번호가 틀렸습니다.");
            }
        });

        // 패널에 컴포넌트 추가
        JPanel panel = new JPanel();
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passLabel);
        panel.add(passText);
        panel.add(loginButton);
        panel.add(messageLabel);

        // 프레임에 패널 추가
        frame.add(panel);

        frame.setSize(250, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
