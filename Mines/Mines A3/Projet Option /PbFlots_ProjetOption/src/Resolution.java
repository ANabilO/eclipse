import java.util.ArrayList;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.concert.IloObjectiveSense;
import ilog.cplex.IloCplex;

public class Resolution {


	public static void resoudre(Instance instance) throws IloException { 
		IloCplex cplex = new IloCplex();

		//Données d'entrée
		int nbClients = instance.nbClients;
		int nbChauffeurs = instance.nbChauffeurs;
		int nbVehicules = instance.nbVehicules;
		double capaciteVehicule = instance.capaciteVehicule;
		double coutFixe = instance.coutFixe;

		int[] clients = instance.clients;
		int[] chauffeurs = instance.chauffeurs; 
		double[] demandeClient = instance.demandeClient;
		double[] dureeChargement = instance.dureeChargement;
		ArrayList<double []> plageHoraires = instance.plageHoraires;

		double[][] matriceCouts = instance.matriceCouts;
		double[][] matriceTemps = instance.matriceTemps;

		//Déclaration de variables
		IloIntVar[][][] x = new IloIntVar[nbClients][nbClients][nbVehicules];
		IloNumVar[] t = new IloNumVar[nbClients];

		for (int i = 0; i < nbClients; i++) {
			for (int j = 0; j < nbClients; j++) {
				for (int k = 0; k <nbVehicules; k++) {
					x[i][j][k] = cplex.boolVar("x_" + i+ "_" + j+"_"+k);
				}
			}
		}


		for (int i = 0; i < nbClients; i++) {
			t[i] = cplex.numVar(0, Double.MAX_VALUE,"t_" + i);
		}
		//	cplex.addEq(0, t[0]);

		//FonctionObjectif
		IloLinearNumExpr objectif = cplex.linearNumExpr();

		for (int j = 1; j < nbClients; j++) {
			for (int k = 0; k < nbVehicules; k++) {
				objectif.addTerm(coutFixe, x[0][j][k]);
			}
		}

		for (int k = 0; k < nbVehicules; k++) {
			for (int i = 0; i < nbClients; i++) {
				for (int j = 0; j < nbClients; j++) {
					objectif.addTerm(matriceCouts[i][j],x[i][j][k]);
				}
			}
		}
		cplex.addObjective(IloObjectiveSense.Minimize, objectif);


		//Contraintes

		//Contraintes 1 & 2 : chaque client doit être visité une fois

		for (int i = 1; i < nbClients; i++) {
			IloLinearNumExpr expr= cplex.linearNumExpr();
			for (int k = 0; k <nbVehicules; k++) {
				for (int j = 0; j < nbClients; j++) {
					expr.addTerm(1, x[i][j][k]);
				}
			}
			cplex.addGe(expr, 1);
		}

		for (int i = 1; i < nbClients; i++) {
			for (int k = 0; k < nbVehicules; k++) {
				IloLinearNumExpr expr= cplex.linearNumExpr();
				for (int j = 0; j < nbClients; j++) {
					expr.addTerm(1, x[i][j][k]);
				}
				for (int j = 0; j < nbClients; j++) {
					expr.addTerm(-1, x[j][i][k]);
				}
				cplex.addEq(expr, 0);
			}
		}

		//Contrainte 3 : la capacité d'un véhicule ne peut être dépassée

		for (int k = 0; k < nbVehicules; k++) {
			IloLinearNumExpr expr= cplex.linearNumExpr();
			for (int i = 1; i < nbClients; i++) {
				for (int j = 0; j < nbClients; j++) {
					expr.addTerm(demandeClient[i], x[i][j][k]);
				}
			}
			cplex.addLe(expr, capaciteVehicule);
		}

		//Contraintes 4 & 5 : respect des fenetres de temps

		for (int i = 0; i < nbClients; i++) {
			for (int j = 1; j < nbClients; j++) {
				IloLinearNumExpr expr= cplex.linearNumExpr();
				double M = plageHoraires.get(i)[1]+dureeChargement[i]+matriceTemps[i][j]-plageHoraires.get(j)[0];
				expr.addTerm(1, t[i]);
				expr.addTerm(-1, t[j]);
				for (int k = 0; k < nbVehicules; k++) {
					expr.addTerm(M, x[i][j][k]);	
				}
				double UpBound = M -dureeChargement[i]-matriceTemps[i][j];
				cplex.addLe(expr, UpBound);
			}
		}

		for (int i = 0; i < nbClients; i++) {
			IloLinearNumExpr expr= cplex.linearNumExpr();
			expr.addTerm(1,t[i]);
			cplex.addLe(expr, plageHoraires.get(i)[1]);
			cplex.addGe(expr, plageHoraires.get(i)[0]);
		}

		/** Résolution du modèle **/

		// Parameters for the resolution
		cplex.setOut(System.out);
		cplex.exportModel("model.lp");

		if(cplex.solve()){
			System.out.println((cplex.getObjValue())); 
			System.out.println("Cplex status : " + cplex.getStatus());
			System.out.println("Value of the objectif function : " + (cplex.getObjValue()));
			System.out.println();
			double somme = 0;
			for (int k = 0; k < nbVehicules; k++) {
				for (int i = 0; i < nbClients; i++) {
					for (int j = 0; j < nbClients; j++) {
						somme += cplex.getValue(x[i][j][k])*matriceCouts[i][j];
					}
				}
			}
			System.out.println("cout total : "+somme);
			System.out.println();
			for (int k = 0; k <nbVehicules; k++) {
				for (int i = 0; i < nbClients; i++) {
					for (int j = 0; j < nbClients; j++) {
						System.out.println("x_"+i+"_"+j+"_"+k+" : "+ cplex.getValue(x[i][j][k]) + " plagesH "+plageHoraires.get(i)[0]+" -> "+plageHoraires.get(i)[1]+" heurePassage ");
					}
				}
			}
			for (int i = 0; i < nbClients; i++) {
				System.out.println(cplex.getValue(t[i]));
			}
		}else{
			System.out.println("no solution");
		}
	}

}
