package dev.banque;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LivretA extends AbstractCompte {
	
	@Column(name = "TAUX_LA")
	private Double taux;

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

}
