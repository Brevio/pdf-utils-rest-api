package br.com.bradesco.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Props {
	static Properties props = new Properties();
	static final Logger log = Logger.getLogger(Props.class);
	
	static {
		try {
			props.load(new FileInputStream(new File("pdf_utils.properties")));
			log.info("Properties carregado com sucesso!");
		} catch (FileNotFoundException e) {
			log.info("Arquivo pdf_utils.properties não encontrado!");
			log.error("Arquivo pdf_utils.properties não encontrado!");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("IOEception :" + e.getMessage());
			log.error("IOEception :" + e.getMessage());
			e.printStackTrace();
		}
	}
	public static String get(String key) {
		return props.getProperty(key);
	}

}
