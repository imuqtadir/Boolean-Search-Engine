package edu.buffalo.cse.irf14.document.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.buffalo.cse.irf14.document.Document;
import edu.buffalo.cse.irf14.document.FieldNames;
import edu.buffalo.cse.irf14.document.Parser;
import edu.buffalo.cse.irf14.document.ParserException;

public class ParserTest {
	@Test
	public final void testPositive() throws ParserException {
		String ipFile = "H:\\news_training\\training\\alum\\0008905";
		Document d = Parser.parse(ipFile);
		assertEquals("0008905", d.getField(FieldNames.FILEID)[0]);
		assertEquals("alum", d.getField(FieldNames.CATEGORY)[0]); //etc
	}

}
