import java.io.IOException;

public class ResolutionMain {

	public static void main(String[] args) throws IOException{

		// --- Premier param√®tre: r√©pertoire o√π sont stock√©es les instances
		String instanceDirectory = "instance";
		// --- Second param√®tre: nom de l'instance
		String fileName = "exemple.txt";

		// Lecture de l'instance
		instanceClients instance = new instanceClients(instanceDirectory + "/" + fileName);
		
		//Affichage de l'instance
		System.out.println("-------- Instance --------");
		System.out.println();
		
		System.out.println("Nombre de Clients: " + instance.getNbClients());
		System.out.println("Nombre de Vehicules: " + instance.getNbVehicules());
		System.out.println("Nombre de Chauffeurs: " + instance.getNbChauffeurs());
		System.out.println();
		
		System.out.println("Distances");
		for (int i = 0; i < instance.getNbClients()+1; i++) 
		{
			System.out.println("Client "+i+": " );
			for (int j = 0; j < instance.getNbClients()+1; j++) 
			{
				System.out.print(instance.getMatDistance()[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("Demande");
		for (int i = 0; i < instance.getNbClients(); i++) 
		{
			System.out.print(instance.getQuantité()[i]+" - ");
		}
		System.out.println();
		
		System.out.println();
		System.out.println("Jours de Visite");
		for (int i = 0; i < instance.getNbClients(); i++) 
		{
			System.out.println("Client "+i+": " );
			for (int j = 0; j < instance.getJour().get(i).length; j++) 
			{
				System.out.print(instance.getJour().get(i)[j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Fenêtres de temps");
		for (int i = 0; i < instance.getNbClients(); i++) 
		{
			System.out.println("Client "+i);
			System.out.print(" - matin: " +instance.getA_am()[i] + " - "+instance.getB_am()[i]);
			System.out.print(" - après-midi: " +instance.getA_pm()[i] + " - "+instance.getB_pm()[i]);
			System.out.println();
		}
		System.out.println();
		
	}	
}

