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

        Restaurant rest1 = new Restaurant("한식집", "서울시 성북구", "02-1111-1111", "한식");
        rest1.addMenu(new Menu("비빔밥", 8000));

        Restaurant rest2 = new Restaurant("중식집", "서울시 동대문구", "02-2222-2222", "중식");
        rest2.addMenu(new Menu("짜장면", 5000));
        rest2.addMenu(new Menu("짬뽕", 6000));

        Restaurant rest3 = new Restaurant("일식집", "서울시 성동구", "02-3333-3333", "일식");
        rest3.addMenu(new Menu("초밥", 10000));

        Restaurant rest4 = new Restaurant("양식집", "서울시 중랑구", "02-4444-4444", "양식");
        rest4.addMenu(new Menu("스테이크", 15000));

        restaurantList.addRestaurant(rest1);
        restaurantList.addRestaurant(rest2);
        restaurantList.addRestaurant(rest3);
        restaurantList.addRestaurant(rest4);

        /////////////////////////////////
        /*      임의의 식당 삽입        */
        /////////////////////////////////
        
        ArrayList<String> Categories = new ArrayList<>();           // 식당의 카테고리 종류
        Categories.add("한식");
        Categories.add("중식");
        Categories.add("일식");
        Categories.add("양식");

        
        /////////////////////////////////
        /*      임의의 회원 삽입        */
        /////////////////////////////////

        userList.appendUser(new ClientUser("1111", "1111", "서울시 성북구", 23, "먹짱"));
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
    private JTextField ageField;
    private JTextField nicknameField;
    private JButton signinButton;
    private JButton backButton;
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
        frame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 2));

        idField = new JTextField();
        pwField = new JPasswordField();
        pw2Field = new JPasswordField();
        addField = new JTextField();
        ageField = new JTextField();
        nicknameField = new JTextField();

        centerPanel.add(new JLabel("아이디 :"));
        centerPanel.add(idField);

        centerPanel.add(new JLabel("비밀번호:"));
        centerPanel.add(pwField);

        centerPanel.add(new JLabel("비밀번호 재입력:"));
        centerPanel.add(pw2Field);

        centerPanel.add(new JLabel("주소:"));
        centerPanel.add(addField);

        centerPanel.add(new JLabel("나이:"));
        centerPanel.add(ageField);

        centerPanel.add(new JLabel("닉네임:"));
        centerPanel.add(nicknameField);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));

        backButton = new JButton("뒤로가기");
        southPanel.add(backButton);
        signinButton = new JButton("회원가입");
        southPanel.add(signinButton, BorderLayout.SOUTH);

        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        backButton.addActionListener(e -> back());
        signinButton.addActionListener(e -> signin());
    }

    public void signin() {
        String id = getUserId();
        String pw = getPassword();
        String pw2 = getPassword();
        String add = getAddress();
        int age = getAge();
        String nickname = getNickName();

        if (id.isEmpty() || pw.isEmpty() || pw2.isEmpty() || add.isEmpty() || nickname.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "정보를 입력하여주세요!");
        }
        else if (userList.signIn(id, pw, pw2, add, age, nickname)) {
            
            JOptionPane.showMessageDialog(frame, "회원가입 성공!");
            close();
            new LoginUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
        }
            
        else {
            JOptionPane.showMessageDialog(frame, "회원가입 실패!");
        }
    }

    public void back() {
        close();
        new LoginUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
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

    public int getAge() {
        return Integer.parseInt(ageField.getText());
    }

    public String getNickName() {
        return nicknameField.getText();
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
        String[] useCases = { "식당 검색", "식당 필터", "맛집 리스트 보기", "내 정보 관리", "식당 정보 수정", "식당 등록", "로그아웃" };

        for (String useCase : useCases) {
            JButton button = new JButton(useCase);
            button.addActionListener(e -> showUIForUseCase(useCase));
            useCasePanel.add(button);
        }

        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new BoxLayout(dynamicPanel, BoxLayout.Y_AXIS));

        frame.add(useCasePanel, BorderLayout.CENTER);
        //frame.add(new JScrollPane(dynamicPanel), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showUIForUseCase(String useCase) {
        dynamicPanel.removeAll();

        switch (useCase) {
            case "식당 검색":
                new SearchRestaurantUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
                break;

            case "식당 필터":
                new FilterRestaurantUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
                break;

            case "맛집 리스트 보기":
                new RestaurantListUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentUser.favoriteList);
                break;

            case "내 정보 관리":
                new UserInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
                break;

            case "식당 정보 수정":
                JOptionPane.showMessageDialog(frame, "아직 개발중입니다.");
                break;

            case "식당 등록":
                JOptionPane.showMessageDialog(frame, "아직 개발중입니다.");
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
            RestaurantList restList = new RestaurantList();
            restList.addRestaurant(restaurantList.findRestaurant(name));
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "식당 이름을 입력하세요.");
                
            } else if (restList.restaurantList == null) {
                JOptionPane.showMessageDialog(frame, "없는 식당입니다.");
            } else {
                frame.dispose();
                new RestaurantListUI(currentUser, userList, currentRestUser, restUserList, restaurantList, restList);
            }
        });

        frame.add(searchButton, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class FilterRestaurantUI {
    private JFrame frame;
    private JTextField nameField;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    private RestaurantList selectRestList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public FilterRestaurantUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        frame = new JFrame("식당 검색");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당 검색", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(new JLabel("카테고리 (Korean, Chinese, Japanese, Western 중): "));
        nameField = new JTextField();
        centerPanel.add(nameField);
        frame.add(centerPanel, BorderLayout.CENTER);

        JButton searchButton = new JButton("입력");
        searchButton.addActionListener(e -> {
            String categ = nameField.getText().trim();
            selectRestList = restaurantList.filterRestaurant(categ);
            if (categ.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "카테고리를 입력해주세요");
                
            } else if (selectRestList == null) {
                JOptionPane.showMessageDialog(frame, "없는 식당입니다.");
            } else {
                frame.dispose();
                new RestaurantListUI(currentUser, userList, currentRestUser, restUserList, restaurantList, selectRestList);
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

    private RestaurantList selectRestList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public RestaurantListUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, RestaurantList resList) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;

        selectRestList = resList;
        
        frame = new JFrame("식당 목록");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("식당", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 16));
        frame.add(title, BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Restaurant rest : resList.restaurantList) {
            JLabel restaurantName = new JLabel(rest.getName());
            JButton infoButton = new JButton("정보보기");

            infoButton.addActionListener(e -> {
                frame.dispose();
                new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, rest);
            });

            listPanel.add(restaurantName);
            listPanel.add(infoButton);
        }

        frame.add(listPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class UserInfoUI {
    private JFrame frame;
    private JLabel idField;
    private JTextField addField;
    private JTextField ageField;
    private JTextField nicknameField;
    private JButton submitButton;
    private JButton backButton;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    public UserInfoUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;
        
        frame = new JFrame("유저 정보");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("유저 정보", SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 16));
        frame.add(title, BorderLayout.NORTH);

        idField = new JLabel();
        addField = new JTextField();
        ageField = new JTextField();
        nicknameField = new JTextField();

        idField.setText(currentUser.getID());
        addField.setText(currentUser.getaddress());
        ageField.setText(Integer.toString(currentUser.getAge()));
        nicknameField.setText(currentUser.getNickname());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2));

        centerPanel.add(new JLabel("아이디: "));
        centerPanel.add(idField);
        centerPanel.add(new JLabel("이름: "));
        centerPanel.add(nicknameField);
        centerPanel.add(new JLabel("주소: "));
        centerPanel.add(addField);
        centerPanel.add(new JLabel("나이: "));
        centerPanel.add(ageField);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 2));

        backButton = new JButton("뒤로 가기");
        submitButton = new JButton("저장");
        backButton.addActionListener(e -> back());
        submitButton.addActionListener(e -> submit());

        southPanel.add(backButton, BorderLayout.SOUTH);
        southPanel.add(submitButton, BorderLayout.SOUTH);

        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void back() {
        close();
        // new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    public void submit() {
        currentUser.manageInfo(nicknameField.getText(), Integer.parseInt(ageField.getText()), addField.getText());
        JOptionPane.showMessageDialog(frame, "작성되었습니다.");
        close();
    }

    public void close() {
        frame.dispose();
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

    private Restaurant currentRest;

    public RestaurantInfoUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;
        currentRest = rest;

        frame = new JFrame("식당 정보");
        frame.setSize(350, 400);
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

        viewRiviewButton = new JButton("리뷰 보기");
        writeRiviewButton = new JButton("리뷰 작성하기");
        addFavoriteButton = new JButton("내 맛집 리스트에 등록");

        infoPanel.add(viewRiviewButton);
        infoPanel.add(writeRiviewButton);
        infoPanel.add(addFavoriteButton);

        viewRiviewButton.addActionListener(e -> viewReview());
        writeRiviewButton.addActionListener(e -> writeReview());
        addFavoriteButton.addActionListener(e -> addFavorite());

        frame.add(infoPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void viewReview() {
        close();
        new ViewReviewUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    void writeReview() {
        close();
        new WriteReviewUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    void addFavorite() {
        currentUser.favoriteList.addRestaurant(currentRest);
        JOptionPane.showMessageDialog(frame, "등록되었습니다.");
        close();
        // new MainUI(currentUser, userList, currentRestUser, restUserList, restaurantList);
    }

    public void close() {
        frame.dispose();
    }
}

class ViewReviewUI {
    private JFrame frame;
    private JLabel reviewLabel;
    private JButton backButton;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    private Restaurant currentRest;

    public ViewReviewUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;
        currentRest = rest;

        frame = new JFrame("별점 보기");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel(currentRest.getName(), SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        String r = currentRest.viewReview();
        reviewLabel = new JLabel(r);
        
        backButton = new JButton("뒤로 가기");
        backButton.addActionListener(e -> back());

        frame.add(reviewLabel, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void back() {
        close();
        // new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    public void close() {
        frame.dispose();
    }
}

class WriteReviewUI {
    private JFrame frame;
    
    private JTextField ratingPanel;
    private JTextField commentLabel;
    private JButton submitButton;
    private JButton backButton;

    private ClientUser currentUser = null;                              // 현재 로그인된 회원
    private RestaurantUser currentRestUser = null;                      // 현재 로그인된 식당 회원

    private ClientUserList userList;                // 회원가입된 Client User 들을 담는 Client List 객체
    private RestaurantUserList restUserList;        // 회원가입된 Restaurent User 들을 담는 Client List 객체
    private RestaurantList restaurantList;          // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

    private Restaurant currentRest;

    public WriteReviewUI(ClientUser cu, ClientUserList ul, RestaurantUser ru, RestaurantUserList rul, RestaurantList rl, Restaurant rest) {
        userList = ul;
        currentUser = cu;
        currentRestUser = ru;
        restUserList = rul;
        restaurantList = rl;
        currentRest = rest;

        frame = new JFrame("별점 작성");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel(currentRest.getName(), SwingConstants.CENTER);
        title.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ratingPanel = new JTextField();
        commentLabel = new JTextField();
        
        centerPanel.add(new JLabel("별점(1 ~ 5)"));
        centerPanel.add(ratingPanel);
        centerPanel.add(new JLabel("리뷰"));
        centerPanel.add(commentLabel);

        JPanel southPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        submitButton = new JButton("저장");
        submitButton.addActionListener(e -> submit());
        backButton = new JButton("뒤로 가기");
        backButton.addActionListener(e -> back());

        southPanel.add(backButton);
        southPanel.add(submitButton);


        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void submit() {
        currentRest.writeReview(5.0f, commentLabel.getText());
        JOptionPane.showMessageDialog(frame, "작성되었습니다.");
        close();
        new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    public void back() {
        close();
        // new RestaurantInfoUI(currentUser, userList, currentRestUser, restUserList, restaurantList, currentRest);
    }

    public void close() {
        frame.dispose();
    }
}