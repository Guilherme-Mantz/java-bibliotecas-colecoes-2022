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
		System.out.println("***** Selecione uma op��o *****");
		System.out.println();
		System.out.println("| Op��o 1 - Candidatar-se                     |");
		System.out.println("| Op��o 2 - Adicionar pergunta ao formul�rio  |");
		System.out.println("| Op��o 3 - Remover pergunta do formul�rio    |");
		System.out.println("| Op��o 4 - Listar formul�rios cadastrados    |");
		System.out.println("| Op��o 5 - Pesquisar formul�rios cadastrados |");
		System.out.println("| Op��o 6 - Validar formul�rios        	      |");
		System.out.println("| Op��o 7 - Sair                              |");

		System.out.println();
		System.out.print("Op��o: ");
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
			System.out.println("Op��o inv�lida!");
			operations();
			break;
		}
	}

	
}
