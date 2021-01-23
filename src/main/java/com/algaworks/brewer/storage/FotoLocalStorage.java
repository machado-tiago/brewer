package com.algaworks.brewer.storage;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class FotoLocalStorage implements FotoStorage {
    //dois locais
    private Path local;
    private Path temp;
    
    
    public FotoLocalStorage(Path local) {
        this.local = local;
        this.criarPastas();
    }
    
    public FotoLocalStorage() {
        this(FileSystems.getDefault().getPath(System.getProperty("user.dir"), "fotos"));//local do projeto atual
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
    
    @Override
    public byte[] recuperarFotoTemporaria(String nome) {
        try {
            return Files.readAllBytes(this.temp.resolve(nome));
		} catch (IOException e) {
            throw new RuntimeException("Erro lendo a foto temporária", e);
		}
    }
    
    @Override
    public byte[] recuperarFotoSalva(String nome) {
        try {
            return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
            throw new RuntimeException("Erro lendo a foto salva", e);
		}
    }
    
    public String renomearArquivo(String nomeOriginal){
        return UUID.randomUUID().toString() + "_"+ nomeOriginal;
    }
    
    @Override
    public void salvar(String nomeFoto) {
        try {
            Files.move(this.getTemp().resolve(nomeFoto), this.getLocal().resolve(nomeFoto));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao mover a foto para o destino final", e);
        }

        //utilização de lib de criação de thumbnail
        try {
            Thumbnails.of(this.getLocal().resolve(nomeFoto).toString()).size(40, 68)
                    .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar thumbnail",e);
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
