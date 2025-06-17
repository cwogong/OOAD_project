import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
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

public class MainApp1 {
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
        
        
        SwingUtilities.invokeLater(() -> new AppFrame());
    }
}

class AppFrame {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private Map<String, Map<String, String>> restaurants = new LinkedHashMap<>();
    private Map<String, Map<String, String>> myFavorites = new LinkedHashMap<>();
    private Map<String, String> currentUserInfo = new LinkedHashMap<>();

    public AppFrame() {
        frame = new JFrame("Restaurant App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 650);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        initMockData();

        cardPanel.add(createLoginPanel(), "Login");
        cardPanel.add(createMainMenu(), "MainMenu");

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private void initMockData() {
        currentUserInfo.put("id", "user1");
        currentUserInfo.put("name", "홍길동");
        currentUserInfo.put("address", "서울시");
        currentUserInfo.put("phone", "010-1234-5678");

        Map<String, String> res1 = new HashMap<>();
        res1.put("name", "김밥천국");
        res1.put("address", "서울 강남구");
        res1.put("phone", "02-000-0000");
        res1.put("menu", "김밥, 떡볶이");
        res1.put("filter", "한식");
        restaurants.put("김밥천국", new HashMap<>(res1));

        Map<String, String> res2 = new HashMap<>();
        res2.put("name", "피자헛");
        res2.put("address", "서울 서초구");
        res2.put("phone", "02-111-1111");
        res2.put("menu", "피자, 파스타");
        res2.put("filter", "양식");
        restaurants.put("피자헛", new HashMap<>(res2));

        myFavorites.put("김밥천국", new HashMap<>(res1));
    }

    private JPanel createHeaderWithMainButton() {
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton mainBtn = new JButton("메인으로");
        mainBtn.addActionListener(e -> cardLayout.show(cardPanel, "MainMenu"));
        topPanel.add(mainBtn, BorderLayout.EAST);
        return topPanel;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JTextField idField = new JTextField();
        JPasswordField pwField = new JPasswordField();
        JButton loginBtn = new JButton("로그인");
        JButton registerBtn = new JButton("회원가입");

        panel.add(new JLabel("아이디:")); panel.add(idField);
        panel.add(new JLabel("비밀번호:")); panel.add(pwField);
        panel.add(loginBtn); panel.add(registerBtn);

        loginBtn.addActionListener(e -> {
            // 간단 로그인 처리 (검증 없이 바로 메인으로)
            cardLayout.show(cardPanel, "MainMenu");
        });
        registerBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "회원가입 기능은 준비 중입니다."));
        return panel;
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panel.add(createHeaderWithMainButton());

        JButton searchBtn = new JButton("식당 검색");
        JButton infoBtn = new JButton("내 정보 보기");
        JButton listBtn = new JButton("맛집 리스트 보기");
        JButton regBtn = new JButton("식당 등록");
        JButton updateBtn = new JButton("식당 정보 수정");

        searchBtn.addActionListener(e -> {
            cardPanel.add(createSearchPanel(), "Search");
            cardLayout.show(cardPanel, "Search");
        });
        infoBtn.addActionListener(e -> {
            cardPanel.add(createUserInfoPanel(), "UserInfo");
            cardLayout.show(cardPanel, "UserInfo");
        });
        listBtn.addActionListener(e -> {
            cardPanel.add(createMyListPanel(), "MyList");
            cardLayout.show(cardPanel, "MyList");
        });
        regBtn.addActionListener(e -> {
            cardPanel.add(createRestaurantForm(null, true), "RegRestaurant");
            cardLayout.show(cardPanel, "RegRestaurant");
        });
        updateBtn.addActionListener(e -> {
            cardPanel.add(createSearchPanelForEdit(), "EditRestaurantSearch");
            cardLayout.show(cardPanel, "EditRestaurantSearch");
        });

        panel.add(new JLabel("<html><h2 style='text-align:center;'>메인 메뉴</h2></html>", SwingConstants.CENTER));
        panel.add(searchBtn);
        panel.add(infoBtn);
        panel.add(listBtn);
        panel.add(regBtn);
        panel.add(updateBtn);
        return panel;
    }

    // -------------- 식당 검색 -------------------
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel top = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("검색");
        top.add(new JLabel("식당 이름:"));
        top.add(searchField);
        top.add(searchBtn);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        searchBtn.addActionListener(e -> {
            resultsPanel.removeAll();
            String keyword = searchField.getText().trim();
            if(keyword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.");
                return;
            }
            boolean found = false;
            for (String name : restaurants.keySet()) {
                if (name.contains(keyword)) {
                    found = true;
                    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    row.add(new JLabel(name));
                    JButton viewBtn = new JButton("조회");
                    viewBtn.addActionListener(ev -> {
                        cardPanel.add(createDetailPanel(name), "Detail_" + name);
                        cardLayout.show(cardPanel, "Detail_" + name);
                    });
                    row.add(viewBtn);
                    resultsPanel.add(row);
                }
            }
            if(!found) {
                resultsPanel.add(new JLabel("검색 결과가 없습니다."));
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultsPanel), BorderLayout.CENTER);
        return panel;
    }

