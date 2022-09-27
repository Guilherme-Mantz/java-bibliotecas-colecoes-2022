package br.com.codar.application;

import br.com.codar.application.options.Candidates;
import br.com.codar.application.options.Forms;
import br.com.codar.application.options.Questions;
import br.com.codar.application.options.Register;
import br.com.codar.factory.DaoFactory;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class Program {

	static DataInput input = new DataInput();
	
	static Register register;
	static Questions questions;
	static Candidates candidates;
	static Forms forms;
	
	static FilesManager filesManager = DaoFactory.createFilesManagerDao();

	public static void main(String[] args) {
		register = new Register(input, filesManager);
		questions = new Questions(input, filesManager);
		candidates = new Candidates();
		forms = new Forms();
		
		operations();
	}

	public static void operations() {

		System.out.println("------Bem vindo ao Movimento Codar 2022--------");
		System.out.println("***** Selecione uma opção *****");
		System.out.println();
		System.out.println("| Opção 1 - Candidatar-se                     |");
		System.out.println("| Opção 2 - Adicionar pergunta ao formulário  |");
		System.out.println("| Opção 3 - Remover pergunta do formulário    |");
		System.out.println("| Opção 4 - Listar formulários cadastrados    |");
		System.out.println("| Opção 5 - Pesquisar formulários cadastrados |");
		System.out.println("| Opção 6 - Validar formulários        	      |");
		System.out.println("| Opção 7 - Sair                              |");

		System.out.println();
		System.out.print("Opção: ");
		int selectedOption = input.entryNumber();
		input.entryString();
		
		switch (selectedOption) {
		case 1:
			register.registration();
			break;
		case 2:
			questions.addQuestion();
			break;
		case 3:
			questions.removeQuestion();
			break;
		case 4:
			candidates.listCandidates();
			break;
		case 5:
			candidates.findCandidate();
			break;
		case 6:
			forms.validateForms();
			break;
		case 7:
			System.out.println("Programa Finalizado!");
			System.exit(0);
		default:
			System.out.println("Opção inválida!");
			operations();
			break;
		}
	}

	
}
