package BanqueService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.cj.util.StringUtils;

import DAO.BanqueDAO;
import dev.banque.Adresse;
import dev.banque.Banque;
import dev.banque.Client;

public class AjouterClient extends BanqueService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AjouterClient.class);

	public void executeUC(Scanner question, BanqueDAO toto) {

		System.out.println("Bonjour, bienvenue dans l'application banque. Merci d'insérer un nouveau client.");
		System.out.println("Commencez par écrire son nom");

		// Demande du nom du client
		String clientNouveauNom = question.nextLine();

		// Deamnde du prénom
		System.out.println("Merci d'écrire également son prénom");
		String clientNouveauPrénom = question.nextLine();

		// Demande de la date de naissance
		System.out.println("Pouvez-vous m'indiquer sa date de naissance ? Au format 01/01/2019.");
		String clientNouveauDateStr = null;
		boolean verifDate = true;
		do {
			try {
				clientNouveauDateStr = question.nextLine();
				LocalDate.parse(clientNouveauDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				verifDate = true;
			} catch (DateTimeParseException e) {
				verifDate = false;
				LOGGER.error(e.getLocalizedMessage());
				System.out.println("Qu'est-ce que vous ne comprenez pas dans \"au format dd/MM/yyyy\" ?");
			}

		}

		while (verifDate == false);

		// Parser un string en LocalDate
		// LocalDate.parse(format doit e^tre yyy-mm-dd);
		LocalDate clientNouveauDate = LocalDate.parse(clientNouveauDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		// Demande de l'adresse
		System.out.println("J'aurais également besoin d'une adresse. Numéro de rue ?");
		String clientNouveauNuméroStr = null;

		// Boucle pour savoir si c'est un numéro
		do {
			clientNouveauNuméroStr = question.nextLine();

			if (StringUtils.isStrictlyNumeric(clientNouveauNuméroStr) == false) {
				System.out.println("Ceci n'est pas un numéro, doofus");
			}
		}

		while (StringUtils.isStrictlyNumeric(clientNouveauNuméroStr) == false);

		int clientNouveauNuméro = Integer.parseInt(clientNouveauNuméroStr);

		// Demande du nom de la rue
		System.out.println("Bien. Intitulé de la rue ?");
		String clientNouveauRue = question.nextLine();

		// Demande du nom de la ville
		System.out.println("Merci. Ville, s'il vous plaît.");
		String clientNouveauVille = question.nextLine();

		// Demande du nom du coe postal
		System.out.println("Et pour finir, le code postal associé !");
		String clientNouveauCodePostalStr = null;

		// Boucle pour savoir si c'est un numéro
		do {
			clientNouveauCodePostalStr = question.nextLine();

			if (StringUtils.isStrictlyNumeric(clientNouveauCodePostalStr) == false) {
				System.out.println("Ceci n'est pas un numéro, doofus");
			}
		}

		while (StringUtils.isStrictlyNumeric(clientNouveauCodePostalStr) == false);

		int clientNouveauCodePostal = Integer.parseInt(clientNouveauCodePostalStr);

		// Définition de la nouvelle adresse puis d'un nouveau client
		Adresse nouvelleAdresse = new Adresse(clientNouveauNuméro, clientNouveauRue, clientNouveauVille,
				clientNouveauCodePostal);
		Client nouveauClient = new Client(clientNouveauNom, clientNouveauPrénom, clientNouveauDate, nouvelleAdresse);

		System.out.println("Merci. Votre client a bien été créé.");

		// Demande pour le choix d'une banque
		System.out.println("Si vous souhaitez lui ajouter une banque, merci de taper le nom de la banque");
		String nomBanque = question.nextLine();

		// Ajout du client dans une nouvelle liste de client pour la définition
		// de la banque.
		// Pour l'instant, on crée une nouvelle banque pour chaque nouveau
		// client (sera changé si j'ai le temps de finir les tp d'abord)
		List<Client> clientNvBanque = new ArrayList<>();
		clientNvBanque.add(nouveauClient);
		Banque nouvelleBanque = new Banque(nomBanque, clientNvBanque);

		toto.saveNewClient(nouveauClient, nouvelleBanque);

	}
}
