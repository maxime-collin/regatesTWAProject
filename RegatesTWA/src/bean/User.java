package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "r_equipier" )
public class User {
	
	@Id
	public String numeroLicence;
	public String nom;
	public String prenom;
	public String identifiant;
	public String motDePasse;
	public String url;
	public String nationalite;
	public int admin;
	
	
	public User(){
		
	}

	@Column(name = "numeroLicence")
	public String getNumeroLicence() {
		return numeroLicence;
	}
	public void setNumeroLicence(String num) {
		numeroLicence = num;
	}
	
	@Column(name = "nom")
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		nom = n;
	}

	@Column(name = "prenom")
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String p) {
		prenom = p;
	}
	
	
	@Column(name = "identifiant")
	public String getIdentifiant(){
		return identifiant;
	}
	public void setIdentifiant(String i){
		identifiant = i;
	}
	
	@Column(name = "motDePasse")
	public String getMotDePasse(){
		return motDePasse;
	}
	public void setMotDePasse(String mdp){
		motDePasse = mdp;
	}

	@Column(name = "url")
	public String getUrl(){
		return url;
	}
	public void setUrl(String u){
		url = u;
	}
	
	@Column(name = "admin")
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Column(name = "nationalite")
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String na) {
		nationalite = na;
	}
}
