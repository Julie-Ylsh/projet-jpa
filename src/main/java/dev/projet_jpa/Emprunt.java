package dev.projet_jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Emrpunt")
public class Emprunt {
	
	@Id // obligatoire
	@Column(name="ID")
	private Integer id;
	
	@Column(name="DATE_DEBUT")
	private String titre;
	
	@Column(name="DATE_FIN")
	private String auteur;

	@Column(name="DELAI")
	private Integer delai;
	
	@Column(name="ID_CLIENT")
	private Integer idClient;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Integer getDelai() {
		return delai;
	}

	public void setDelai(Integer delai) {
		this.delai = delai;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}
	
}