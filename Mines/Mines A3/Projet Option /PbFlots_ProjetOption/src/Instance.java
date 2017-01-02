import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Instance {

	//Matrices
	public double[][] matriceCouts;
	public double[][] matriceTemps;
	public double[][] matriceDistance;
	//Tableaux
	public int[] clients;
	public int[] chauffeurs;
	public double[] demandeClient;
	public double[] dureeChargement;
	public ArrayList<double []> plageHoraires;

	//Nombres
	public int nbClients;
	public int  nbChauffeurs;
	public int nbVehicules;
	public double capaciteVehicule;
	public double coutFixe;
	public double coutParKm;


	/********Accesseurs ***********/

	public double[][] getMatriceCouts() {
		return matriceCouts;
	}
	public void setMatriceCouts(double[][] matriceCouts) {
		this.matriceCouts = matriceCouts;
	}
	public double[][] getMatriceTemps() {
		return matriceTemps;
	}
	public void setMatriceTemps(double[][] matriceTemps) {
		this.matriceTemps = matriceTemps;
	}
	public int[] getClients() {
		return clients;
	}
	public void setClients(int[] clients) {
		this.clients = clients;
	}
	public int[] getChauffeurs() {
		return chauffeurs;
	}
	public void setChauffeurs(int[] chauffeurs) {
		this.chauffeurs = chauffeurs;
	}
	public double[] getDemandeClient() {
		return demandeClient;
	}
	public void setDemandeClient(double[] demandeClient) {
		this.demandeClient = demandeClient;
	}
	public double[] getDureeChargement() {
		return dureeChargement;
	}
	public void setDureeChargement(double[] dureeChargement) {
		this.dureeChargement = dureeChargement;
	}
	public ArrayList<double[]> getPlageHoraires() {
		return plageHoraires;
	}
	public void setPlageHoraires(ArrayList<double[]> plageHoraires) {
		this.plageHoraires = plageHoraires;
	}
	public int getNbClients() {
		return nbClients;
	}
	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}
	public int getNbChauffeurs() {
		return nbChauffeurs;
	}
	public void setNbChauffeurs(int nbChauffeurs) {
		this.nbChauffeurs = nbChauffeurs;
	}
	public double getCapaciteVehicule() {
		return capaciteVehicule;
	}
	public void setCapaciteVehicule(double capaciteVehicule) {
		this.capaciteVehicule = capaciteVehicule;
	}
	public double getCoutFixe() {
		return coutFixe;
	}
	public void setCoutFixe(double coutFixe) {
		this.coutFixe = coutFixe;
	}


	public double[][] getMatriceDistance() {
		return matriceDistance;
	}
	public void setMatriceDistance(double[][] matriceDistance) {
		this.matriceDistance = matriceDistance;
	}
	public int getNbVehicules() {
		return nbVehicules;
	}
	public void setNbVehicules(int nbVehicules) {
		this.nbVehicules = nbVehicules;
	}
	public double getCoutParKm() {
		return coutParKm;
	}
	public void setCoutParKm(double coutParKm) {
		this.coutParKm = coutParKm;
	}
	/*******Constructeur*****/


	public Instance(String nomFichier) throws IOException {

		File mfile = new File(nomFichier);
		if (!mfile.exists()) {
			throw new IOException("Le fichier saisi : " + nomFichier + ", n'existe pas.");
		}
		Scanner sc = new Scanner(mfile);

		nbClients = sc.nextInt();
		sc.nextLine();
		nbChauffeurs = sc.nextInt();
		sc.nextLine();
		nbVehicules = sc.nextInt();
		sc.nextLine();
		capaciteVehicule = sc.nextDouble();
		sc.nextLine();
		coutFixe = sc.nextDouble();
		sc.nextLine();
		coutParKm = sc.nextDouble();

		/*for (int i = 0; i < nbClients; i++) {
			clients[i]=sc.nextInt();
		}*/

		plageHoraires = new ArrayList<>();

		sc.nextLine();

		demandeClient = new double[nbClients];
		for (int i = 0; i < nbClients; i++) {
			demandeClient[i]=sc.nextInt();
		}

		sc.nextLine();

		dureeChargement = new double[nbClients];
		for (int i = 0; i < nbClients; i++) {
			dureeChargement[i]=sc.nextDouble();
		}

		sc.nextLine();

		for (int i = 0; i < nbClients; i++) {
			//TabPlages[0] = 0;
			//	TabPlages[1] = 100;
			if(sc.hasNext()){
				if(sc.hasNextDouble()){
					double[] TabPlages = new double[2];
					TabPlages[0] = sc.nextDouble();
					TabPlages[1]= sc.nextDouble();
					plageHoraires.add(TabPlages);
				}
			}
		}
		
		sc.nextLine();

		matriceTemps = new double[nbClients][nbClients];
		for (int i = 0; i < nbClients; i++) {
			//	sc.nextLine();
			for (int j = 0; j < nbClients; j++) {
				if(sc.hasNext()){
					if(sc.hasNextDouble()){
						matriceTemps[i][j]=sc.nextDouble();
					}
				}

			}
		}
		sc.nextLine();

		matriceDistance = new double [nbClients][nbClients];
		matriceCouts = new double[nbClients][nbClients];
		for (int i = 0; i < nbClients; i++) {
			//	sc.nextLine();
			for (int j = 0; j < nbClients; j++) {
				if(sc.hasNext()){
					if(sc.hasNextDouble()){
						matriceDistance[i][j]=sc.nextDouble();
						matriceCouts[i][j] = coutParKm*matriceDistance[i][j];
					}
				}
			}
		}
	}
}