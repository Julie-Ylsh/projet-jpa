package dev.banque;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BANQUE")
public class Banque {

	public Banque() {

	}

	public Banque(String nom, List<Client> clients) {
		super();
		this.nom = nom;
		this.clients = clients;
	}

	public Banque(String nom) {
		super();
		this.nom = nom;
	}

	@Id // obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOM")
	private String nom;

	@OneToMany(mappedBy = "banque")
	private List<Client> clients;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return nom ;
	}

}
