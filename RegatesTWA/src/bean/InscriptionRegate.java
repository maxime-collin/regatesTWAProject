package bean;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table( name = "r_inscriptionRegate" )
@AssociationOverrides({	@AssociationOverride(name = "pkIR.regate", joinColumns = @JoinColumn(name = "regate")),
	@AssociationOverride(name = "pkIR.bateau", joinColumns = @JoinColumn(name = "bateau"))
})
public class InscriptionRegate implements Serializable {
	
	private static final long serialVersionUID = -2687880721590367850L;
	
	
	private InscriptionRegateId pkIR = new InscriptionRegateId();
	private int classement;

	
	public InscriptionRegate(){
		
	}

	@EmbeddedId
	public InscriptionRegateId getPkIR() {
		return pkIR;
	}
	public void setPkIR(InscriptionRegateId pkIR) {
		this.pkIR = pkIR;
	}	
	
	@Column(name = "classement")
	public int getClassement() {
		return classement;
	}
	public void setClassement(int classement) {
		this.classement = classement;
	}

}
