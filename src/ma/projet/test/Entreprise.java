package ma.projet.test;

import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;

public class Entreprise {
    public static void main(String[] args) {
        DeveloppeurService developpeurService = new DeveloppeurService();
        ManagerService managerService = new ManagerService();

        
        Developpeur developpeur1 = new Developpeur("ihssane elmaizi", 50000);
        developpeurService.create(developpeur1);

        Developpeur developpeur2 = new Developpeur("aaa bbb", 60000);
        developpeurService.create(developpeur2);

        Manager manager = new Manager("bbb ccc", 80000, null);
        manager.setType("manager"); 
        managerService.create(manager);

        Developpeur developpeur3 = new Developpeur("ccc ddd", 60000);
        developpeurService.create(developpeur3);

       
        Manager directeurGeneral = new Manager("ddd eee", 100000, null);
        directeurGeneral.setId(5);
        directeurGeneral.setType("directeur général");

        if (!managerService.exists(directeurGeneral)) {
            managerService.create(directeurGeneral);
        } else {
            System.out.println("Le directeur général existe déjà, évitant la création.");
        }

        developpeur1.setManagerId(manager.getId());
        developpeurService.update(developpeur1); 
        developpeur2.setManagerId(manager.getId()); 
        developpeurService.update(developpeur2); 

       
        manager.setManagerId(directeurGeneral.getId()); 
        managerService.update(manager); 
        developpeur3.setManagerId(directeurGeneral.getId()); 
        developpeurService.update(developpeur3); 

        
        System.out.println("Directeur général : " + directeurGeneral.getNom() + " - Salaire : " + directeurGeneral.getSalaire());
        System.out.println("Manager : " + manager.getNom() + " - Salaire : " + manager.getSalaire());
        System.out.println("Développeur 1 : " + developpeur1.getNom() + " - Salaire : " + developpeur1.getSalaire());
        System.out.println("Développeur 2 : " + developpeur2.getNom() + " - Salaire : " + developpeur2.getSalaire());
        System.out.println("Développeur 3 : " + developpeur3.getNom() + " - Salaire : " + developpeur3.getSalaire());
    }
}