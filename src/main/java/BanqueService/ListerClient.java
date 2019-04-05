package BanqueService;

import java.util.List;
import java.util.Scanner;

import DAO.BanqueDAO;
import dev.banque.Client;

public class ListerClient extends BanqueService {
	public void executeUC(Scanner question, BanqueDAO dao) {
		System.out.println("Liste des clients");
		
		// La fonction pour afficher
		List<Client> clients = dao.findAllClients();
	}

}
