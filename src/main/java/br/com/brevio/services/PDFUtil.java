package br.com.brevio.services;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFEncodeParam;

public class PDFUtil {
	
	final static Logger log = Logger.getLogger(PDFUtil.class);
	static boolean ret = false;
	public static boolean pdfToTiff(String source, String destination) {
		
	try {
		File sourceFile = new File(source);
		File destinationFile = new File(destination);
		List <BufferedImage>listBuff =  null;

		if (!destinationFile.exists()) {
			destinationFile.mkdir();
			log.info("Folder Created -> " + destinationFile.getAbsolutePath());
		}
		if (sourceFile.exists()) {
			PDDocument document = PDDocument.load(source);
			@SuppressWarnings("unchecked")
			List<PDPage> list = document.getDocumentCatalog().getAllPages();

			String fileName = sourceFile.getName().replace(".pdf", "");
			
			ImageEncoder encoder ;
			OutputStream out = new FileOutputStream(destination+fileName+".tif");
			BufferedImage image[] = new BufferedImage[list.size()];
			listBuff = new ArrayList<BufferedImage>();
			int j = 0;
			for (PDPage page : list) {
				image[j] = page.convertToImage();
			    	j++;		
			}
			for (int i = 1; i < image.length; i++) {
		        listBuff.add(image[i]);
		    }
			TIFFEncodeParam params = new TIFFEncodeParam();
			params.setCompression(TIFFEncodeParam.COMPRESSION_DEFLATE);
			params.setExtraImages(listBuff.iterator());
			encoder = ImageCodec.createImageEncoder("tiff", out, params);    
			encoder.encode(image[0]);	
			out.flush();
			out.close();
			document.close();
			ret = true;
			log.info("Image saved at -> " + destinationFile.getAbsolutePath());
		} else {
			log.error(sourceFile.getName() + " File does not exist");
		}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} 
		return ret;
	
	}
	public static boolean pdfToPng(String source, String destination) {
		
	try {
		File sourceFile = new File(source);
		File destinationFile = new File(destination);
		if (!destinationFile.exists()) {
			destinationFile.mkdir();
			log.info("Folder Created -> " + destinationFile.getAbsolutePath());
		}
		if (sourceFile.exists()) {
			PDDocument document = PDDocument.load(source);
			@SuppressWarnings("unchecked")
			List<PDPage> list = document.getDocumentCatalog().getAllPages();

			String fileName = sourceFile.getName().replace(".pdf", "");
			int pageNumber = 1;
			for (PDPage page : list) {
				BufferedImage image = page.convertToImage();
				File outputfile = new File(destination + fileName + "_" + pageNumber + ".png");
				ImageIO.write(image, "png", outputfile);
				pageNumber++;
			}
			document.close();
			ret=true;
			log.info("Image saved at -> " + destinationFile.getAbsolutePath());
		} else {
			log.error(sourceFile.getName() + " File does not exist");
		}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	return ret;
	}
	
	public static boolean pdfToJpg(String source, String destination) {
		
	try {
		File sourceFile = new File(source);
		File destinationFile = new File(destination);
		if (!destinationFile.exists()) {
			destinationFile.mkdir();
			log.info("Folder Created -> " + destinationFile.getAbsolutePath());
		}
		if (sourceFile.exists()) {
			PDDocument document = PDDocument.load(source);
			@SuppressWarnings("unchecked")
			List<PDPage> list = document.getDocumentCatalog().getAllPages();

			String fileName = sourceFile.getName().replace(".pdf", "");
			int pageNumber = 1;
			for (PDPage page : list) {
				BufferedImage image = page.convertToImage();
				File outputfile = new File(destination + fileName + "_" + pageNumber + ".jpg");
				ImageIO.write(image, "jpg", outputfile);
				pageNumber++;
			}
			document.close();
			ret = true;
			log.info("Image saved at -> " + destinationFile.getAbsolutePath());
		} else {
			log.error(sourceFile.getName() + " File does not exist");
		}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	return ret;
	}
	
	
	
}
