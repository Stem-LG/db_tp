import ma.projet.beans.client;
import ma.projet.service.ClientService;

public class Test {
    public static void main(String[] args) {
        ClientService cs = new ClientService(); // Création des clients
        // cs.create(new client("SAFI", "ali"));
        // cs.create(new client("ALAOUI", "widane"));
        // cs.create(new client("RAMI", "amine"));
        // cs.create(new client("ALAMI", "kamal"));
        // cs.create(new client("SELAMI", "mohamed"));
        // // Afficher le client dont id = 3 client
        // client c = cs.findById(3);
        // System.out.println(c.getNom() + " " + c.getPrenom());
        // // Supprimer le client dont id = 3
        // cs.delete(cs.findById(3));
        // // Modifier le client dont id = 2
        client cc = cs.findById(2);
        cc.setNom("nouveau nom");
        cc.setPrenom("nouveau Prénom");
        cs.update(cc);
        // Afficher la liste des clients
        for (client cl : cs.findAll())
            System.out.println(cl.getNom());
    }
}