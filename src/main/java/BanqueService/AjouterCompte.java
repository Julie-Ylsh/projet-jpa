package BanqueService;

import java.util.Scanner;

import DAO.BanqueDAO;
import dev.banque.Client;
import utils.NumberUtils;

public class AjouterCompte extends BanqueService {
	public void executeUC(Scanner question, BanqueDAO toto) {
		// Demande du type de compte
		System.out.println("Bonjour, quel type de compte souhaitez-vous ajouter ?");
		System.out.println("Tapez 1 pour un compte perso (1 client)");
		System.out.println("Tapez 2 pour un compte joint (2 clients)");

		// Vérification du bon nombre
		String typeCompteSrt = null;
		do {
			typeCompteSrt = question.nextLine();

			if (NumberUtils.isNumber(typeCompteSrt) == false) {
				System.out.println("Ceci n'est pas un numéro, doofus");
			}
			if (!(typeCompteSrt.equals("1") || typeCompteSrt.equals("2"))) {
				System.out.println("Vous n'avez pas tapé le bon choix");

			}
		} while (NumberUtils.isNumber(typeCompteSrt) == false
				|| (!(typeCompteSrt.equals("1") || typeCompteSrt.equals("2"))));

		// Ce qu'il se passe pour la création d'un compte perso
		if (typeCompteSrt.equals("1")) {
			System.out.println("Pour quel client voulez-vous créer un compte ? Inscrivez le nom de famille");

			// Recherche du client via la méthode findAClient
			String nomClient = question.nextLine();

			System.out.println("Inscrivez également le prénom");
			String prenomClient = question.nextLine();
			Client clientConcerne = toto.findAClient(nomClient, prenomClient);

			toto.saveNewCompte(clientConcerne);

			System.out.println("Quel type de compte souhaitez-vous ?");
			System.out.println("Tapez 1 pour un livretA (taux 20%)");
			System.out.println("Tapez 2 pour une assurance vie (taux 30%)");

			// Vérification du bon nombre
			String typeCompteSrt2 = null;
			do {
				typeCompteSrt2 = question.nextLine();

				if (NumberUtils.isNumber(typeCompteSrt2) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
				if (!(typeCompteSrt2.equals("1") || typeCompteSrt2.equals("2"))) {
					System.out.println("Vous n'avez pas tapé le bon choix");

				}
			} while (NumberUtils.isNumber(typeCompteSrt2) == false
					|| (!(typeCompteSrt2.equals("1") || typeCompteSrt2.equals("2"))));

			// Ce qu'il se passe pour la création d'un livret A
			if (typeCompteSrt.equals("1")) {
				toto.saveNewLivretA(clientConcerne);
			}

			// Ce qu'il se passe pour la création d'una assurance vie
			if (typeCompteSrt.equals("2")) {
				toto.saveNewAssuranceVie(clientConcerne);

			}

			System.out.println("Votre compte a bien été ajouté");

		}

		// Ce qu'il se passe pour la création d'un compte joint
		if (typeCompteSrt.equals("2")) {

			// Recherche du client1 via la méthode findAClient
			System.out.println("Donnez-moi le nom du premier client concerné");

			String nomClient1 = question.nextLine();

			System.out.println("Inscrivez également le prénom");
			String prenomClient = question.nextLine();
			Client clientConcerne1 = toto.findAClient(nomClient1, prenomClient);

			// Recherche du client2 via la méthode findAClient
			System.out.println("Donnez-moi le nom du deuxième client concerné");
			String nomClient2 = question.nextLine();

			System.out.println("Inscrivez également le prénom");
			String prenomClient2 = question.nextLine();
			Client clientConcerne2 = toto.findAClient(nomClient2, prenomClient2);

			toto.saveNewCompteJoint(clientConcerne1, clientConcerne2);

			System.out.println("Quel type de compte souhaitez-vous ?");
			System.out.println("Tapez 1 pour un livretA (taux 20%)");
			System.out.println("Tapez 2 pour une assurance vie (taux 30%)");

			// Vérification du bon nombre
			String typeCompteSrt2 = null;
			do {
				typeCompteSrt2 = question.nextLine();

				if (NumberUtils.isNumber(typeCompteSrt2) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
				if (!(typeCompteSrt2.equals("1") || typeCompteSrt2.equals("2"))) {
					System.out.println("Vous n'avez pas tapé le bon choix");

				}
			} while (NumberUtils.isNumber(typeCompteSrt2) == false
					|| (!(typeCompteSrt2.equals("1") || typeCompteSrt2.equals("2"))));

		}
	}

}
