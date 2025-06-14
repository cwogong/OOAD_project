import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI {
    public JFrame frame;

    public JPanel loginPanel;
    public JPanel signinPanel;
    public JPanel mainPanel;

    public void UI() {
        frame = new JFrame("muck pick");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setLocationRelativeTo(null);

        // 로그인 Panel
        loginPanel = new JPanel();
    }
}