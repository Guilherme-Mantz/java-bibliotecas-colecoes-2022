package br.com.codar.application.options.primaryoptions;

import br.com.codar.application.options.Option;

public class ExitOption implements Option {

	@Override
	public String getDescription() {
		return "Sair";
	}

	@Override
	public void execute() {
		System.exit(0);		
	}

}
