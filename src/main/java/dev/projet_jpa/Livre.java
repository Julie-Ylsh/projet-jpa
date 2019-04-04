package dev.projet_jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Livre")
public class Livre {
	
	public Livre (){
		
	}

	public Livre(String titre, String auteur) {
		super();
		this.titre = titre;
		this.auteur = auteur;
	}

	@Id // obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITRE")
	private String titre;

	@Column(name = "AUTEUR")
	private String auteur;

	@ManyToMany
	@JoinTable(name = "Compo",

			joinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"), 
			inverseJoinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID")

	)

	private List<Emprunt> emprunt;

	public Integer getId() {
		return id;

	}
	
	@Override
	public String toString () {
		String livreAfficher = titre + " " + auteur;
	    return livreAfficher;
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
