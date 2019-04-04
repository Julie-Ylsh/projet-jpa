package dev.projet_jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
		TypedQuery<Livre> requete = em1.createQuery("select l from Livre l where l.id='" + question + "'", Livre.class);
		List<Livre> listeLivre = requete.getResultList();
		listeLivre.forEach(unLivre -> {
			System.out.println(unLivre.getId() + " - " + unLivre.getTitre() + " - " + unLivre.getAuteur());

		});

		em1.close();
		emf.close();
		questionLivre.close();
	}

}
