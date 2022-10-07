package test.java.br.com.codar.application.options.primaryoptions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.codar.application.options.primaryoptions.RemoveQuestionForm;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

class RemoveQuestionFormTest {

	private RemoveQuestionForm removeQuestion;
	
	@Mock
	private DataInput input;
	
	@Mock
	private FilesManager filesManager;
	
	@Captor
	private ArgumentCaptor<String> captor;
	
	@BeforeEach
	public void beforeEach() {
		
		MockitoAnnotations.openMocks(this);
		this.removeQuestion = new RemoveQuestionForm(input, filesManager);
	}
	
	@Test
	void deveriaRemoverUmaPergunta() {
		int index = 5;
		
		List<String> listQuestions = listFakeQuestions();
		Mockito.when(filesManager.readerQuestions()).thenReturn(listQuestions);
		Mockito.when(input.entryNumber()).thenReturn(index);
		
		removeQuestion.execute();
		
		String removed = listFakeQuestions().get(index -1);
		Assert.assertTrue(!listQuestions.contains(removed));
	}
	
	private List<String> listFakeQuestions(){
		List<String> listFakeQuestions = new ArrayList<>();
		
		listFakeQuestions.add("Qual o seu nome completo?");
		listFakeQuestions.add("Qual seu e-mail?");
		listFakeQuestions.add("Qual sua idade?");
		listFakeQuestions.add("Qual seu whatsapp ou celular?");
		listFakeQuestions.add("pergunta Teste");
		listFakeQuestions.add("pergunta Teste2");
		
		return listFakeQuestions;
	}

}
