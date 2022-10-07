package br.com.codar.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class FormatUtil {
	
	public static String formatString(String text) {
		
        String perguntaNormalizada = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        
        return pattern.matcher(perguntaNormalizada).replaceAll("").toUpperCase();
	}
}
