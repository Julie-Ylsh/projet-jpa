package dev.banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssuranceVie extends AbstractCompte {
	
	
	@Column(name = "DATE_FIN")
	private LocalDate dateFin;
	
	@Column(name = "TAUX_AV")
	private Double taux;
	
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public Double getTaux() {
		return taux;
	}
	public void setTaux(Double taux) {
		this.taux = taux;
	}
}
