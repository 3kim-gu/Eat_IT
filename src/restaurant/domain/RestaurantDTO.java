package restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RestaurantDTO {
	private String rname;
	private String category;
	private String food;
	private int price;
	private int distance;
	private int watting_time;
	private String is_able_group;
	private float score;
	private String review;
	private String url;
}
