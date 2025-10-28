package orchestre.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PublicAspect {

	@Pointcut("execution ......")
	public void cibleLaFonctionJouer() {}
	

	@AfterReturning(pointcut="execution(public String orchestre.model.Guitariste.toString())",returning = "leToString")
	public void afterToStringGuitariste(String leToString) 
	{
		System.out.println(leToString);
		System.out.println("Le guitariste vient de se presenter");
	}


	@xxx(pointcut="cibleLaFonctionJouer()")
	public void installer(){System.out.println("Le public s'installe");}

	@xxx(pointcut="cibleLaFonctionJouer()")
	public void applaudir(){System.out.println("Le public applaudit");}

	@xxx(pointcut="cibleLaFonctionJouer()")
	public void huer(){System.out.println("Le public jette des tomates");}

}
