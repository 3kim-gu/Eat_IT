package restaurant.view;

import java.util.Iterator;

public class RunningEndView {
	public static void printResult(Object o) {
		String[] result = o.toString().replace("[","").replace("]","").split(",");
		StringBuilder sb = new StringBuilder();
		for (String s: result) {
			sb.append(s);
		}
		
		System.out.println("#### 검색 결과 ####");
		System.out.println(sb.toString());
		System.out.println("#################");
	}
	
	public static void printError(Exception e) {
		System.out.println("#### Error ####");		
		System.out.println(e.getMessage());
		System.out.println("###############");
	}
}
