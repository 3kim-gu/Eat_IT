package restaurant.controller;

import java.sql.SQLException;

import restaurant.domain.RestaurantDTO;
import restaurant.model.RestaurantDAO;
import restaurant.view.RunningEndView;

public class RestaurantController {

	public static void selectAllRestaurant() {
		try {
			RunningEndView.printResult(RestaurantDAO.selectAllRestaurant());
		} catch (SQLException e) {
			System.out.println("selectAllRestaurant 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByCategory(String category) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByCategory(category));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByCategory 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByPrice(int low, int high) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByPrice(low, high));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByPrice 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByDistance(int i) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByDistance(i));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByRname 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByRname(String rname) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByRname(rname));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByRname 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByPriceAndDistance(int low, int high, int i) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByPriceAndDistance(low, high, i));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByPriceAndDistance 검색 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByCategoryAndPrice(String category, int low, int high) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByCategoryAndPrice(category, low, high));
		} catch (SQLException e) {
			System.out.println("selectRestaurantByCategoryAndPrice 검색 불가.");
			RunningEndView.printError(e);		
		}
	}

	// ---- DML ----
	public static void insertRestaurant(RestaurantDTO restaurant) {
		try {
			RestaurantDAO.insertRestaurant(restaurant);
		} catch (SQLException e) {
			System.out.println("insertRestaurant 추가 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void updateRestaurantByRname(String rname, RestaurantDTO rest) {
		try {
			RestaurantDAO.updateRestaurantByRname(rname, rest);
		} catch (SQLException e) {
			System.out.println("updateRestaurantByRname 수정 불가.");
			RunningEndView.printError(e);
		}
	}

	public static void deleteRestaurantByRname(String rname) {
		try {
			RestaurantDAO.deleteRestaurantByRname(rname);
		} catch (SQLException e) {
			System.out.println("deleteRestaurantByRname 삭제 불가.");
			RunningEndView.printError(e);
		}
	}

}
