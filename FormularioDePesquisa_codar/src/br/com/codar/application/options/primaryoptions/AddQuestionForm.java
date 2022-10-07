package br.com.codar.application.options.primaryoptions;

import java.util.List;

import br.com.codar.application.options.Option;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class AddQuestionForm implements Option {
	
	private DataInput input;
	private FilesManager filesManager;
	
	public AddQuestionForm(DataInput input, FilesManager filesManager) {
		this.input = input;
		this.filesManager = filesManager;
	}
	
	@Override
	public String getDescription() {
		return "Adicionar pergunta";
	}

	@Override
	public void execute() {
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
		
	}
}
