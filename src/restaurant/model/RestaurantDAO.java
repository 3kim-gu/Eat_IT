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
	
//	1. 메뉴 카테고리로 음식점 검색

//	2. 가격 범위로 음식점 검색

//	3. 거리순으로 음식점 검색
//	4. 음식점 이름으로 음식점 검색
//	5. 가격, 거리 기준으로 음식점 검색
//7
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
public static boolean updateRestaurantByCategory(String rname, RestaurantDTO rest) {
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
        return result == 1;
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBUtil.close(conn, pstmt);
    }
    return false;
}

}
