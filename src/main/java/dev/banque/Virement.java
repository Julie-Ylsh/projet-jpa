package dev.banque;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Virement extends Operation {

	public Virement() {
	}

	public Virement(String beneficiaire) {
		super();
		this.beneficiaire = beneficiaire;
	}

	@Column(name = "BENEFICIAIRE")
	private String beneficiaire;

	public String getBeneficiaire() {
		return beneficiaire;
	}

	public void setBeneficiaire(String beneficiaire) {
		this.beneficiaire = beneficiaire;
	}

}
