import java.util.ArrayList;

public class UI {
    LoginUI loginUI;
    MainUI mainUI;
    public static void main(String[] args) {
        UI ui = new UI();
        ClientUser currentUser[] = new ClientUser[1];                              // 현재 로그인된 회원
        RestaurantUser currentRestUser = null;                          // 현재 로그인된 식당

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

        {   // 로그인 화면
            ui.loginUI = new LoginUI();

            ui.loginUI.setSigninAction(() -> {
                
            });

            ui.loginUI.setLoginAction(() -> {
                String id = ui.loginUI.getUserId();
                String pw = ui.loginUI.getPassword();
                                
                currentUser[0] = userList.login(id, pw);

                // 로그인 성공
                if (currentUser[0] != null) {
                    System.out.println("로그인 성공! : " + currentUser[0].getID() + "님, 환영합니다.");
                    ui.loginUI.close();
                    ui.mainUI = new MainUI();
                }
            });
        }

        {   // 메인 화면
            
        }
        
            
            
    }
}