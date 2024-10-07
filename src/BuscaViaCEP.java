import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscaViaCEP {
    private final String baseUrlViaCEP = "http://viacep.com.br/ws/";
    private String cep;

    public BuscaViaCEP(String cep) {
        this.cep = cep;
    }

    public String getUrlBuscaViaCEP() {
        return this.baseUrlViaCEP + this.cep + "/json";
    }

    public String buscaCEP() throws IOException, InterruptedException {
        String json = "";
        final String endereco = this.getUrlBuscaViaCEP();
        try {
            // Configurar o autenticador para o proxy
            String username = JOptionPane.showInputDialog(null, "Digite o usuário do proxy:");
            JPasswordField passwordField = new JPasswordField();
            passwordField.setEchoChar('*');

            String password = JOptionPane.showInputDialog(null, "Digite a senha do proxy:");
            //String password = new String(passwordField.getPassword());
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password.toCharArray());
                }
            });

            InetSocketAddress proxy = new InetSocketAddress("127.0.0.1", 40080);
            HttpClient client = HttpClient.newBuilder()
                    .proxy(ProxySelector.of(proxy))
                    .authenticator(Authenticator.getDefault())
                    .build();

            //HostConfiguration config = client.proxy();


            HttpRequest request = HttpRequest.newBuilder()

                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

}
