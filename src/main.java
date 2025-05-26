import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        UserList userList = new UserList();

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

            // Use Case 3 : 내 정보 관리

            // Use Case 4 : 별점, 리뷰 작성

            // Use Case 5, 6 : 검색하기, 필터 적용

            // Use Case 7 : 맛집 리스트 보기
            else if (type.equals("viewList")) {
                if (currentUser == null) {
                    System.out.print("로그인을 먼저 해 주세요");
                    continue;
                }
                
            }

            // Case : 
            else if (true) {
                
            }


        }
    }
}