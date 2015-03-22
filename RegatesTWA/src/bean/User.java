package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name = "r_equipier" )
public class User {
	
	@Id
	private String numeroLicence;
	private String nom;
	private String prenom;
	private String identifiant;
	private String motDePasse;
	private String url;
	private String nationalite;
	private int admin;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pkE.equipier", cascade=CascadeType.ALL)
	private List<Equipage> bateaux = new ArrayList<Equipage>();
	
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

	public List<Equipage> getBateaux() {
		return bateaux;
	}
	public void setBateaux(List<Equipage> bateaux) {
		this.bateaux = bateaux;
	}

}
