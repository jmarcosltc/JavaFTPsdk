import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TesteFTP {
    public static void main(String[] args) throws IOException {
        ClienteFTP novoClient = new ClienteFTP("ftp.testedominio.com", "root", "123");

        File arquivo = new File("meuteste.txt");
        novoClient.uploadArq(arquivo, "C:\\Documentos");

        novoClient.listarDir();
        novoClient.listarArq();

        novoClient.criarDir("testedir");
        novoClient.mudarDir("C:\\Videos");

        novoClient.delArq("arquivoteste.txt");
        novoClient.baixarArq("arquivoteste.txt");
    }
}
