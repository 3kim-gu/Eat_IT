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
		builder.append("RestaurantDTO [rname=");
		builder.append(rname);
		builder.append(", category=");
		builder.append(category);
		builder.append(", food=");
		builder.append(food);
		builder.append(", price=");
		builder.append(price);
		builder.append(", distance=");
		builder.append(distance);
		builder.append(", waiting_time=");
		builder.append(waiting_time);
		builder.append(", is_able_group=");
		builder.append(is_able_group);
		builder.append(", score=");
		builder.append(score);
		builder.append(", review=");
		builder.append(review);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		builder.append("\n");
		return builder.toString();
	}
}
