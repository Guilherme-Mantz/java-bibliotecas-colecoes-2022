package br.com.codar.application;

import java.util.Scanner;

public class Program {

	static Scanner input = new Scanner(System.in);
	

	public static void main(String[] args) {

		operations();
	}

	static void operations() {

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
		int selectedOption = input.nextInt();
		input.nextLine();

		switch (selectedOption) {
		case 1:
			Options.registration();
			break;
		case 2:
			Options.addQuestion();
			break;
		case 3:
			Options.removeQuestion();
			break;
		case 4:
			Options.listCandidates();
			break;
		case 5:
			Options.findCandidate();
			break;
		case 6:
			Options.validateForms();
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
