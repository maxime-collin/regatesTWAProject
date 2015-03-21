package bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table( name = "r_inscriptionRegate" )
public class InscriptionRegate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	public int regate;
	
	public int classement;

	
	public InscriptionRegate(){
		
	}

	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	@Column(name = "regate")
	public int getRegate() {
		return regate;
	}
	public void setRegate(int regate) {
		this.regate = regate;
	}
	
	@Column(name = "classement")
	public int getClassement() {
		return classement;
	}
	public void setClassement(int classement) {
		this.classement = classement;
	}

}
