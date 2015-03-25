package bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table( name = "r_equipier" )
public class User implements Serializable {
	
	@Id
	@Column(name = "numeroLicence")
	private String numeroLicence;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "identifiant")
	private String identifiant;
	
	@Column(name = "motDePasse")
	private String motDePasse;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "nationalite")
	private String nationalite;
	
	@Column(name = "admin")
	private int admin;
	
    @ManyToMany(mappedBy="equipage")
    @JsonBackReference
	private Set<Bateau> bateaux = new HashSet<Bateau>();
	
	public User(){
		
	}


	public String getNumeroLicence() {
		return numeroLicence;
	}
	public void setNumeroLicence(String num) {
		numeroLicence = num;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		nom = n;
	}


	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String p) {
		prenom = p;
	}
	
	
	public String getIdentifiant(){
		return identifiant;
	}
	public void setIdentifiant(String i){
		identifiant = i;
	}
	

	public String getMotDePasse(){
		return motDePasse;
	}
	public void setMotDePasse(String mdp){
		motDePasse = mdp;
	}


	public String getUrl(){
		return url;
	}
	public void setUrl(String u){
		url = u;
	}
	

	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}

	
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String na) {
		nationalite = na;
	}

	
	public Set<Bateau> getBateaux() {
		return bateaux;
	}
	public void setBateaux(Set<Bateau> bateaux) {
		this.bateaux = bateaux;
	}

}
