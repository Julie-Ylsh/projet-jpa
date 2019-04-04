package dev.projet_jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.mysql.cj.util.StringUtils;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Scanner questionLivre = new Scanner(System.in);
		System.out.println("Bonjour, veuillez choisir un numéro de 1 à 5");

		String questionStr = null;
		int question = 0;
		do {
			questionStr = questionLivre.nextLine();

			if (StringUtils.isStrictlyNumeric(questionStr) == false) {
				System.out.println("Ceci n'est pas un numéro, doofus");
				question = 6;
			}

			else if (!(questionStr.equals("1") || questionStr.equals("2") || questionStr.equals("3")
					|| questionStr.equals("4") || questionStr.equals("5"))) {
				System.out.println("Ceci n'est pas un numéro de 1 à 5");
			}

		}

		while (!(questionStr.equals("1") || questionStr.equals("2") || questionStr.equals("3")
				|| questionStr.equals("4") || questionStr.equals("5")));

		question = Integer.parseInt(questionStr);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");

		EntityManager em1 = emf.createEntityManager();

		TypedQuery<Emprunt> requete = em1.createQuery("select e from Emprunt e where e.id='" + question + "'",
				Emprunt.class);
		List<Emprunt> listeLivre = requete.getResultList();
		listeLivre.forEach(unEmprunt -> {
			System.out
					.println("Emprunt numéro : " + unEmprunt.getId() + " / Livres associés : " + unEmprunt.getLivre());
		});

		System.out.println("Merci de saisir un nom de client pour trouver les livres empruntés");
		TypedQuery<Client> requete2 = em1.createQuery("select c from Client c", Client.class);
		List<Client> listeClients = requete2.getResultList();
		listeClients.forEach(unClient -> {
			System.out.println(unClient.getNom() + ", " + unClient.getPrenom());
		});

		String nomClientStr = null;
		nomClientStr = questionLivre.nextLine();

		TypedQuery<Client> requete3 = em1.createQuery("select c from Client c where c.nom='" + nomClientStr + "'",
				Client.class);
		List<Client> listeClients2 = requete3.getResultList();
		listeClients2.forEach(unClient -> {
			System.out.println("Client " + unClient.getNom() + ", "
					+ unClient.getEmprunt().stream().map(e -> e.toString()).collect(Collectors.joining("\n")));

		});
		// Questions scanner pour créer le nom du livre et l'auteur
		System.out.println("Pour emprunter un nouveau livre, merci de taper son nom :");
		String livreTitreNouveau = questionLivre.nextLine();

		System.out.println("Connaissez-vous l'auteur ?");
		String livreAuteurNouveau = questionLivre.nextLine();

		// insertion du nouveau livre en ouvrant une transaction
		EntityTransaction et = em1.getTransaction();
		et.begin();
		Livre nouveauLivre = new Livre(livreTitreNouveau, livreAuteurNouveau);
		em1.persist(nouveauLivre);

		System.out.println("Votre livre a bien été ajouté");

		// Récupération de l'ID du nouveau livre
		//long idNouveauLivre = nouveauLivre.getId();

		
		Client client = requete3.getSingleResult();
		Emprunt nouvelEmprunt = new Emprunt();
		nouvelEmprunt.getLivre().add(nouveauLivre);
		nouvelEmprunt.setClient(client);
		nouvelEmprunt.setDateDebut(LocalDateTime.now());
		nouvelEmprunt.setDelai(21);
		em1.persist(nouvelEmprunt);
		
		System.out.println("Votre emprunt a bien été pris en compte. Vous avez " + nouvelEmprunt.getDelai()+ " jours à compter d'aujourd'hui pour le rendre");
		

		et.commit();
		em1.close();
		emf.close();
		questionLivre.close();
	}

}
