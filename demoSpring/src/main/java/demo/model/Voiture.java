package demo.model;

public class Voiture {

	private int vitesse;
	private boolean arret;
	private String marque;
	

	public Voiture(String marque) {
		this.vitesse = 0;
		this.arret = true;
		this.marque = marque;
	}


	public int getVitesse() {
		return vitesse;
	}


	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}


	public boolean isArret() {
		return arret;
	}


	public void setArret(boolean arret) {
		this.arret = arret;
	}


	public String getMarque() {
		return marque;
	}


	public void setMarque(String marque) {
		this.marque = marque;
	}

	
	public void start(int vitesse) 
	{
		this.vitesse=vitesse;
		this.arret=false;
	}
	
	public void stop() 
	{
		this.vitesse=0;
		this.arret=true;
	}
	

	@Override
	public String toString() {
		return "Voiture [vitesse=" + vitesse + ", arret=" + arret + ", marque=" + marque + "]";
	}
	
	
	
}
