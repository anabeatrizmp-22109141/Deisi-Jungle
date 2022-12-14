package pt.ulusofona.lp2.deisiJungle;

public record MovementResult(MovementResultCode code, String mensagem) {
    public String message() {
        return "";
    }
}
