package dev.projet_jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Livre")
public class Livre {
	
	@Id // obligatoire
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TITRE")
	private String titre;
	
	@Column(name="AUTEUR")
	private String auteur;
	
	public Integer getId(){
		return id;
		
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
	public void setId(Integer id) {
		this.id = id;
	}


}
