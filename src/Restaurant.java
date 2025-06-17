import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant {

    public ArrayList<Menu> menuList; //메뉴들 리스트
    public ArrayList<Review> reviews; //리뷰들 리스트

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
        System.out.println("\n---------- 식당 정보 ----------\n");
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

        System.out.println("\n--------------------\n");
    }

    public void viewReview() { //식당리뷰들 보기 메소드

        System.out.println("\n---------- 리뷰 -----------\n");

        if (reviews.isEmpty()) {        //reviews 가 비어있어서 리뷰가 없을때
            System.out.println("리뷰가 없습니다.");
        }else{                          //reviews 를 순회하며 별점, 리뷰 출력
            for(int i=0; i<reviews.size(); i++){
                System.out.println("별점: " + reviews.get(i).starRating +", 리뷰: "+ reviews.get(i).review);   /*별점, 리뷰 출력 */
            }
        }

        System.out.println("\n----------------------------\n");
    }

    public void writeReview() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("별점을 입력해주세요(0 ~ 5) : ");
        float rating = Float.parseFloat(scanner.nextLine());
        while (rating < 0 || rating > 5) {
            System.out.print("0에서 5 사이의 값을 입력해주세요 : ");
            rating = Float.parseFloat(scanner.nextLine());
        }

        System.out.print("리뷰를 작성해주세요 : ");
        String comment = scanner.nextLine();


        Review review = new Review(rating, comment);
        this.reviews.add(review);

        System.out.println("\n----------------------------\n");
    }

    public void addMenu(Menu m) {
        menuList.add(m);
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public String getCategory() {
        return this.category;
    }
}