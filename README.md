# EAT_IT 🍜  
<img src="https://github.com/user-attachments/assets/9de1e1ba-163a-4850-b389-8209d027b697" width="800" height="330">

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
  <br>
  
---

## 🗓️ 개발기간
- 2025.01.06 ~ 2025.01.13
<br>

## 🚀 사용기술
### DEVELOPMENT
![js](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![js](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black)
<br>
<br>

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
![ERD](https://github.com/user-attachments/assets/6c023ee0-dfdb-46a0-9d49-bb6faf71c61e)


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
###  **주요 변경 사항 1️**: NullPointerException 예외 해결
- **문제** : 기존 코드에서 Object타입의 o로 가져온 인수가 null 즉 controller에서 model로 요청한 결과가 null일 경우 o.toString() 메소드에서 NullPointerException 예외 발생
<div align="center">
  <img src="https://github.com/user-attachments/assets/50281d14-605e-439b-a0b1-1b331ff0f865" width="900" height="110">
</div>


<br>
<br>

-  **해결** : 1개의 RestaurantDTO 타입이 반환될 경우에는 Optional을 이용하여 null값에 대한 처리를 수행하도록 수정
```java
	public static void printResult(Object o) {
		Optional <Object> container = Optional.ofNullable(o);
		
		System.out.println("** 검색 결과 **");
		System.out.println(container.orElse("검색 결과가 없습니다."));
		System.out.println("-".repeat(140) + "\n");
	}
```
<div align="center">
<img src="https://github.com/user-attachments/assets/69acbc2f-6035-440f-8fcd-01b75a585f8a"  width="800" height="130">
</div>



###  **주요 변경 사항 2️⃣**: Stream API 활용하여 출력 메소드 추가 
- **필요성** : ArrayList<RestaurantDTO> 타입의 반환을 Object 타입으로 받아 일괄적으로 처리


- **개선** : ArrayList<RestaurantDTO> 타입을 인자로 하는 메소드를 새로 작성하여 해당 객체의 사이즈를 기준으로 결과가 0개일 때와 1개 이상일때 출력 포맷 추가
```java
	public static void printResults(ArrayList<RestaurantDTO> o) {
		System.out.println("** 검색 결과 **");
		if (o.size() == 0) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			StringBuilder sb = new StringBuilder();
			String separator = "-".repeat(150) + "\n";
			String header = String.format(
			        "%-20s %-15s %-15s %-10s %-10s %-15s %-15s %-10s %-20s %-30s\n",
			        "RName", "Category", "Food", "Price", "Distance", "Waiting Time", "Able Group", "Score", "Review", "URL"
			    );
			sb.append(header);
			sb.append(separator);
			System.out.println(sb.toString());
			
			o.stream().forEach(System.out::println);
		}
		System.out.println( "-".repeat(140) + "\n");			
	}
```



## 📝 프로젝트 후기

---

### 김대연
프로젝트 진행 초기에 git 연동을 하는 것부터 여러가지 문제가 있었습니다. STS 에서 생성한 프로젝트를 github 리포지토리에 연동한 이후에 팀원들이 pull하여 프로젝트 파일을 설정하는 과정에서 컴파일러 버젼문제가 있었습니다. STS라는 IDE가 처음이었던 점, git을 통한 프로젝트 협업이 처음이었기 때문에 문제해결까지 시간이 많이 지체되었습니다. 하지만 이런 점들을 통해 이를 통해 프로젝트의 property를 조정하여 컴파일 버젼 에러를 해결해볼 수 있었고, git merge 방법들에 대해 학습할 수 있었던 의미있는 시간이었다고 생각합니다.

뿐만아니라 MVC 패턴에 대한 이해와 다형성을 이용한 OOP 개발 방법론을 학습할 수 있었고, JDBC를 이용한 db연동을 실습해볼 수 있었습니다. 이 과 팀원분들과 프로젝트 기간동안 기초 개념부터 함께 학습해나가면서 같이 성장할 수 있었다고 생각합니다.

### 구민지
DML 작성 시 가장 유의할 점은 선언된 타입값을 맞추어 작성하는 것인데, 한 속성값을 반환값이 true, false인  boolean을
사용해 선언하려고 했는데, Oracle에서는 boolean타입이 존재하지 않음을 학습하게 되었습니다.
그리고 선언된 변수 값과 길이가 맞지 않아서 오류가 발생하기도 하였습니다.

하나의 클래스안에서 여러 메소드를 각자 맡아 작성을 했기에 git을 통해 main 브랜치로 commit하는 과정에서 
충돌이 많았습니다. 그래서 main 브랜치의 코드가 사라지는 것을 방지 하기위해 main브랜치 전에 사전에 병합하는 
mainpreview 브랜치를 생성해 코드가 사라는것을 방지하려고 노력했습니다.

프로젝트를 진행하면서 팀원끼리 먼저 코드 규칙 명명을 통해 규칙을 만들고
어떤 구조로 프로젝트를 만들지 미리 꼼꼼하게 계획하는것이 중요하다고 느꼈습니다.


