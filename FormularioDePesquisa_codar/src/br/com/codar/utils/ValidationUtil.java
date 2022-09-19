package br.com.codar.utils;

public class ValidationUtil {
	
	public static boolean validAge(String age) {
		
		if(Integer.parseInt(age) <= 16) {
			System.out.println("Agradecemos sua inscrição, mas a idade mínima é de 16 anos, "
							+ "você poderá tentar novamente quando chegar na idade permitida!");
			return false;
		}
		
		return true;
	}
}
