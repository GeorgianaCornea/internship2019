import java.util.ArrayList;
import java.util.List;

public class Main {
	
	
	
	public static void main(String[] args) {
		
	
		Route route=new Route();
		List<Atm> finalRoute=new ArrayList<Atm>();
		finalRoute=route.getAtmsRoute();
		System.out.println("The final route is: "+finalRoute);
		
			
		
	}

}
