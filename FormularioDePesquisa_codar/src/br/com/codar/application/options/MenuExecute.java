package br.com.codar.application.options;

import br.com.codar.utils.DataInput;

public class MenuExecute {

	private final DataInput input = new DataInput();
	private final Menu menu;	
	
	public MenuExecute(Menu menu) {
		this.menu = menu;
	}

	public void execute() {
		
		menu.options().forEach((number, option) -> System.out.println(number + " - " + option.getDescription()));
		String selectedOption = input.entryString();
		
		Option option = menu.options().get(selectedOption);
		
		while(option == null) {
			System.out.println("Comando inválido!");
			execute();
		}
		
		if(option != null) {
			option.execute();
			execute();
		}
	}
}
