


public class ClientUser extends User { //user 클래스를 상속받는 client user 클래스


    public String nickname; // 사용자의 닉네임
    public int age; // 사용자의 나이
    public RestaurantList favoriteList;

	
    public ClientUser(String userID, String userPW, String address, int age, String nickname) { //생성자
    	super(userID, userPW, address);
    	this.nickname = nickname;
    	this.age = age;
        this.favoriteList = new RestaurantList();
    }

    public ClientUser(String userID, String userPW, String address) { //생성자
    	super(userID, userPW, address);
        this.favoriteList = new RestaurantList();
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

    public void editReview(Review review, float newStar, String newContent) { // 리뷰 수정
        // review.setStarRating(newStar);
        // review.setReview(newContent);
        System.out.println("리뷰 수정 완료");
    }


    public RestaurantList search(String keyword, RestaurantList allRestaurants) { // 맛집 검색
    	 System.out.println("🔍 검색 결과:");
    	 RestaurantList searchRestaurant = new RestaurantList();
    	 for(Restaurant r : allRestaurants.restaurantList) { // allRestaurants 리스트의 Restaurant 객체들을 하나하나 가져와서 for문을 돌린다
    		 if(r.getName().contains(keyword)){ // Restaurant 객체 r의 이름을 가져와 키워드가 포함되어 있는지 검사
    			 searchRestaurant.addRestaurant(r); //참이면 r을 리스트에 추가후 리턴
    			 return searchRestaurant;
    		 }
    	 }
         return null;
    }

    public RestaurantList fliter(String category, RestaurantList allRestaurants) { // 필터
    	RestaurantList categoryRestaurant = new RestaurantList();
    	for(Restaurant r : allRestaurants.restaurantList) { // allRestaurants 리스트의 Restaurant 객체들을 하나하나 가져와서 for문을 돌린다
    		if(r.getCategory().equals(category)){ // Restaurant 객체 r의 카테고리를 가져와 카테고리가 일치하는지 검사
    			categoryRestaurant.addRestaurant(r); // 참이면 식당의 이름 가져와서 리턴
    			return categoryRestaurant;
    		}
    	}
        return null;
    }



    public void viewFavoriteList() { // 즐겨찾기 보기
    	System.out.println("\n---------- 내 맛집 리스트 ----------\n");
    	for (Restaurant r : this.favoriteList.restaurantList) {
    		System.out.println(r.getName());
    	}
        System.out.println("----------------------------");
    }


    public void addRestaurant(Restaurant r) { // 즐겨찾기에 담기
    	favoriteList.addRestaurant(r);
    	System.out.println(r.getName() + "이(가) 즐겨찾기에 추가되었습니다.");
    }

}