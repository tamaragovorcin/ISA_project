package com.isaproject.isaproject.psw.controller;

import java.io.*;
import java.net.URLConnection;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.sshd.client.SshClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

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
			if (stringPart.equals(api)) {
				a = 5;
			}
		}
		if (a == 0) {
			return "\n\nThere is no file report.\n";
		}

		String filePath = "src/main/resources/TextFile.txt";
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	private SSHClient setupSshj() throws IOException {
		SSHClient client = new SSHClient();
		client.addHostKeyVerifier(new PromiscuousVerifier());
		client.connect("192.168.56.1", 22);
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

	//@RequestMapping(value = "/file/http", method = RequestMethod.POST)
	@PostMapping(value = "/file/http")
	public ResponseEntity<String> reportHttp(HttpServletRequest request) throws IOException, ServletException {
		System.out.println("TU SAMMMMMMMMMMMMMMM");
		Part filepart = request.getPart("file");
		String fileName = filepart.getSubmittedFileName();
		InputStream fileContent = filepart.getInputStream();
		FileWriter writer = new FileWriter("src/main/resources/htpp.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		String line = null;
		while ((line = reader.readLine()) != null) {
			writer.write(line + "\n");
		}
		writer.flush();
		writer.close();
		return ResponseEntity.ok().build();
	}
}