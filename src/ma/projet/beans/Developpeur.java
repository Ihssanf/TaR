package ma.projet.beans;

public class Developpeur extends Personne {
  
    public Developpeur(String nom, double salaire) {
        super(nom, salaire);
        this.setType("developpeur"); 
    }
}