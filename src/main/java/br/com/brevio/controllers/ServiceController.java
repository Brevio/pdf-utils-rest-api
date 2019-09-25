package br.com.brevio.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.text.Utilities;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brevio.models.PdfResponse;
import br.com.brevio.models.PdfResquest;
import br.com.brevio.properties.Props;
import br.com.brevio.services.PDFUtil;

@RestController
@RequestMapping("services")
public class ServiceController {
	
	private final String pathSource = Props.get("path.source");
	private final String pathTarget = Props.get("path.target");
	@RequestMapping(value = "tiff", method = RequestMethod.POST)
	public PdfResponse tiff(@RequestBody PdfResquest request) {
		PdfResponse response = new PdfResponse();
		if(request != null) {
			if(request.getFileName()!=null || request.getFileName().equals("")) {
				String fileNamePath = pathSource+request.getFileName();
				String fileNamePathAfter = pathTarget+request.getFileName().replace(".pdf", ".tif");
				try {
					byte[] byteArrayFile = Base64.decodeBase64(request.getFileBase64());
					try (FileOutputStream fos = new FileOutputStream(fileNamePath)) {
						   fos.write(byteArrayFile);
						}
					if(PDFUtil.pdfToTiff(fileNamePath, pathTarget)) {
						byte[] fileResponse = Files.readAllBytes(Paths.get(new File(fileNamePathAfter).toURI()));
						response.setStatus(200);
						response.setMessage("sucesso");
						response.setFileBase64(Base64.encodeBase64String(fileResponse));
					}
				}catch (Exception e) {
					response.setStatus(500);
					response.setMessage("falha. ex "+e.getMessage());
				}
			}
		}
		return response;
	}
}
