package bean;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table( name = "r_equipage" )
@AssociationOverrides({	@AssociationOverride(name = "pkE.equipier", joinColumns = @JoinColumn(name = "equipier")),
						@AssociationOverride(name = "pkE.bateau", joinColumns = @JoinColumn(name = "bateau"))
})
public class Equipage implements Serializable {
	
	private static final long serialVersionUID = -2687880721590367850L;
	
	
	private EquipageId pkE = new EquipageId();
	
	public Equipage(){
		
	}

	@EmbeddedId
	public EquipageId getPkE() {
		return pkE;
	}
	public void setPkE(EquipageId pkE) {
		this.pkE = pkE;
	}

}
