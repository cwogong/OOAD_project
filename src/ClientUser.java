
import java.io.*;
import java.util.*;


public class ClientUser extends User { //user 클래스를 상속받는 client user 클래스


    public String nickname; // 사용자의 닉네임
    public int age; // 사용자의 나이
    public ArrayList<Restaurant> favoriteList;

	
    public ClientUser(String userID, String userPW, String address, String nickname, int age) { //생성자
    	super.(userID, userPW, address);
    	this.nickname = nickname;
    	this.age = age;
    	this.favoriteList = new ArrayList<>();
    }

    // Getter
    public String getNickname() {
    	return nickname;
    }
    public int getAge() {
    	return age;
    }
    
    
    public void manageInfo(String newNickname, int newAge, String newAddress) { // 정보 관리, 수정
       this.nickname =  newNickname;
       this.age = newAge;
       super.setAddress(newAddress);
       System.out.println("사용자 정보가 성공적으로 수정되었습니다.");
    }

    public Review writeReview(float star, String content) { // 리뷰 작성
    	Review review = new Review(star, content);
        System.out.println("리뷰 작성 완료");
        return review;
    }

    public Review editReview(Review review, float newStar, String newContent) { // 리뷰 수정
        review.setStarRating(newStar);
        review.setReview(newContent);
        System.out.println("리뷰 수정 완료");
    }


    public void search(String keyword, ArrayList<Restaurant> allRestaurants) { // 맛집 검색
    	 System.out.println("🔍 검색 결과:");
    	 ArrayList<Restaurant> searchRestaurant = new ArrayList<>();
    	 for(Restaurant r : allRestaurants) { // allRestaurants 리스트의 Restaurant 객체들을 하나하나 가져와서 for문을 돌린다
    		 if(r.getName().contains(keyword)){ // Restaurant 객체 r의 이름을 가져와 키워드가 포함되어 있는지 검사
    			 searchRestaurant.add(r); //참이면 r을 리스트에 추가후 리턴
    			 return searchRestaurant;
    		 }
    	 }
    }

 
    public void fliter(String category, ArrayList<Restaurant> allRestaurants) { // 필터
    	ArrayList<Restaurant> categoryRestaurant = new ArrayList<>();
    	for(Restaurant r : allRestaurants) { // allRestaurants 리스트의 Restaurant 객체들을 하나하나 가져와서 for문을 돌린다
    		if(r.getCategory().equals(category)){ // Restaurant 객체 r의 카테고리를 가져와 카테고리가 일치하는지 검사
    			categoryRestaurant.add(r); // 참이면 식당의 이름 가져와서 리턴
    			return categoryRestaurant;
    		}
    	}
    }



    public void viewFavoriteList() { // 즐겨찾기 보기
    	System.out.println("즐겨찾기 보기");
    	for(Restaurant r : favoritelList) {
    		System.out.println(r.getName(););
    	}
    }


    public void addRestaurent(Restaurant r) { // 즐겨찾기에 담기
    	favoriteList.add(r);
    	System.out.println(r.getName() + "이(가) 즐겨찾기에 추가되었습니다.");
    }

}