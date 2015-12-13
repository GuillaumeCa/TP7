interface Comparable <T> {
	int compareTo(T o);
}


public class ArbreBinaire implements Comparable<ArbreBinaire> {
	
	// Attributs
	String nomSommet;
	ArbreBinaire filsG,filsD;
	
	
	// Constructeur Sommet
	public ArbreBinaire(String nomSommet) {
		this.nomSommet = nomSommet;
	}
	
	// Constructeur Sommet + fils
	public ArbreBinaire(String nomSommet, ArbreBinaire filsG, ArbreBinaire filsD) {
		this.nomSommet = nomSommet;
		this.filsG = filsG;
		this.filsD = filsD;
	}
	
	public int compareTo(ArbreBinaire o) {
		return nomSommet.compareTo(this.nomSommet);
	}
	
	// Méthodes
	public static void affichagePrefixe(ArbreBinaire sommet) {
		System.out.print(sommet.nomSommet + " ");
		if (sommet.filsG != null){
			affichagePrefixe(sommet.filsG);
		}
		if (sommet.filsD != null){
			affichagePrefixe(sommet.filsD);
		}
	}
	
	
	public static int profondeur(ArbreBinaire sommet) {
		if (sommet == null){
			return 0;
		} else {
			return 1 + Math.max(profondeur(sommet.filsD), profondeur(sommet.filsG));
		}
	}
	
	public static String niveau(ArbreBinaire a, int prof) {
	    String G, D;
	    if (a == null) {
	    	return "";
	    }
	    if (prof > 0) {
	    	G = niveau(a.filsG, prof-1);
	    	D = niveau(a.filsD, prof-1);
	    	return G+D;
	    } else {
	    	return a.nomSommet;
	    }
	}
	
	public static void afficheEnLargeur(ArbreBinaire racine) {
	    int i = 0;
	    String s;
	    do {
	    	s = niveau(racine, i);
	    	System.out.print("("+s+") ");
	    	i++;
	    } while (niveau(racine, i).length() > 0);
	}
	
	public static ArbreBinaire inserer(String str, ArbreBinaire a) {
		if (a == null) {
			return new ArbreBinaire(str);
		}
		if (str.compareTo(a.nomSommet) <= 0) {
			a.filsG = inserer(str, a.filsG);
		}
		if (str.compareTo(a.nomSommet) > 0) {
			a.filsD = inserer(str, a.filsD);
		}
		return a;
	}
	
	public static boolean contient(String str, ArbreBinaire a) {
		if (a == null) return false;
		if (str == a.nomSommet) {
			return true;
		} else {
			return contient(str, a.filsD) || contient(str, a.filsG);
		}
	}
	
	public static int compteRecherche(String str, ArbreBinaire a) {
		if (!contient(str, a)) return 0;
		if (a == null)
			return 0;
		if (str == a.nomSommet) {
			return 1;
		} else {
			return 1 + compteRecherche(str, a.filsD) + compteRecherche(str, a.filsG);
		}
	}
	
	
	// Main
	public static void main(String[] args) {
		
		ArbreBinaire D = new ArbreBinaire("D");
		ArbreBinaire F = new ArbreBinaire("F");
		ArbreBinaire G = new ArbreBinaire("G");
		ArbreBinaire H = new ArbreBinaire("H");
		ArbreBinaire C = new ArbreBinaire("C", F, null);
		ArbreBinaire E = new ArbreBinaire("E", G, H);
		ArbreBinaire B = new ArbreBinaire("B", D, E);
		ArbreBinaire A = new ArbreBinaire("A",B,C);
		
		System.out.println("Affichage préfixé:");
		affichagePrefixe(A);
		
		System.out.println("\nProfondeur:");
		System.out.println(profondeur(A));
		
		
		System.out.println("Affichage en largeur:");
		afficheEnLargeur(A);
		
		System.out.println("");
		System.out.println("\nArbre binaire de recherche:");
		ArbreBinaire a = new ArbreBinaire("the");
		inserer("quick", a);
		inserer("brown", a);
		inserer("fox", a);
		inserer("jumps", a);
		inserer("over", a);
		inserer("the", a);
		inserer("lazy", a);
		inserer("dog", a);
		
		System.out.println("Affichage prefixé:");
		affichagePrefixe(a);
		
		System.out.println("\nAffichage en largeur:");
		afficheEnLargeur(a);
		
		String mot = "fox";
		System.out.println("\nL'arbre contient-il "+mot+" ? "+contient(mot, a));
		System.out.println("Nombre de noeuds explorés: "+compteRecherche(mot, a));
		
		System.out.println("");
		System.out.println("\nArbre binaire de recherche:");
		ArbreBinaire nombres = new ArbreBinaire("1");
		inserer("2", nombres);
		inserer("4", nombres);
		inserer("6", nombres);
		inserer("5", nombres);
		inserer("8", nombres);
		inserer("7", nombres);
		
		System.out.println("Affichage prefixé:");
		affichagePrefixe(nombres);
		
		System.out.println("\nAffichage en largeur:");
		afficheEnLargeur(nombres);
		
		String num = "4";
		System.out.println("\nL'arbre contient-il "+num+" ? "+contient(num, nombres));
		System.out.println("Nombre de noeuds explorés: "+compteRecherche(num, nombres));
	}

	

}
