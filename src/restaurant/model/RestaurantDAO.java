package restaurant.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restaurant.domain.RestaurantDTO;
import restaurant.model.util.DBUtil;

public class RestaurantDAO {
	
//	1. 메뉴 카테고리로 음식점 검색
	public static RestaurantDTO selectRestaurantByCategory(String category) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RestaurantDTO restaurant = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from restaurant where category=?");
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// @Builder(builderMethodName = "activistDTOBuilder")
				restaurant = RestaurantDTO.activistDTOBuilder().rname(rset.getString(1)).category(rset.getString(2))
						.food(rset.getString(3)).(rset.getString(4)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return null;
	}
//	2. 가격 범위로 음식점 검색
	
//	3. 거리순으로 음식점 검색
//	4. 음식점 이름으로 음식점 검색
//	5. 가격, 거리 기준으로 음식점 검색
//	6. 카테고리, 가격 기준으로 음식점 검색
//	7. 전체검색
//	8. 상호명 조건으로 업데이트
//	9. 상호명 조건으로 삭제
//	10. 음식점 추가

}
