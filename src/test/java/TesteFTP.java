import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TesteFTP {
    public static void main(String[] args) throws IOException {
        ClienteFTP novoClient = new ClienteFTP("ftp.teste.com", "testeuser", "testespw", "testedir");

        novoClient.listarArq();
        FileInputStream arqUpload = new FileInputStream("\\C:\\Users\\Joao\\Desktop\\as.txt");
        novoClient.uploadArq(new File("C:\\Users\\Joao\\Desktop\\as.txt"), "arquivo.txt");
    }
}
