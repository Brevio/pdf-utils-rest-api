package br.com.bradesco.tests;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bradesco.services.PDFUtil;

public class PDFTests {
	
	static String source;
	static String destination;
	@Before
	public void init() {
		source = "./src/test/resources/files/source/devops.pdf";
		destination = "./src/test/resources/files/destination/";		
	}
	
	@Test
	public void testToTiff() {
		PDFUtil.pdfToTiff(source, destination);
		File des = new File(destination);
	}
	@Ignore
	@Test
	public void testToPng() {
		PDFUtil.pdfToPng(source, destination);
	}
	@Ignore
	@Test
	public void testToJpg() {
		PDFUtil.pdfToJpg(source, destination);
	}

}
