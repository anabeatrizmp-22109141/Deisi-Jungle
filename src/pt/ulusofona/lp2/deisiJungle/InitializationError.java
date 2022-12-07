package pt.ulusofona.lp2.deisiJungle;

public class InitializationError {

    private final String mensagem;

    public InitializationError(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMessage() {
        return mensagem;
    }
}
