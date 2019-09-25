package br.com.brevio.models;

public class PdfResponse extends ServiceResponse{
	
	private String fileBase64;

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}
	

}
