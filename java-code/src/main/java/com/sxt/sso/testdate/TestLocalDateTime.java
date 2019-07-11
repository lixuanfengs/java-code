package com.sxt.sso.testdate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class TestLocalDateTime {


	    public static void main(String[] args) throws Exception {
	        URL url = new URL("http://192.168.1.63:8080/DuplicateCheckingSystem/views/formal_assess/assess-detail.html?id=3");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        BufferedWriter writer = new BufferedWriter(new FileWriter("save2yiibai-index.docx"));
	        String line;

	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	            writer.write(line);
	            writer.newLine();
	        }
	        reader.close();

	        writer.close();
	    }

}
