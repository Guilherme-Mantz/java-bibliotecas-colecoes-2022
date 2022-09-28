package br.com.codar.application.options;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import br.com.codar.application.Program;
import br.com.codar.factory.DaoFactory;
import br.com.codar.manager.FilesManager;

public class Forms {
	static Scanner input = new Scanner(System.in);
	static FilesManager filesManager = DaoFactory.createFilesManagerDao();
	
	public void validateForms() {
		System.out.println("Selecione uma das op��es:");
		System.out.println("1 - Digitar e-mail");
		System.out.println("2 - buscar todos os formul�rios duplicados");
		
		int selectedOption = input.nextInt();
		input.nextLine();
		
		switch(selectedOption) {
		case 1:
			System.out.print("Informe o e-mail para busca: ");
			String email = input.nextLine();
			
			List<List<String>> duplicateds = filesManager.findDuplicatedByEmail(email);
			
			if(duplicateds.size() == 0) {
				System.out.println("Nenhuma duplicidade com esse e-mail foi encontrada");
				Program.operations();
			}
			
			System.out.println("Esses s�o os formul�rios com mesmo e-mail");
			
			for(List<String> candidate : duplicateds) {
				System.out.println("[ Nome: " + candidate.get(0) + 
						", e-mail: " + candidate.get(1) 
						+ ", idade: " + candidate.get(2) 
						+ ", telefone: " + candidate.get(3) + " ]");
			}
			
			Program.operations();
			break;			
		case 2:
			Set<List<String>> allDuplicateds = filesManager.findAllDuplicatedCandidates();
			
			if(allDuplicateds.size() == 0) {
				System.out.println("Nenhuma duplicidade foi encontrada");
				Program.operations();
			}
			
			System.out.println("Esses s�o todos os formul�rios duplicados");
			
			allDuplicateds.forEach(System.out::println);
			
			Program.operations();
			break;
		default:
			System.out.println("Op��o inv�lida!");
			validateForms();
			break;
		}
		
	}
}
