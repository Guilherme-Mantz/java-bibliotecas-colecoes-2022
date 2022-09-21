package br.com.codar.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codar.utils.CompareUtil;
import br.com.codar.utils.FormatUtil;

public class FilesManager {

	private static final String FORM = "formulario.txt";
	private static final String PATH_SYSTEM = "C:\\Users\\Guilherme\\Desktop\\inscricoes_bluesoft\\";
	private static int idForm = 0;
	
	public void addCandidate(List<String> answers) {
		
		String path = PATH_SYSTEM + idForm + "-" + FormatUtil.formatString(answers.get(0)) + ".txt";
		idForm++;
		
		try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(path))) {
			
			for(String response : answers) {
				bfWriter.write(response);
				bfWriter.newLine();
			}
			
		} 
		catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public List<String> readerQuestions() {
		List<String> questions = new ArrayList<>();

		try (BufferedReader bfReader = new BufferedReader(new FileReader(FORM))) {

			String line = bfReader.readLine();
			while (line != null) {

				questions.add(line);
				line = bfReader.readLine();
			}
		} 
		catch (IOException ex) {
			ex.getMessage();
		}

		return questions;
	}
	
	public void addQuestionForm(String question) {
		
		try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FORM, true))) {
			bfWriter.newLine();
			bfWriter.write(question);
			
		} 
		catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public void removeQuestionForm(List<String> questions) {
		try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(FORM))) {
			
			int lastIndex = questions.size() -1;
			
			for(String question : questions) {
				bfWriter.write(question);
				if(!CompareUtil.comapreStrings(questions.get(lastIndex), question)) {
					bfWriter.newLine();
				}
			}
			
		} 
		catch (IOException ex) {
			ex.getMessage();
		}
	}
	
	public List<List<String>> listAllCandidates(){
		List<List<String>> listCandidates = new ArrayList<>();
		
		File archive[] = new File(PATH_SYSTEM).listFiles();

		for(File file : archive){
			
			try (BufferedReader bfReader = new BufferedReader(new FileReader(file))) {
				
				List<String> candidate = new ArrayList<>();
				String line = bfReader.readLine();
				
				while (line != null) {
					candidate.add(line);
					line = bfReader.readLine();
				}
				
				listCandidates.add(candidate);
			} 
			catch (IOException ex) {
				ex.getMessage();
			}
		}
		
		return listCandidates; 
	}
	
	public List<List<String>> findCandidateByName(String nameCandidate){
		List<List<String>> listCandidates = new ArrayList<>();
		
		File archive[] = new File(PATH_SYSTEM).listFiles();

		for(File file : archive){
			
			try (BufferedReader bfReader = new BufferedReader(new FileReader(file))) {
				
				List<String> candidate = new ArrayList<>();
				String line = bfReader.readLine();
				
				if(CompareUtil.comapreStrings(line, nameCandidate)) {
				
					while (line != null) {
					
						candidate.add(line);
						line = bfReader.readLine();
					}
					
					listCandidates.add(candidate);
				}
				
			} 
			catch (IOException ex) {
				ex.getMessage();
			}
		}
		
		return listCandidates;
	}
	
	public Set<List<String>> findAllDuplicatedCandidates(){
		List<List<String>> listCandidates = listAllCandidates();
		Set<List<String>> duplicateds = new HashSet<>();

		for(int i = 0; i < listCandidates.size(); i++) {
			
			for(int j = 1; j < listCandidates.size(); j++) {
				
				if(CompareUtil.comapreStrings(listCandidates.get(i).get(1), listCandidates.get(j).get(1)) && 
						listCandidates.get(i) != listCandidates.get(j)) {
					
					duplicateds.add(listCandidates.get(i));
					duplicateds.add(listCandidates.get(j));
				}
			}

		}
		return duplicateds;
	}
	
	public List<List<String>> findDuplicatedByEmail(String email){
		List<List<String>> listCandidates = listAllCandidates();
		
		List<List<String>> duplicateds = listCandidates
				.stream()
				.filter(c -> CompareUtil.comapreStrings(c.get(1), email))
				.collect(Collectors.toList());
		
		return duplicateds;
	}
}
