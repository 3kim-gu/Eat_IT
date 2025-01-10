package restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RestaurantDTO {
	private String rname;
	private String category;
	private String food;
	private int price;
	private int distance;
	private int waiting_time;
	private String is_able_group;
	private float score;
	private String review;
	private String url;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("상호명 : ");
		builder.append(rname);
		builder.append("|");
		builder.append(", 음식종류 : ");
		builder.append(category);
		builder.append("|");
		builder.append(", 메뉴 :");
		builder.append(food);
		builder.append("|");
		builder.append(", 가격 : ");
		builder.append(price);
		builder.append("|");
		builder.append(", 도보(분) : ");
		builder.append(distance);
		builder.append("|");
		builder.append(", 대기시간 : ");
		builder.append(waiting_time);
		builder.append("|");
		builder.append(", 단체가능여부: ");
		builder.append(is_able_group);
		builder.append("|");
		builder.append(", 평점: ");
		builder.append(score);
		builder.append("|");
		builder.append(", 후기: ");
		builder.append(review);
		builder.append("|");
		builder.append(", 홈페이지: ");
		builder.append(url);
		builder.append("|");
		builder.append("\n");
		return builder.toString();
	}
}
