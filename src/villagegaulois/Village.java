package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtal;
		
		private Marche(int nbEtal) {
			this.nbEtal = nbEtal;
			for (int i1=0; i1<nbEtal;i1++){
				this.etals[i1]= new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int indiceEtal=-1;
			for (int i=0; i<nbEtal && indiceEtal==-1; i++){
				if(!etals[i].isEtalOccupe()) {
					indiceEtal = i;
				}
			}
			return indiceEtal;
		}
		
		private Etal[] trouverEtals(String produit){
			int nbEtalProduit = 0;
			for (int i=0; i<nbEtal; i++){
				if(etals[i].contientProduit(produit)){
					nbEtalProduit++;
				}
			}
			Etal[] pEtals = new Etal[nbEtalProduit];
			int indiceTab = 0;
			for(int i=0; i<nbEtal; i++) {
				if(etals[i].contientProduit(produit)){
					pEtals[indiceTab]=etals[i];
					indiceTab++;
				}
			}
			return pEtals;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal gEtal = null;
			for (int i=0; i<nbEtal && gEtal == null; i++){
				if(etals[i].getVendeur()==gaulois){
					gEtal = etals[i];
				}
			}
			return gEtal;
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = -1;
			for (int i=0; i<nbEtal && nbEtalVide == -1; i++){
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nbEtalVide = nbEtal - i;
					chaine.append("Il reste "+nbEtalVide+" étals non utilisés dans le marché. \n");
				}
			}
			return chaine.toString();
		}
			
	}
	
	
	
	
	
	
	
	
}