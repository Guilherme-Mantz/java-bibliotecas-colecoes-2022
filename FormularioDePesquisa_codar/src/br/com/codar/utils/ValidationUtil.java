package br.com.codar.utils;

public class ValidationUtil {
	
	public static boolean validAge(String age) {
		
		if(Integer.parseInt(age) <= 16) {
			System.out.println("Agradecemos sua inscri��o, mas a idade m�nima � de 16 anos, "
							+ "voc� poder� tentar novamente quando chegar na idade permitida!");
			return false;
		}
		
		return true;
	}
}
