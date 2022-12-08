package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;
import pt.ulusofona.lp2.deisiJungle.comida.*;
import pt.ulusofona.lp2.deisiJungle.especie.*;

public class TestGameManager {

    @Test
    public void test_001_getSpecies(){
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

        GameManager jogo = new GameManager();

        Assert.assertEquals(especies,jogo.getSpecies());
    }

    @Test
    public void test_002_getFoodType(){
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

        GameManager jogo = new GameManager();

        Assert.assertEquals(comida,jogo.getFoodTypes());

    }

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
    public void test_006_verificacoesMapaNovoIdComida() {

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
    public void test_007_verificacoesMapaNovoPosicoesComida() {
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
    public void test_008_verificacoesMapaAntigoNrJogadores(){
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
    public void test_009_verificacoesMapaAntigoMapa(){
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

    @Test
    public void test_createInitialJungle_criaMapaCriaJogadores(){
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
    public void test_createInitialJungle_MapaJogadoresComida(){
        GameManager jogo = new GameManager();
        String[][] foodinfo = new String[5][3];
    }

    @Test
    public void test_getSquareInfoInicial() {
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
        infoEsperada[1] = "Agua : + 10U|20% energia";
        infoEsperada[2] = "";

        String[] infoObtida = jogo.getSquareInfo(10);

        Assert.assertEquals(infoEsperada[0], infoObtida[0]);
        Assert.assertEquals(infoEsperada[1], infoObtida[1]);
        Assert.assertEquals(infoEsperada[2], infoObtida[2]);
    }

    @Test
    public void test_retiraJogadorAPosicao() {
        Square posicao = new Square(1, "blank.png" , "Vazio" , "1,2,3,4");
        posicao.retiraJogadorAPosicao(2);
        Assert.assertEquals("1,3,4", posicao.jogadoresNaPosicao);

        posicao.retiraJogadorAPosicao(3);
        Assert.assertEquals("1,4", posicao.jogadoresNaPosicao);

        posicao.retiraJogadorAPosicao(4);
        Assert.assertEquals("1", posicao.jogadoresNaPosicao);
    }
}

