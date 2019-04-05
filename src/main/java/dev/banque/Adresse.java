package dev.banque;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	
	
	public Adresse(Integer numero, String rue, String ville, Integer codePostal) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	public Adresse(){
		
	}
	
	private Integer numero;
	private String rue;
	private String ville;
	private Integer codePostal;
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Integer getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return numero + " " + rue + ", " + codePostal + " " + ville;
	}

	

}
