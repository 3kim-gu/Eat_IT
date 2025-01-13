package restaurant.domain;

import java.util.Optional;

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
		Optional<String> container = Optional.ofNullable(review);
	    String row = String.format(
	        "%-20s %-15s %-15s %-10d %-10d %-15d %-15s %-10.1f %-20s %-30s\n",
	        rname, 
	        category, 
	        food, 
	        price, 
	        distance, 
	        waiting_time, 
	        is_able_group, 
	        score, 
	        container.orElse("리뷰가 없습니다."), 
	        url
	    );
		StringBuilder builder = new StringBuilder();
		builder.append(row);
		return builder.toString();
	}
}
