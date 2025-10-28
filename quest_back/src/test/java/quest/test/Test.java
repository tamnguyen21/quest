package quest.test;

import java.time.LocalDate;

import quest.context.Singleton;
import quest.model.Civilite;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.Module;
import quest.model.Ordinateur;
import quest.model.Stagiaire;

public class Test {


	public void run(String[] args) {

		//Singleton.getInstance();
		
		Ordinateur ordinateur1 = new Ordinateur("Dell",4);
		Ordinateur ordinateur2 = new Ordinateur("Asus",8);
		Ordinateur ordinateur3 = new Ordinateur("HP",4);
		
		//ordinateur1 = Singleton.getInstance().getDaoOrdinateur().save(ordinateur1);
		//ordinateur2 = Singleton.getInstance().getDaoOrdinateur().save(ordinateur1);
		//ordinateur3 = Singleton.getInstance().getDaoOrdinateur().save(ordinateur1);
	
		Formateur formateur1 = new Formateur("formateur","formateur","ABID","Jordan",Civilite.Homme,true);
		Formateur formateur2 = new Formateur("formateur2","formateur2","PERROUALT","Jeremy",Civilite.Homme,false);


		Filiere filiere1 = new Filiere("250905-DIS-448-JAVA",LocalDate.parse("2025-09-05"),LocalDate.parse("2025-12-05"));
		Filiere filiere2 = new Filiere("250908-DIS-448-JAVA",LocalDate.parse("2025-09-08"),LocalDate.parse("2025-12-08"));


		Matiere matiere1 = new Matiere("Java");
		Matiere matiere2 = new Matiere("SQL");
		Matiere matiere3 = new Matiere("HTML");
		Matiere matiere4 = new Matiere("Gestion du stress");

		Module module1 = new Module(LocalDate.parse("2025-09-10"), LocalDate.parse("2025-09-15"), 7575, filiere1, matiere1,formateur1);
		Module module2 = new Module(LocalDate.parse("2025-09-16"), LocalDate.parse("2025-09-18"), 8781, filiere1, matiere2,formateur1);
		Module module3 = new Module(LocalDate.parse("2025-10-16"), LocalDate.parse("2025-10-17"), 9276, filiere1, matiere3,formateur1);
		Module module4 = new Module(LocalDate.parse("2025-09-23"), LocalDate.parse("2025-09-23"), 6867, filiere1, matiere4,formateur1);
		Module module5 = new Module(LocalDate.parse("2025-09-23"), LocalDate.parse("2025-09-23"), 7777, filiere2, matiere1,formateur2);


		Stagiaire stagiaire1 = new Stagiaire("stagiaire","stagiaire","COSTENARO","Alyssa",Civilite.Femme,"email@email.fr","1","rue de Paris","75009","Paris", ordinateur1, filiere1);
		Stagiaire stagiaire2 = new Stagiaire("stagiaire2","stagiaire2","DA COSTA","Jany",Civilite.Homme,"email2@email.fr","3bis","rue de Paris","75009","Paris", ordinateur2, filiere1);
		
		System.out.println(stagiaire1);

		//Singleton.getInstance().getEmf().close();
	}

}
