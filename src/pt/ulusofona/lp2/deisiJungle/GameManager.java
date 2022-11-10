package pt.ulusofona.lp2.deisiJungle;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class GameManager {

    int nrJogadores;

    int initialEnergy;
    ArrayList<Jogador> jogadores;
    ArrayList<Integer> idsJogador;
    HashMap<String, String> especies;
    HashMap<Integer, Jogador> mapaIdsJogadores;
    HashMap<Integer,Square> mapa;
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
        this.especies = new HashMap<>();
        String[][] especies = new String[5][3];

        especies[0][0] = "E";
        especies[0][1] = "Elefante";
        especies[0][2] = "elephant.png";
        this.especies.put("E", "Elefante");

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";
        this.especies.put("L", "Leão");

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";
        this.especies.put("T", "Tartaruga");

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";
        this.especies.put("P", "Pássaro");

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";
        this.especies.put("Z", "Tarzan");

        return especies;
    }

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {
        this.initialEnergy = initialEnergy;

        this.idsJogador = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.mapa = new HashMap<>();
        this.mapaIdsJogadores = new HashMap<>();

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

        if(squareNr > this.jungleSize || squareNr < 1){
            return new int[0];
        }
        if(mapa.get(squareNr).jogadoresNaPosicao.equals("")) {
            return new int[0];
        }

        String jogadores = this.mapa.get(squareNr).jogadoresNaPosicao;
        String[] jogadoresSeparados = jogadores.split(",");

        int [] id_players = new int[jogadoresSeparados.length];

        for (int i = 0; i < jogadoresSeparados.length; i++) {
            id_players[i] = Integer.parseInt(jogadoresSeparados[i]);
        }

        return id_players;
    }

    public String[] getSquareInfo(int squareNr) {

        String[] player_info = new String[3];

        if(squareNr > jungleSize || squareNr < 1){
            return null;
        }

        player_info[0] = this.mapa.get(squareNr).getImagem();
        player_info[1] = this.mapa.get(squareNr).getDescricao();
        player_info[2] = this.mapa.get(squareNr).getJogadoresNaPosicao();


        return player_info;
    }

    public String[] getPlayerInfo(int playerId) {

        for (Jogador j : jogadores) {
            if(j.getId() == playerId) {
                return j.getInfoJogador();
            }
        }

        return null;
    }

    public String[] getCurrentPlayerInfo() {

        for (Jogador j : jogadores) {
            if(j.isTurnoDoJogador()) {
                return getPlayerInfo(j.getId());
            }
        }

        return null;
    }

    public String[][] getPlayersInfo() {
        String[][] informacao = new String[jogadores.size()][4];

        for(int i = 0 ; i < jogadores.size() ; i++) {
            informacao[i] = getPlayerInfo(jogadores.get(i).getId());
        }
        this.playersInfo = informacao;

        return informacao;
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        for(Jogador j : this.jogadores) {

            if(verificaTodosSemEnergia()) {
                return false;
            }

            if(!bypassValidations) {
                if(nrSquares < 1 || nrSquares > 6) {
                    mudaJogadorAtual(j.getId());
                    return false;
                }
            }

            if(j.isTurnoDoJogador()) {
                if(j.temEnergiaParaMover()) {
                    int nrCasa = j.getCasaAtual().nrSquare;
                    if(nrCasa + nrSquares >= jungleSize){
                        j.getCasaAtual().retiraJogadorAPosicao(j.getId());
                        j.casaAtual = mapa.get(jungleSize);
                        mapa.get(jungleSize).adicionaJogadorAPosicao(j.getId());
                        j.ganhou = true;
                        //getWinnerInfo();

                        return true;
                    }

                    if(nrCasa + nrSquares < jungleSize) {
                        j.getCasaAtual().retiraJogadorAPosicao(j.getId());
                        mapa.get(nrCasa + nrSquares).adicionaJogadorAPosicao(j.getId());
                        j.casaAtual = mapa.get(nrCasa + nrSquares);
                        j.diminuiEnergia();
                    }

                    mudaJogadorAtual(j.getId());


                    return true;
                }
                else {
                    if(verificaTodosSemEnergia()) {
                        return false;
                    }
                    mudaJogadorAtual(j.getId());
                    return false;
                }
            }
        }
        return true;
    }

    public String[] getWinnerInfo() {

        for(Jogador j : jogadores){
            if (j.ganhou()){
                return getPlayerInfo(j.getId());
            }
        }

        int maiorCasaComJogadores = 0;
        if(verificaTodosSemEnergia()) {

            for(Jogador j :jogadores) {
                if(j.casaAtual.getNrSquare() > maiorCasaComJogadores) {
                    maiorCasaComJogadores = j.casaAtual.getNrSquare();
                }
            }
            return getPlayerInfo(mapa.get(maiorCasaComJogadores).getJogadoresNaPosicaoPorOrdem()[0]);
        }

        return null;
    }

    public ArrayList<String> getGameResults() {

        ArrayList<String> resultados = new ArrayList<>();
        int nrClassificacao = 1;
        for(int i = jungleSize ; i > 0 ; i--) {

            if(mapa.get(i).jogadoresNaPosicao.length() >= 1) {
                int nrJogadoresNaPos = mapa.get(i).getJogadoresNaPosicaoPorOrdem().length;
                for(int j = 0 ; j < nrJogadoresNaPos ; j++) {
                    String classificacao = "#" + nrClassificacao + " " +
                            mapaIdsJogadores.get(mapa.get(i).getJogadoresNaPosicaoPorOrdem()[j]).getClassificacao();
                    resultados.add(classificacao);
                    nrClassificacao++;
                }
            }
            if(nrClassificacao > jogadores.size()) {
                return resultados;
            }
        }
        return resultados;
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
                jogador = new Jogador(id, nome, idEspecie, initialEnergy, true, mapa.get(id), especies.get(idEspecie));
            }else {
                jogador = new Jogador(id, nome, idEspecie, initialEnergy, false, mapa.get(idsJogador.get(0)),especies.get(idEspecie));
            }
            this.mapaIdsJogadores.put(id,jogador);
            this.jogadores.add(jogador);
        }
    }

    public void mudaJogadorAtual(int id) {

        for(int i = 0 ; i < jogadores.size() ; i++) {
            if(jogadores.get(i).getId() == id) {
                if(i == 0) {
                    jogadores.get(i).trocaJogadorAtual();
                    jogadores.get(i+1).trocaJogadorAtual();
                }
                else if(i == jogadores.size()-1) {
                    jogadores.get(i).trocaJogadorAtual();
                    jogadores.get(0).trocaJogadorAtual();
                }
                else {
                    jogadores.get(i).trocaJogadorAtual();
                    jogadores.get(i+1).trocaJogadorAtual();
                }
            }
        }
    }

    public boolean verificaTodosSemEnergia() {

        for(Jogador j : jogadores) {
            if(j.temEnergiaParaMover()) {
                return false;
            }
        }
        return true;
    }


}
