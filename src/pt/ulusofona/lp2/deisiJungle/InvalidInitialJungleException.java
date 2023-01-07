package pt.ulusofona.lp2.deisiJungle;

public class InvalidInitialJungleException extends Exception {

    private String tipoDeErro;

    public InvalidInitialJungleException(String message, String tipoDeErro) {
        super(message);
        this.tipoDeErro = tipoDeErro;
    }

    public InvalidInitialJungleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public boolean isInvalidPlayer() {
        return tipoDeErro.equals("Jogador Inválido");
    }

    public boolean isInvalidFood(){
        return tipoDeErro.equals("Alimento inválido");
    }
}
