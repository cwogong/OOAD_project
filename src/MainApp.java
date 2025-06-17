import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

// ====================== MainApp (실행 진입점) ======================
public class MainApp {
    public static void main(String[] args) {

        ClientUser currentUser = null;                              // 현재 로그인된 회원
        RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

        ClientUserList userList = new ClientUserList();             // 회원가입된 Client User 들을 담는 Client List 객체
        RestaurantUserList restUserList = new RestaurantUserList(); // 회원가입된 Restaurent User 들을 담는 Client List 객체
        RestaurantList restaurantList = new RestaurantList();       // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

        //////////////////////////////////
        /*      임의의 식당 삽입         */
        //////////////////////////////////

        Restaurant rest1 = new Restaurant("rest1", "서울시 성북구", "02-1111-1111", "Korean");
        rest1.addMenu(new Menu("비빔밥", 8000));

        Restaurant rest2 = new Restaurant("rest2", "서울시 동대문구", "02-2222-2222", "Chinese");
        rest2.addMenu(new Menu("짜장면", 5000));
        rest2.addMenu(new Menu("짬뽕", 6000));

        Restaurant rest3 = new Restaurant("rest3", "서울시 성동구", "02-3333-3333", "Japanese");
        rest3.addMenu(new Menu("초밥", 10000));

        Restaurant rest4 = new Restaurant("rest4", "서울시 중랑구", "02-4444-4444", "Western");
        rest4.addMenu(new Menu("스테이크", 15000));

        restaurantList.addRestaurant(rest1);
        restaurantList.addRestaurant(rest2);
        restaurantList.addRestaurant(rest3);
        restaurantList.addRestaurant(rest4);

        /////////////////////////////////
        /*      임의의 식당 삽입        */
        /////////////////////////////////
        
        ArrayList<String> Categories = new ArrayList<>();           // 식당의 카테고리 종류
        Categories.add("Korean");
        Categories.add("Chinese");
        Categories.add("Japanese");
        Categories.add("Western");

        
        /////////////////////////////////
        /*      임의의 회원 삽입        */
        /////////////////////////////////

        userList.appendUser(new ClientUser("1111", "1111", "서울시 성북구"));
        userList.appendUser(new ClientUser("2222", "2222", "서울시 중구"));

        /////////////////////////////////
        /*      임의의 회원 삽입        */
        /////////////////////////////////


        SwingUtilities.invokeLater(() -> {
            LoginUI loginUI = new LoginUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
        });
    }
}

// ====================== Login UI ======================
class LoginUI {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField pwField;
    private JButton loginButton;
    private JButton signinButton;
    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public LoginUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("로그인");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        idField = new JTextField();
        pwField = new JPasswordField();
        signinButton = new JButton("회원가입");
        loginButton = new JButton("로그인");

        frame.add(new JLabel("아이디:"));
        frame.add(idField);
        frame.add(new JLabel("비밀번호:"));
        frame.add(pwField);
        frame.add(signinButton);
        frame.add(loginButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginButton.addActionListener(e -> login());
        signinButton.addActionListener(e -> signin());
    }

    public void login() {
        String id = getUserId();
        String pw = getPassword();
        currentUser = userList.login(id, pw);
        if (currentUser != null) {
            JOptionPane.showMessageDialog(frame, "로그인 성공!");
            close();
            new MainUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
        } else {
            JOptionPane.showMessageDialog(frame, "로그인 실패!");
        }
    }

    public void signin() {
        close();
        new SigninUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
    }

    public String getUserId() {
        return idField.getText();
    }

    public String getPassword() {
        return new String(pwField.getPassword());
    }

    public void setLoginAction(Runnable action) {
        loginButton.addActionListener(e -> action.run());
    }

    public void close() {
        frame.dispose();
    }
}

