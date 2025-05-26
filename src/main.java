import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        UserList userList = new UserList();
        RestaurantList restaurantList = new RestaurantList();

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
                if (currentUser == null) {
                    System.out.print("로그인을 먼저 해 주세요");
                    continue;
                }
                currentUser.viewList();
            }

            // Use Case 8 : 맛집 리스트 등록

            // Use Case 9, 12, 4 : 식당 정보 보기 + 별점, 리뷰 보기 -> 별점, 리뷰 작성
            else if (type.equals("view")) {
                if (currentUser == null) {
                    System.out.print("로그인을 먼저 해 주세요");
                    continue;
                }

                System.out.print("식당 이름 입력 : ");
                String rest = scanner.nextLine();

                Restaurant restaurant = restaurantList.findRestaurant(rest);
                if (restaurant != null) {
                    // 식당 발견
                    restaurant.viewInfo();
                }
                else {
                    // 식당 존재 X
                    System.out.print("없는 식당입니다.");
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