package br.com.codar.application.options.primaryoptions;

import java.util.List;
import java.util.Set;

import br.com.codar.application.options.Option;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class Forms implements Option{
	private DataInput input;
	private FilesManager filesManager;
	
	public Forms(DataInput input, FilesManager filesManager) {
		this.input = input;
		this.filesManager = filesManager;
	}

	@Override
	public String getDescription() {
		return "Validar formularios";
	}

	@Override
	public void execute() {
		System.out.println("Selecione uma das opções:");
		System.out.println("1 - Digitar e-mail");
		System.out.println("2 - buscar todos os formulários duplicados");
		
		int selectedOption = input.entryNumber();
		input.entryString();
		
		switch(selectedOption) {
		case 1:
			System.out.print("Informe o e-mail para busca: ");
			String email = input.entryString();
			
			List<List<String>> duplicateds = filesManager.findDuplicatedByEmail(email);
			
			if(duplicateds.size() == 0) {
				System.out.println("Nenhuma duplicidade com esse e-mail foi encontrada");
				return;
			}
			
			System.out.println("Esses são os formulários com mesmo e-mail");
			
			for(List<String> candidate : duplicateds) {
				System.out.println("[ Nome: " + candidate.get(0) + 
						", e-mail: " + candidate.get(1) 
						+ ", idade: " + candidate.get(2) 
						+ ", telefone: " + candidate.get(3) + " ]");
			}
			
			
			break;			
		case 2:
			Set<List<String>> allDuplicateds = filesManager.findAllDuplicatedCandidates();
			
			if(allDuplicateds.size() == 0) {
				System.out.println("Nenhuma duplicidade foi encontrada");
				return;
			}
			
			System.out.println("Esses são todos os formulários duplicados");
			
			allDuplicateds.forEach(System.out::println);
			
			break;
		default:
			System.out.println("Opção inválida!");
			execute();
			break;
		}		
	}
}
