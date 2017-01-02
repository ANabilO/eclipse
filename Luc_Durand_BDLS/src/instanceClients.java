import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class instanceClients {
	
	/* Variables
	 * 
	 */	
	
	private int nbClients;			
	private int nbVehicules;
	private int capacite;
	private double coutKm;
	private double coutFixe;
	private int nbCreneaux;
	private int nbChauffeurs;
	private double [][] matDistance;
	private int [] quantité;       	    // qté à déposer chez i
	private ArrayList<boolean[]> jour;  // Arraylist de tableaux de booleans : 
									    // Chaque tableau est de taille 5 et chaque case vaut true si on peut livrer ce jour
	private int [] a_am; 				//borne inférieure de l'intervalle pour visite matin
	private int [] b_am; 				//borne supérieure de l'intervalle pour visite après-midi
	private int [] a_pm; 				//borne inférieure de l'intervalle pour visite matin
	private int [] b_pm; 				//borne supérieure de l'intervalle pour visite après-midi
	
	/* getters
	 * 
	 */
	
	public int [] getQuantité() {
		return quantité;
	}
	public ArrayList<boolean[]> getJour() {
		return jour;
	}
	public int [] getA_am() {
		return a_am;
	}
	public int [] getB_am() {
		return b_am;
	}
	public int [] getA_pm() {
		return a_pm;
	}
	public int [] getB_pm() {
		return b_pm;
	}
	public int getNbClients() {
		return nbClients;
	}
	public int getNbVehicules() {
		return nbVehicules;
	}
	public int getNbChauffeurs() {
		return nbChauffeurs;
	}
	public double [][] getMatDistance() {
		return matDistance;
	}
	public int getCapacite() {
		return capacite;
	}	
	public double getCoutKm() {
		return coutKm;
	}
	public double getCoutFixe() {
		return coutFixe;
	}
	public int getNbCreneaux() {
		return nbCreneaux;
	}
	
	/* setters
	 * 
	 */
	
	public void setQuantité(int [] quantité) {
		this.quantité = quantité;
	}
	public void setJour(ArrayList<boolean[]> jour) {
		this.jour = jour;
	}
	public void setA_am(int [] a) {
		this.a_am = a;
	}
	public void setB_am(int [] b) {
		this.b_am = b;
	}	
	public void setA_pm(int [] a_pm) {
		this.a_pm = a_pm;
	}
	public void setB_pm(int [] b_pm) {
		this.b_pm = b_pm;
	}
	public void setNbClients(int nbClients) {
		this.nbClients = nbClients;
	}
	public void setNbVehicules(int nbVehicules) {
		this.nbVehicules = nbVehicules;
	}
	public void setNbChauffeurs(int nbChauffeurs) {
		this.nbChauffeurs = nbChauffeurs;
	}
	public void setMatDistance(double [][] matDistance) {
		this.matDistance = matDistance;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public void setCoutKm(double coutKm) {
		this.coutKm = coutKm;
	}
	public void setCoutFixe(double coutFixe) {
		this.coutFixe = coutFixe;
	}
	public void setNbCreneaux(int nbCreneaux) {
		this.nbCreneaux = nbCreneaux;
	}
	/* Constructeurs
	 * 
	 */
	
	// Le fichier instance doit donner les informations dans le même ordre que ci-dessous 
	
	public instanceClients (String nomFichier) throws IOException { 
		File mfile = new File(nomFichier);
		if (!mfile.exists()) {
			throw new IOException("Le fichier saisi : " + nomFichier + ", n'existe pas.");
		}
		Scanner sc = new Scanner(mfile);
		nbVehicules = sc.nextInt();
		sc.nextLine();
		nbChauffeurs = sc.nextInt();
		sc.nextLine();
		nbClients = sc.nextInt();
		sc.nextLine();
		capacite = sc.nextInt();
		sc.nextLine();
		coutKm = sc.nextDouble();
		sc.nextLine();
		coutFixe = sc.nextDouble();
		sc.nextLine();
		nbCreneaux = sc.nextInt();
		
		// Remplissage de la matrice des distances 
		
		matDistance = new double[nbClients+1][nbClients+1];	
		for (int i = 0; i < matDistance.length; i++) {
			sc.nextLine();
			for (int j = 0; j < nbClients+1; j++) {
				matDistance[i][j] = sc.nextDouble();
			}
		}
		sc.nextLine();
		
		// Remplissage des quantités de linge à récolter et déposer par client 
		
		quantité = new int[nbClients];
		for (int i = 0; i < nbClients; i++) {
			quantité[i] = sc.nextInt();
		}
		
		// Remplissage des booléens pour savoir sur quels jours de la semaine on peut livrer 
		
		jour = new ArrayList<boolean[]>();
		for (int i = 0; i < nbClients; i++) {
			sc.nextLine();
			boolean [] bl = new boolean[nbCreneaux];
			for (int j = 0; j < nbCreneaux; j++) {
				String st = sc.next();
				if (st.equals("false")) {
					bl[j] = false;
				}
				else bl[j] = true;
			}
			jour.add(bl);
		}
		
		// Remplissage de a_am
		
		sc.nextLine();
		a_am = new int[nbClients];
		for (int i = 0; i < nbClients; i++) {
			int p = sc.nextInt();
			a_am[i] = p*60;
		}
		
		// Remplissage de b_am
		
		sc.nextLine();
		b_am = new int[nbClients];
		for (int i = 0; i < nbClients; i++) {
			int p = sc.nextInt();
			b_am[i] = 60*p;
		}

		// Remplissage de a_pm
		sc.nextLine();
		a_pm = new int[nbClients];
		for (int i = 0; i < nbClients; i++) {
			int p = sc.nextInt();
			a_pm[i] = 60*p;
		}
		
		// Remplissage de b_pm
		sc.nextLine();
		b_pm = new int[nbClients];
		for (int i = 0; i < nbClients; i++) {
			int p = sc.nextInt();
			b_pm[i] = 60*p;
		}
		sc.close();
	}
	
	
}
