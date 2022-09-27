package br.com.codar.application.options;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.codar.application.Program;
import br.com.codar.factory.DaoFactory;
import br.com.codar.manager.FilesManager;

public class Candidates {
	
	static Scanner input = new Scanner(System.in);
	static FilesManager filesManager = DaoFactory.createFilesManagerDao();
	
	public void listCandidates() {
		System.out.println("Qual filtro deseja utilizar?");
		System.out.println("1 - Listar os nomes dos candidatos cadastrados agrupados por idade");
		System.out.println("2 - Exibir a quantidade de candidatos por idade");

		List<List<String>> listCandidates = filesManager.listAllCandidates();

		int optionFilter = input.nextInt();

		switch (optionFilter) {
		case 1:

			listCandidates.sort((c1, c2) -> c1.get(2).compareTo(c2.get(2)));

			System.out.println();
			System.out.println("Todos os inscritos:");

			for (List<String> candidate : listCandidates) {
				System.out.println("Nome: " + candidate.get(0) + " | Idade: " + candidate.get(2));
			}

			input.nextLine();
			Program.operations();
			break;
		case 2:

			Map<String, Long> countAges = listCandidates.stream()
					.collect(Collectors.groupingBy(idade -> idade.get(2), Collectors.counting()));

			countAges.forEach(
					(age, count) -> System.out.println(count + " candidatos inscritos possuem " + age + " anos"));

			input.nextLine();
			Program.operations();
			break;
		default:
			System.out.println("Opção inválida!");
			listCandidates();
			break;
		}

	}

	public void findCandidate() {
		System.out.print("Por favor informe o nome do candidato que procura: ");
		String nameCandidate = input.nextLine();

		List<List<String>> listCandidates = filesManager.findCandidateByName(nameCandidate);

		if (listCandidates.size() == 0) {
			System.out.println("Nenhum candidato com este nome foi encontrado!");
			Program.operations();
		}

		for (List<String> candidate : listCandidates) {
			System.out.println(candidate);
		}

		Program.operations();

	}
}
