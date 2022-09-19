package br.com.codar.factory;

import br.com.codar.manager.FilesManager;

public class DaoFactory {

	public static FilesManager createFilesManagerDao() {
		return new FilesManager();
	}
}