    // 식당 상세정보 보기
    private JPanel createDetailPanel(String resName) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        Map<String, String> res = restaurants.get(resName);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new JLabel("<html><h2>식당 정보</h2></html>"));
        content.add(new JLabel("식당 이름: " + res.get("name")));
        content.add(new JLabel("주소: " + res.get("address")));
        content.add(new JLabel("전화번호: " + res.get("phone")));
        content.add(new JLabel("카테고리: " + res.get("filter")));
        content.add(new JLabel("메뉴: " + res.get("menu")));

        panel.add(content, BorderLayout.CENTER);
        return panel;
    }


    // -------------- 내 정보 보기 -------------------
    private JPanel createUserInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new JLabel("<html><h2>내 정보</h2></html>"));
        content.add(new JLabel("아이디: " + currentUserInfo.get("id")));
        content.add(new JLabel("이름: " + currentUserInfo.get("name")));
        content.add(new JLabel("주소: " + currentUserInfo.get("address")));
        content.add(new JLabel("전화번호: " + currentUserInfo.get("phone")));

        JButton modifyBtn = new JButton("수정하기");
        modifyBtn.addActionListener(e -> {
            cardPanel.add(createUserInfoEditPanel(), "UserInfoEdit");
            cardLayout.show(cardPanel, "UserInfoEdit");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(modifyBtn);

        panel.add(content, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // 내 정보 수정 화면 (한줄 라벨+입력창)
    private JPanel createUserInfoEditPanel() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(currentUserInfo.get("name"));
        JTextField addrField = new JTextField(currentUserInfo.get("address"));
        JTextField phoneField = new JTextField(currentUserInfo.get("phone"));

        form.add(makeLabeledField("이름:", nameField));
        form.add(makeLabeledField("주소:", addrField));
        form.add(makeLabeledField("전화번호:", phoneField));

        JButton saveBtn = new JButton("입력완료");
        saveBtn.addActionListener(e -> {
            currentUserInfo.put("name", nameField.getText().trim());
            currentUserInfo.put("address", addrField.getText().trim());
            currentUserInfo.put("phone", phoneField.getText().trim());
            JOptionPane.showMessageDialog(frame, "수정완료되었습니다.");
            cardPanel.add(createUserInfoPanel(), "UserInfo");
            cardLayout.show(cardPanel, "UserInfo");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(saveBtn);

        panel.add(form, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // -------------- 맛집 리스트 보기 -------------------
    private JPanel createMyListPanel() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(new JLabel("<html><h2>내 맛집 리스트</h2></html>"));

        if (myFavorites.isEmpty()) {
            content.add(new JLabel("등록된 맛집이 없습니다."));
        } else {
            for (String name : new ArrayList<>(myFavorites.keySet())) {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.add(new JLabel(name));
                JButton deleteBtn = new JButton("삭제");
                deleteBtn.addActionListener(e -> {
                    myFavorites.remove(name);
                    JOptionPane.showMessageDialog(frame, name + "이(가) 삭제되었습니다.");
                    cardPanel.add(createMyListPanel(), "MyList");
                    cardLayout.show(cardPanel, "MyList");
                });
                JButton viewBtn = new JButton("조회");
                viewBtn.addActionListener(e -> {
                    cardPanel.add(createDetailPanel(name), "Detail_" + name);
                    cardLayout.show(cardPanel, "Detail_" + name);
                });
                row.add(deleteBtn);
                row.add(viewBtn);
                content.add(row);
            }
        }

        JButton addBtn = new JButton("맛집 추가");
        addBtn.addActionListener(e -> {
            cardPanel.add(createSearchPanelForAddFavorite(), "SearchForAdd");
            cardLayout.show(cardPanel, "SearchForAdd");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);

        panel.add(new JScrollPane(content), BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // 맛집 추가 위한 식당 검색 (검색 결과 옆에 '등록' 버튼)
    private JPanel createSearchPanelForAddFavorite() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel top = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("검색");
        top.add(new JLabel("식당 이름:"));
        top.add(searchField);
        top.add(searchBtn);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        searchBtn.addActionListener(e -> {
            resultsPanel.removeAll();
            String keyword = searchField.getText().trim();
            if(keyword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.");
                return;
            }
            boolean found = false;
            for (String name : restaurants.keySet()) {
                if (name.contains(keyword)) {
                    found = true;
                    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    row.add(new JLabel(name));
                    JButton addBtn = new JButton("등록");
                    addBtn.addActionListener(ev -> {
                        if (myFavorites.containsKey(name)) {
                            JOptionPane.showMessageDialog(frame, "이미 등록된 맛집입니다.");
                        } else {
                            myFavorites.put(name, new HashMap<>(restaurants.get(name)));
                            JOptionPane.showMessageDialog(frame, name + "이(가) 맛집 리스트에 추가되었습니다.");
                            cardPanel.add(createMyListPanel(), "MyList");
                            cardLayout.show(cardPanel, "MyList");
                        }
                    });
                    row.add(addBtn);
                    resultsPanel.add(row);
                }
            }
            if(!found) {
                resultsPanel.add(new JLabel("검색 결과가 없습니다."));
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultsPanel), BorderLayout.CENTER);

        return panel;
    }

    // -------------- 식당 등록 -------------------
    private JPanel createRestaurantForm(String existingName, boolean isNew) {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField();
        JTextField addrField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField filterField = new JTextField();
        JTextField menuField = new JTextField();

        if (!isNew && existingName != null) {
            Map<String, String> res = restaurants.get(existingName);
            nameField.setText(res.get("name"));
            addrField.setText(res.get("address"));
            phoneField.setText(res.get("phone"));
            filterField.setText(res.get("filter"));
            menuField.setText(res.get("menu"));
        }

        form.add(makeLabeledField("식당 이름:", nameField));
        form.add(makeLabeledField("주소:", addrField));
        form.add(makeLabeledField("전화번호:", phoneField));
        form.add(makeLabeledField("카테고리:", filterField));
        form.add(makeLabeledField("메뉴:", menuField));

        JButton submitBtn = new JButton(isNew ? "등록하기" : "수정하기");
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String addr = addrField.getText().trim();
            String phone = phoneField.getText().trim();
            String filter = filterField.getText().trim();
            String menu = menuField.getText().trim();

            if (name.isEmpty() || addr.isEmpty() || phone.isEmpty() || filter.isEmpty() || menu.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "모든 필드를 입력해주세요.");
                return;
            }

            if (isNew) {
                if(restaurants.containsKey(name)) {
                    JOptionPane.showMessageDialog(frame, "이미 존재하는 식당 이름입니다.");
                    return;
                }
                Map<String, String> newRes = new HashMap<>();
                newRes.put("name", name);
                newRes.put("address", addr);
                newRes.put("phone", phone);
                newRes.put("filter", filter);
                newRes.put("menu", menu);
                restaurants.put(name, newRes);
                JOptionPane.showMessageDialog(frame, "식당이 등록되었습니다.");
                cardLayout.show(cardPanel, "MainMenu");
            } else {
                // 수정인 경우, 기존 key 이름 바꾸기 가능하도록 처리
                if (!name.equals(existingName) && restaurants.containsKey(name)) {
                    JOptionPane.showMessageDialog(frame, "수정하려는 식당 이름이 이미 존재합니다.");
                    return;
                }
                // 기존 데이터를 새 이름으로 옮기기
                Map<String, String> res = restaurants.remove(existingName);
                res.put("name", name);
                res.put("address", addr);
                res.put("phone", phone);
                res.put("filter", filter);
                res.put("menu", menu);
                restaurants.put(name, res);
                JOptionPane.showMessageDialog(frame, "식당 정보가 수정되었습니다.");
                cardPanel.add(createDetailPanel(name), "Detail_" + name);
                cardLayout.show(cardPanel, "Detail_" + name);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.add(submitBtn);

        panel.add(form, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        return panel;
    }

    // -------------- 식당 정보 수정 위한 검색 -------------------
    private JPanel createSearchPanelForEdit() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(createHeaderWithMainButton(), BorderLayout.NORTH);

        JPanel top = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("검색");
        top.add(new JLabel("수정할 식당 검색:"));
        top.add(searchField);
        top.add(searchBtn);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        searchBtn.addActionListener(e -> {
            resultsPanel.removeAll();
            String keyword = searchField.getText().trim();
            if(keyword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.");
                return;
            }
            boolean found = false;
            for (String name : restaurants.keySet()) {
                if (name.contains(keyword)) {
                    found = true;
                    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    row.add(new JLabel(name));
                    JButton editBtn = new JButton("조회");
                    editBtn.addActionListener(ev -> {
                        cardPanel.add(createRestaurantForm(name, false), "EditDetail_" + name);
                        cardLayout.show(cardPanel, "EditDetail_" + name);
                    });
                    row.add(editBtn);
                    resultsPanel.add(row);
                }
            }
            if(!found) {
                resultsPanel.add(new JLabel("검색 결과가 없습니다."));
            }
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultsPanel), BorderLayout.CENTER);

        return panel;
    }

    // ----------------- 유틸: 라벨 + 필드 한 줄 JPanel -----------------
    private JPanel makeLabeledField(String label, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return panel;
    }
}
