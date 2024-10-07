package ma.projet.beans;

public class Manager extends Personne {

    public Manager(String nom, double salaire, Integer managerId) {
        super(nom, salaire);
        this.setManagerId(managerId); 
        this.setType("manager"); 
    }

}