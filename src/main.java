import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientUser currentUser = null;                              // 현재 로그인된 회원

        ClientUserList userList = new ClientUserList();             // 회원가입된 Client User 들을 담는 Client List 객체
        RestaurantList restaurantList = new RestaurantList();       // 식당 등록된 Restaurant 들을 담는 Restaurant List 객체

        //////////////////////////////////
        /*      임의의 식당 삽입         */
        //////////////////////////////////

        Restaurant rest1 = new Restaurant("rest1", "서울시 성북구", "02-1111-1111", "한식");
        rest1.addMenu(new Menu("비빔밥", 8000));

        Restaurant rest2 = new Restaurant("rest2", "서울시 동대문구", "02-2222-2222", "중식");
        rest2.addMenu(new Menu("짜장면", 5000));
        rest2.addMenu(new Menu("짬뽕", 6000));

        Restaurant rest3 = new Restaurant("rest3", "서울시 성동구", "02-3333-3333", "일식");
        rest3.addMenu(new Menu("초밥", 10000));

        restaurantList.addRestaurant(rest1);
        restaurantList.addRestaurant(rest2);
        restaurantList.addRestaurant(rest3);

        /////////////////////////////////
        /*      임의의 식당 삽입        */
        /////////////////////////////////

        
        /////////////////////////////////
        /*      임의의 회원 삽입        */
        /////////////////////////////////

        userList.appendUser(new ClientUser("1111", "1111", "서울시 성북구"));
        userList.appendUser(new ClientUser("2222", "2222", "서울시 중구"));

        /////////////////////////////////
        /*      임의의 회원 삽입        */
        /////////////////////////////////


        // 프로그램 시작
        System.out.println("\n---------- 먹픽! ----------\n");

        while (true) {
            String type;

            System.out.println("## 원하는 동작을 입력해주세요 ##\n");
            System.out.println("- 회원가입 : sign");
            System.out.println("- 로그인 : login");
            System.out.println("- 로그아웃 : logout");
            System.out.println("- 내 맛집 리스트 보기 : viewList");
            System.out.println("- 식당 정보 보기 : view\n");
            System.out.print("type : ");
            type = scanner.nextLine();

            // Use Case 1 : 회원가입
            if (type.equals("sign")) {
                if (userList.signIn()) {        // 회원가입 성공
                    System.out.println("\n---------- 정상적으로 가입되었습니다! ---------\n");
                } 
                else {                          // 회원가입 실패
                    System.out.println("\n---------- 이미 존재하는 아이디입니다. ----------\n");
                }

                System.out.println("\n----------------------------");
            }

            // Use Case 2 : 로그인
            else if (type.equals("login")) {
                while (currentUser == null) {    // 로그인 실패시 성공할 때까지 계속 로그인 시도
                    currentUser = userList.login();
                }
                
                // 로그인 성공
                System.out.println("로그인 성공! : " + currentUser.getID() + "님, 환영합니다.");

                System.out.println("\n----------------------------");
            }

            // 로그아웃
            else if (type.equals("logout")) {
                System.out.println("\n----------------------------");
                currentUser = null;         // 로그인된 회원을 없앰
                System.out.println("로그아웃 되었습니다.");
                System.out.println("\n----------------------------");
            }

            // Use Case 7 : 맛집 리스트 보기
            else if (type.equals("viewList")) {
                if (currentUser == null) {
                    System.out.println("\n-------- 로그인을 먼저 해 주세요 --------\n");
                    continue;
                }
                currentUser.viewFavoriteList();
            }

            // Use Case 9 : 식당 정보 보기 + Use Case 12, 4, 8
            else if (type.equals("view")) {

                if (currentUser == null) {
                    System.out.println("-------- 로그인을 먼저 해 주세요 --------");
                    continue;
                }

                System.out.print("식당 이름 입력 : ");
                String rest = scanner.nextLine();

                // Restaurant List 에서 입력된 식당 검색
                Restaurant currRestaurant = restaurantList.findRestaurant(rest);
                if (currRestaurant != null) {
                    // 식당 발견 -> 식당 정보 출력
                    currRestaurant.viewInfo();

                    // Use Case 12 :  별점, 리뷰 보기
                    System.out.print("식당의 별점과 리뷰도 확인하시겠습니까?(Y/N) : ");
                    String answer = scanner.nextLine();
                    
                    // Y 또는 N 이외의 답이 들어오는 경우
                    while (!answer.equals("Y") && !answer.equals("N")) {
                        System.out.print("Y 또는 N 을 입력해주세요. \n식당의 별점과 리뷰도 확인하시겠습니까?(Y/N) : ");
                        answer = scanner.nextLine();
                    }

                    if(answer.equals("Y")){
                        // 리뷰 보기
                        currRestaurant.viewReview();
                        
                        // Use Case 4 : 별점, 리뷰 작성
                        System.out.print("식당에 리뷰를 작성하시겠습니까?(Y/N) : ");
                        answer = scanner.nextLine();

                        // Y 또는 N 이외의 답이 들어오는 경우
                        while (!answer.equals("Y") && !answer.equals("N")) {
                            System.out.println("Y 또는 N 을 입력해주세요. \n식당의 별점과 리뷰도 확인하시겠습니까?(Y/N) : ");
                            answer = scanner.nextLine();
                        }

                        if(answer.equals("Y")){
                            // 리뷰 작성
                            currRestaurant.writeReview();
                        }
                    }

                    // Use Case 8 : 맛집 리스트 등록하기
                    System.out.print("내 맛집 리스트에 등록하시겠습니까?(Y/N) : ");
                    answer = scanner.nextLine();
                    
                    // Y 또는 N 이외의 답이 들어오는 경우
                    while (!answer.equals("Y") && !answer.equals("N")) {
                        System.out.print("Y 또는 N 을 입력해주세요. \n내 맛집 리스트에 등록하시겠습니까?(Y/N) : ");
                        answer = scanner.nextLine();
                    }

                    if (answer.equals("Y")) {
                        currentUser.addRestaurant(currRestaurant);
                        currentUser.viewFavoriteList();
                    }
                    
                }
                else {
                    // 식당 존재 X
                    System.out.println("\n-------- 없는 식당입니다. --------");
                }
            }

            // Use Case 10 : 식당 업체 등록

            // Use Case 11 : 식당 정보 수정 & 삭제

            // Use Case 3 : 내 정보 관리

            // Use Case 5 : 검색 + Use Case 6 : 필터

            else {
                System.out.println("\n---------- 정해진 동작을 입력해주세요 ---------- \n");
            }
        }
    }
}