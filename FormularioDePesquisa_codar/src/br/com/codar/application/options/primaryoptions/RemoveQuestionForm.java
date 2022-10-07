package br.com.codar.application.options.primaryoptions;

import java.util.InputMismatchException;
import java.util.List;

import br.com.codar.application.options.Option;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class RemoveQuestionForm implements Option{
	private DataInput input;
	private FilesManager filesManager;
	
	public RemoveQuestionForm(DataInput input, FilesManager filesManager) {
		this.input = input;
		this.filesManager = filesManager;
	}

	@Override
	public String getDescription() {
		return "Remover pergunta";
	}

	@Override
	public void execute() {
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
					return;
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
		
	}
}
