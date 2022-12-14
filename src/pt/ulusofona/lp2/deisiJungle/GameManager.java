package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.comida.*;
import pt.ulusofona.lp2.deisiJungle.especie.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameManager {

    ArrayList<Jogador> jogadores;
    HashMap<Integer, Jogador> mapaIdsJogadores;
    HashMap<Integer,Square> mapa;
    private int jungleSize;
    private String[][] playersInfo;

    public int nrjogadas = 0;

    public GameManager() {
    }
/*
-------------------------------------------------------------------------------
                     Classes Iniciais
-------------------------------------------------------------------------------
 */
    public String[][] getSpecies() {
        String[][] especies = new String[5][7];
        Especie especie;

        especie = new Elefante();
        especies[0] = especie.getInfo();

        especie = new Leao();
        especies[1] = especie.getInfo();

        especie = new Passaro();
        especies[2] = especie.getInfo();

        especie = new Tartaruga();
        especies[3] = especie.getInfo();

        especie = new Tarzan();
        especies[4] = especie.getInfo();

        return especies;
    }

    public String[][] getFoodTypes() {
        String[][] comida = new String[5][4];
        Alimento alimento;

        alimento = new Banana();
        comida[0] = alimento.getInfo();

        alimento = new Agua();
        comida[1] = alimento.getInfo();

        alimento = new Erva();
        comida[2] = alimento.getInfo();

        alimento = new Carne();
        comida[3] = alimento.getInfo();

        alimento = new CogumelosMagicos();
        comida[4] = alimento.getInfo();

        return comida;
    }

    /*
-------------------------------------------------------------------------------
                                 CRIA JUNGLE INICIAL
-------------------------------------------------------------------------------
     */

    public InitializationError verificacoesMapaAntigo(int jungleSize, String[][] playersInfo) {
        this.jogadores = new ArrayList<>();
        this.mapa = new HashMap<>();
        this.mapaIdsJogadores = new HashMap<>();
        this.playersInfo = playersInfo;

        // Verificação dos nomes
        if(isNomeInvalido(playersInfo)) {
            return new InitializationError("Nome é inválido");
        }

        //Verificação dos ids dos jogadores
        if(isIdJogadorInvalido(playersInfo)) {
            return new InitializationError("Id do jogador é inválido");
        }

        //Verficação dos ids das especies
        if(idEspecieInvalido(playersInfo)) {
            return new InitializationError("ID Especie é inválido");
        }

        //Verificação de nr de Jogadores
        if(playersInfo.length < 2 || playersInfo.length > 4) {
            return new InitializationError("Nr de jogadores é inválido");
        }

        //Verificacao tamanho mapa
        if(jungleSize < 2 * playersInfo.length) {
            return new InitializationError("Tamanho do mapa é inválido");
        }

        this.jungleSize = jungleSize;

        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) {

        if(verificacoesMapaAntigo(jungleSize, playersInfo) != null) {
            return verificacoesMapaAntigo(jungleSize, playersInfo);
        }

        //Verificacao ids da Comida
        if(idFoodTypesInvalido(foodsInfo)) {
            return new InitializationError("ID da comida é inválido");
        }

        //Verificacao posicoes da Comida
        if(isFoodsPositionsInvalido(foodsInfo, jungleSize)) {
            return new InitializationError("FoodsInfo possui posições inválidas");
        }

        //Cria Mapa
        criaMapa(jungleSize, playersInfo);
        //Cria Jogadores
        criaJogadores(playersInfo);
        //Colocar Comida nas Posicoes
        colocaComidasNoMapa(foodsInfo);

        return null;
    }

    public InitializationError createInitialJungle(int jungleSize, String[][] playersInfo) {

        if(verificacoesMapaAntigo(jungleSize, playersInfo) != null) {
            return verificacoesMapaAntigo(jungleSize, playersInfo);
        }

        //Cria Mapa
        criaMapa(jungleSize, playersInfo);
        //CriaJogadores
        criaJogadores(playersInfo);

        return null;
    }

    /*
-------------------------------------------------------------------------------
                              FAZ COISAS NO MAPA
-------------------------------------------------------------------------------
 */

    public void criaMapa(int jungleSize, String[][] playersInfo ) {
        String jogadoresNaPrimeiraPosicao = "";
        ArrayList<Integer> idsJogador = getIdsJogadorOrdenados(playersInfo);

        for(int i = 1 ; i <= idsJogador.size() ; i++) {
            if(i != idsJogador.size()) {
                jogadoresNaPrimeiraPosicao += idsJogador.get(i-1) + ",";
            }else {
                jogadoresNaPrimeiraPosicao +=  idsJogador.get(i-1);
            }
        }

        for(int i = 1 ; i <= jungleSize ; i++) {
            if(i == 1) {
                Square posicao = new Square(i, "blank.png", "Vazio", jogadoresNaPrimeiraPosicao);
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
    }

    public void criaJogadores(String[][] playersInfo) {

        ArrayList<Integer> idsJogador = getIdsJogadorOrdenados(playersInfo);

        for(int i = 0 ; i < playersInfo.length ; i++) {

            int id = Integer.parseInt(playersInfo[i][0]);
            String nome = playersInfo[i][1];
            String idEspecie = playersInfo[i][2];

            Jogador jogador;

            if(idEspecie.equals("E")) {
                Especie elefante = new Elefante();
                jogador = new Jogador(id, nome, elefante, mapa.get(1));
                this.jogadores.add(jogador);
            }
            else if(idEspecie.equals("L")) {
                Especie leao = new Leao();
                jogador = new Jogador(id, nome, leao, mapa.get(1));
                this.jogadores.add(jogador);
            }
            else if(idEspecie.equals("P")) {
                Especie passaro = new Passaro();
                jogador = new Jogador(id, nome, passaro, mapa.get(1));
                this.jogadores.add(jogador);
            }
            else if(idEspecie.equals("T")) {
                Especie tartaruga = new Tartaruga();
                jogador = new Jogador(id, nome, tartaruga, mapa.get(1));
                this.jogadores.add(jogador);
            }
            else if(idEspecie.equals("Z")) {
                Especie tarzan = new Tarzan();
                jogador = new Jogador(id, nome, tarzan, mapa.get(1));
                this.jogadores.add(jogador);
            }
        }

        for(Jogador jogador : jogadores) {
            if(jogador.getId() == idsJogador.get(0)) {
                jogador.trocaJogadorAtual();
            }
        }
    }

    public void colocaComidasNoMapa(String[][] foodsInfo) {

        for(int i = 0 ; i < foodsInfo.length ; i++) {
            String id = foodsInfo[i][0];
            int posicaoTerreno = Integer.parseInt(foodsInfo[i][1]);

            if(id.equals("e")) {
                Alimento erva = new Erva();
                mapa.get(posicaoTerreno).colocaAlimentoNaCasa(erva);
            }
            else if(id.equals("a")) {
                Alimento agua = new Agua();
                mapa.get(posicaoTerreno).colocaAlimentoNaCasa(agua);
            }
            else if(id.equals("b")) {
                Alimento bananas = new Banana();
                mapa.get(posicaoTerreno).colocaAlimentoNaCasa(bananas);
            }
            else if(id.equals("c")) {
                Alimento carne = new Carne();
                mapa.get(posicaoTerreno).colocaAlimentoNaCasa(carne);
            }
            else if(id.equals("m")) {
                Alimento cogumelos = new CogumelosMagicos();
                mapa.get(posicaoTerreno).colocaAlimentoNaCasa(cogumelos);
            }
        }
    }

    /*
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
*/
    public Jogador getCurrentPlayer() {
        for(Jogador j : jogadores) {
            if(j.isTurnoDoJogador()) {
                return j;
            }
        }
        return null;
    }

    public ArrayList<Integer> getIdsJogadorOrdenados(String[][] playersInfo) {

        ArrayList<Integer> idsJogador = new ArrayList<>();

        for(int i = 0 ; i < playersInfo.length ; i++) {
            idsJogador.add(Integer.parseInt(playersInfo[i][0]));
        }

        Collections.sort(idsJogador);
        return idsJogador;
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
        Jogador jogadorAtual = getCurrentPlayer();

        return getPlayerInfo(jogadorAtual.getId());
    }

    public String[][] getPlayersInfo() {
        String[][] informacao = new String[jogadores.size()][4];

        for(int i = 0 ; i < jogadores.size() ; i++) {
            informacao[i] = getPlayerInfo(jogadores.get(i).getId());
        }

        return informacao;
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
                if(j.getCasaAtual().getNrSquare() > maiorCasaComJogadores) {
                    maiorCasaComJogadores = j.getCasaAtual().getNrSquare();
                }
            }
            return getPlayerInfo(mapa.get(maiorCasaComJogadores).getJogadoresNaPosicaoPorOrdem()[0]);
        }

        return null;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] info = new String[2];

        Jogador jogadorAtual = getCurrentPlayer();

        info[0] = jogadorAtual.getInfoEnergiaGastaSeMover(nrPositions) + "";
        info[1] = jogadorAtual.getInfoEnergiaConseguidaSeFicar() + "";

        return info;
    }

    // REFAZ ESTA MERDA
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

    //JÁ ESTÁ
    public void mudaJogadorAtual() {
        Jogador jogadorAtual = getCurrentPlayer();
        ArrayList<Integer> idsJogadorOrdenados = getIdsJogadorOrdenados(playersInfo);

        int idPlayerAtual = jogadorAtual.getId();
        int idPlayerNovo = 0;

        for(int i = 0; i < idsJogadorOrdenados.size() ; i++) {

            if(idsJogadorOrdenados.get(i) == idPlayerAtual) {
                if(idPlayerAtual == idsJogadorOrdenados.get(idsJogadorOrdenados.size() - 1 ) ) {
                    idPlayerNovo = idsJogadorOrdenados.get(0);
                }
                else {
                    idPlayerNovo = idsJogadorOrdenados.get(i + 1);
                }
            }
        }


        for(Jogador j : jogadores) {
            if(j.getId() == idPlayerAtual) {
                j.trocaJogadorAtual();
            }
            if(j.getId() == idPlayerNovo) {
                j.trocaJogadorAtual();
            }
        }

    }

    /*
-------------------------------------------------------------------------------
                        MOVER JOGADOR + EFEITOS DAS COMIDAS
-------------------------------------------------------------------------------
     */

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        Jogador jogadorAtual = getCurrentPlayer();

        if(isNrSquareInvalid(nrSquares, bypassValidations)){
            mudaJogadorAtual();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, "Movimento inválido");
        }

        if(!jogadorAtual.temEnergiaParaMover(nrSquares) && nrSquares != 0) {
            mudaJogadorAtual();
            return new MovementResult(MovementResultCode.NO_ENERGY, "Jogador sem energia para o movimento");
        }

        if(nrSquares == 0) {
            jogadorAtual.descansa();
        }
        else {
            int nrCasaNova = jogadorAtual.getProximoNrSquare(nrSquares);

            if(nrCasaNova >= mapa.size()) {
                nrCasaNova = mapa.size();
            }

            Square novaCasa = mapa.get(nrCasaNova);
            jogadorAtual.diminuiEnergiaMovimento(nrSquares);

            mapa.get(jogadorAtual.getCasaAtual().getNrSquare()).retiraJogadorAPosicao(jogadorAtual.getId());
            mapa.get(nrCasaNova).adicionaJogadorAPosicao(jogadorAtual.getId());

            jogadorAtual.setCasaAtual(novaCasa);

            nrjogadas++;
        }

        if(jogadorAtual.getCasaAtual().getAlimento() != null) {
            aplicaEfeitoComida(jogadorAtual.getCasaAtual().getNrSquare(),jogadorAtual);
        }

        mudaJogadorAtual();

        return new MovementResult(MovementResultCode.VALID_MOVEMENT, "Movimento válido");
    }

    public void aplicaEfeitoComida(int nrSquare, Jogador jogador) {
        Alimento alimentoNaCasa = mapa.get(nrSquare).getAlimento();

        switch (alimentoNaCasa.getId()) {
            case "e":
                efeitoErva(jogador);
                break;
            case "a":
                efeitoAgua(jogador);
                break;
            case "b":
                Banana cacho = (Banana) alimentoNaCasa;
                if(cacho.temBananas()) {
                    efeitoBananas(jogador);
                    System.out.println(cacho.getDescricaoTooltip());
                    cacho.diminuiBanana();
                    System.out.println(cacho.getDescricaoTooltip());

                }
                break;
            case "c":
                Carne carne = (Carne) alimentoNaCasa;
                carne.setNrJogadas(nrjogadas);
                if(jogador.getEspecie().eCarnivoro() || jogador.getEspecie().eOmnivoro()){
                    efeitoCarne(jogador);
                }
                if(jogador.getEspecie().eHerbivoro()) {
                    break;
                }

                break;
            case "m":
                CogumelosMagicos cogumelosMagicos = (CogumelosMagicos) alimentoNaCasa;
                efeitoCogumelos(jogador,cogumelosMagicos);
                break;
        }
    }

    public void efeitoErva(Jogador jogador) {
        if(jogador.getEspecie().eHerbivoro() || jogador.getEspecie().eOmnivoro()) {
            jogador.mudaEnergiaComidaValorInteiro(20);
        }
        else if(jogador.getEspecie().eCarnivoro()) {
            jogador.mudaEnergiaComidaValorInteiro(-20);
        }
    }

    public void efeitoAgua(Jogador jogador) {
        if(jogador.getEspecie().eHerbivoro() || jogador.getEspecie().eCarnivoro()) {
            jogador.mudaEnergiaComidaValorInteiro(15);
        }
        else if(jogador.getEspecie().eOmnivoro()) {
            jogador.mudaEnergiaComidaPercentagem(20);
        }
    }

    public void efeitoBananas(Jogador jogador) {

        if(jogador.getNrBananasComidas() > 0) {
            jogador.mudaEnergiaComidaValorInteiro(-40);
        }
        else {
            jogador.mudaEnergiaComidaValorInteiro(40);
        }

        jogador.aumentaNrBananasComidas();
    }

    public void efeitoCarne(Jogador jogador) {
        if(nrjogadas > 12){
            jogador.reduzEnergiaComidaPercentagem(50);
        }
        else {
            if (jogador.getEspecie().eCarnivoro() || jogador.getEspecie().eOmnivoro()) {
                jogador.mudaEnergiaComidaValorInteiro(50);
            }
        }
    }

    public void efeitoCogumelos(Jogador jogador,CogumelosMagicos cogumelos) {
        if(nrjogadas%2 == 0){
            jogador.mudaEnergiaComidaPercentagem(cogumelos.getNumeroAleatorio());
        }
        else{
            jogador.reduzEnergiaComidaPercentagem(cogumelos.getNumeroAleatorio());
        }
    }

    /*
-------------------------------------------------------------------------------
                        VERIFICAÇÕES DIVERSAS
-------------------------------------------------------------------------------
     */

    public boolean isNomeInvalido(String[][] playersInfo) {

        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][1] == null || playersInfo[i][1].isBlank()) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdJogadorInvalido(String[][] playersInfo) {

        ArrayList<Integer> idsJogador = new ArrayList<>();

        for(int i = 0 ; i < playersInfo.length ; i++) {
            if(playersInfo[i][0] == null || playersInfo[i][0].isBlank()) { // Verifica null e vazio
                return true;
            }
            if(!playersInfo[i][0].chars().allMatch(Character ::isDigit)) { // Verifica se o id é só digitos
                return true;
            }
            if(idsJogador.contains(Integer.parseInt(playersInfo[i][0]))) { //Verifica se id já existe
                return true;
            }
            idsJogador.add(Integer.parseInt(playersInfo[i][0]));
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
            if(playersInfo[i][2] == null || playersInfo[i][2].isBlank()) { //Verifica null e vazio
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

    public boolean idFoodTypesInvalido(String[][] foodsInfo) {
        String[][] comidas = getFoodTypes();
        ArrayList<String> idsComida = new ArrayList<>();

        for(int i = 0 ; i < comidas.length ; i++) {
            idsComida.add(comidas[i][0]);
        }

        for(int i = 0 ; i < foodsInfo.length ; i++) {
            if(foodsInfo[i][0] == null || foodsInfo[i][0].isBlank()) {
                return true;
            }
            if(!idsComida.contains(foodsInfo[i][0])) {
                return true;
            }
        }
        return false;
    }

    public boolean isFoodsPositionsInvalido(String[][] foodsInfo, int jungleSize) {

        for(int i = 0 ; i < foodsInfo.length ; i++) {
            if(Integer.parseInt(foodsInfo[i][1]) >= jungleSize) {
                return true;
            }
            if(Integer.parseInt(foodsInfo[i][1]) <= 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isNrSquareInvalid(int nrSquares , boolean bypassValidations) {

        Jogador jogadorAtual = getCurrentPlayer();

        if((nrSquares > 6 || nrSquares < -6) && !bypassValidations) {
            return true;
        }

        if(nrSquares < 0 && jogadorAtual.getCasaAtual().getNrSquare() + nrSquares < 1) { // é + pq + (-nrCasa)
            return true;
        }
        return false;
    }

    public boolean verificaTodosSemEnergia() {

        for(Jogador j : jogadores) {
            if(j.getInfoEnergiaAtual() != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean verificaSeHaVencedor() {

        for(Jogador j : jogadores) {
            if(j.ganhou()) {
                return true;
            }
        }
        return false;
    }

    /*
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
     */

    public boolean saveGame(File file) {
        return true;
    }

    public boolean loadGame(File file) {
        return true;
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }

    public String whoIsTaborda() {
        return "Wrestling";
    }

}
