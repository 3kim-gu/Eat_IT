package restaurant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import restaurant.domain.RestaurantDTO;
import restaurant.model.util.DBUtil;

public class RestaurantDAO {
//	1. 메뉴 카테고리로 음식점 검색
	
//	2. 가격 범위로 음식점 검색
//	3. 거리순으로 음식점 검색
//	4. 음식점 이름으로 음식점 검색
//	5. 가격, 거리 기준으로 음식점 검색
	
	
	
//7
	//7
	//8
	

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
	        pstmt.setInt(8, restaurant.getScore());
	        pstmt.setString(9, restaurant.getReview());
	        pstmt.setString(10, restaurant.getUrl());

	        int result = pstmt.executeUpdate();
	        return result == 1; // 삽입 성공 여부 반환
	    } finally {
	        DBUtil.close(conn, pstmt);
	    }
	}
}