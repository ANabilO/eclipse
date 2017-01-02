
public class Capitale extends Ville {
		
	protected String monument;

	public Capitale(){
		super();
		this.monument = "aucun";
	}
	
	public Capitale(String string, int i, String string2, String string3) {
		super(string,i,string2);
		this.monument=string3;
	}

	public String decrisToi(){
		String stgr = super.decrisToi() + " et a pour monument " +this.monument;
		return stgr;
	}
	
	public  String getMonument(){
		return this.monument;
	}
	
	public void setMonument(String m){
		this.monument = m;
	}
	
	public static void main(final String[] args) {
		Capitale cap = new Capitale();
		Capitale cop = new Capitale("mok",10,"bak","khtek");
		cap.setNom("Paris");
		cap.setNomPays("France");
		cap.setMonument("La Tour Eiffel");
		cap.setNombreHabitants(17);
		System.out.println(cap.decrisToi());
		System.out.println(cap.comparer(cop));
	}
	
}
