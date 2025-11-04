package eshop.view;

public class Views {

	public class Common{}
	
	public class Produit extends Common{}
	public class ProduitWithVentes extends Produit{}
	
	public class Fournisseur extends Common{}
	public class FournisseurWithStock extends Fournisseur{}
	
	public class Client extends Common{}
	public class ClientWithAchat extends Client{}
	
	public class Achat extends Common{}
}
