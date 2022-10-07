package br.com.codar.application.options.primaryoptions;

import java.util.ArrayList;
import java.util.List;

import br.com.codar.application.options.Option;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.CompareUtil;
import br.com.codar.utils.DataInput;
import br.com.codar.utils.ValidationUtil;

public class Register implements Option{
	
	private DataInput input;
	private FilesManager filesManager;
	
	public Register(DataInput input, FilesManager filesManager) {
		this.input = input;
		this.filesManager = filesManager;
	}
	
	@Override
	public String getDescription() {
		return "Candidatar-se";
	}

	@Override
	public void execute() {
		System.out.println();
		System.out.println("Você está se candidatando, por favor informe seus dados: ");
		System.out.println();

		List<String> questions = filesManager.readerQuestions();
		List<String> answers = new ArrayList<>();
		
		int index = 0;
		for(String question : questions) {
			if(CompareUtil.comapreStrings(question, questions.get(2))) {
				
				System.out.println("P" + (index += 1) + "| " + question);
				String age = input.entryString();
				
				while(age.equals("")) {
					System.out.println("O campo não pode estar vazio!");
					age = input.entryString();
				}

				if (!ValidationUtil.validAge(age)) {
					return;
				}
				answers.add(age);
			}
			else {
				System.out.println("P" + (index += 1) + "| " + question);
				String response = input.entryString();
				
				while(response.equals("")) {
					System.out.println("O campo não pode estar vazio!");
					response = input.entryString();
				}

				answers.add(response);
			}
		}

		filesManager.addCandidate(answers);
		System.out.println("Cadastro realizado!");
		
	}

}
