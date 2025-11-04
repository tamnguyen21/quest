package quest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordinateur")
public class Ordinateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="numero")
	private Integer id;
	@Column(length = 25,nullable = false)
	private String marque;
	@Column(columnDefinition = "int default 4")
	private int ram;
	
	public Ordinateur() {}
	
	public Ordinateur(Integer id, String marque, int ram) {
		this.id = id;
		this.marque = marque;
		this.ram = ram;
	}
	public Ordinateur(String marque, int ram) {
		this.marque = marque;
		this.ram = ram;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	
	public String getInfos() 
	{
		return this.id+" - "+this.marque;
	}
	
	@Override
	public String toString() {
		return "Ordinateur [id=" + id + ", marque=" + marque + ", ram=" + ram + "]";
	}
	
	
	
}
