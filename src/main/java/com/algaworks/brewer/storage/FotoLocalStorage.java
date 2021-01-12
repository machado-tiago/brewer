package com.algaworks.brewer.storage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class FotoLocalStorage implements FotoStorage {
    //dois locais
    private Path local;
    private Path temp;
    
    
    public FotoLocalStorage(Path local) {
        this.local = local;
        this.criarPastas();
    }
    
    public FotoLocalStorage() {
        this(FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerfotos"));//local da pasta quando não é passado
        this.criarPastas();
        
    }
    
    private void criarPastas() {
        try {
            Files.createDirectories(this.local);//cria o diretório
            this.setTemp(FileSystems.getDefault().getPath(this.getLocal().toString(), "temp"));//path da pasta dentro do diretório criado, chamada temp
            Files.createDirectories(this.getTemp());//criação da temp

        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar pasta para salvar foto", e);
        }
    }

    @Override
    public String salvarTemporariamente(@NotNull MultipartFile file) {
        String novonome = renomearArquivo(file.getOriginalFilename());
        Path locationPath = Paths.get(this.temp.toAbsolutePath().toString(), novonome);
        try {
            file.transferTo(locationPath);
		} catch (IOException e) {
            throw new RuntimeException("Erro ao salvar na pasta temporária", e);
		}
        return novonome;
    }

    public String renomearArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_"+ nomeOriginal;
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
