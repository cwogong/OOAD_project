// import java.io.*;
import java.util.ArrayList;


// 삽입, 삭제(RES ID 필요), 수정(필요한가?), 조회
public class RestaurantList {
    // 식당 리스트 생성
    public ArrayList<Restaurant> restaurantList = new ArrayList<>();
    
    public boolean isEmpty() {
        return restaurantList.isEmpty();
    }

    public void addRestaurant(Restaurant r) {    
        // 식당추가기능
        restaurantList.add(r);
    }
    
    public void remove(){        
        /*삭제기능( 이후 추가할 것) */
    }
    
    public void view(){                   
        // 리스트 보기
        if(restaurantList.isEmpty()){
            System.out.println("등록된 식당이 없습니다.");
        }
        else{
            for(Restaurant r: this.restaurantList){
                r.viewInfo();
                System.out.println("\n");
            }
        }
    }

    public Restaurant findRestaurant(String rest) {
        for (Restaurant restaurant : this.restaurantList) {
            if (restaurant.getName().equals(rest)) {
                return restaurant;
            }
        }
        return null;
    }

    public RestaurantList filterRestaurant(String categ) {
        RestaurantList resList = new RestaurantList();
        for (Restaurant restaurant : this.restaurantList) {
            if (restaurant.getCategory().equals(categ)) {
                resList.addRestaurant(restaurant);
            }
        }
        return resList;
    }
}