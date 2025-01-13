package restaurant.view;
import java.util.ArrayList;
import java.util.Optional;

import restaurant.domain.RestaurantDTO;

public class RunningEndView {
	public static void printResult(Object o) {
		Optional <Object> container = Optional.ofNullable(o);
		
		System.out.println("** 검색 결과 **");
		System.out.println(container.orElse("검색 결과가 없습니다."));
		System.out.println("-".repeat(140) + "\n");
	}
	
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
	
	public static void printError(Exception e) {
		System.out.println("** Error 결과 **");
		System.out.println(e.getMessage());
		System.out.println("-".repeat(140) + "\n");
	}
}
