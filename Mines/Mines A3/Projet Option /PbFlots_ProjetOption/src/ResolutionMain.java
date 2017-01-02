import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import ilog.concert.IloException;

public class ResolutionMain {


	public static void main(String[] args) throws IloException, IOException {

		String instanceDirectory = "instance";

		String fileName = "instance.txt";

		String name = "resultat";

		// Lecture de l'instance
		Instance instance = new Instance(instanceDirectory + "/" + fileName);
/*		System.out.println("nbclients "+instance.getNbClients());
		System.out.println("nbChauff "+instance.getNbChauffeurs());
		System.out.println("nbVehicules "+instance.getNbVehicules());
		System.out.println("capaciteVehicule "+instance.getCapaciteVehicule());
		System.out.println("coutFixe "+instance.getCoutFixe());
		System.out.println("coutParKm "+instance.getCoutParKm());

		for (int i = 0; i < instance.getNbClients(); i++) {
			System.out.println("demande_"+i+" : "+instance.getDemandeClient()[i]);
			System.out.println("dureeChargement_"+i+" : "+instance.getDureeChargement()[i]);
			
			System.out.println("PlagesHoraires_"+i+" : "+instance.getPlageHoraires().get(i)[0] +" -> "+instance.getPlageHoraires().get(i)[1]);
		}
		for (int i = 0; i < instance.getNbClients(); i++) {
			for (int j = 0; j < instance.getNbClients(); j++) {
				System.out.println("matriceTemps_"+i+"_"+j+" : "+instance.matriceTemps[i][j]);
			}
		}
		for (int i = 0; i < instance.getNbClients(); i++) {
			for (int j = 0; j < instance.getNbClients(); j++) {
				System.out.println("matriceDistance_"+i+"_"+j+" : "+instance.matriceDistance[i][j]);		
			}
		}
		for (int i = 0; i < instance.getNbClients(); i++) {
			for (int j = 0; j < instance.getNbClients(); j++) {
				System.out.println("matriceCouts_"+i+"_"+j+" : "+instance.matriceCouts[i][j]);
			}
		}*/
		System.out.print("R�solution du fichier " + fileName + "...");

		String dir = name+"/log/";
		new File(dir).mkdirs();
		String fichierLog =  dir + fileName.replace(".txt", ".log");
		File fileLog = new File(fichierLog);
		PrintStream printStreamLog = new PrintStream(fileLog);
		PrintStream original = System.out;
		System.setOut(printStreamLog);
		System.setErr(printStreamLog);

		// Appel de la méthode de résolution
		Resolution.resoudre(instance);

		System.setOut(original);
	}	
}
