package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {
    public GameManager() {
    }

    public String[][] getSpecies() {
        return new String[1][1];
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {
        return true;
    }

    public int[] getPlayerIds(int squareNr) {
        return new int[1];
    }

    public String[] getSquareInfo(int squareNr) {
        return new String[1];
    }

    public String[] getPlayerInfo(int playerId) {
        return new String[1];
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
        return new String[1];
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
