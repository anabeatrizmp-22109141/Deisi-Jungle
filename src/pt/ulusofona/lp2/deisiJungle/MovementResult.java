package pt.ulusofona.lp2.deisiJungle;

public record MovementResult(MovementResultCode code, String message) {

    public String message() {
        if(code == MovementResultCode.INVALID_MOVEMENT) {
            return "Movimento Inválido";
        }
        if(code == MovementResultCode.VALID_MOVEMENT) {
            return "Movimento Válido";
        }
        if(code == MovementResultCode.NO_ENERGY) {
            return "Sem energia para mover";
        }
        return "";
    }
}
