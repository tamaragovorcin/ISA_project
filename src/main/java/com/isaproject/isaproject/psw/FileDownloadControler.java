package com.isaproject.isaproject.psw;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.sshd.client.SshClient;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;



@RestController
@RequestMapping("/download")
public class FileDownloadControler {

	  @GetMapping("/file")
	  @ResponseBody
	  public void getFile() throws IOException {
		  whenDownloadFileUsingSshj_thenSuccess();
	  }
	  private SSHClient setupSshj() throws IOException {
		    SSHClient client = new SSHClient();
		    client.addHostKeyVerifier(new PromiscuousVerifier());
		    client.connect("192.168.0.28",22);
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