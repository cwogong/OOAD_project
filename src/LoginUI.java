import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField pwField;
    private JButton loginButton;
    private JButton signinButton;

    public LoginUI() {
        frame = new JFrame("로그인");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        idField = new JTextField();
        pwField = new JPasswordField();
        loginButton = new JButton("로그인");
        signinButton = new JButton("회원가입");

        frame.add(new JLabel("아이디:"));
        frame.add(idField);
        frame.add(new JLabel("비밀번호:"));
        frame.add(pwField);
        frame.add(signinButton);
        frame.add(loginButton);

        frame.setVisible(true);
    }

    // 아이디 입력값 가져오기
    public String getUserId() {
        return idField.getText();
    }

    // 비밀번호 입력값 가져오기
    public String getPassword() {
        return new String(pwField.getPassword());
    }

    // 로그인 버튼에 ActionListener 붙이기
    public void setLoginAction(Runnable action) {
        loginButton.addActionListener(e -> action.run());
    }

    // 로그인 버튼에 ActionListener 붙이기
    public void setSigninAction(Runnable action) {
        signinButton.addActionListener(e -> action.run());
    }

    // 창 닫기
    public void close() {
        frame.dispose();
    }
}
