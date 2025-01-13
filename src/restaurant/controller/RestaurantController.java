package restaurant.controller;

import java.sql.SQLException;

import restaurant.domain.RestaurantDTO;
import restaurant.model.RestaurantDAO;
import restaurant.view.RunningEndView;

public class RestaurantController {

	public static void selectAllRestaurant() {
		try {
			RunningEndView.printResults(RestaurantDAO.selectAllRestaurant());
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByCategory(String category) {
		try {
			RunningEndView.printResults(RestaurantDAO.selectRestaurantByCategory(category));
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByPrice(int low, int high) {
		try {
			RunningEndView.printResults(RestaurantDAO.selectRestaurantByPrice(low, high));
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByDistance(int i) {
		try {
			RunningEndView.printResults(RestaurantDAO.selectRestaurantByDistance(i));
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByRname(String rname) {
		try {
			RunningEndView.printResult(RestaurantDAO.selectRestaurantByRname(rname));
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByPriceAndDistance(int low, int high, int i) {
		try {
			RunningEndView.printResults(RestaurantDAO.selectRestaurantByPriceAndDistance(low, high, i));
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void selectRestaurantByCategoryAndPrice(String category, int low, int high) {
		try {
			RunningEndView.printResults(RestaurantDAO.selectRestaurantByCategoryAndPrice(category, low, high));
		} catch (SQLException e) {
			RunningEndView.printError(e);		
		}
	}

	// ---- DML ----
	public static void insertRestaurant(RestaurantDTO restaurant) {
		try {
			RestaurantDAO.insertRestaurant(restaurant);
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void updateRestaurantByRname(String rname, RestaurantDTO rest) {
		try {
			RestaurantDAO.updateRestaurantByRname(rname, rest);
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

	public static void deleteRestaurantByRname(String rname) {
		try {
			RestaurantDAO.deleteRestaurantByRname(rname);
		} catch (SQLException e) {
			RunningEndView.printError(e);
		}
	}

}
