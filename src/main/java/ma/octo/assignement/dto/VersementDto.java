package ma.octo.assignement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VersementDto {
	private String nom_prenom_emetteur;
	private String motifVersement;
	private String ribCompteBeneficiaire;
	private BigDecimal montantVirement;
	private Date date;
	public String getRibCompteBeneficiaire() {
		return ribCompteBeneficiaire;
	}
	public void setNrCompteBeneficiaire(String ribCompteBeneficiaire) {
		this.ribCompteBeneficiaire = ribCompteBeneficiaire;
	}
	public BigDecimal getMontantVirement() {
		return montantVirement;
	}
	public void setMontantVirement(BigDecimal montantVirement) {
		this.montantVirement = montantVirement;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNom_prenom_emetteur() {
		return nom_prenom_emetteur;
	}
	public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
		this.nom_prenom_emetteur = nom_prenom_emetteur;
	}
	public String getMotifVersement() {
		return motifVersement;
	}
	public void setMotifVersement(String motifVersement) {
		this.motifVersement = motifVersement;
	}
	
	

}
