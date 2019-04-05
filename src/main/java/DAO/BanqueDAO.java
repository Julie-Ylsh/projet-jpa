package DAO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.RandomStringUtils;

import dev.banque.AssuranceVie;
import dev.banque.Banque;
import dev.banque.Client;
import dev.banque.Compte;
import dev.banque.LivretA;
import dev.banque.Operation;
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
		listeClients.forEach(unClient -> {
			System.out.println(unClient.getNom() + ", " + unClient.getPrenom() + ", " + unClient.getDateNaissance()
					+ ", " + unClient.getAdresse() + ", " + unClient.getBanque());
		});
		em1.close();
		emf.close();
		return listeClients;

	}

	public void saveNewCompte(Client client) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du compte avec des valeurs par défaut
		Compte c = new Compte();

		// Création d'un numéro random
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		c.setNumero(randomAlphabetic);
		c.setSolde(0d);
		em1.persist(c);

		client = em1.find(Client.class, client.getId());
		if (client != null) {
			client.setComptes(c);
		}

		et.commit();
		em1.close();
		emf.close();

	}

	public void saveNewCompteJoint(Client client1, Client client2) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du compte avec des valeurs par défaut
		Compte c = new Compte();

		// Création d'un numéro random
		String randomAlphabetic = RandomStringUtils.randomAlphabetic(5);
		c.setNumero(randomAlphabetic);
		c.setSolde(0d);
		em1.persist(c);

		client1 = em1.find(Client.class, client1.getId());
		if (client1 != null) {
			client1.setComptes(c);
		}

		client2 = em1.find(Client.class, client2.getId());
		if (client2 != null) {
			client2.setComptes(c);
		}

		et.commit();
		em1.close();
		emf.close();

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
		em1.persist(la);

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

		// Création du livretA avec des valeurs par défaut
		AssuranceVie av = new AssuranceVie();
		av.setTaux(0.3);
		em1.persist(av);

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
		Client clientC = requete.getSingleResult();

		em1.close();
		emf.close();

		return clientC;
	}

	public Compte findCompte(Integer id) {
		// Ouverture de l'Entity Manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		// Affichage du client
		TypedQuery<Compte> requete = em1.createQuery("select ct from Compte ct where (ID='" + id + "' )", Compte.class);
		Compte compteC = requete.getSingleResult();

		em1.close();
		emf.close();

		return compteC;
	}

	public void saveNewVirement(Virement virement) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du livretA avec des valeurs par défaut
		Virement v = new Virement();
		v.setBeneficiaire(virement.getBeneficiaire());
		em1.persist(v);

		et.commit();
		em1.close();
		emf.close();

	}

	public void saveNewOperation(Operation operation) {

		// Ouverture de l'Entity Manager et de la Transaction
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projet-jpa");
		EntityManager em1 = emf.createEntityManager();

		EntityTransaction et = em1.getTransaction();
		et.begin();

		// Création du livretA avec des valeurs par défaut
		Operation o = new Operation();
		o.setCompte(operation.getCompte());
		o.setDate(LocalDateTime.now());
		o.setMontant(operation.getMontant());
		o.setMotif(operation.getMotif());
		em1.persist(o);

		et.commit();
		em1.close();
		emf.close();

	}

}
