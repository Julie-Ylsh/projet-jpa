package DAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.RandomStringUtils;

import dev.banque.AbstractCompte;
import dev.banque.AssuranceVie;
import dev.banque.Banque;
import dev.banque.Client;
import dev.banque.LivretA;
import dev.banque.Virement;

public class BanqueDAO {

	public void saveNewClient(Client nouveauClient, Banque nouvelleBanque) {

		// Ouverture de l'Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		// insertion du nouveau client en ouvrant une transaction
		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Ajout du client récupéré depuis AjouterClient
		em1.persist(nouveauClient);

		// Ajout de la Banque récupérée depuis AjouterClient
		em1.persist(nouvelleBanque);

		// Association du client et de la Banque
		nouveauClient.setBanque(nouvelleBanque);

		System.out.println("Le client a bien été ajouté dans la banque. Voici ses informations mises à jour :");

		// Affichage du client
		TypedQuery<Client> requete = em1
				.createQuery("select c from Client c where NOM='" + nouveauClient.getNom() + "'", Client.class);
		Client clientC = requete.getSingleResult();
		System.out.println(clientC);

		et.commit();
		em1.close();
		emf.close();

	}

	public List<Client> findAllClients() {

		// Ouverture de l'Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		// Affichage des clients
		TypedQuery<Client> requete = em1.createQuery("select c from Client c", Client.class);
		List<Client> listeClients = requete.getResultList();
		if (listeClients.isEmpty())
			System.out.println("Il n'y a aucun client dans la base de données");
		listeClients.forEach(unClient -> {
			System.out.println(unClient.getNom() + ", " + unClient.getPrenom() + ", " + unClient.getDateNaissance()
					+ ", " + unClient.getAdresse() + ", " + unClient.getBanque());
		});
		em1.close();
		emf.close();
		return listeClients;

	}

	public void saveNewLivretA(Client client) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du livretA avec des valeurs par défaut
		LivretA la = new LivretA();
		la.setTaux(0.2);

		// Création d'un numéro random qui sera le numéro de compte
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		la.setNumero(randomAlphabetic);

		// Démarrage du solde à zéro (faut pas exagérer quand même !)
		la.setSolde(0d);

		// Insertion du livret A dans la base de données
		em1.persist(la);

		// Recherche du client concerné par l'ouverture du compte et ajout de ce
		// compte au client
		client = em1.find(Client.class, client.getId());
		if (client != null) {
			client.setComptes(la);
		}

		et.commit();
		em1.close();
		emf.close();

	}

	public void saveNewLivretAJoint(Client client1, Client client2) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du livretA avec des valeurs par défaut
		LivretA la = new LivretA();
		la.setTaux(0.2);

		// Création d'un numéro random qui sera le numéro de compte
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		la.setNumero(randomAlphabetic);

		// Démarrage du solde à zéro (faut pas exagérer quand même !)
		la.setSolde(0d);

		// Insertion du livret A dans la base de données
		em1.persist(la);

		// Recherche des clients concernés et ajout de leur Livret A à tous les
		// 2
		client1 = em1.find(Client.class, client1.getId());
		if (client1 != null) {
			client1.setComptes(la);
		}

		client2 = em1.find(Client.class, client2.getId());
		if (client2 != null) {
			client2.setComptes(la);
		}

		et.commit();
		em1.close();
		emf.close();

	}

	public void saveNewAssuranceVie(Client client) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création de l'AV avec des valeurs par défaut
		AssuranceVie av = new AssuranceVie();
		av.setTaux(0.3);
		av.setDateFin(LocalDate.now().plus(8, ChronoUnit.YEARS));

		// Création d'un numéro random qui sera le numéro de compte
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		av.setNumero(randomAlphabetic);

		// Démarrage du solde à zéro (faut pas exagérer quand même !)
		av.setSolde(0d);

		// Insertion de l'AV dans la base de données
		em1.persist(av);

		// Recherche du client concerné par l'ouverture du compte et ajout de ce
		// compte au client
		client = em1.find(Client.class, client.getId());
		if (client != null) {
			client.setComptes(av);
		}

		et.commit();
		em1.close();
		emf.close();

	}

	public void saveNewAssuranceVieJoint(Client client1, Client client2) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création de l'AV avec des valeurs par défaut
		AssuranceVie av = new AssuranceVie();
		av.setTaux(0.3);
		av.setDateFin(LocalDate.now().plus(8, ChronoUnit.YEARS));

		// Création d'un numéro random qui sera le numéro de compte
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		av.setNumero(randomAlphabetic);

		// Démarrage du solde à zéro (faut pas exagérer quand même !)
		av.setSolde(0d);

		// Insertion de l'Assurance Vie dans la base de données
		em1.persist(av);

		// Recherche des clients concernés et ajout de leur Assurance Vie à tous
		// les 2
		client1 = em1.find(Client.class, client1.getId());
		if (client1 != null) {
			client1.setComptes(av);
		}

		client2 = em1.find(Client.class, client2.getId());
		if (client2 != null) {
			client2.setComptes(av);
		}

		et.commit();
		em1.close();
		emf.close();

	}

	public Client findAClient(String nomClient, String prenomClient) {
		// Ouverture de l'Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		// Affichage du client
		TypedQuery<Client> requete = em1.createQuery(
				"select c from Client c where (NOM='" + nomClient + "' and PRENOM = '" + prenomClient + "')",
				Client.class);
		Client clientC = new Client();
		try {
			clientC = requete.getSingleResult();
		} catch (NoResultException e) {
			clientC = null;
		}

		em1.close();
		emf.close();

		return clientC;
	}

	public void findComptes(Client client) {
		// Ouverture de l'Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		// Sortie de l'ID CLient
		int idClient = client.getId();


		// Affichage des comptes
		Query requete = em1.createQuery("select c.comptes from Client c where c.id=:idClient");
		requete.setParameter("idClient", idClient);
		List<AbstractCompte> listeComptes = requete.getResultList();
		if (listeComptes.isEmpty())
			System.out.println("Aucun compte n'est associé à ce client.");
		
		else System.out.println(listeComptes);
		
		
		//else {
		//	affichage = listeComptes.stream().map(unCompte -> "Compte numéro " + unCompte.getId() + ", solde : "
		//			+ unCompte.getSolde() + ", opérations effectuées : " + unCompte.getVirements())
		//			.collect(Collectors.joining("\n"));
		//}

		em1.close();
		emf.close();

		
	}

	public void saveNewVirement(Virement virement, Double montant, String motif, int idCompte) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Récupération du compte concerné
		TypedQuery<AbstractCompte> requete = em1.createQuery(
				"select ct from AbstractCompte ct where (id='" + idCompte + "' )", AbstractCompte.class);
		AbstractCompte compteC = null;
		try {
			compteC = requete.getSingleResult();
		} catch (NoResultException e) {
			compteC = null;
		}

		// Création du virement avec des valeurs données
		Virement v = new Virement();
		v.setBeneficiaire(virement.getBeneficiaire());
		v.setCompte(compteC);
		v.setDate(LocalDateTime.now());
		v.setMontant(montant);
		v.setMotif(motif);
		em1.persist(v);

		et.commit();
		em1.close();
		emf.close();

	}

}