class SigninUI {
    private JFrame frame;
    private JTextField idField;
    private JPasswordField pwField;
    private JPasswordField pw2Field;
    private JTextField addField;
    private JButton signinButton;
    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public SigninUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("회원가입");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));

        idField = new JTextField();
        pwField = new JPasswordField();
        pw2Field = new JPasswordField();
        addField = new JTextField();

        signinButton = new JButton("회원가입");

        frame.add(new JLabel("아이디 :"));
        frame.add(idField);

        frame.add(new JLabel("비밀번호:"));
        frame.add(pwField);

        frame.add(new JLabel("비밀번호 재입력:"));
        frame.add(pw2Field);

        frame.add(new JLabel("주소:"));
        frame.add(addField);

        frame.add(new JLabel(""));
        frame.add(signinButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        signinButton.addActionListener(e -> signin());
    }

    public void signin() {
        String id = getUserId();
        String pw = getPassword();
        String pw2 = getPassword();
        String add = getAddress();

        if (id.isEmpty() || pw.isEmpty() || pw2.isEmpty() || add.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "정보를 입력하여주세요!");
        }
        else if (userList.signIn(id, pw, pw2, add)) {
            
            JOptionPane.showMessageDialog(frame, "회원가입 성공!");
            close();
            new LoginUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
        }
            
        else {
            JOptionPane.showMessageDialog(frame, "회원가입 실패!");
        }
    }

    public String getUserId() {
        return idField.getText();
    }

    public String getPassword() {
        return new String(pwField.getPassword());
    }

    public String getPassword2() {
        return new String(pw2Field.getPassword());
    }

    public String getAddress() {
        return addField.getText();
    }

    public void close() {
        frame.dispose();
    }
}

// ====================== Main Menu UI ======================
class MainUI {
    private JFrame frame;
    private JPanel useCasePanel;
    private JPanel dynamicPanel;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public MainUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("메인 메뉴");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        useCasePanel = new JPanel(new FlowLayout());
        //useCasePanel.add(new JLabel)
        String[] useCases = { "식당 검색", "회원 정보 조회", "로그아웃" };

        for (String useCase : useCases) {
            JButton button = new JButton(useCase);
            button.addActionListener(e -> showUIForUseCase(useCase));
            useCasePanel.add(button);
        }

        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS));

        frame.add(useCasePanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(dynamicPanel), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showUIForUseCase(String useCase) {
        dynamicPanel.removeAll();

        switch (useCase) {
            case "식당 검색":
                new SearchRestaurantUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
                break;

            case "회원 정보 조회":
                dynamicPanel.add(new JLabel("회원 ID:"));
                dynamicPanel.add(new JTextField(15));
                dynamicPanel.add(new JButton("조회"));
                break;

            case "로그아웃":
                frame.dispose();
                new LoginUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
                return;
        }

        dynamicPanel.revalidate();
        dynamicPanel.repaint();
    }
}

// ====================== Search Restaurant UI ======================
class SearchRestaurantUI {
    private JFrame frame;
    private JTextField nameField;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public SearchRestaurantUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("식당 검색");
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당 검색", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(new JLabel("식당 이름:"));
        nameField = new JTextField();
        centerPanel.add(nameField);
        frame.add(centerPanel, BorderLayout.CENTER);

        JButton searchButton = new JButton("입력");
        searchButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            Restaurant rest = restaurantList.findRestaurant(name);
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "식당 이름을 입력하세요.");
                
            } else if (rest == null) {
                JOptionPane.showMessageDialog(frame, "없는 식당입니다.");
            } else {
                frame.dispose();
                new RestaurantListUI(currentUser, userList, currentRestUser, restUserList, restaurantList, rest);
            }
        });

        frame.add(searchButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// ====================== Restaurant List UI ======================
class RestaurantListUI {
    private JFrame frame;
    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public RestaurantListUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;
        
        frame = new JFrame("식당 목록");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 16));
        frame.add(title, BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel restaurantName = new JLabel(rest.getName());
        JButton infoButton = new JButton("정보보기");

        infoButton.addActionListener(e -> {
            frame.dispose();
            new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, rest);
        });

        listPanel.add(restaurantName);
        listPanel.add(infoButton);

        frame.add(listPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

// ====================== Restaurant Info UI ======================
class RestaurantInfoUI {
    private JFrame frame;
    private JButton viewRiviewButton;
    private JButton writeRiviewButton;
    private JButton addFavoriteButton;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public RestaurantInfoUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("식당 정보");
        frame.setSize(350, 250);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당 정보", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        infoPanel.add(new JLabel("식당 이름: " + rest.getName()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("주소: " + rest.getAddress()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("전화번호: " + rest.getPhoneNumber()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("카테고리: " + rest.getCategory()));
        infoPanel.add(Box.createVerticalStrut(10));
        //infoPanel.add(new JLabel("평점: " + rest.));

        frame.add(infoPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class UserInfoUI {
    private JFrame frame;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public UserInfoUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("회원 정보");
        frame.setSize(350, 250);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당 정보", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        infoPanel.add(new JLabel("식당 이름: " + rest.getName()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("주소: " + rest.getAddress()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("전화번호: " + rest.getPhoneNumber()));
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(new JLabel("카테고리: " + rest.getCategory()));
        infoPanel.add(Box.createVerticalStrut(10));
        //infoPanel.add(new JLabel("평점: " + rest.));

        frame.add(infoPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}