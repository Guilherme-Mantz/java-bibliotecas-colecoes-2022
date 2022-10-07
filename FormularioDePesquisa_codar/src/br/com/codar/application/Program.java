package br.com.codar.application;

import br.com.codar.application.options.MenuExecute;
import br.com.codar.application.options.primaryoptions.MenuPrimary;

public class Program {

	public static void main(String[] args) {
		new MenuExecute(new MenuPrimary()).execute();
	}
	
}
