package br.com.codar.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codar.factory.DaoFactory;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.ValidationUtil;

public class Options {
	
	static Scanner input = new Scanner(System.in);
	static FilesManager filesManager = DaoFactory.createFilesManagerDao();
	
	static void registration() {
		System.out.println();
		System.out.println("Você está se candidatando, por favor informe seus dados: ");
		System.out.println();

		List<String> questions = filesManager.readerQuestions();
		List<String> answers = new ArrayList<>(); 
		
		for(int index = 0; index < questions.size(); index++) {
			if(index == 2) {
				System.out.println("P"+ (index+1) +"| "+ questions.get(index));
				String age = input.nextLine();
				
				if (!ValidationUtil.validAge(age)) {

					System.exit(0);
				}
				answers.add(age);
				
			}
			else {
				System.out.println("P"+ (index+1) +"| "+questions.get(index));
				String response = input.nextLine();
				
				answers.add(response);
			}
		}
		
		filesManager.addCandidate(answers);

		System.out.println("Cadastro realizado!");

		Program.operations();
	}

	static void addQuestion() {
		System.out.println("Digite a nova pergunta:");

		String newQuestion = input.nextLine();
		List<String> questions = filesManager.readerQuestions();

		for (String question : questions) {
			while (newQuestion.toUpperCase().equals(question.toUpperCase())) {
				System.out.println("Essa pergunta já foi adicionada, tente outra!");
				newQuestion = input.nextLine();
			}
		}

		filesManager.addQuestionForm(newQuestion);

		System.out.println("Pergunta adicionada ao formulário!");
		Program.operations();

	}

	static void removeQuestion() {
		System.out.println("Informe o número da pergunta que deseja remover: ");
		List<String> questions = filesManager.readerQuestions();
		
		int index = 0;
		for(String question : questions) {
			System.out.println((index += 1) + " - " + question);
		}
		System.out.println("(-1) - Cancelar");
		
		int numberQuestion = input.nextInt();
		
		if(numberQuestion == -1) {
			Program.operations();
			input.nextLine();
		}

		while(numberQuestion <= 4 || numberQuestion > index) {
			System.out.println("Essa pergunta não pode ser removida ou não existe, tente outra!");
			numberQuestion = input.nextInt();
		}
		
		questions.remove(numberQuestion -1);
		filesManager.removeQuestionForm(questions);
		
		System.out.println("A pergunta foi removida!");
		
		input.nextLine();
		Program.operations();
		
	}

	static void listCandidates() {
		System.out.println("Qual filtro deseja utilizar?");
		System.out.println("1 - Listar os nomes dos candidatos cadastrados agrupados por idade");
		System.out.println("2 - Exibir a quantidade de candidatos por idade");
		
		List<List<String>> listCandidates = filesManager.listAllCandidates();
		
		int optionFilter = input.nextInt();
		
		switch(optionFilter) {
		case 1:
			
			listCandidates.sort((c1, c2) -> c1.get(2).compareTo(c2.get(2)));
			
			System.out.println();
			System.out.println("Todos os inscritos:");
			
			for(List<String> candidate : listCandidates) {
				System.out.println("Nome: "+ candidate.get(0) + " | Idade: " + candidate.get(2));
			}
			
			input.nextLine();
			Program.operations();
			break;
		case 2:
			
			Map<String, Long> countAges = listCandidates.stream()
				.collect(Collectors.groupingBy(idade -> idade.get(2), Collectors.counting()));
			
			countAges.forEach((age, count) -> System.out.println(count + " candidatos inscritos possuem " + age + " anos"));

			input.nextLine();
			Program.operations();
			break;
		default:
			System.out.println("Opção inválida!");
			listCandidates();
			break;
		}
		
	}

	static void findCandidate() {
		System.out.print("Por favor informe o nome do candidato que procura: ");
		String nameCandidate = input.nextLine();
		
		List<List<String>> listCandidates = filesManager.findCandidateByName(nameCandidate);
		
		if(listCandidates.size() == 0) {
			System.out.println("Nenhum candidato com este nome foi encontrado!");
			Program.operations();
		}
		
		for(List<String> candidate : listCandidates) {
			System.out.println(candidate);
		}
		
		Program.operations();

	}
	
	static void validateForms() {
		System.out.println("Selecione uma das opções:");
		System.out.println("1 - Digitar e-mail");
		System.out.println("2 - buscar todos os formulários duplicados");
		
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
			
			System.out.println("Esses são os formulários com mesmo e-mail");
			
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
			
			System.out.println("Esses são todos os formulários duplicados");
			
			for(List<String> candidate : allDuplicateds) {
				System.out.println(candidate);
			}
			
			Program.operations();
			break;
		default:
			System.out.println("Opção inválida!");
			validateForms();
			break;
		}
		
	}
}
