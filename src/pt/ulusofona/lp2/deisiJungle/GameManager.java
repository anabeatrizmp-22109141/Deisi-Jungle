package pt.ulusofona.lp2.deisiJungle;
import javax.swing.*;
import java.util.ArrayList;

public class GameManager {

    Jogadores[] jogadores = new Jogadores[4];

    public GameManager() {
    }

    public String[][] getSpecies() {
        String[][] especies = new String[5][3];

        especies[0][0] = "E";
        especies[0][1] = "Elefante";
        especies[0][2] = "elephant.png";

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";

        return especies;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {
        return true;
    }

    public int[] getPlayerIds(int squareNr) {
        int [] id_players = new int[4];

        return id_players;
    }

    public String[] getSquareInfo(int squareNr) {
        return new String[1];
    }

    public String[] getPlayerInfo(int playerId) {

        String[] informacaoJogador = new String[4];

        for (Jogadores j : jogadores) {
            if(j.id == playerId) {
                informacaoJogador[0] = playerId + "";
                informacaoJogador[1] = j.nome;
                informacaoJogador[2] = j.especie.id + "";
                informacaoJogador[3] = j.energia + "";
                return informacaoJogador;
            }
        }

        return null;
    }

    public String[] getCurrentPlayerInfo() {
        return new String[1];
    }

    public String[][] getPlayersInfo() {
        return new String[1][1];
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        return false;
    }

    public String[] getWinnerInfo() {
        return new String[2];
    }

    public ArrayList<String> getGameResults() {
        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }

    public String whoIsTaborda() {
        return "Wrestling";
    }
}
