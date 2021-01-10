package com.isaproject.isaproject.psw.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface IFilesStorageService {
	  public void init();

	  public void save(MultipartFile file);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
	  void store(MultipartFile file);
	  Resource loadAsResource(String filename);
}
