package restaurant.view;

import restaurant.controller.RestaurantController;
import restaurant.domain.RestaurantDTO;

public class RunningStartView {

	public static void main(String[] args) {
		System.out.println("Eat it!");
		System.out.println("""
				!~!~ WELLCOME ~!~! 
				EAT_IT은 IT 타워 근처의 맛집을 공유하는 서비스입니다.
				""");
		System.out.println("\n ---- 검색 ----");
		System.out.println("0. 전체 음식점 검색");
		RestaurantController.selectAllRestaurant();
		
		System.out.println("1. 메뉴 카테고리로 음식점 검색 > 양식");
		RestaurantController.selectRestaurantByCategory("양식");
		
		System.out.println("2. 가격 범위로 음식점 검색 > 0 ~ 15000");
		RestaurantController.selectRestaurantByPrice(0, 15000);
		
		System.out.println("3. 거리순으로 음식점 검색 > 5분 거리");
		RestaurantController.selectRestaurantByDistance(5);
		
		System.out.println("4. 음식점 이름으로 음식점 검색 > 도락");
		RestaurantController.selectRestaurantByRname("도락");
		
		System.out.println("5. 가격, 거리 기준으로 음식점 검색 > 0 ~ 15000, 5분 거리");
		RestaurantController.selectRestaurantByPriceAndDistance(0, 15000, 5);
		
		System.out.println("6. 카테고리, 가격 기준으로 음식점 검색 > 양식 0 ~ 15000");
		RestaurantController.selectRestaurantByCategoryAndPrice("양식", 0, 15000);
		
		
		System.out.println("\n\n---- 추가 ----");
		System.out.println("7. 음식점 추가 > Test1");
		RestaurantController.selectAllRestaurant();
		RestaurantController.insertRestaurant(
				RestaurantDTO.builder()
					.rname("Test1")
					.category("tcate")
					.food("tfood")
					.price(9999)
					.distance(10)
					.waiting_time(9)
					.is_able_group("true")
					.score(5)
					.review(null)	
					.url("url")
					.build()
				);
		RestaurantController.selectAllRestaurant();
		
		
		System.out.println("\n\n---- 수정 ----");
		System.out.println("7. 음식점 이름으로 음식점 업데이트");
		RestaurantController.selectAllRestaurant();
		RestaurantController.updateRestaurantByRname(
				"Test1", 
				RestaurantDTO.builder()
					.category("수정된 카테고리")
					.food("수정된 메뉴")
					.price(9998)
					.distance(20)
					.waiting_time(19)
					.is_able_group("수정된 단체가능여부")
					.score(1)
					.review("수정된 리뷰")	
					.url("수정된 url")
					.build()
				);
		RestaurantController.selectAllRestaurant();
		
		
		System.out.println("\n\n---- 삭제 ----");
		System.out.println("8. 음식점 이름으로 음식점 삭제");
		RestaurantController.selectAllRestaurant();
		RestaurantController.deleteRestaurantByRname("Test1");
		RestaurantController.selectAllRestaurant();
		
		
	}

}
