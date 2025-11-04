package quest.view;

public class Views {

	public class Common {} //Toutes les types primitifs + String 
	
	public class Filiere extends Common {} //Tous les objets toOne dans la classe Filiere + de Common
	
	public class FiliereWithStagiaire extends Filiere{} //Tout le contenu de la View Filiere + la liste des stagiaires
	
	public class FiliereWithModule extends Filiere{}  // Tout le contenu de la view Filiere + la liste des modules
	
	public class Stagiaire extends Common {} //Tous les objets toOne de la classe Stagiaire + de Common
	
	public class Formateur extends Common{} //Tous les objets toOne de la classe Formateur + de Common
	
	public class FormateurWithModules extends Formateur{}
	
	public class Matiere extends Common{} //Tous les objets toOne de la classe Matiere + de Common
	
	public class MatiereWithModule extends Matiere{}

	public class Ordinateur extends Common {} //Tous les objets toOne de la classe Ordinateur + de Common
	
	public class Module extends Common{}
}
