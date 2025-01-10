package restaurant.view;

import restaurant.controller.RestaurantController;

public class RunningStartView {

	public static void main(String[] args) {
		System.out.println("Eat it!");
		
		System.out.println("---- DQL ----");
		System.out.println("0. 전체 음식점 검색");
		RestaurantController.selectAllRestaurant();
		
		System.out.println("1. 메뉴 카테고리로 음식점 검색");
		RestaurantController.selectRestaurantByCategory("양식");
		
		System.out.println("2. 가격 범위로 음식점 검색");
		RestaurantController.selectRestaurantByPrice(0, 15000);
		
		System.out.println("3. 거리순으로 음식점 검색");
		RestaurantController.selectRestaurantByDistance(5);
		
		System.out.println("4. 음식점 이름으로 음식점 검색");
		RestaurantController.selectRestaurantByRname("도락");
		
		System.out.println("5. 가격, 거리 기준으로 음식점 검색");
		RestaurantController.selectRestaurantByPriceAndDistance(0, 15000, 5);
		
		System.out.println("6. 카테고리, 가격 기준으로 음식점 검색");
		RestaurantController.selectRestaurantByCategoryAndPrice("양식", 0, 15000);
		
		
		System.out.println("---- DML ----");
		System.out.println("7. 음식점 추가");
		RestaurantController.selectAllRestaurant();
		RestaurantController.insertRestaurantByCategory("양식");
		RestaurantController.selectAllRestaurant();
		
		System.out.println("7. 음식점 이름으로 음식점 업데이트");
		RestaurantController.selectAllRestaurant();
		RestaurantController.updateRestaurantByCategory("양식");
		RestaurantController.selectAllRestaurant();
		
		System.out.println("8. 음식점 이름으로 음식점 삭제");
		RestaurantController.selectAllRestaurant();
		RestaurantController.deleteRestaurantByCategory("양식");
		RestaurantController.selectAllRestaurant();
		
	}

}
