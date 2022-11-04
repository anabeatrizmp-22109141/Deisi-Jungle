package pt.ulusofona.lp2.deisiJungle;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameManager {

    int nrJogadores;

    int initialEnergy;
    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Especie> especiesarray = new ArrayList<>();
    HashMap<Integer,Square> mapa = new HashMap<>();
    int jungleSize;

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

        // Verificação dos ids de jogador iguais
        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][0] == null || playersInfo[i][0].isBlank()) {
                return false;
            }
            if(!playersInfo[i][0].chars().allMatch(Character ::isDigit)) {
                return false;
            }
            if(idsJogador.contains(Integer.parseInt(playersInfo[i][0]))) {
                return false;
            }
            idsJogador.add(Integer.parseInt(playersInfo[i][0]));
        }
        // Verificação dos nomes
        if(nomeInvalido(playersInfo)) {
            return false;
        }
        //Verficação dos ids das especies
        if(idEspecieInvalido(playersInfo)) {
            return false;
        }
        //Verificação de nr de Jogadores
        if(playersInfo.length < 2 || playersInfo.length > 4) {
            return false;
        }
        //Verificacao tamanho mapa
        if(jungleSize < 2 * playersInfo.length) {
            return false;
        }
        //Cria mapa
        criaMapa(jungleSize, idsJogador);

        //cria jogadores
        criaJogadores(playersInfo, idsJogador);
        
        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        String jogadores = this.mapa.get(squareNr).jogadoresNaPosicao;
        String[] jogadoresSeparados = jogadores.split(",");

        int [] id_players = new int[jogadoresSeparados.length];

        if(squareNr >jungleSize || squareNr < 1){
            return null;
        }
        for(int i = 0 ; i < jogadoresSeparados.length ; i++) {
            if(!jogadores.isBlank()) {
                id_players[i] = Integer.parseInt(jogadoresSeparados[i]);
            }
            else{
                return new int[0];
            }
        }

        return id_players;
    }

    public String[] getSquareInfo(int squareNr) {

        String[] player_info = new String[3];

        if(squareNr > jungleSize || squareNr < 1){
            return null;
        }

        player_info[0] = this.mapa.get(squareNr).imagem;
        player_info[1] = this.mapa.get(squareNr).descricao;
        player_info[2] = this.mapa.get(squareNr).jogadoresNaPosicao;


        return player_info;
    }

    public String[] getPlayerInfo(int playerId) {

        String[] informacaoJogador = new String[4];

        String[][] especie = getSpecies();

        for (Jogador j : jogadores) {
            if(j.id == playerId) {
                informacaoJogador[0] = j.id + "";
                informacaoJogador[1] = especie[playerId][1];
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
        String[][] informacao = new String[jogadores.size()][3];

        for(int i = 0 ; i < jogadores.size() ; i++) {
            informacao[i] = getPlayerInfo(jogadores.get(i).id);
        }
        this.playersInfo = informacao;

        return informacao;
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

    public boolean nomeInvalido(String[][] playersInfo) {

        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][1] == null || playersInfo[i][1].isBlank()) {
                return true;
            }
        }
        return false;
    }

    public boolean idEspecieInvalido(String[][] playersInfo) {

        String[][] especies = getSpecies();
        ArrayList<String> idsEspecie = new ArrayList<>();
        int nrOcurrenciasTarzan = 0;

        for(int i = 0 ; i < especies.length ; i++) {
            idsEspecie.add(especies[i][0]);
        }

        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][2] == null || playersInfo[i][2].isBlank()) {
                return true;
            }
            if(!idsEspecie.contains(playersInfo[i][2])) {
                return true;
            }
            if(playersInfo[i][2].equals("Z")) {
                nrOcurrenciasTarzan++;
                if(nrOcurrenciasTarzan > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void criaMapa(int jungleSize, ArrayList<Integer> idsJogador) {
        String jogadoresNaPosicao = "";
        Collections.sort(idsJogador);

        for(int i = 1 ; i <= idsJogador.size() ; i++) {
            if(i != idsJogador.size()) {
                jogadoresNaPosicao += idsJogador.get(i-1) + ",";
            }else {
                jogadoresNaPosicao +=  idsJogador.get(i-1);
            }
        }

        for(int i = 1 ; i <= jungleSize ; i++) {
            if(i == 1) {
                Square posicao = new Square(i, "blank.png", "Vazio", jogadoresNaPosicao);
                this.mapa.put(i, posicao);
            }
            else if(i == jungleSize) {
                Square posicao = new Square(i,"finish.png", "Meta", "");
                this.mapa.put(i, posicao);
            }
            else {
                Square posicao = new Square(i, "blank.png", "Vazio", "");
                this.mapa.put(i, posicao);
            }
        }
        this.jungleSize = jungleSize;
    }

    public void criaJogadores(String[][] playersInfo, ArrayList<Integer> idsJogador) {
        Collections.sort(idsJogador);
        for(int i = 0 ; i < playersInfo.length ; i++) {
            int id = Integer.parseInt(playersInfo[i][0]);
            String nome = playersInfo[i][1];
            String idEspecie = playersInfo[i][2];
            Jogador jogador;

            if(idsJogador.get(0) == id) {
                jogador = new Jogador(id, nome, idEspecie, initialEnergy, true, mapa.get(idsJogador.get(id)));
            }else {
                jogador = new Jogador(id, nome, idEspecie, initialEnergy, false, mapa.get(idsJogador.get(id)));
            }
            this.jogadores.add(jogador);
        }
    }
}
