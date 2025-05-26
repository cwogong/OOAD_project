
// import java.io.*;
import java.util.*;


// 삽입, 삭제(RES ID 필요), 수정(필요한가?), 조회
public class FavoriteList {                                            
    // 맛집집 리스트 생성
    private ArrayList<Restaurant> favoriteList = new ArrayList<>();
    
    public void add(Restaurant r) {    
        // 맛집집추가기능
        favoriteList.add(r);
    }
    
    public void remove(String rest){        
        /*삭제기능( 이후 추가할 것) */
        Restaurant toRemove = null;  
        for (Restaurant restaurant : this.favoriteList) {
            if (restaurant.getName().equals(rest)) {
                toRemove = restaurant;
                break;
            }
        }
        if (toRemove != null) {
            favoriteList.remove(toRemove);
            System.out.println(rest + "이(가) 삭제되었습니다.");
        } else {
            System.out.println("해당 맛집이 등록되어 있지 않습니다.");
        }     
    }
    
    public void view(){                   
        // 리스트 보기
        if(favoriteList.isEmpty()){
            System.out.println("등록된 맛집이 없습니다.");
        }else{
            for(Restaurant r: favoriteList){
                r.viewInfo();
                System.out.println("\n");
            }
        }
    }

    public Restaurant findRestaurant(String rest) {
        for (Restaurant restaurant : this.favoriteList) {
            if (restaurant.getName().equals(rest)) {
                return restaurant;
            }
        }
        return null;
    }
}

