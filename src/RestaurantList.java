// import java.io.*;
import java.util.*;


// 삽입, 삭제(RES ID 필요), 수정(필요한가?), 조회
public class RestaurantList {                                            
    // 식당 리스트 생성
    private ArrayList<Restaurant> restaurantList = new ArrayList<>();
    
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
        }else{
            for(Restaurant r: restaurantList){
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
}