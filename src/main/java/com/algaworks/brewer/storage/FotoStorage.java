package com.algaworks.brewer.storage;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
    public String salvarTemporariamente(MultipartFile file);
    public byte[] recuperarFotoTemporaria(String nome);
    public Path getTemp();
    public Path getLocal();
	public byte[] recuperarFotoSalva(String nome);
}
