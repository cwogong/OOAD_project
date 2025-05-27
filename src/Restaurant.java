import java.util.*;

public class Restaurant {

    private ArrayList<Menu> menuList; //메뉴들 리스트
    private ArrayList<Review> reviews; //리뷰들 리스트

    public String name;
    public String address;
    public String phoneNumber;
    public String category;

    public Restaurant(String name, String address, String phoneNumber, String category) { //생성자
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.category = category;

        this.menuList = new ArrayList<>();              /*메뉴랑 리뷰 리스트 생성 */ 
        this.reviews = new ArrayList<>();
    }
    
    
    public void viewInfo() { //식당의 정보 보기 메소드
        System.out.println("식당이름: "+ name);
        System.out.println("주소: "+ address);
        System.out.println("전화번호: "+ phoneNumber);
        System.out.println("카테고리: "+ category);
        System.out.println("메뉴: ");
        
        if( menuList.isEmpty()){
            System.out.println("등록된 메뉴 없음");
        }else{ 
            for(int i=0; i<menuList.size();i++){                                 /*메뉴 출력 */
                System.out.println(" - "+menuList.get(i).food );
            }
        }
    }



    public void viewReview() { //식당리뷰들 보기 메소드
        if (reviews.isEmpty()) {
            System.out.println("리뷰가 없습니다.");
        }else{
            for(int i=0; i<reviews.size(); i++){
                System.out.println("별점: " + reviews.get(i).starRating +" 리뷰: "+ reviews.get(i).review);   /*별점, 리뷰 출력 */
            }
        }
    }

    public void addMenu(Menu m) {
        menuList.add(m);
    }

    public String getName() {
        return this.name;
    }
    
    public String getCategory() {
        return this.category;
    }
}