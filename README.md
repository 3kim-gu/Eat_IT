# EAT_IT 🍜  

## 🎯 목표  
1️⃣ **Oracle과 JDBC**를 활용하여 **CRUD 기능**을 구현한 실습 프로젝트  
2️⃣ **MVC 패턴 구조**를 적용하여 개발  
3️⃣ **Stream API**를 활용한 데이터 처리  

---


## 🌟 **서비스 소개**  
**EAT_IT**은 IT 타워 근처의 맛집을 공유하는 서비스로, 아래와 같은 의미를 담고 있습니다!
- **"이것(IT)을 먹다(EAT)"**  
- **"IT 타워(IT) 근처에서 먹다(EAT)"**  
- 먼 미래에는 "우리가 IT를 씹어먹겠다"는 의지를 담았습니다!
  
---

## 🗓️ 개발기간
- 2025.01.06 ~ 2025.01.13

## 👥 개발구성원
<table>
  <tbody>
    <tr>
      <td align="center">
         <a href="https://github.com/user0830">
          <img src="https://avatars.githubusercontent.com/u/82265395?v=4" width="150px;" alt=""/>
          <br /><sub><b> 구민지 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
          <a href="https://github.com/dyoun12">
          <img src="https://avatars.githubusercontent.com/u/107902336?v=4" width="150px;" alt=""/>
          <br /><sub><b> 김대연 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
        <a href="https://github.com/riyeong0916">
          <img src="https://avatars.githubusercontent.com/u/193798531?v=4" width="150px;" alt=""/>
          <br /><sub><b> 김리영 </b></sub>
        </a>
        <br />
      </td>
      <td align="center">
        <a href="https://github.com/minsung159357">
          <img src="https://avatars.githubusercontent.com/u/87555330?v=4" width="150px;" alt=""/>
          <br /><sub><b> 김민성 </b></sub>
        </a>
        <br />
      </td>
    </tr>
  </tbody>
</table>

---

## 🖧 아키텍처 구조

- **Windows OS**: 호스트 운영체제  
- **VirtualBox**: Linux OS 실행 환경  
- **Docker**: 컨테이너 기반 Oracle 11g XE DB 실행  
- **Java 애플리케이션**:  
  - JDBC 드라이버 추가  
  - JDBC URL을 설정해 Oracle DB와 연결  

<img src="https://github.com/user-attachments/assets/c041245e-efd5-4e0c-a4fa-e0727a4c7aed" width="600" height="300">

---

## 🗃️ ERD
<img src = https://github.com/user-attachments/assets/7f087f30-70db-4bc5-af2b-d5efd92c0c26 width = "180" height = "300"></img>


---
## ⚙️ 구현 기능
| 기능                        | 상세 기능                                                               |
|-----------------------------|--------------------------------------------------------------------------|
| **메뉴 카테고리로 음식점 검색** | 특정 메뉴 카테고리로 음식점을 검색할 수 있는 기능                  |
| **가격 범위로 음식점 검색**      | 가격 범위를 설정하여 해당 조건에 맞는 음식점을 검색               |
| **거리순으로 음식점 검색**      | 사용자 위치를 기준으로 거리순으로 음식점을 정렬하여 검색           |
| **음식점 이름으로 음식점 검색**  | 음식점 이름을 입력하여 해당 음식점을 검색                        |
| **가격, 거리 기준으로 음식점 검색** | 가격과 거리를 동시에 고려하여 음식점을 검색                  |
| **전체검색**                  | 모든 음식점을 한 번에 조회할 수 있는 기능                            |
| **상호명 조건으로 업데이트**   | 음식점의 상호명을 기준으로 데이터를 업데이트                        |
| **상호명 조건으로 삭제**       | 특정 음식점을 상호명을 기준으로 삭제                               |
| **음식점 추가**              | 새로운 음식점을 추가하는 기능                                        |
| **카테고리, 가격 기준으로 음식점 검색** | 카테고리와 가격 조건을 동시에 설정하여 음식점을 검색할 수 있는 기능      |

---
## 💻 CRUD 구현 코드 설명
### 1️⃣ Create (새로운 음식점을 DB에 저장 - INSERT 구문)
```java
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
```

### 2️⃣ Read (가격과 거리 조건으로 음식점 검색 - SELECT 구문)
```java
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
```

### 3️⃣ Update (음식점을 기준으로 데이터 업데이트 - UPDARE 구문) 
```java
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
```
### 4️⃣ Delete (특정 음식점을 기준으로 삭제 - DELETE 문)
```java
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
```
---

## ✨ Refactoring
- **주요 변경 사항 1️**: 



---




