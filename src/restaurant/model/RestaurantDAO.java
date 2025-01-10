package restaurant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import restaurant.domain.RestaurantDTO;
import restaurant.model.util.DBUtil;

public class RestaurantDAO {
	@Test
	public void Test()	{
		try {
//			ArrayList<RestaurantDTO> rd = RestaurantDAO.selectRestaurantByCategory("양식");
//			ArrayList<RestaurantDTO> rd = RestaurantDAO.selectRestaurantByPrice(5000, 10000);
//			ArrayList<RestaurantDTO> rd = selectRestaurantByDistance(5);
			RestaurantDTO rd = selectRestaurantByRname("이");
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
	public static ArrayList<RestaurantDTO> selectRestaurantByPrice(int row, int high) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RestaurantDTO> restaurant = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from restaurant where price >= ? and price <= ?");
			pstmt.setInt(1, row);
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
			pstmt = conn.prepareStatement("select * from restaurant where distance <= ?");
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
//	6. 카테고리, 가격 기준으로 음식점 검색
//	7. 전체검색
//	8. 상호명 조건으로 업데이트
//	9. 상호명 조건으로 삭제
//	10. 음식점 추가

}
