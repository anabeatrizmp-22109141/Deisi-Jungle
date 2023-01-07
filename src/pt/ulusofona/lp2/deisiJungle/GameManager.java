package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.comida.*;
import pt.ulusofona.lp2.deisiJungle.especie.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public class GameManager {

    protected ArrayList<Jogador> jogadores;
    protected HashMap<Integer,Square> mapa;
    private int jungleSize;
    protected int nrjogadas = 0;
    private final HashSet<String> alimentos = new HashSet<>();

    private int idJogadorAtual = 0;
    public GameManager() {
    }
/*
-------------------------------------------------------------------------------
                     Classes Iniciais
-------------------------------------------------------------------------------
 */
    public String[][] getSpecies() {
        String[][] especies = new String[6][7];
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

        especie = new Sapo();
        especies[5] = especie.getInfo();

        return especies;
    }

    public String[][] getFoodTypes() {
        String[][] comida = new String[6][4];
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

        alimento = new Chocolate();
        comida[5] = alimento.getInfo();

        return comida;
    }

    /*
-------------------------------------------------------------------------------
                                 CRIA JUNGLE INICIAL
-------------------------------------------------------------------------------
     */

    public void verificacoesMapaAntigo(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException{
        this.jogadores = new ArrayList<>();
        this.mapa = new HashMap<>();

        // Verificação dos nomes
        if(isNomeInvalido(playersInfo)) {
            throw new InvalidInitialJungleException("Nome do jogador é invalido","Jogador Invalido");
        }

        //Verificação dos ids dos jogadores
        if(isIdJogadorInvalido(playersInfo)) {
            throw new InvalidInitialJungleException("Id do jogador é invalido","Jogador Invalido");
        }

        //Verficação dos ids das especies
        if(idEspecieInvalido(playersInfo)) {
            throw new InvalidInitialJungleException("Id da especie é invalido","Jogador Invalido");
        }

        //Verificação de nr de Jogadores
        if(playersInfo.length < 2 || playersInfo.length > 4) {
            throw new InvalidInitialJungleException("Nr de jogadores é invalido","Jogador Invalido");
        }

        //Verificacao tamanho mapa
        if(jungleSize < 2 * playersInfo.length) {
            throw new InvalidInitialJungleException("tamanho do mapa inválido");
        }

        this.jungleSize = jungleSize;
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo)
            throws InvalidInitialJungleException {

        verificacoesMapaAntigo(jungleSize, playersInfo);


        //Verificacao ids da Comida
        if(idFoodTypesInvalido(foodsInfo)) {
            throw new InvalidInitialJungleException("Id alimento inválido","Alimento Invalido");
        }

        //Verificacao posicoes da Comida
        if(isFoodsPositionsInvalido(foodsInfo, jungleSize)) {
            throw new InvalidInitialJungleException("Posições alimento inválido","Alimento Invalido");
        }

        //Cria Mapa
        criaMapa(jungleSize, playersInfo);
        //Cria Jogadores
        criaJogadores(playersInfo);
        //Colocar Comida nas Posicoes
        colocaComidasNoMapa(foodsInfo);

    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {

        verificacoesMapaAntigo(jungleSize, playersInfo);

        //Cria Mapa
        criaMapa(jungleSize, playersInfo);
        //CriaJogadores
        criaJogadores(playersInfo);

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

        for (String[] jogadores : playersInfo) {

            int id = Integer.parseInt(jogadores[0]);
            String nome = jogadores[1];
            String idEspecie = jogadores[2];

            Jogador jogador;

            switch (idEspecie) {
                case "E" -> {
                    Especie elefante = new Elefante();
                    jogador = new Jogador(id, nome, elefante, mapa.get(1));
                    this.jogadores.add(jogador);
                }
                case "L" -> {
                    Especie leao = new Leao();
                    jogador = new Jogador(id, nome, leao, mapa.get(1));
                    this.jogadores.add(jogador);
                }
                case "P" -> {
                    Especie passaro = new Passaro();
                    jogador = new Jogador(id, nome, passaro, mapa.get(1));
                    this.jogadores.add(jogador);
                }
                case "T" -> {
                    Especie tartaruga = new Tartaruga();
                    jogador = new Jogador(id, nome, tartaruga, mapa.get(1));
                    this.jogadores.add(jogador);
                }
                case "Z" -> {
                    Especie tarzan = new Tarzan();
                    jogador = new Jogador(id, nome, tarzan, mapa.get(1));
                    this.jogadores.add(jogador);
                }
                case "S" -> {
                    Especie sapo = new Sapo();
                    jogador = new Jogador(id, nome, sapo, mapa.get(1));
                    this.jogadores.add(jogador);
                }
            }
        }

        idJogadorAtual = idsJogador.get(0);
    }

    public void colocaComidasNoMapa(String[][] foodsInfo) {

        for (String[] comida : foodsInfo) {
            String id = comida[0];
            int posicaoTerreno = Integer.parseInt(comida[1]);

            switch (id) {
                case "e" -> {
                    Alimento erva = new Erva();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(erva);
                }
                case "a" -> {
                    Alimento agua = new Agua();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(agua);
                }
                case "b" -> {
                    Alimento bananas = new Banana();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(bananas);
                }
                case "c" -> {
                    Alimento carne = new Carne();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(carne);
                }
                case "m" -> {
                    Alimento cogumelos = new CogumelosMagicos();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(cogumelos);
                }
                case "h" -> {
                    Alimento chocolate = new Chocolate();
                    mapa.get(posicaoTerreno).colocaAlimentoNaCasa(chocolate);
                }
            }
        }
    }

    /*
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
*/

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public HashMap<Integer, Square> getMapa() {
        return mapa;
    }

    public HashSet<String> getAlimentos() {
        return alimentos;
    }

    public Jogador getCurrentPlayer() {
        for(Jogador j : jogadores) {
            if(j.getId() == idJogadorAtual) {
                return j;
            }
        }
        return null;
    }

    public ArrayList<Integer> getIdsJogadorOrdenados(String[][] playersInfo) {

        ArrayList<Integer> idsJogador = new ArrayList<>();

        for (String[] jogadores : playersInfo) {
            idsJogador.add(Integer.parseInt(jogadores[0]));
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
        if(mapa.get(squareNr).getAlimento() != null){
            player_info[1] = this.mapa.get(squareNr).alimento.getDescricaoTooltip();
        }
        else {
            player_info[1] = this.mapa.get(squareNr).getDescricao();
        }

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

        return null;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {
        String[] info = new String[2];

        Jogador jogadorAtual = getCurrentPlayer();

        info[0] = jogadorAtual.getInfoEnergiaGastaSeMover(nrPositions) + "";
        info[1] = jogadorAtual.getInfoEnergiaConseguidaSeFicar() + "";

        return info;
    }

    public Jogador[] getJogadoresOrdenadosPorNrCasa() {
        Jogador[] jogadoresOrdenados = jogadores.toArray(new Jogador[0]);

        Arrays.sort(jogadoresOrdenados, Comparator.comparingInt(j -> j.getCasaAtual().getNrSquare()));

        return jogadoresOrdenados;
    }

    public Jogador getPlayer(int id) {
        for(Jogador j : jogadores) {
            if(j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    public Jogador[] ordenaJogadoresGameResults(Jogador[] jogadores) {

        Arrays.sort(jogadores, (j1, j2) -> {
            int nrCasa1 = j1.getCasaAtual().getNrSquare();
            int nrCasa2 = j2.getCasaAtual().getNrSquare();
            if (nrCasa1 != nrCasa2) {
                return nrCasa2 - nrCasa1;
            }
            int id1 = j1.getId();
            int id2 = j2.getId();
            return id1 - id2;
        });

        return jogadores;
    }

    public ArrayList<String> getGameResults() {
        HashMap<Integer, String> classificacoes = new HashMap<>();
        ArrayList<String> resultados = new ArrayList<>();

        int nrClassificacao = 1;
        Jogador[] jogadoresOrdenados = getJogadoresOrdenadosPorNrCasa();
        int nrJogNaoEstaoNaMeta = 0;

        for(Jogador j : jogadoresOrdenados){
            if (j.getCasaAtual().getNrSquare() != mapa.size()) {
                nrJogNaoEstaoNaMeta++;
            }
        }

        if(isDistanciaEntre1e2LugarMaiorQueMetadeDoMapa(jogadoresOrdenados) && nrJogNaoEstaoNaMeta == jogadoresOrdenados.length) {

            Jogador [] jogadoresOrdemGameResult = ordenaJogadoresGameResults(jogadoresOrdenados);

            for(Jogador j : jogadoresOrdenados) {
                if (j.getId() == jogadoresOrdemGameResult[1].getId()) {
                    String classificacao = "#" + 1 + " " + j.getClassificacao();
                    classificacoes.put(1, classificacao);
                    nrClassificacao++;
                } else if (j.getId() == jogadoresOrdemGameResult[0].getId()) {
                    String classificacao = "#" + 2 + " " + j.getClassificacao();
                    classificacoes.put(2, classificacao);
                    nrClassificacao++;
                } else {
                    String classificacao = "#" + nrClassificacao + " " + j.getClassificacao();
                    classificacoes.put(nrClassificacao, classificacao);
                    nrClassificacao++;
                }
            }
            for (int i = 1; i <= jogadores.size(); i++) {
                resultados.add(classificacoes.get(i));
            }
        }
        else {
            Jogador [] jogadoresOrdemGameResult = ordenaJogadoresGameResults(jogadoresOrdenados);

            for(Jogador j : jogadoresOrdemGameResult) {
                String classificacao = "#" + nrClassificacao + " " + j.getClassificacao();
                resultados.add(classificacao);
                nrClassificacao++;
            }
        }

        return resultados;
    }

    public void mudaJogadorAtual() {
        Jogador jogadorAtual = getCurrentPlayer();

        ArrayList<Integer> idsJogadorOrdenados = new ArrayList<>();

        for (Jogador j : jogadores) {
            idsJogadorOrdenados.add(j.getId());
        }

        Collections.sort(idsJogadorOrdenados);

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
        idJogadorAtual = idPlayerNovo;
    }

    private void insereAlimento(String alimento) {
        alimentos.add(alimento);
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
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }


        if(!jogadorAtual.temEnergiaParaMover(nrSquares) && nrSquares != 0) {
            mudaJogadorAtual();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        if(nrSquares == 0) {
            jogadorAtual.descansa();
        }
        else {
            int nrCasaNova = jogadorAtual.getProximoNrSquare(nrSquares);

            if(nrCasaNova >= mapa.size()) {
                nrCasaNova = mapa.size();
                jogadorAtual.setGanhou(true);
            }

            Square novaCasa = mapa.get(nrCasaNova);
            jogadorAtual.diminuiEnergiaMovimento(nrSquares);

            mapa.get(jogadorAtual.getCasaAtual().getNrSquare()).retiraJogadorAPosicao(jogadorAtual.getId());
            mapa.get(nrCasaNova).adicionaJogadorAPosicao(jogadorAtual.getId());

            jogadorAtual.setCasaAtual(novaCasa);
            jogadorAtual.adicionaNrMovimentacoes(nrSquares);

            nrjogadas++;
            aplicaAumentoJogadasNaCarne();
        }
        //Ganhou por distância entre dois ultimos ser maior que tamanho do mapa
        if(!jogadorAtual.ganhou()) {
            Jogador[] jogadoresOrdenados = getJogadoresOrdenadosPorNrCasa();

            if(isDistanciaEntre1e2LugarMaiorQueMetadeDoMapa(jogadoresOrdenados)) {
                getPlayer(jogadoresOrdenados[jogadoresOrdenados.length-2].getId()).setGanhou(true);
            }
        }


        if(jogadorAtual.getCasaAtual().getAlimento() != null) {
            MovementResult result = aplicaEfeitoComida(jogadorAtual.getCasaAtual().getNrSquare(),jogadorAtual);
            jogadorAtual.aumentaNrAlimentos();
            if(result.code() != null) {
                mudaJogadorAtual();
                return result;
            }
        }

        mudaJogadorAtual();

        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public void aplicaAumentoJogadasNaCarne(){
        for(int i = 1; i < mapa.size(); i++){
            if(mapa.get(i).getAlimento() != null){
                mapa.get(i).getAlimento().aumentaNrJogadasCarne();
            }
        }
    }

    public MovementResult aplicaEfeitoComida(int nrSquare, Jogador jogador) {
        Alimento alimentoNaCasa = mapa.get(nrSquare).getAlimento();

        switch (alimentoNaCasa.getId()) {
            case "e" -> {
                efeitoErva(jogador);
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Erva");
            }
            case "a" -> {
                efeitoAgua(jogador);
                insereAlimento("Agua");
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Agua");
            }
            case "b" -> {
                Banana cacho = (Banana) alimentoNaCasa;
                if (cacho.temBananas()) {
                    efeitoBananas(jogador);
                    cacho.diminuiBanana();
                    insereAlimento("Bananas");
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Bananas");
                }
            }
            case "c" -> {
                if (jogador.getEspecie().eCarnivoro() || jogador.getEspecie().eOmnivoro()) {
                    efeitoCarne(jogador);
                    insereAlimento("Carne");
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Carne");
                }
            }
            case "m" -> {
                CogumelosMagicos cogumelosMagicos = (CogumelosMagicos) alimentoNaCasa;
                efeitoCogumelos(jogador, cogumelosMagicos);
                insereAlimento("Cogumelos Magicos");
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Cogumelo Magico");
            }
            case "h" -> {
                efeitoChocolate(jogador);
                insereAlimento("Chocolate");
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou Chocolate");
            }
        }
        return new MovementResult(null, "");
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
        if(nrjogadas % 2 == 0){
            jogador.mudaEnergiaComidaPercentagem(cogumelos.getNumeroAleatorio());
        }
        else{
            jogador.reduzEnergiaComidaPercentagem(cogumelos.getNumeroAleatorio());
        }
    }

    public void efeitoChocolate(Jogador jogador) {
        if(jogador.getInfoEnergiaAtual() + 80 > 200) {
            jogador.setEnergia(0);
        }
        else {
            jogador.mudaEnergiaComidaValorInteiro(80);
        }
    }

    /*
-------------------------------------------------------------------------------
                        VERIFICAÇÕES DIVERSAS
-------------------------------------------------------------------------------
     */

    public boolean isNomeInvalido(String[][] playersInfo) {

        for (String[] jogadores : playersInfo) {
            if (jogadores[1] == null || jogadores[1].isBlank()) {
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

        for (String[] especie : especies) {
            idsEspecie.add(especie[0]);
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

        for (String[] comida : comidas) {
            idsComida.add(comida[0]);
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
            if(!foodsInfo[i][1].chars().allMatch(Character ::isDigit)) { //Verifica se posição é número
                return true;
            }
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

        switch (jogadorAtual.getEspecie().getId()){
            case "P":
                if((nrSquares < 5 || nrSquares > 6) && !bypassValidations){
                    if((nrSquares > -5 || nrSquares < -6) && nrSquares!=0) {
                        return true;
                    }
                }
                break;
            case "L":
                if((nrSquares < 4 || nrSquares > 6) && !bypassValidations){
                    if((nrSquares > -4 || nrSquares < -6) && nrSquares!=0) {
                        return true;
                    }
                }
                break;
            case "T":
                if((nrSquares < 1 || nrSquares > 3) && !bypassValidations){
                    if((nrSquares > -1 || nrSquares < -3) && nrSquares!=0) {
                        return true;
                    }
                }
                break;
        }

        if((nrSquares > 6 || nrSquares < -6) && !bypassValidations) {
            return true;
        }

        // é + pq + (-nrCasa)
        return nrSquares < 0 && jogadorAtual.getCasaAtual().getNrSquare() + nrSquares < 1;

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

    public boolean isDistanciaEntre1e2LugarMaiorQueMetadeDoMapa(Jogador[] jogadoresOrdenados) {
        return (jogadoresOrdenados[jogadoresOrdenados.length-1].getCasaAtual().getNrSquare() -
                jogadoresOrdenados[jogadoresOrdenados.length-2].getCasaAtual().getNrSquare()) > mapa.size()/2;
    }
    /*
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
     */

    public boolean saveGame(File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for(int i = 1 ; i <= mapa.size() ; i++) {
                writer.write(mapa.get(i).getSquareInfoSaveLoad());
                writer.newLine();
            }

            for(Jogador j : jogadores) {
                writer.write(j.getPlayerInfoSaveLoad());
                writer.newLine();
            }

            for(int i = 1; i <= mapa.size() ; i++) {
                if(mapa.get(i).getAlimento() != null) {
                    writer.write(mapa.get(i).getNrSquare() + ";" + mapa.get(i).getAlimento().getAlimentoInfoSaveLoad());
                    writer.newLine();
                }
            }

            writer.write("JA" + ";" + idJogadorAtual);
            writer.close();
            fileWriter.close();
            return true;
        }
        catch (IOException ioe) {
            return false;
        }
    }

    public boolean loadGame(File file) {
        //limpa o mapa e os jogadores
        mapa = new HashMap<>();
        jogadores = new ArrayList<>();
        idJogadorAtual = 0;
        nrjogadas = 0;
        FileReader fr;
        String linha;

        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if(dados[0].equals("S")) {
                    createSquareLoadGame(dados);
                }
                if(dados[0].equals("J")){
                    createJogadorLoadGame(dados);
                }
                if(dados[1].equals("A")){
                    createAlimentoLoadGame(dados);
                }
                if(dados[0].equals("JA")) {
                    idJogadorAtual = Integer.parseInt(dados[1]);
                }
            }

            fr.close();
            br.close();
            jungleSize = mapa.size();
        }
        catch (IOException e) {
           return false;
        }

        return true;
    }

    public void createSquareLoadGame(String[] dados) {
        int nrSquare = Integer.parseInt(dados[1]);
        String imagem = dados[2];
        String descricao = dados[3];
        Square square;

        if(!Objects.equals(dados[4], "NA")) {
            square = new Square(nrSquare, imagem, descricao, dados[4]);
        }
        else {
            square = new Square(nrSquare, imagem, descricao);
        }

        mapa.put(nrSquare, square);
    }

    public void createJogadorLoadGame(String[] dados) {
        int id = Integer.parseInt(dados[1]);
        String nome = dados[2];
        String idEspecie = dados[3];
        boolean ganhou = Boolean.parseBoolean(dados[4]);
        int nrCasaAtual = Integer.parseInt(dados[5]);
        int energia = Integer.parseInt(dados[6]);
        int nrBananasComidas = Integer.parseInt(dados[7]);
        int nrMovimentos = Integer.parseInt(dados[8]);
        int nrAlimentos = Integer.parseInt(dados[9]);


        Especie especie = null;

        switch (idEspecie){
            case "E" -> especie = new Elefante();
            case "L" -> especie = new Leao();
            case "P" -> especie = new Passaro();
            case "S" -> especie = new Sapo();
            case "T" -> especie = new Tartaruga();
            case "Z" -> especie = new Tarzan();
        }

        Jogador j = new Jogador(id, nome, especie,ganhou, mapa.get(nrCasaAtual),
                energia,nrBananasComidas,nrMovimentos,nrAlimentos);

        jogadores.add(j);
    }

    public void createAlimentoLoadGame(String[] dados) {
        // 0 -> nrSquare , 1 -> "A" , 2 -> id , 3 -> outras infos (depende)
        Alimento alimento = null;
        int nrSquare = Integer.parseInt(dados[0]);

        switch(dados[2]) {
            case "a" -> alimento = new Agua();
            case "b" -> alimento = new Banana(Integer.parseInt(dados[3]));
            case "c" -> {
                alimento = new Carne(Integer.parseInt(dados[3]));
                nrjogadas = Integer.parseInt(dados[3]);
            }
            case "e" -> alimento = new Erva();
            case "m" -> alimento = new CogumelosMagicos(Integer.parseInt(dados[3]));
            case "h" -> alimento = new Chocolate();
        }
        assert alimento != null;
        mapa.get(nrSquare).colocaAlimentoNaCasa(alimento);
    }

    public JPanel getAuthorsPanel() {
        JFrame f = new JFrame();
        String htmlString = "<html><body><p>      _______</p>" +
                "<p>    |.-----.|</p>" +
                "<p>    ||      ||</p>" +
                "<p>    ||_____/|</p>" +
                "<p>    | .     |</p>" +
                "<p>    |-|-  oo|</p>" +
                "<p>    |  _ _  |</p>" +
                "<p>    |       /</p>" +
                "    `\"\"\"\"\"\"``</body></html>";

        JLabel intro = new JLabel("Com suor, sangue e lágrimas:");
        JLabel autores = new JLabel("Bruno Miguel - 22106424 e Ana Beatriz - 22109141");

        JLabel jogo = new JLabel();
        jogo.setText(htmlString);
        JPanel p = new JPanel();

        p.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        p.add(intro);
        p.add(autores);
        //p.add(ana);
        p.add(jogo);

        // setbackground of panel
        p.setBackground(Color.black);
        intro.setForeground(Color.white);
        autores.setForeground(Color.yellow);
        //ana.setForeground(Color.yellow);
        jogo.setForeground(Color.white);
        // Adding panel to frame
        f.add(p);

        // Setting the size of frame
        f.setSize(300, 300);

        return p;
    }

    public String whoIsTaborda() {
        return "Wrestling";
    }

}
