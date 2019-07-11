package com.sxt.sso.itext;

import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


public class ItextPDF {
	
	      
	public static void main(String[] args) {
			String pageContent = "";
			try {
				URL url = new URL("http://124.207.169.19:8888/group1/M00/01/DE/CgIQYFrCzsbw_QzsAAEr6ykNkaw802_big.pdf");
				PdfReader reader = new PdfReader(url.openStream());
				int pageNum = reader.getNumberOfPages();
				for(int i=1;i<=pageNum;i++){
					pageContent += PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
				}
				System.out.println(pageContent);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}
