package br.com.codar.application.options.primaryoptions;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.codar.application.options.Menu;
import br.com.codar.application.options.Option;
import br.com.codar.factory.DaoFactory;
import br.com.codar.manager.FilesManager;
import br.com.codar.utils.DataInput;

public class MenuPrimary implements Menu {

	private final DataInput input = new DataInput();
	private final FilesManager filesManager = DaoFactory.createFilesManagerDao();

	@Override
	public Map<String, Option> options() {
		Map<String, Option> options = new LinkedHashMap<>();
		
		options.put("1", new Register(input, filesManager));
		options.put("2", new AddQuestionForm(input, filesManager));
		options.put("3", new RemoveQuestionForm(input, filesManager));
		options.put("4", new Candidates(input, filesManager));
		options.put("5", new Forms(input, filesManager));
		options.put("9", new ExitOption());
		
		return options;
	}

}
