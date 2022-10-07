package test.java.br.com.codar.application.options.primaryoptions;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.codar.application.options.primaryoptions.AddQuestionForm;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

class AddQuestionFormTest {

private AddQuestionForm addQuestion;
	
	@Mock
	private DataInput input;
	
	@Mock
	private FilesManager filesManager;
	
	@Captor
	private ArgumentCaptor<String> captor;
	
	@BeforeEach
	public void beforeEach() {
		
		MockitoAnnotations.openMocks(this);
		this.addQuestion = new AddQuestionForm(input, filesManager);
	}
	
	@Test
	void adicionandoPerguntaNoFormulario() {
		String testQuestion = "Teste";
		Mockito.when(input.entryString()).thenReturn(testQuestion);
		
		addQuestion.execute();	
		Mockito.verify(filesManager).addQuestionForm(captor.capture());
		
		String question = captor.getValue();		
		Assert.assertTrue(!question.isEmpty());
	}

}
