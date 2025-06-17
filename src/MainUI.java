import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainUI {
    private JFrame frame;
    private JTextField search;
    private JButton searchButton;
    private JButton favoriteButton;
    private JButton infoButton;
    private JButton enrollButton;

    public MainUI() {
        frame = new JFrame("메인 화면");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        search = new JTextField();
        searchButton = new JButton("검색");
        favoriteButton = new JButton("내 즐겨찾기");
        infoButton = new JButton("내 정보 관리");
        // loginButton = new JButton("로그인");
        // loginButton = new JButton("로그인");
        // loginButton = new JButton("로그인");

        frame.add(search);
        frame.add(searchButton);
        frame.add(favoriteButton);
        frame.add(infoButton);

        frame.setVisible(true);
    }

    // // 아이디 입력값 가져오기
    // public String getUserId() {
    //     return idField.getText();
    // }

    // // 비밀번호 입력값 가져오기
    // public String getPassword() {
    //     return new String(pwField.getPassword());
    // }

    // 로그인 버튼에 ActionListener 붙이기
    public void setLoginAction(Runnable action) {
        searchButton.addActionListener(e -> action.run());
    }

    // 창 닫기
    public void close() {
        frame.dispose();
    }
}
