package com.isaproject.isaproject.psw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.sshd.client.SshClient;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.service.IPharmacyService;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;



@RestController
@RequestMapping("/download")
public class FileDownloadControler {
	  private IPharmacyService service;
	
	  @GetMapping("/file")
	  @ResponseBody
	  public void getFile() throws IOException {
		  whenDownloadFileUsingSshj_thenSuccess();
	  }
	  
	  @GetMapping("/file/report/{api}")
	  @ResponseBody
	  public String getFileReport(@PathVariable String api) throws IOException {
		  whenDownloadFileUsingSshj_thenSuccess();
		 
		 
		  BufferedReader brTest = new BufferedReader(new FileReader("src/main/resources/TextFile.txt"));
		  String text = brTest.readLine();
		  String text_parts[] = text.split("!");
		  String again_parts[] = text_parts[0].split(";");
	      int a = 0;
		  for (String stringPart : again_parts) {
			if(stringPart.equals(api)) {
					a = 5;
			}
		  }
		  if(a==0) {
		     return "\n\nThere is no file report.\n";
		  }
		  
		  String filePath = "src/main/resources/TextFile.txt";
		  StringBuilder contentBuilder = new StringBuilder();
		  
	        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
	        {
	            stream.forEach(s -> contentBuilder.append(s).append("\n"));
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	 
	        return contentBuilder.toString();
	  }
	  private SSHClient setupSshj() throws IOException {
		    SSHClient client = new SSHClient();
		    client.addHostKeyVerifier(new PromiscuousVerifier());
		    client.connect("192.168.56.1",22);
		    client.authPassword("tester", "password");
		    return client;
		}
	  
	  public void whenDownloadFileUsingSshj_thenSuccess() throws IOException {
		  SSHClient sshClient = setupSshj();
		  SFTPClient sftpClient = sshClient.newSFTPClient();
		  String localFolder = "src/main/resources/";
		  String remoteFile = "/pub/TextFile.txt";
		  System.out.println("***********************************************************************************************************");
		  sftpClient.get(remoteFile, localFolder + "TextFile.txt");
		  System.out.println("*************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!**********************");

	      sftpClient.close();
	      sshClient.disconnect();
	  }
	  
}