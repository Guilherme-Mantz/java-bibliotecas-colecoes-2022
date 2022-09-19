package br.com.codar.application;

import java.util.Scanner;

public class Program {

	static Scanner input = new Scanner(System.in);
	

	public static void main(String[] args) {

		operations();
	}

	static void operations() {

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
			System.out.println("Opção inválida!");
			operations();
			break;
		}
	}

	
}
