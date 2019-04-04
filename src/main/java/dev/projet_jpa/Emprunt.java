package dev.projet_jpa;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Emprunt")
public class Emprunt {
	
	@Id // obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DATE_DEBUT")
	private LocalDateTime dateDebut;
	
	@Column(name="DATE_FIN")
	private LocalDateTime dateFin;

	@Column(name="DELAI")
	private Integer delai;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private Client client;
	
	@ManyToMany(mappedBy="emprunt")
	private List<Livre> livre = new ArrayList<>();
	
	@Override
	public String toString () {
		String empruntAfficher = "1 livre emprunté le : " + dateDebut + ", nombre de jours possibles : " + delai;
	    return empruntAfficher;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Livre> getLivre() {
		return livre;
	}

	public void setLivre(List<Livre> livre) {
		this.livre = livre;
	}


	
}