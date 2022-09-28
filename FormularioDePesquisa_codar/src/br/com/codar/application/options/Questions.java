package br.com.codar.application.options;

import java.util.InputMismatchException;
import java.util.List;

import br.com.codar.application.Program;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class Questions {
	
	private DataInput input;
	private FilesManager filesManager;
	
	public Questions(DataInput input, FilesManager filesManager) {
		this.input = input;
		this.filesManager = filesManager;
	}
	
	public void addQuestion() {
		System.out.println("Digite a nova pergunta:");

		String newQuestion = input.entryString();
		List<String> questions = filesManager.readerQuestions();
		
		while(newQuestion.isEmpty()) {
			System.out.println("O campo não pode ser vazio!");
			newQuestion = input.entryString();
		}

		for (String question : questions) {
			while (newQuestion.toUpperCase().equals(question.toUpperCase())) {
				System.out.println("Essa pergunta já foi adicionada, tente outra!");
				newQuestion = input.entryString();
			}
		}

		filesManager.addQuestionForm(newQuestion);

		System.out.println("Pergunta adicionada ao formulário!");
		Program.operations();

	}

	public void removeQuestion() {
		System.out.println("Informe o número da pergunta que deseja remover: ");
		List<String> questions = filesManager.readerQuestions();
		
		int index = 0;
		for(String question : questions) {
			System.out.println((index += 1) + " - " + question);
		}
		System.out.println("(-1) - Cancelar");
		
		int numberQuestion;
		boolean notNumber = true;
		
		while(notNumber) {
			try {
				numberQuestion = input.entryNumber();
				
				if(numberQuestion == -1) {
					Program.operations();
					input.entryNumber();
				}

				while(numberQuestion <= 4 || numberQuestion > index) {
					System.out.println("Essa pergunta não pode ser removida ou não existe, tente outra!");
					numberQuestion = input.entryNumber();
				}
				
				questions.remove(numberQuestion -1);
				filesManager.removeQuestionForm(questions);
				
				System.out.println("A pergunta foi removida!");
				
				notNumber= false;
			}
			catch (InputMismatchException ex) {
				System.out.println("Somente números inteiros são aceitos");			
				input.entryNumber();
			}
		}
		
		input.closeInput();
		Program.operations();
	}
}
