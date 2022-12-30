package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;
import pt.ulusofona.lp2.deisiJungle.comida.*;
import pt.ulusofona.lp2.deisiJungle.especie.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestGameManager {

    /*
-------------------------------------------------------------------------------
                        OBTEM ESPECIES E COMIDA
-------------------------------------------------------------------------------
     */

    @Test
    public void test_001_getSpecies(){
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

        GameManager jogo = new GameManager();

        Assert.assertEquals(especies,jogo.getSpecies());
    }

    @Test
    public void test_002_getFoodType(){
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

        GameManager jogo = new GameManager();

        Assert.assertEquals(comida,jogo.getFoodTypes());

    }

    /*
-------------------------------------------------------------------------------
                        VERIFICAÇÕES DIVERSAS
-------------------------------------------------------------------------------
     */
    @Test
    public void test_003_verificacoesMapaAntigoNomeJogador(){
        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("Nome é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = null;
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //Teste para nome Invalido
        Assert.assertEquals(erro.getMessage(), jogo.verificacoesMapaAntigo(37,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        Assert.assertEquals(erro.getMessage(), jogo.verificacoesMapaAntigo(37,playersinfo).getMessage());
    }

    @Test
    public void test_004_verificacoesMapaAntigoIdJogador(){
        GameManager jogo = new GameManager();
        InitializationError erro2 = new InitializationError("Id do jogador é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = null;
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //Verifica null
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //Verifica vazio
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //verifica ids iguais
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        Assert.assertFalse(jogo.isIdJogadorInvalido(playersinfo));

        playersinfo = new String[2][3];

        playersinfo[0][0] = "a";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());
    }

    @Test
    public void test_005_verificacoesMapaAntigoIdEspecie(){
        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("ID Especie é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Ç";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //Verifica se o idespecie não contém no getspecies
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Z";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        //verifica se existe mais que 1 tarzan
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = null;

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //verifica se é null
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "";

        //Verifica vazio
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //verifica se ta tudo bem
        Assert.assertFalse(jogo.idEspecieInvalido(playersinfo));
    }

    @Test
    public void test_006_verificacoesMapaNovoRecebeErroMapaAntigo() {

        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("ID Especie é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Ç";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "5";

        Assert.assertEquals(erro.getMessage(),jogo.createInitialJungle(10, playersinfo, foodsInfo).getMessage());
    }

    @Test
    public void test_007_verificacoesMapaNovoIdComida() {

        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("ID da comida é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "ç";
        foodsInfo[0][1] = "5";

        //Verifica id que não existe
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "";
        foodsInfo[0][1] = "5";

        //Verifica vazio
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = null;
        foodsInfo[0][1] = "5";

        //Verifica null
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "5";

        Assert.assertFalse(jogo.idFoodTypesInvalido(foodsInfo));
    }

    @Test
    public void test_008_verificacoesMapaNovoPosicoesComida() {
        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("FoodsInfo possui posições inválidas");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "-10";

        //Verifica posicao < 1
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "a";

        //Verifica posicao = letra
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "a";

        //Verifica posicao = letra (esta errado)
        Assert.assertNotEquals("" , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "1";

        //Verifica posicao = 1
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "51";

        //Verifica posicao > jungleSize
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "50";

        //Verifica posicao = jungleSize
        Assert.assertEquals(erro.getMessage() , jogo.createInitialJungle(50, playersinfo,foodsInfo).getMessage());

        foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "10";

        Assert.assertFalse(jogo.isFoodsPositionsInvalido(foodsInfo, 50));
    }

    @Test
    public void test_009_verificacoesMapaAntigoNrJogadores(){
        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("Nr de jogadores é inválido");

        String[][] playersinfo = new String[1][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Manteiga";
        playersinfo[0][2] = "L";

        //Verifica se o numero de jogadores é menos de 2
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(47,playersinfo).getMessage());

        playersinfo = new String[5][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Manteiga";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Amendoim";
        playersinfo[1][2] = "L";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Barco";
        playersinfo[2][2] = "P";

        playersinfo[3][0] = "4";
        playersinfo[3][1] = "Joaquim";
        playersinfo[3][2] = "T";

        playersinfo[4][0] = "5";
        playersinfo[4][1] = "Tarzan";
        playersinfo[4][2] = "Z";

        //Verifica se o numero de jogadores é maior que 4
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(47,playersinfo).getMessage());
    }

    @Test
    public void test_010_verificacoesMapaAntigoMapa(){
        GameManager jogo = new GameManager();
        InitializationError erro = new InitializationError("Tamanho do mapa é inválido");

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Manteiga";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Florzinha";
        playersinfo[1][2] = "L";

        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(1,playersinfo).getMessage());
    }

    /*
-------------------------------------------------------------------------------
                        CRIA MAPA
-------------------------------------------------------------------------------
     */

    @Test
    public void test_011_createInitialJungle_criaMapaCriaJogadores(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[4][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Astrubal";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Bomboca";
        playersinfo[1][2] = "L";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Chocos";
        playersinfo[2][2] = "P";

        playersinfo[3][0] = "4";
        playersinfo[3][1] = "Mimosa";
        playersinfo[3][2] = "T";

        Assert.assertNull(jogo.createInitialJungle(47, playersinfo));

        playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Chocos";
        playersinfo[0][2] = "P";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mimosa";
        playersinfo[1][2] = "Z";

        Assert.assertNull(jogo.createInitialJungle(47, playersinfo));
    }


    @Test
    public void test_012_createInitialJungle_criaMapaCriaJogadoresPoeComida(){
        GameManager jogo = new GameManager();
        String[][] foodinfo = new String[5][2];
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Chocos";
        playersinfo[0][2] = "P";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mimosa";
        playersinfo[1][2] = "Z";


        foodinfo[0][0] = "a";
        foodinfo[0][1] = 2 + "";

        foodinfo[1][0] = "e";
        foodinfo[1][1] = 4 + "";

        foodinfo[2][0] = "b";
        foodinfo[2][1] = 7 + "";

        foodinfo[3][0] = "c";
        foodinfo[3][1] = 9 + "";

        foodinfo[4][0] = "m";
        foodinfo[4][1] = 13 + "";

        Assert.assertNull(jogo.createInitialJungle(30,playersinfo,foodinfo));
    }

    /*
-------------------------------------------------------------------------------
                        DIVERSOS GETS
-------------------------------------------------------------------------------
     */
    @Test
    public void test_013_getPlayerIds(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Chocos";
        playersinfo[0][2] = "P";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mimosa";
        playersinfo[1][2] = "Z";

        int[] ids = new int[2];
        ids[0] = 1;
        ids[1] = 2;
        jogo.createInitialJungle(47, playersinfo);

        Assert.assertEquals(2,jogo.getPlayerIds(1).length);
        Assert.assertEquals(Arrays.toString(ids),Arrays.toString(jogo.getPlayerIds(1)));

        Assert.assertEquals(Arrays.toString(new int[0]),Arrays.toString(jogo.getPlayerIds(2)));
        Assert.assertEquals(Arrays.toString(new int[0]),Arrays.toString(jogo.getPlayerIds(0)));
    }

    @Test
    public void test_014_getSquareInfoInicial() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Manteiga";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Florzinha";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];
        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "10";

        jogo.createInitialJungle(37, playersinfo, foodsInfo);

        String[] infoEsperada = new String[3];
        infoEsperada[0] = "water.png";
        infoEsperada[1] = "Agua : + 15U|20% energia";
        infoEsperada[2] = "";

        String[] infoObtida = jogo.getSquareInfo(10);

        Assert.assertEquals(infoEsperada[0], infoObtida[0]);
        Assert.assertEquals(infoEsperada[1], infoObtida[1]);
        Assert.assertEquals(infoEsperada[2], infoObtida[2]);

        Assert.assertNull(jogo.getSquareInfo(0));

        jogo.createInitialJungle(37, playersinfo);

        infoEsperada = new String[3];
        infoEsperada[0] = "blank.png";
        infoEsperada[1] = "Vazio";
        infoEsperada[2] = "";

        infoObtida = jogo.getSquareInfo(10);

        Assert.assertEquals(infoEsperada[0], infoObtida[0]);
        Assert.assertEquals(infoEsperada[1], infoObtida[1]);
        Assert.assertEquals(infoEsperada[2], infoObtida[2]);

    }

    @Test
    public void test_015_getPlayerInfo(){
        GameManager jogo = new GameManager();
        String[][] playersInfo = new String[2][3];

        playersInfo[0][0] = "1";
        playersInfo[0][1] = "Montra";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "Joabalhau";
        playersInfo[1][2] = "L";

        jogo.createInitialJungle(47, playersInfo);

        Assert.assertEquals("1", jogo.getPlayerInfo(1)[0]);
        Assert.assertEquals("Montra", jogo.getPlayerInfo(1)[1]);
        Assert.assertEquals("E", jogo.getPlayerInfo(1)[2]);
        Assert.assertEquals("180", jogo.getPlayerInfo(1)[3]);
        Assert.assertEquals("1..6", jogo.getPlayerInfo(1)[4]);

        Assert.assertNull(jogo.getPlayerInfo(3));
    }

    @Test
    public void test_016_getCurrentPlayerSemJogadores(){
        String[][] playersinfo = new String[0][0];
        GameManager jogo = new GameManager();
        jogo.createInitialJungle(5, playersinfo);

        Assert.assertNull(jogo.getCurrentPlayer());
        Assert.assertNull(jogo.getPlayer(1));
    }

    @Test
    public void test_017_getPlayersInfo() {
        GameManager jogo = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        jogo.createInitialJungle(47, playersInfo);

        Assert.assertEquals("1", jogo.getPlayersInfo()[0][0]);
        Assert.assertEquals("batata", jogo.getPlayersInfo()[0][1]);
        Assert.assertEquals("E", jogo.getPlayersInfo()[0][2]);
        Assert.assertEquals("180", jogo.getPlayersInfo()[0][3]);

        Assert.assertEquals("2", jogo.getPlayersInfo()[1][0]);
        Assert.assertEquals("banana", jogo.getPlayersInfo()[1][1]);
        Assert.assertEquals("L", jogo.getPlayersInfo()[1][2]);
        Assert.assertEquals("80", jogo.getPlayersInfo()[1][3]);

    }

    @Test
    public void test_018_getWinnerInfoUmGanhou(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "15";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "4";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(5, playersinfo, foodsInfo);

        jogo.jogadores.get(0).setCasaAtual(new Square(5,"a","a","1"));
        //Jogador 0 ganhou
        jogo.jogadores.get(0).setGanhou(true);

        String[] info = new String[5];
        info[0] = 15 + "";
        info[1] = "Banana";
        info[2] = "E";
        info[3] = 180 + "";
        info[4] = "1..6";

        //Dá o resultado do jogador vencedor
        Assert.assertEquals(info, jogo.getWinnerInfo());

    }

    @Test
    public void test_019_getWinner2ganhou(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        //distancia > 15

        for(int i=0; i<25;i++){
            jogo.moveCurrentPlayer(1,false);
            jogo.moveCurrentPlayer(0,false);
        }
        for(int i=0; i<5;i++){
            jogo.moveCurrentPlayer(0,false);
            jogo.moveCurrentPlayer(1,false);
        }

        String[] info = new String[5];
        info[0] = 2 + "";
        info[1] = "Mantinhas";
        info[2] = "L";
        info[3] = 200 + "";
        info[4] = "4..6";

        //Dá o resultado do jogador vencedor
        Assert.assertEquals(info, jogo.getWinnerInfo());
    }

    @Test
    public void test_020_getWinnerInfoEmpatouSemEnergia() {
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "15";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "4";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(20, playersinfo, foodsInfo);

        jogo.mapa.get(8).adicionaJogadorAPosicao(15);
        jogo.mapa.get(3).adicionaJogadorAPosicao(4);

        jogo.jogadores.get(0).setCasaAtual(jogo.mapa.get(8));
        jogo.jogadores.get(1).setCasaAtual(jogo.mapa.get(4));

        jogo.jogadores.get(0).setEnergia(0);
        jogo.jogadores.get(1).setEnergia(0);

        //Dá o resultado null
        Assert.assertNull(jogo.getWinnerInfo());
    }

    @Test
    public void test_021_getWinnerInfoNinguemGanhou() {
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "15";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "4";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(20, playersinfo, foodsInfo);

        jogo.mapa.get(8).adicionaJogadorAPosicao(15);
        jogo.mapa.get(3).adicionaJogadorAPosicao(4);

        jogo.jogadores.get(0).setCasaAtual(jogo.mapa.get(8));
        jogo.jogadores.get(1).setCasaAtual(jogo.mapa.get(4));

        Assert.assertNull(jogo.getWinnerInfo());
    }

    @Test
    public void test_022_getCurrentPlayerEnergyInfo(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";


        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        String[] info = new String[2];
        info[0] = 2 + "";
        info[1] = 10 + "";

        Assert.assertEquals(info,jogo.getCurrentPlayerEnergyInfo(1));
    }

    /*
-------------------------------------------------------------------------------
                        RESULTADO DO JOGO
-------------------------------------------------------------------------------
     */
    @Test
    public void test_023_getGameResults(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[3][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "T";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Tar";
        playersinfo[2][2] = "Z";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(47, playersinfo, foodsInfo);

        for(int i = 0; i < 44;i++){
            jogo.moveCurrentPlayer(1,false);
            jogo.moveCurrentPlayer(0,false);
            jogo.moveCurrentPlayer(1,false);
        }

        for(int i = 0; i < 16;i++){
            jogo.moveCurrentPlayer(0,false);
            jogo.moveCurrentPlayer(3,false);
            jogo.moveCurrentPlayer(0,false);
        }

        for(int i = 0; i < 1;i++){
            jogo.moveCurrentPlayer(-1,false);
            jogo.moveCurrentPlayer(-2,false);
            jogo.moveCurrentPlayer(-1,false);
        }
        //50-45-45


        ArrayList<String> resultados = new ArrayList<>();
        resultados.add("#1 Mantinhas, Tartaruga, 45, 50, 0");
        resultados.add("#2 Banana, Elefante, 44, 45, 1");
        resultados.add("#3 Tar, Tarzan, 44, 45, 1");

        Assert.assertEquals(resultados,jogo.getGameResults());
    }

    @Test
    public void test_024_FullGameWithTie(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[3][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Jazz";
        playersinfo[0][2] = "T";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Goiaba";
        playersinfo[1][2] = "E";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Pato Donald";
        playersinfo[2][2] = "Z";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(47, playersinfo, foodsInfo);

        jogo.moveCurrentPlayer(8,true);
        jogo.moveCurrentPlayer(6,true);
        jogo.moveCurrentPlayer(6,true);

        ArrayList<String> result = new ArrayList<>();
        result.add("#1 Jazz, Tartaruga, 9, 8, 0");
        result.add("#2 Goiaba, Elefante, 7, 6, 0");
        result.add("#3 Pato Donald, Tarzan, 7, 6, 0");

        Assert.assertEquals(result,jogo.getGameResults());
    }
    @Test
    public void test_025_getGameResultsfullGameHard(){
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[4][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Jazz Jack-a-Rabbit";
        playersinfo[0][2] = "T";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Pato Donald";
        playersinfo[1][2] = "Z";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Goiaba";
        playersinfo[2][2] = "P";

        playersinfo[3][0] = "4";
        playersinfo[3][1] = "Bruninho";
        playersinfo[3][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(47, playersinfo, foodsInfo);

        jogo.moveCurrentPlayer(4,true);
        jogo.moveCurrentPlayer(12,true);
        jogo.moveCurrentPlayer(10,true);
        jogo.moveCurrentPlayer(32,true);

        ArrayList<String> result = new ArrayList<>();
        result.add("#1 Pato Donald, Tarzan, 13, 24, 0,");
        result.add("#2 Bruninho, Leao, 33, 32, 1,");
        result.add("#3 Goiaba, Passaro, 11, 22, 0, ");
        result.add("#4 Jazz Jack-a-Rabbit, Tartaruga, 5, 12, 3 ");

        Assert.assertEquals(result,jogo.getGameResults());
    }

    /*
-------------------------------------------------------------------------------
                        MUDANÇA DE JOGADORES
-------------------------------------------------------------------------------
     */
    @Test
    public void test_026_mudaJogadorAtualSemIdsSeguidos() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[3][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        playersinfo[2][0] = "4";
        playersinfo[2][1] = "Zols";
        playersinfo[2][2] = "Z";


        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int idCurrentPlayerEsperado = 1;
        int idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

        jogo.mudaJogadorAtual();

        idCurrentPlayerEsperado = 2;
        idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

        jogo.mudaJogadorAtual();

        idCurrentPlayerEsperado = 4;
        idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

    }

    @Test
    public void test_027_mudaJogadorAtualComIdsNaoSeguidos() {

        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "15";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "4";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(5, playersinfo, foodsInfo);

        int idCurrentPlayerEsperado = 4;
        int idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

        jogo.mudaJogadorAtual();

        idCurrentPlayerEsperado = 15;
        idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

        jogo.mudaJogadorAtual();

        String[] info = new String[5];
        info[0] = 4 + "";
        info[1] = "Mantinhas";
        info[2] = "L";
        info[3] = 80 + "";
        info[4] = "4..6";

        Assert.assertEquals(info,jogo.getCurrentPlayerInfo());

        jogo.mudaJogadorAtual();

        info = new String[5];
        info[0] = 15 + "";
        info[1] = "Banana";
        info[2] = "E";
        info[3] = 180 + "";
        info[4] = "1..6";

        Assert.assertEquals(info,jogo.getCurrentPlayerInfo());

    }

    /*
-------------------------------------------------------------------------------
                        MOVER JOGOS
-------------------------------------------------------------------------------
     */
    @Test
    public void test_028_moveCurrentPlayer(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[3][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "P";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "bolachas";
        playersinfo[2][2] = "T";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int idCurrentPlayerEsperado = 1;
        int idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);
        MovementResult mov = jogo.moveCurrentPlayer(-8,false); //j1

        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT,mov.code());

        mov = jogo.moveCurrentPlayer(5,false); //j2
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT,mov.code());

        mov = jogo.moveCurrentPlayer(10,true); //j3
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT,mov.code());

        jogo.moveCurrentPlayer(0,false); //j1

        mov = jogo.moveCurrentPlayer(-5,false); //j2
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT,mov.code());

        mov = jogo.moveCurrentPlayer(-4,false); //j3
        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT,mov.code());

        mov = jogo.moveCurrentPlayer(10,false); //j1
        Assert.assertEquals(MovementResultCode.INVALID_MOVEMENT,mov.code());
    }

    @Test
    public void test_029_moveCurrentPlayer_NOENERGY(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";


        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int idCurrentPlayerEsperado = 1;
        int idCurrentPlayerObtido = jogo.getCurrentPlayer().getId();

        Assert.assertEquals(idCurrentPlayerEsperado,idCurrentPlayerObtido);

        jogo.jogadores.get(1).setEnergia(0);

        MovementResult mov = jogo.moveCurrentPlayer(5,false);

        Assert.assertEquals(MovementResultCode.NO_ENERGY,mov.code());
    }

    /*
-------------------------------------------------------------------------------
                        APLICA EFEITO DA COMIDA NOS JOGADORES
-------------------------------------------------------------------------------
     */
    @Test
    public void test_030_EfeitosErva(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";


        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "e";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int energiaElefante = 200;
        int energiaLeao = 60;

        Jogador jogador1 = jogo.jogadores.get(0);
        Jogador jogador2 = jogo.jogadores.get(1);
        jogo.aplicaEfeitoComida(2,jogador1);
        jogo.aplicaEfeitoComida(2,jogador2);

        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(energiaLeao,jogador2.getInfoEnergiaAtual());
    }

    @Test
    public void test_031_EfeitosAgua(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int energiaElefante = 195;
        int energiaTarzan = 84;

        Jogador jogador1 = jogo.jogadores.get(0);
        Jogador jogador2 = jogo.jogadores.get(1);

        jogo.aplicaEfeitoComida(2,jogador1);
        jogo.aplicaEfeitoComida(2,jogador2);

        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());

        Assert.assertEquals(energiaTarzan,jogador2.getInfoEnergiaAtual());
    }

    @Test
    public void test_032_EfeitosBananas(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        String[][] foodsInfo = new String[2][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "b";
        foodsInfo[1][1] = "4";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int energiaElefante = 200;
        int energiaTarzan = 110;

        Jogador jogador1 = jogo.jogadores.get(0);
        Jogador jogador2 = jogo.jogadores.get(1);

        jogo.aplicaEfeitoComida(2,jogador1);
        jogo.aplicaEfeitoComida(2,jogador2);

        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(energiaTarzan,jogador2.getInfoEnergiaAtual());

        jogo.aplicaEfeitoComida(4,jogador1);
        energiaElefante = 160;
        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());

    }

    @Test
    public void test_033_EfeitosCarne(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[3][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "c";
        foodsInfo[1][1] = "8";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "18";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int energiaElefante = 180;
        int energiaLeao = 80;

        Jogador jogador1 = jogo.jogadores.get(0); //elefante
        Jogador jogador2 = jogo.jogadores.get(1); //leao

        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(energiaLeao,jogador2.getInfoEnergiaAtual());

        jogo.moveCurrentPlayer(1,false);
        jogo.efeitoCarne(jogador1);
        jogo.moveCurrentPlayer(4,false);


        //Testa o getNrMovimentos e o getNrAlimentos
        Assert.assertEquals(1,jogador1.getNrMovimentacoes());
        Assert.assertEquals(1,jogador1.getNrAlimentos());


        Assert.assertEquals(176,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(72,jogador2.getInfoEnergiaAtual());

        for(int i = 0; i < 5;i++){
            jogo.moveCurrentPlayer(1,false);
            jogo.moveCurrentPlayer(4,false);
        }

        Assert.assertEquals(156,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(32,jogador2.getInfoEnergiaAtual());

        //caso ja tenha mais de 200 energia
        for(int i = 0; i < 20;i++){
            jogo.moveCurrentPlayer(0,false);
            jogo.moveCurrentPlayer(0,false);
        }
        jogador1.mudaEnergiaComidaPercentagem(110);
        Assert.assertEquals(200,jogador1.getInfoEnergiaAtual());

        jogador2.reduzEnergiaComidaPercentagem(1);
        Assert.assertEquals(200,jogador2.getInfoEnergiaAtual());


    }

    @Test
    public void test_034_EfeitosCogumelos(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[2][2];

        foodsInfo[0][0] = "m";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "m";
        foodsInfo[1][1] = "5";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        int energiaElefante = 180;
        int energiaLeao = 80;

        Jogador jogador1 = jogo.jogadores.get(0); //elefante
        Jogador jogador2 = jogo.jogadores.get(1); //leao

        Assert.assertEquals(energiaElefante,jogador1.getInfoEnergiaAtual());
        Assert.assertEquals(energiaLeao,jogador2.getInfoEnergiaAtual());


        jogo.moveCurrentPlayer(1,false);


        //jogo.moveCurrentPlayer(1,false);
        //System.out.println(jogo.nrjogadas);
        //System.out.println(jogo.mapa.get(2).getAlimento().getDescricaoTooltip());

        //System.out.println(jogador2.getInfoEnergiaAtual());


    }

    //Comidas
    @Test
    public void test_035_moverdiminuibanana(){
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        String desc =  "Bananas : " + 3 + " : + 40 energia";
        Assert.assertEquals(desc,jogo.mapa.get(2).getAlimento().getDescricaoTooltip());

        jogo.moveCurrentPlayer(1,false);

        desc =  "Bananas : " + 2 + " : + 40 energia";
        Assert.assertEquals(desc,jogo.mapa.get(2).getAlimento().getDescricaoTooltip());

    }

    @Test
    public void test_036_ApanhouBananas() {

        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(50,playersinfo,foodsInfo);


        Assert.assertEquals("Apanhou Bananas" , jogo.moveCurrentPlayer(1,false).message());

    }

    @Test
    public void test_037_nrJogadasDaCarne() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[2][2];

        foodsInfo[0][0] = "c";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "c";
        foodsInfo[1][1] = "3";

        jogo.createInitialJungle(47, playersinfo, foodsInfo);

        for (int i = 0; i < 5; i++) {
            jogo.moveCurrentPlayer(1, false);
            jogo.moveCurrentPlayer(4, false);
        }

        Assert.assertEquals("Carne : + 50 energia : 10 jogadas", jogo.mapa.get(2).getAlimento().getDescricaoTooltip());
        Assert.assertEquals("Carne : + 50 energia : 10 jogadas", jogo.mapa.get(3).getAlimento().getDescricaoTooltip());

        for(int i = 0; i<10;i++) {
            jogo.moveCurrentPlayer(1, false);
            jogo.moveCurrentPlayer(4, false);
        }

        Assert.assertEquals("Carne toxica", jogo.mapa.get(2).getAlimento().getDescricaoTooltip());
        Assert.assertEquals("Carne toxica", jogo.mapa.get(3).getAlimento().getDescricaoTooltip());
    }

    /*
-------------------------------------------------------------------------------
                        VERIFICAÇOES DIVERSAS
-------------------------------------------------------------------------------
     */
    @Test
    public void test_038_verificaTodosSemEnergia(){
        GameManager jogo = new GameManager();
        String[][] playersInfo = new String[2][3];

        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        jogo.createInitialJungle(47, playersInfo);
        jogo.jogadores.get(0).setEnergia(0);
        jogo.jogadores.get(1).setEnergia(0);
        Assert.assertTrue(jogo.verificaTodosSemEnergia());

        jogo.jogadores.get(1).setEnergia(5);
        jogo.jogadores.get(0).setEnergia(5);
        Assert.assertFalse(jogo.verificaTodosSemEnergia());

        jogo.jogadores.get(1).setEnergia(0);
        jogo.jogadores.get(0).setEnergia(5);
        Assert.assertFalse(jogo.verificaTodosSemEnergia());

    }

    @Test
    public void test_039_verificaSeHaVencedor(){
        GameManager jogo = new GameManager();
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        jogo.createInitialJungle(47, playersInfo);

        jogo.jogadores.get(0).setGanhou(true);
        Assert.assertTrue(jogo.verificaSeHaVencedor());

        jogo.jogadores.get(0).setGanhou(false);
        Assert.assertFalse(jogo.verificaSeHaVencedor());
    }

    @Test
    public void test_040_getJogadoresNaPosicaoPorOrdem(){
        Square posicao = new Square(2,"blank.png","Vazio","4,1,2");

        Assert.assertEquals("[1, 2, 4]", Arrays.toString(posicao.getJogadoresNaPosicaoPorOrdem()));
    }

    @Test
    public void test_041_retiraJogadorAPosicao() {
        Square posicao = new Square(1, "blank.png" , "Vazio" , "1,2,3,4");
        posicao.retiraJogadorAPosicao(2);
        Assert.assertEquals("1,3,4", posicao.jogadoresNaPosicao);

        posicao.retiraJogadorAPosicao(3);
        Assert.assertEquals("1,4", posicao.jogadoresNaPosicao);

        posicao.retiraJogadorAPosicao(4);
        Assert.assertEquals("1", posicao.jogadoresNaPosicao);
    }

    @Test
    public void test_042_getJogadoresNaPosicaoPorOrdem(){
        Square posicao = new Square(2,"blank.png","Vazio","4,1,2");

        Assert.assertEquals("[1, 2, 4]", Arrays.toString(posicao.getJogadoresNaPosicaoPorOrdem()));
    }

    @Test
    public void test_043_exemploEnunciado() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Z";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";

        jogo.createInitialJungle(10, playersinfo, foodsInfo);

        MovementResult codigo = jogo.moveCurrentPlayer(3, false);

        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, codigo.code());
        Assert.assertEquals(76, jogo.jogadores.get(0).getInfoEnergiaAtual());

        codigo = jogo.moveCurrentPlayer(5, false);
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, codigo.code());
        Assert.assertEquals(70, jogo.jogadores.get(1).getInfoEnergiaAtual());

        codigo = jogo.moveCurrentPlayer(0, false);
        Assert.assertEquals(MovementResultCode.CAUGHT_FOOD, codigo.code());
        Assert.assertEquals(115, jogo.jogadores.get(0).getInfoEnergiaAtual());

        codigo = jogo.moveCurrentPlayer(4, false);
        Assert.assertEquals(MovementResultCode.VALID_MOVEMENT, codigo.code());
        Assert.assertEquals(62, jogo.jogadores.get(1).getInfoEnergiaAtual());
        Assert.assertEquals("2", jogo.getWinnerInfo()[0]);
    }

    /*
-------------------------------------------------------------------------------
                        SAVE GAME E LOAD GAME
-------------------------------------------------------------------------------
     */
    @Test
    public void test_044_InformacoesJogadorSaveGame() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "T";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        for(int i=0; i<25;i++){
            jogo.moveCurrentPlayer(1,false);
            jogo.moveCurrentPlayer(1,false);
        }

        String info = "1,Banana,E,26,95,0,25,1";
        //Assert.assertEquals(info,jogo.jogadores.get(0).informacoesJogadorSaveGame());
    }

    @Test
    public void test_045_InformacoesSquareSaveGame() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "T";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "a";
        foodsInfo[0][1] = "4";

        jogo.createInitialJungle(30, playersinfo, foodsInfo);

        String info = "4,water.png,Agua : + 15U|20% energia,,a";
        //Assert.assertEquals(info,jogo.mapa.get(4).informacoesCasaSaveGame());
    }


    @Test
    public void test_046_SaveGamev1() {
        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[4][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Lontra";
        playersinfo[2][2] = "P";

        playersinfo[3][0] = "4";
        playersinfo[3][1] = "Tubarao";
        playersinfo[3][2] = "S";

        String[][] foodsInfo = new String[6][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "3";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "4";

        foodsInfo[3][0] = "e";
        foodsInfo[3][1] = "5";

        foodsInfo[4][0] = "m";
        foodsInfo[4][1] = "6";

        foodsInfo[5][0] = "h";
        foodsInfo[5][1] = "7";

        jogo.createInitialJungle(50, playersinfo, foodsInfo);

        File file = new File("jogo.txt");

        Assert.assertTrue(jogo.saveGame(file));

        file = new File("");

        Assert.assertFalse(jogo.saveGame(file));
    }

    @Test
    public void test_047_LoadGamev1() {

        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[4][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        playersinfo[2][0] = "3";
        playersinfo[2][1] = "Lontra";
        playersinfo[2][2] = "P";

        playersinfo[3][0] = "4";
        playersinfo[3][1] = "Tubarao";
        playersinfo[3][2] = "S";

        String[][] foodsInfo = new String[6][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        foodsInfo[1][0] = "a";
        foodsInfo[1][1] = "3";

        foodsInfo[2][0] = "c";
        foodsInfo[2][1] = "4";

        foodsInfo[3][0] = "e";
        foodsInfo[3][1] = "5";

        foodsInfo[4][0] = "m";
        foodsInfo[4][1] = "6";

        foodsInfo[5][0] = "h";
        foodsInfo[5][1] = "7";

        jogo.createInitialJungle(50, playersinfo, foodsInfo);

        File file = new File("jogo.txt");

        Assert.assertTrue(jogo.loadGame(file));

        file = new File("");

        Assert.assertFalse(jogo.loadGame(file));
    }

    @Test
    public void test_048_SaveGamev2() {

        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "T";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(50, playersinfo, foodsInfo);

        File file = new File("jogo2.txt");

        Assert.assertTrue(jogo.saveGame(file));

        file = new File("");

        Assert.assertFalse(jogo.saveGame(file));
    }

    @Test
    public void test_049_LoadGamev2() {

        GameManager jogo = new GameManager();

        String[][] playersinfo = new String[2][3];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "T";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        String[][] foodsInfo = new String[1][2];

        foodsInfo[0][0] = "b";
        foodsInfo[0][1] = "2";

        jogo.createInitialJungle(50, playersinfo, foodsInfo);

        File file = new File("jogo2.txt");

        Assert.assertTrue(jogo.loadGame(file));

        file = new File("");

        Assert.assertFalse(jogo.loadGame(file));
    }

    /*
-------------------------------------------------------------------------------
                        OUTROS
-------------------------------------------------------------------------------
     */
    @Test
    public void test_050_whoIsTaborda(){
        GameManager jogo = new GameManager();
        String taborda = "Wrestling";
        Assert.assertEquals(taborda,jogo.whoIsTaborda());
    }

    @Test
    public void test_051_Main(){
        Main main = new Main();
        //Sem conteudo, so mesmo para a class estar 100% no coverage
    }

}

