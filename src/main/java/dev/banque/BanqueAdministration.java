package dev.banque;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import BanqueService.AjouterClient;
import BanqueService.AjouterCompte;
import BanqueService.FaireOperation;
import BanqueService.ListerClient;
import DAO.BanqueDAO;

public class BanqueAdministration {
	private static final Logger LOGGER = LoggerFactory.getLogger(BanqueAdministration.class);

	public static void main(String[] args) {

		LOGGER.info("L'application vient de démarrer.");

		BanqueDAO dao = new BanqueDAO();
		Scanner question = new Scanner(System.in);

		// Afficher les options du menu
		System.out.println(
				"***** Bienvenue dans le logiciel de gestion des banques, des clients, et toute la finance de tout le mond entier *****");


		try  {
		boolean fin = false;
		while (fin == false) {

			// On demande quelle action l'utilisateur veut faire
			System.out.println("/n" + "Menu principal. Veuillez faire votre choix");
			System.out.println("1. Afficher la liste des clients");
			System.out.println("2. Ajouter un nouveau client");
			System.out.println("3. Ajouter un compte à des clients existants");
			System.out.println("4. Effectuer une opération sur un compte existant");
			System.out.println("99. Sortir");

			String z = question.nextLine();
			int numeroQuestion = Integer.parseInt(z);
		
			
				
			
			if (numeroQuestion != 99) {
				fin = false;
				switch (numeroQuestion) {
				case 1:

					ListerClient clients = new ListerClient();
					clients.executeUC(question, dao);

					break;
				case 2:
					AjouterClient ajout = new AjouterClient();

					ajout.executeUC(question, dao);

					break;
				case 3:
					AjouterCompte ajoutCmpte = new AjouterCompte();
					ajoutCmpte.executeUC(question, dao);

					break;

				case 4:
					FaireOperation operation = new FaireOperation();
					operation.executeUC(question, dao);

					break;

				default:
					System.out.println("Je n'ai pas compris votre choix, merci de réessayer");

				}

			} else

			{
				System.out.println("Au revoir !");
				fin = true;
			}

		}
		} catch (RuntimeException e) {
			LOGGER.error("une errru   ...", e);
			System.out.println("La connexion au serveur a échoué. Merci de recommencer plus tard.");
		} finally {
			question.close();
		}
		
	}
}
