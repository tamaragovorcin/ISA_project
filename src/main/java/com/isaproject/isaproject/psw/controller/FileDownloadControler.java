package com.isaproject.isaproject.psw.controller;

import java.io.*;
import java.net.URLConnection;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.xml.bind.v2.runtime.output.XMLStreamWriterOutput;
import org.apache.sshd.client.SshClient;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.service.IPharmacyService;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/download")
public class FileDownloadControler {
	private IPharmacyService service;
	private WebClient.Builder webClientBuilder;
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
		client.connect("192.168.1.244", 22);
		client.authPassword("tester", "password");
		return client;
	}

	public void whenDownloadFileUsingSshj_thenSuccess() throws IOException {
		SSHClient sshClient = setupSshj();
		SFTPClient sftpClient = sshClient.newSFTPClient();
		String localFolder = "src/main/resources/";
		String remoteFile = "/pub/TextFile.txt";
		sftpClient.get(remoteFile, localFolder + "TextFile.txt");

		sftpClient.close();
		sshClient.disconnect();
	}


	@PostMapping(value = "/file/http")
	public ResponseEntity<String> reportHttp(HttpServletRequest request) throws IOException, ServletException {
		FileWriter writer;
		System.out.println("22222221435768743247");
		Part filepart = request.getPart("file");
		String fileName = filepart.getSubmittedFileName();
		InputStream fileContent = filepart.getInputStream();
		File f = new File("src/main/resources/FileReports/" + fileName);
		if(f.exists() && !f.isDirectory()) {
			Random rand = new Random();
			int n = rand.nextInt(1000);
			String []fileParts = fileName.split("\\.");
			String newName = fileParts[0] + "_" +n +".txt";
			writer= new FileWriter("src/main/resources/FileReports/" + newName);
		}
		else {
			writer = new FileWriter("src/main/resources/FileReports/" + fileName);

		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		String line = null;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			writer.write(line + "\n");
		}
		writer.flush();
		writer.close();
		return ResponseEntity.ok().build();
	}


	@PostMapping(value = "/prescription/http")
	public ResponseEntity<String> prescriptionHttp(HttpServletRequest request) throws IOException, ServletException {
		FileWriter writer;
		System.out.println("22222221435768743247");
		Part filepart = request.getPart("file");
		String fileName = filepart.getSubmittedFileName();
		InputStream fileContent = filepart.getInputStream();
		File f = new File("src/main/resources/FilePrescriptions/" + fileName);
		if(f.exists() && !f.isDirectory()) {
			Random rand = new Random();
			int n = rand.nextInt(1000);
			String []fileParts = fileName.split("\\.");
			String newName = fileParts[0] + "_" +n +".txt";
			writer= new FileWriter("src/main/resources/FilePrescriptions/" + newName);
		}
		else {
			writer = new FileWriter("src/main/resources/FilePrescriptions/" + fileName);

		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		String line = null;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			writer.write(line + "\n");
		}
		writer.flush();
		writer.close();
		return ResponseEntity.ok().build();
	}

}