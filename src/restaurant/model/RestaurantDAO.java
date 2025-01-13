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
			ArrayList<RestaurantDTO> rd = selectRestaurantByCategoryAndPrice("양식", 0, 15000);
			System.out.println(rd);
		} catch (SQLException e) {
			System.out.println("예외처리오류");
			e.printStackTrace();
		}
	}
	
	//	1. 메뉴 카테고리로 음식점 검색
	public static ArrayList<RestaurantDTO> selectRestaurantByCategory(String category) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where category=?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rs.getString(1))
						.category(rs.getString(2))
						.food(rs.getString(3))
						.price(rs.getInt(4))
						.distance(rs.getInt(5))
						.waiting_time(rs.getInt(6))
						.is_able_group(rs.getString(7))
						.score(rs.getInt(8))
						.review(rs.getString(9))
						.url(rs.getString(10))
						.build()
				);
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return restaurant;
	}
	
	//	2. 가격 범위로 음식점 검색
	public static ArrayList<RestaurantDTO> selectRestaurantByPrice(int low, int high) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where price>=? and price<=?");
			pstmt.setInt(1, low);
			pstmt.setInt(2, high);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rs.getString(1))
						.category(rs.getString(2))
						.food(rs.getString(3))
						.price(rs.getInt(4))
						.distance(rs.getInt(5))
						.waiting_time(rs.getInt(6))
						.is_able_group(rs.getString(7))
						.score(rs.getInt(8))
						.review(rs.getString(9))
						.url(rs.getString(10))
						.build()
				);
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return restaurant;
	}
	
	//	3. 거리순으로 음식점 검색
	public static ArrayList<RestaurantDTO> selectRestaurantByDistance(int min) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where distance<=?");
			pstmt.setInt(1, min);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rs.getString(1))
						.category(rs.getString(2))
						.food(rs.getString(3))
						.price(rs.getInt(4))
						.distance(rs.getInt(5))
						.waiting_time(rs.getInt(6))
						.is_able_group(rs.getString(7))
						.score(rs.getInt(8))
						.review(rs.getString(9))
						.url(rs.getString(10))
						.build()
				);
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return restaurant;
	}
	
	//	4. 음식점 이름으로 음식점 검색
	public static RestaurantDTO selectRestaurantByRname(String rname) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RestaurantDTO restaurant = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where rname=?");
			pstmt.setString(1, rname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				restaurant = RestaurantDTO.builder()
						.rname(rs.getString(1))
						.category(rs.getString(2))
						.food(rs.getString(3))
						.price(rs.getInt(4))
						.distance(rs.getInt(5))
						.waiting_time(rs.getInt(6))
						.is_able_group(rs.getString(7))
						.score(rs.getFloat(8))
						.review(rs.getString(9))
						.url(rs.getString(10))
						.build();
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return restaurant;
	}
	
	//	5. 가격, 거리 기준으로 음식점 검색
	public static ArrayList<RestaurantDTO> selectRestaurantByPriceAndDistance(int low, int high, int i) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();
	
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from restaurant where distance = ? and price >= ? and price <= ?");
			pstmt.setInt(1, i);
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
	public static ArrayList<RestaurantDTO> selectRestaurantByCategoryAndPrice(String category, int low, int high) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where category=? and price>=? and price<=?");
			pstmt.setString(1, category);
			pstmt.setInt(2, low);
			pstmt.setInt(3, high);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant.add(
						RestaurantDTO.builder()
						.rname(rs.getString(1))
						.category(rs.getString(2))
						.food(rs.getString(3))
						.price(rs.getInt(4))
						.distance(rs.getInt(5))
						.waiting_time(rs.getInt(6))
						.is_able_group(rs.getString(7))
						.score(rs.getFloat(8))
						.review(rs.getString(9))
						.url(rs.getString(10))
						.build()
						);
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return restaurant;
	}

	// 7. 전체검색
	public static ArrayList<RestaurantDTO> selectAllRestaurant() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> list = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant");
			rs = pstmt.executeQuery();

			list = new ArrayList<RestaurantDTO>();
			while (rs.next()) {
				list.add(RestaurantDTO.builder()
			            .rname(rs.getString(1))
			            .category(rs.getString(2))
			            .food(rs.getString(3))
			            .price(rs.getInt(4))
			            .distance(rs.getInt(5))
			            .waiting_time(rs.getInt(6))
			            .is_able_group(rs.getString(7))
			            .score(rs.getFloat(8))
			            .review(rs.getString(9))
			            .url(rs.getString(10))
			            .build());
			}
		} finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return list;
	}

	// 8. 상호명 조건으로 (DTO있는거 가져와서)업데이트
	public static boolean updateRestaurantByRname(String rname, RestaurantDTO rest) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	        conn = DBUtil.getConnection();
	        pstmt = conn.prepareStatement("UPDATE restaurant SET category=?, food=?, price=?, distance=?, waiting_time=?, is_able_group=?, score=?, review=?, url=? WHERE rname = ?");
	        pstmt.setString(1, rest.getCategory());
	        pstmt.setString(2, rest.getFood());
	        pstmt.setInt(3, rest.getPrice());
	        pstmt.setInt(4, rest.getDistance());
	        pstmt.setInt(5, rest.getWaiting_time());
	        pstmt.setString(6, rest.getIs_able_group());
	        pstmt.setFloat(7, rest.getScore());
	        pstmt.setString(8, rest.getReview());
	        pstmt.setString(9, rest.getUrl());
	        pstmt.setString(10, rname);
	        int result = pstmt.executeUpdate();
	        if (result == 1) {
	        	return true;
	        }
	    } finally {
	        DBUtil.close(conn, pstmt);
	    }
	    return false;
	}

	//  9. 상호명 이름으로 삭제(delete)
	public static boolean deleteRestaurantByRname(String rname) throws SQLException {
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