package BanqueService;

import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import DAO.BanqueDAO;
import dev.banque.Client;
import dev.banque.Compte;
import dev.banque.Operation;
import dev.banque.Virement;
import utils.NumberUtils;

public class FaireOperation extends BanqueService {
	public void executeUC(Scanner question, BanqueDAO toto) {
		// Demande du type de compte
		System.out.println(
				"Bonjour, la seule opération possible aujourd'hui est un virement. Nous allons donc avoir besoin de quelques informations");
		System.out.println("Pouvez-vous nous donner votre nom ?");

		// Recherche du client via la méthode findAClient
		String nomClient = question.nextLine();

		System.out.println("Pouvez-vous également donner votre prénom ?");
		String prenomClient = question.nextLine();

		Client clientConcerne = toto.findAClient(nomClient, prenomClient);

		// Cas où le client possède plusieurs comptes
		if (clientConcerne.getComptes().size() > 1) {
			System.out.println("Voici la liste des comptes associés : ");
			System.out.println(clientConcerne.getComptes());

			// Boucle pour savoir si c'est bien un numéro
			System.out.println("Merci de sélectionner un compte par son id");
			String questionIdSrt = null;
			do {
				questionIdSrt = question.nextLine();

				if (NumberUtils.isNumber(questionIdSrt) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
			}

			while (StringUtils.isStrictlyNumeric(questionIdSrt) == false);

			int questionId = Integer.parseInt(questionIdSrt);

			// récupérer le compte à partir de son ID
			Compte compteConcerne = toto.findCompte(questionId);

			System.out.println("Pour quelle personne souhaitez-vous faire un virement ? Ecrivez le nom et le prénom");
			String nomPrenom = question.nextLine();

			// Création du nouveau virement
			Virement virement = new Virement(nomPrenom);
			toto.saveNewVirement(virement);

			System.out.println("Quel montant souhaitez-vous virer ?");
			// Boucle pour savoir si c'est bien un numéro
			String montantStr = null;
			do {
				montantStr = question.nextLine();

				if (NumberUtils.isNumber(montantStr) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
			}

			while (StringUtils.isStrictlyNumeric(montantStr) == false);
			Double montant = Double.parseDouble(montantStr);

			System.out.println("Inscrivez un motif pour cette opération");
			String motif = question.nextLine();

			// Création de l'opération
			Operation operation = new Operation(montant, motif, compteConcerne);
			toto.saveNewOperation(operation);

		}

		// Cas où le client possède un compte
		if (clientConcerne.getComptes().size() == 1) {
			System.out.println("Voici le compte associé : ");
			System.out.println(clientConcerne.getComptes());

			// Boucle pour savoir si c'est bien un numéro
						System.out.println("Merci de sélectionner le compte par son id");
						String questionIdSrt = null;
						do {
							questionIdSrt = question.nextLine();

							if (NumberUtils.isNumber(questionIdSrt) == false) {
								System.out.println("Ceci n'est pas un numéro, doofus");
							}
						}

						while (StringUtils.isStrictlyNumeric(questionIdSrt) == false);

						int questionId = Integer.parseInt(questionIdSrt);

			// récupérer le compte à partir de son ID
			Compte compteConcerne = toto.findCompte(questionId);

			System.out.println("Pour quelle personne souhaitez-vous faire un virement ? Ecrivez le nom et le prénom");
			String nomPrenom = question.nextLine();

			// Création du nouveau virement
			Virement virement = new Virement(nomPrenom);
			toto.saveNewVirement(virement);

			System.out.println("Quel montant souhaitez-vous virer ?");
			// Boucle pour savoir si c'est bien un numéro
			String montantStr = null;
			do {
				montantStr = question.nextLine();

				if (NumberUtils.isNumber(montantStr) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
			}

			while (StringUtils.isStrictlyNumeric(montantStr) == false);
			Double montant = Double.parseDouble(montantStr);

			System.out.println("Inscrivez un motif pour cette opération");
			String motif = question.nextLine();

			// Création de l'opération
			Operation operation = new Operation(montant, motif, compteConcerne);
			toto.saveNewOperation(operation);
		}

		// Cas où le client ne possède pas de compte
		if (clientConcerne.getComptes().size() < 1) {
			System.out.println("Aucun compte n'est associé à ce client.");
		}

	}
}
