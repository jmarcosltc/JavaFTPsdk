/*

    ************************************************************************
        -- Biblioteca simples pronta para uso FTP (File Transfer Protocol) --

        Simples, prática e rápida
        Usada para automação de processos FTP

        Uso de:
        -> Apache Commons Net (https://commons.apache.org/proper/commons-net/)
        -> Apache Commons Net FTPClient

        Funções:
        -> Listagem de arquivos e diretótios (OK)
        -> Upload e download de arquivos (OK)
        -> Log out (OK)

        Feito por: João Marcos Carneiro (https://github.com/jmarcosltc)
        Em: 03/07/2022
    ************************************************************************

 */

import java.io.*;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ClienteFTP {
    private String dominio;
    private String usuario;
    private String senha;
    private String dirFtp;
    private boolean adInfo = false;

    public ClienteFTP(String dominio, String usuario, String senha) throws FileNotFoundException {
        this.dominio = dominio;
        this.usuario = usuario;
        this.senha = senha;
    }

    public ClienteFTP(String dominio, String usuario, String senha, String dirFtp) throws FileNotFoundException {
        this.dominio = dominio;
        this.usuario = usuario;
        this.senha = senha;
        this.dirFtp = dirFtp;
    }

    public void uploadArq(File uploadFtp, String destino) throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        ftp.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
        ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);

        FileInputStream arqUpload = new FileInputStream(uploadFtp);

        ftp.changeWorkingDirectory(dirFtp);

        if (ftp.storeFile(destino, arqUpload)) {
            System.out.printf("Arquivo enviado com sucesso!");
        }
        else {
            System.out.printf("Erro ao enviar arquivo.");
        }

        ftp.logout();

        ftp.disconnect();

    }

    public void setInfoAdicional() {
        this.adInfo = true;
    }

    public void listarDir() throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        FTPFile[] dirs = ftp.listDirectories(dirFtp);

        ftp.changeWorkingDirectory(dirFtp);

        if (adInfo == true) {
            for (FTPFile f : dirs) {
                System.out.printf(String.valueOf(f) + "\n");
                System.out.printf("Nome: " + f.getName() + "\n");
                System.out.printf("Tamanho do arquivo: " + f.getSize() + "\n");
            }
        }
        else {
            for (FTPFile f : dirs) {
                System.out.printf(String.valueOf(f) + "\n");
            }
        }

        ftp.logout();

        ftp.disconnect();

    }

    public void listarArq() throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        ftp.changeWorkingDirectory (dirFtp);

        String[] arq = ftp.listNames();

        for (String f : arq){
            System.out.println(f);
        }

        ftp.logout();

        ftp.disconnect();

    }

    public void criarDir(String subDirNome) throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        ftp.changeWorkingDirectory (dirFtp);

        ftp.makeDirectory(subDirNome);

        ftp.logout();

        ftp.disconnect();
    }

    public void delArq(String nomeDoArq) throws IOException {
        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        ftp.changeWorkingDirectory (dirFtp);

        ftp.deleteFile(nomeDoArq);

        ftp.logout();

        ftp.disconnect();
    }

    public void mudarDir(String novoDir) {
        this.dirFtp = novoDir;
    }

    public void baixarArq(String nomeDoArq) throws IOException {

        FTPClient ftp = new FTPClient();

        ftp.connect(dominio);
        ftp.login(usuario, senha);

        ftp.changeWorkingDirectory (dirFtp);

        String[] arq = ftp.listNames();

        FileOutputStream fos = new FileOutputStream(nomeDoArq);

        if (ftp.retrieveFile(nomeDoArq, fos )) {
            System.out.println("Arquivo baixado.");
        }

        else {
            System.out.println("Erro ao baixar o arquivo.");
        }

        ftp.logout();

        ftp.disconnect();
    }
}


