package bean;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class InscriptionRegateId implements Serializable{

	private static final long serialVersionUID = 8869492896533150280L;

	private Regate regate;
	private Bateau bateau;
	
	@ManyToOne
	public Regate getRegate() {
		return regate;
	}
	public void setRegate(Regate regate) {
		this.regate = regate;
	}
	
	@ManyToOne
	public Bateau getBateau() {
		return bateau;
	}
	public void setBateau(Bateau bateau) {
		this.bateau = bateau;
	}
	
	
}
