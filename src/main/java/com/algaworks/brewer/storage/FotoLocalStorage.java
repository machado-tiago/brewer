package com.algaworks.brewer.storage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class FotoLocalStorage implements FotoStorage {
    
    private Path local;
    private Path temp;
    
    
    public FotoLocalStorage(Path local) {
        this.local = local;
        this.criarPastas();
    }
    
    public FotoLocalStorage() {
        this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerfotos"));
        
    }
    
    @Override
    public void salvarTemporariamente(@NotNull MultipartFile file) {
        Path locationPath = Paths.get(this.temp.toAbsolutePath().toString(), file.getOriginalFilename());
        try {
			file.transferTo(locationPath);
		} catch (IOException e) {
            throw new RuntimeException("Erro ao salvar na pasta temporária", e);
		}
    }

    private void criarPastas() {
        try {
            Files.createDirectories(this.local);
            this.setTemp(FileSystems.getDefault().getPath(this.getLocal().toString(), "temp"));
            Files.createDirectories(this.getTemp());

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar pasta para salvar foto", e);
        }
    }

    public Path getLocal() {
        return local;
    }

    public void setLocal(Path local) {
        this.local = local;
    }

    public Path getTemp() {
        return temp;
    }

    public void setTemp(Path temp) {
        this.temp = temp;
    }




    
}
