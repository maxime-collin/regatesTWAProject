package bean;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class EquipageId implements Serializable{

	private static final long serialVersionUID = 8869492896533150280L;

	private User equipier;
	private Bateau bateau;
	
	@ManyToOne
	public User getEquipier() {
		return equipier;
	}
	public void setEquipier(User equipier) {
		this.equipier = equipier;
	}
	
	@ManyToOne
	public Bateau getBateau() {
		return bateau;
	}
	public void setBateau(Bateau bateau) {
		this.bateau = bateau;
	}
	
	
}
