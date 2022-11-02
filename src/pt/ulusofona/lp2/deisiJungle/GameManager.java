package pt.ulusofona.lp2.deisiJungle;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager {

    int nrJogadores;
    int jungleSize;
    int initialEnergy;
    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Especie> especies = new ArrayList<>();
    ArrayList<Square> mapa = new ArrayList<>();

    String[][] playersInfo;

    public GameManager() {
    }

    public GameManager(int nrJogadores, int jungleSize, int initialEnergy, String[][] playersInfo) {
        this.nrJogadores = nrJogadores;
        this.jungleSize = jungleSize;
        this.initialEnergy = initialEnergy;
        this.playersInfo = playersInfo;
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
        //WARNING! Cuidado código esparguete

        ArrayList<Integer> idsJogador = new ArrayList<>();
        String[][] especies = getSpecies();
        ArrayList<String> idsEspecie = new ArrayList<>();

        // Verificação dos ids de jogador iguais
        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(idsJogador.contains(Integer.parseInt(playersInfo[i][0]))) {
                return false;
            }
            idsJogador.add(Integer.parseInt(playersInfo[i][0]));
        }

        // Verificação dos nomes
        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][1] == null || playersInfo[i][1].isBlank()) {
                return false;
            }
        }

        //Verficação dos ids das especies
        for(int i = 0 ; i < especies.length ; i++) {
            idsEspecie.add(especies[i][0]);
        }

        int nrOcurrenciasTarzan = 0;
        for(int i = 0 ; i < playersInfo.length ; i++) {

            if(playersInfo[i][2].equals("Z")) {
                nrOcurrenciasTarzan++;
            }

            if(nrOcurrenciasTarzan > 1) {
                return false;
            }

            if(!idsEspecie.contains(playersInfo[i][2])) {
                return false;
            }
        }

        //Verificação de nr de Jogadores
        if(playersInfo.length < 2 || playersInfo.length > 4) {
            return false;
        }

        //Verificacao tamanho mapa
        if(jungleSize < 2 * playersInfo.length) {
            return false;
        }

        String jogadoresNaPosicao = "";
        Collections.sort(idsJogador);

        for(int i = 0 ; i <= idsJogador.size() ; i++) {

            if(i != idsJogador.size()) {
                jogadoresNaPosicao += idsJogador + ",";
            }else {
                jogadoresNaPosicao += "" + idsJogador;
            }
        }

        //Criação mapa
        for(int i = 0 ; i <= jungleSize ; i++) {
            if(i == 0) {
                Square posicao = new Square(i, "blank.png", "Vazio", jogadoresNaPosicao);
                this.mapa.add(posicao);
            }
            else if(i == jungleSize) {
                Square posicao = new Square(i,"finish.png", "Meta", "");
                this.mapa.add(posicao);
            }
            else {
                Square posicao = new Square(i, "blank.png", "Vazio", "");
                this.mapa.add(posicao);
            }
        }

        //cria jogadores
        for(int i = 0 ; i <= playersInfo.length ; i++) {
            int id = Integer.parseInt(playersInfo[i][0]);
            String nome = playersInfo[i][1];
            String idEspecie = playersInfo[i][2];

            Jogador jogador = new Jogador(id, nome, idEspecie, initialEnergy, false, mapa.get(0));
            jogadores.add(jogador);
        }
        
        return true;

    }

    public int[] getPlayerIds(int squareNr) {
        int [] id_players = new int[4];

        if(squareNr > jungleSize || squareNr < 0){
            return id_players;
        }

        for(int i = 0; i < jogadores.size(); i++){
            if(squareNr == jogadores.get(i).casaAtual.nrSquare){
                id_players[i] = jogadores.get(i).id;
            }
        }

        return id_players;
    }

    public String[] getSquareInfo(int squareNr) {

        String[] player_info = new String[4];

        if(squareNr > jungleSize || squareNr < 0){
            return null;
        }

        for (Square square : mapa) {
            player_info[0] = square.imagem;
            player_info[1] = square.descricao;
            player_info[2] = square.jogadoresNaPosicao;
        }

        return player_info;
    }

    public String[] getPlayerInfo(int playerId) {

        String[] informacaoJogador = new String[4];

        for (Jogador j : jogadores) {
            if(j.id == playerId) {
                informacaoJogador[0] = playerId + "";
                informacaoJogador[1] = j.nome;
                informacaoJogador[2] = j.idEspecie + "";
                informacaoJogador[3] = j.energia + "";
                return informacaoJogador;
            }
        }

        return null;
    }

    public String[] getCurrentPlayerInfo() {

        for (Jogador j : jogadores) {
            if(j.isTurnoDoJogador()) {
                return getPlayerInfo(j.id);
            }
        }

        return null;
    }

    public String[][] getPlayersInfo() {
        return this.playersInfo;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        if(nrSquares < 1 || nrSquares > 6 && !bypassValidations){
            return false;
        }

        for (Jogador j : jogadores) {
            if (j.isTurnoDoJogador()) {
                for (int i = 0; i < nrSquares; i++) {
                    j.movimentacao();
                }
            }
        }

        return true;
    }

    public String[] getWinnerInfo() {

        for(Jogador j : jogadores){
            if (j.ganhou()){
                return getPlayerInfo(j.id);
            }
        }

        return null;
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
