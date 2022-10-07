package test.java.br.com.codar.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.codar.utils.FormatUtil;

class FormatUtilTest {

	@Test
	void deveriaRetornarStringSemEspacosAcentosETodosMaiusculos() {
		String format = FormatUtil.formatString("ÁúãçpórTPá");
		Assert.assertEquals("AUACPORTPA", format);
	}

}
