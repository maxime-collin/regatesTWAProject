package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "r_bateau" )
public class Bateau {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public String numero;
	public String nom;
	public String capitaine;
	public String type;
	public String nationalite;
	
	public Bateau(){
		
	}
	
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "nom")
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		nom = n;
	}

	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "capitaine")
	public String getCapitaine() {
		return capitaine;
	}
	public void setCapitaine(String capitaine) {
		this.capitaine = capitaine;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "nationalite")
	public String getNationalite() {
		return nationalite;
	}
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

}
