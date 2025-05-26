import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    // 유저 리스트
    public ArrayList<User> userList = new ArrayList<>();

    // 유저 리스트에 유저 추가
    public void appendUser(User user) {
        this.userList.add(user);
    }

    // 유저 검색 (for-each 문을 통해ID 일치 여부 확인)
    public User findUser(String id) {
        for (User user : this.userList) {
            if (user.getID().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // 이미 존재하는 아이디인지 확인
    public boolean isUsableID(String id) {
        for (User user : this.userList) {
            if (user.getID().equals(id)) {
                return false;
            }
        }
        return true;
    }

    // 유저 리스트 출력 (확인용, 실사용 X)
    public void print() {
        System.out.println("User list : ");
        for (User user : this.userList) {
            System.out.println(user.getID());
        }
    }

    // 회원가입
    public boolean signIn() {

        Scanner scanner = new Scanner(System.in);

        // id, password 입력 받음
        System.out.print("id : ");
        String id_ = scanner.nextLine();
        System.out.print("pw : ");
        String pw_ = scanner.nextLine();
        System.out.print("address : ");
        String add_ = scanner.nextLine();

        
        // 이미 존재하는 아이디인지 확인
        if (this.isUsableID(id_)) {
            // 회원가입 성공
            User user = new User(id_, pw_, add_);
            this.appendUser(user);
            return true;
        } else {
            // 이미 같은 아이디가 존재하는 경우
            return false;
        }
    }

    // 로그인
    public User login() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("id : ");
        String id_ = scanner.nextLine();
        System.out.print("pw : ");
        String pw_ = scanner.nextLine();

        User currentUser = this.findUser(id_);
        if (currentUser != null && currentUser.getPW().equals(pw_)) {
            return currentUser;
        }
        else {
            System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
            return null;
        }
    }

}