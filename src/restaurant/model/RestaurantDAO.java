package restaurant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import lombok.Builder;

import java.sql.SQLException;

import restaurant.domain.RestaurantDTO;
import restaurant.model.util.DBUtil;

public class RestaurantDAO {
	@Test
	public void Test()	{
		try {
			ArrayList<RestaurantDTO> rd = RestaurantDAO.selectRestaurantByCategoryAndPrice("양식", 0, 15000);
			System.out.println(rd);
		} catch (SQLException e) {
			System.out.println("예외처리오류");
			e.printStackTrace();
		}
	}
//	1. 메뉴 카테고리로 음식점 검색
	public static ArrayList<RestaurantDTO> selectRestaurantByCategory(String category) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from restaurant where category=?");
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rset.getString(1))
						.category(rset.getString(2))
						.food(rset.getString(3))
						.price(rset.getInt(4))
						.distance(rset.getInt(5))
						.waiting_time(rset.getInt(6))
						.is_able_group(rset.getString(7))
						.score(rset.getInt(8))
						.review(rset.getString(9))
						.url(rset.getString(10))
						.build()
				);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return restaurant;
	}
//	2. 가격 범위로 음식점 검색
//	3. 거리순으로 음식점 검색
//	4. 음식점 이름으로 음식점 검색
//	5. 가격, 거리 기준으로 음식점 검색
	// select * from restaurant where category = ? and price 
	public static ArrayList<RestaurantDTO> selectRestaurantByCategoryAndPrice(String category, int low, int high) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();
	
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from restaurant where category = ? and price >= ? and price <= ?");
			pstmt.setString(1, category);
			pstmt.setInt(2, low);
			pstmt.setInt(3, high);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rset.getString(1))
						.category(rset.getString(2))
						.food(rset.getString(3))
						.price(rset.getInt(4))
						.distance(rset.getInt(5))
						.waiting_time(rset.getInt(6))
						.is_able_group(rset.getString(7))
						.score(rset.getInt(8))
						.review(rset.getString(9))
						.url(rset.getString(10))
						.build()
				);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return restaurant;
	}	
	
//	6. 카테고리, 가격 기준으로 음식점 검색
//	7. 전체검색
//	8. 상호명 조건으로 업데이트
//	9. 상호명 조건으로 삭제
//	10. 음식점 추가

	//  9. 상호명 이름으로 삭제(delete) - delete from restaurant where rname=?
	public static boolean deleteRestaurantByName(String rname) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement("delete from restaurant where rname = ?");
	        pstmt.setString(1, rname);

	        int result = pstmt.executeUpdate();
	        return result == 1; // 삭제 성공 여부 반환
	    } finally {
	        DBUtil.close(conn, pstmt);
	    }
	}
	
	// 10. 음식점 추가(insert)
	// 음식점 추가
	
	public static boolean insertRestaurant(RestaurantDTO restaurant) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement("INSERT INTO restaurant (rname, category, food, price, distance, waiting_time, is_able_group, score, review, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

	        pstmt.setString(1, restaurant.getRname());
	        pstmt.setString(2, restaurant.getCategory());
	        pstmt.setString(3, restaurant.getFood());
	        pstmt.setInt(4, restaurant.getPrice());
	        pstmt.setInt(5, restaurant.getDistance());
	        pstmt.setInt(6, restaurant.getWaiting_time()); 
	        pstmt.setString(7, restaurant.getIs_able_group());
	        pstmt.setFloat(8, restaurant.getScore());
	        pstmt.setString(9, restaurant.getReview());
	        pstmt.setString(10, restaurant.getUrl());

	        int result = pstmt.executeUpdate();
	        return result == 1; // 삽입 성공 여부 반환
	    } finally {
	        DBUtil.close(conn, pstmt);
	    }
	}
}