package test.java.br.com.codar.application.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.codar.application.options.Register;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

class RegisterTest {
	
	private Register register;

	@Mock
	private DataInput input;

	@Mock
	private FilesManager filesManager;
	
	@Captor
	private ArgumentCaptor<List<String>> captor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.register = new Register(input, filesManager);
	}

	@Test
	void registrarCandidatoComInformacoesValidas() {
		List<String> listQuestions = listFakeQuestions();
		List<String> example = Arrays.asList("Guilherme Mantz", "gui@gmail", "19", "103102301");
		
		Mockito.when(filesManager.readerQuestions()).thenReturn(listQuestions);
		
		Mockito.when(input.entryString()).thenReturn(
				example.get(0),
				example.get(1),
				example.get(2),
				example.get(3));
		
		register.registration();
		
		Mockito.verify(filesManager).addCandidate(captor.capture());
		List<String> candidate = captor.getValue();
		
		Assert.assertEquals(example, candidate);
		
	}
	
	@Test
	void naoDeveriaCadastrarCandidatoComCampoNulo() {
		List<String> listQuestions = listFakeQuestions();
		List<String> example = Arrays.asList("Guilherme Mantz", "gui@gmail", "18", "");
		
		Mockito.when(filesManager.readerQuestions()).thenReturn(listQuestions);
		
		Mockito.when(input.entryString()).thenReturn(
				example.get(0),
				example.get(1),
				example.get(2),
				example.get(3));
		
		register.registration();
		/*cai no loop de mensagens*/
	}

	private List<String> listFakeQuestions(){
		List<String> listFakeQuestions = new ArrayList<>();
		
		listFakeQuestions.add("Qual o seu nome completo?");
		listFakeQuestions.add("Qual seu e-mail?");
		listFakeQuestions.add("Qual sua idade?");
		listFakeQuestions.add("Qual seu whatsapp ou celular?");
		
		return listFakeQuestions;
	}

}
