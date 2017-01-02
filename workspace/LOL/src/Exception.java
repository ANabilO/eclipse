import java.util.LinkedList;
import java.util.List;

public class Exception {
	
	public static void main(final String[] args) {
	/*	int j=20,i=0;
		try {
			System.out.println(j/i);		
		} catch (ArithmeticException e){
			System.out.println("Division par zero "+ e.getMessage());
		}
		System.out.println("Coucou toi");*/
		
		List l = new LinkedList();
		l.add(12);
		l.add("toto");
		System.out.println(l);
		
		
	}
}
