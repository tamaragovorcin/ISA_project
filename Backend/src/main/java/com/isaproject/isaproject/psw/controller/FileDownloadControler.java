package com.isaproject.isaproject.psw.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.isaproject.isaproject.psw.service.IPharmacyService;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileDownloadControler {

	@Autowired
	private IPharmacyService service;
	@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping("/download/file/report")
	public void getFile() throws IOException {
		whenDownloadFileUsingSshj_thenSuccessReport();
	}

	@GetMapping("/download/file/prescription")
	@ResponseBody
	public void getFilePrescription() throws IOException {
		whenDownloadFileUsingSshj_thenSuccessPrescription();
	}

	@GetMapping("/download/file/report/{api}")
	@ResponseBody
	ResponseEntity<String> getFileReport(@PathVariable String api) throws IOException {
		System.out.println("Pogodio metodu za report");
		whenDownloadFileUsingSshj_thenSuccessReport();
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
			ResponseEntity.ok("There is no file report.");
		}

		String filePath = "src/main/resources/TextFile.txt";
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(contentBuilder.toString());
	}

	private SSHClient setupSshj() throws IOException {
		SSHClient client = new SSHClient();
		client.addHostKeyVerifier(new PromiscuousVerifier());
		client.connect("192.168.56.1", 22);
		client.authPassword("tester", "password");
		return client;
	}

	public void whenDownloadFileUsingSshj_thenSuccessReport() throws IOException {
		SSHClient sshClient = setupSshj();
		SFTPClient sftpClient = sshClient.newSFTPClient();
		String localFolder = "src/main/resources/FileReport/";
		//String remoteDir = "/pub/";
		final  File remoteDir = new File("C:/Users/Tamara/Desktop/REBEX/data/pub/");
		FileWriter writer;
		String []contents = remoteDir.list();
		for(String fn : contents) {
			File localFile = new File("src/main/resources/FileReports/" + fn);
			if(fn.startsWith("Report")) {
				if (!localFile.exists()) {
					String remoteFileString = "/pub/" + fn;
					writer = new FileWriter("src/main/resources/FileReports/" + fn);
					writer.write("");
					writer.flush();
					writer.close();
					sftpClient.get(remoteFileString, "src/main/resources/FileReports/" + fn);
				}
			}
		}
		sftpClient.close();
		sshClient.disconnect();
	}
	public void whenDownloadFileUsingSshj_thenSuccessPrescription() throws IOException {
		SSHClient sshClient = setupSshj();
		SFTPClient sftpClient = sshClient.newSFTPClient();
		String localFolder = "src/main/resources/FilePrescriptions/";
		//String remoteDir = "/pub/";
		final  File remoteDir = new File("C:/Users/Tamara/Desktop/REBEX/data/pub/");
		FileWriter writer;
		String []contents = remoteDir.list();
		for(String fn : contents) {
			File localFile = new File("src/main/resources/FilePrescriptions/" + fn);
			if(fn.startsWith("Pre")) {
				if (!localFile.exists()) {
					String remoteFileString = "/pub/" + fn;
					writer = new FileWriter("src/main/resources/FilePrescriptions/" + fn);
					writer.write("");
					writer.flush();
					writer.close();
					sftpClient.get(remoteFileString, "src/main/resources/FilePrescriptions/" + fn);
				}
			}
		}
		sftpClient.close();
		sshClient.disconnect();
	}

	@PostMapping(value = "/download/file/http")
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


	@PostMapping(value = "/download/prescription/http")
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