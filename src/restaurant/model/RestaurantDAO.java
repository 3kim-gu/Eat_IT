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
			ArrayList<RestaurantDTO> rd = RestaurantDAO.selectRestaurantByCategory("양식");
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
	public static selectRestaurantByPrice
//	3. 거리순으로 음식점 검색
//	4. 음식점 이름으로 음식점 검색
//	5. 가격, 거리 기준으로 음식점 검색
//	6. 카테고리, 가격 기준으로 음식점 검색
//	7. 전체검색
//	8. 상호명 조건으로 업데이트
//	9. 상호명 조건으로 삭제
//	10. 음식점 추가

}
