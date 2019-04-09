package BanqueService;

import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

import DAO.BanqueDAO;
import dev.banque.Client;
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

		// Cas où le client n'existe pas
		if (clientConcerne == null)
			System.out.println("Le client concerné n'existe pas");

		// Cas où le client existe
		else {

			System.out.println("Voici la liste des comptes associés : ");
			toto.findComptes(clientConcerne);

			// Boucle pour savoir si c'est bien un numéro
			System.out.println("Merci de sélectionner un compte par son id");
			String compteIdSrt = null;
			do {
				compteIdSrt = question.nextLine();

				if (NumberUtils.isNumber(compteIdSrt) == false) {
					System.out.println("Ceci n'est pas un numéro, doofus");
				}
			}

			while (StringUtils.isStrictlyNumeric(compteIdSrt) == false);

			int compteId = Integer.parseInt(compteIdSrt);

			System.out.println("Pour quelle personne souhaitez-vous faire un virement ? Ecrivez le nom et le prénom");
			String nomPrenom = question.nextLine();

			System.out.println("Quel montant souhaitez-vous virer ? Inscrivez-le sans les euros");
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

			// Création du nouveau virement
			Virement virement = new Virement(nomPrenom);
			toto.saveNewVirement(virement, montant, motif, compteId);
			
			System.out.println("Votre virement a bien été effectué");

		}

	}
}
