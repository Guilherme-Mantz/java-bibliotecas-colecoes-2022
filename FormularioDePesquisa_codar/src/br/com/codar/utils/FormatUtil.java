package br.com.codar.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FormatUtil {
	
	public static String formatString(String name) {
		
        String nfdNormalizedString = Normalizer.normalize(name, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        
        return pattern.matcher(nfdNormalizedString).replaceAll("").toUpperCase();
	}
}
