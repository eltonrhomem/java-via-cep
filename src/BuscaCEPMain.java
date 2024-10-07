import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BuscaCEPMain {
    public static void main(String args[]) throws OpcaoInvalidaException {
        final String menu = """
                *** Sistema de Busca de CEP ***
                selecione uma das opções abaixo:
                
                1 - Buscar um CEP
                2 - Sair do sistema
                """;
        byte opcaoSelecionada = 0;
        List<Byte> opcoesValidas = Collections.unmodifiableList(Arrays.asList((byte)1, (byte) 2, (byte) 4));
        Scanner entradaDoUsuario = new Scanner(System.in);

        try {
            while(opcaoSelecionada != 2) {
                System.out.println(menu);
                System.out.print("Informa a opção: ");
                opcaoSelecionada = entradaDoUsuario.nextByte();
                entradaDoUsuario.nextLine();

                if(!opcoesValidas.contains(opcaoSelecionada)) {
                    throw new OpcaoInvalidaException("Informe uma opção válida do menu!");
                }

                switch (opcaoSelecionada) {
                    case 1:
                        System.out.print("Digite o CEP: ");
                        //String cep = entradaDoUsuario.nextLine();
                        //Scanner entradaCEP = new Scanner(System.in);
                        String cep = entradaDoUsuario.nextLine();
                        BuscaViaCEP cepInformado = new BuscaViaCEP(cep);
                        String reposta = cepInformado.buscaCEP();
                        System.out.println(reposta);
                        break;
                    case 2: System.out.println("Tchaaaaauu!!!");
                            break;
                    default: break;
                }
            }


        }
        catch (OpcaoInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
