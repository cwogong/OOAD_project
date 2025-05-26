import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientUser currentUser = null;

        ClientUserList userList = new ClientUserList();
        RestaurantList restaurantList = new RestaurantList();


        /*      임의의 식당 삽입         */
        
        restaurantList.addRestaurant(new Restaurant("rest1", "서울시 성북구", "02-1111-1111", "한식"));
        restaurantList.addRestaurant(new Restaurant("rest2", "서울시 동대문구", "02-2222-2222", "중식"));
        restaurantList.addRestaurant(new Restaurant("rest3", "서울시 성동구", "02-3333-3333", "일식"));
        
        /*      임의의 식당 삽입        */

        /*      임의의 회원 삽입        */

        userList.appendUser(new ClientUser("1111", "1111", "서울시"));

        while (true) {
            String type;

            System.out.print("type : ");
            type = scanner.nextLine();

            // Use Case 1 : 회원가입
            if (type.equals("sign")) {
                if (userList.signIn()) {    
                    // 회원가입 성공
                    userList.print();
                } 
                else {      
                    // 회원가입 실패
                    System.out.println("이미 존재하는 아이디입니다.");
                }
            }

            // Use Case 2 : 로그인
            else if (type.equals("login")) {
                while (currentUser == null) {
                    // 로그인 실패, 다시 시도
                    currentUser = userList.login();
                }
                // 로그인 성공
                System.out.println("로그인 성공! : " + currentUser.getID() + "님, 환영합니다.");
            }

            // 로그아웃
            else if (type.equals("logout")) {
                currentUser = null;
                System.out.print("로그아웃 되었습니다.");
            }

            // Use Case 3 : 내 정보 관리 -> 추후 구현 예정

            // Use Case 5, 6 : 검색하기, 필터 적용 -> 추후 구현 예정

            // Use Case 7 : 맛집 리스트 보기
            else if (type.equals("viewList")) {
                // if (currentUser == null) {
                //     System.out.print("로그인을 먼저 해 주세요");
                //     continue;
                // }
                currentUser.viewFavoriteList();
            }

            // Use Case 8 : 맛집 리스트 등록
            else if (type.equals("8")) {
                currentUser.addRestaurant(restaurantList.findRestaurant("rest1"));
                //currentUser.viewFavoriteList();
            }

            // Use Case 9, 12, 4 : 식당 정보 보기 + 별점, 리뷰 보기 -> 별점, 리뷰 작성
            else if (type.equals("view")) {

                // if (currentUser == null) {
                //     System.out.print("로그인을 먼저 해 주세요");
                //     continue;
                // }

                System.out.print("식당 이름 입력 : ");
                String rest = scanner.nextLine();

                Restaurant currRestaurant = restaurantList.findRestaurant(rest);
                if (currRestaurant != null) {
                    // 식당 발견
                    currRestaurant.viewInfo();
                }
                else {
                    // 식당 존재 X
                    System.out.print("없는 식당입니다.\n");
                }
            }

            // Use Case 10 : 식당 업체 등록

            // Use Case 11 : 식당 정보 수정 & 삭제

            // Case : 
            else if (true) {
                
            }


        }
    }
}