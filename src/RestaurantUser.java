
import java.util.Scanner;

/**#############레스토랑 고유 id 추가할것
 * 
 */
public class RestaurantUser extends User {

    Scanner sc = new Scanner(System.in);

    public String name;
    public Restaurant myRestaurant;
    
    public RestaurantUser(String id, String pw, String add, String name) {
        super(id, pw, add);
        this.name = name;
        this.myRestaurant = null;
    }

    public RestaurantUser(String id, String pw, String add) {
        super(id, pw, add);
        this.name = null;
        this.myRestaurant = null;
    }

    
    public Restaurant create_restuarant() {                               /*식당정보git 등록(메뉴는 restaurant 클래스에 포함)*/
        String restaurantName;
        String address;
        String phoneNumber;
        String category;

        System.out.println("식당이름: ");
        restaurantName = sc.nextLine();
        System.out.println("주소: ");
        address = sc.nextLine();
        System.out.println("전화번호: ");
        phoneNumber = sc.nextLine();
        System.out.println("카테고리(한식, 양식, ...): ");
        category = sc.nextLine();

        Restaurant rest = new Restaurant(restaurantName, address, phoneNumber, category);
        this.myRestaurant = rest;

        return rest;
    }

    /**
     * 
     */
    public void updateInfo() {                                           /*식당정보 수정 */
        if (myRestaurant == null) {
            System.out.println("아직 등록된 식당이 없습니다.");
        }
        else{
            System.out.print("새 이름: ");
            myRestaurant.name = sc.nextLine();

            System.out.print("새 주소: ");
            myRestaurant.address = sc.nextLine();

            System.out.print("새 전화번호: ");
            myRestaurant.phoneNumber = sc.nextLine();

            System.out.print("새 카테고리(한식, 양식,...): ");
            myRestaurant.category = sc.nextLine();

            System.out.println("정보 수정 완료!");
        } 
    }
}

    
