public class OpcaoInvalidaException extends Throwable {
    private String message;

    public OpcaoInvalidaException(String mensagem) {
        this.message = mensagem;

    }

    @Override
    public String getMessage() {
        return "Opção inválida!";
    }
}
