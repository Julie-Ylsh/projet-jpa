package dev.banque;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OPERATIONS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {

	public Operation(Double montant, String motif, Compte compte) {
		super();
		this.montant = montant;
		this.motif = motif;
		this.compte = compte;
	}

	public Operation() {

	}

	@Id // obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DATE_OPERATION")
	private LocalDateTime date;

	@Column(name = "MONTANT")
	private Double montant;

	@Column(name = "MOTIF")
	private String motif;

	@ManyToOne
	@JoinColumn(name = "COMPTE_NOM")
	private Compte compte;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
