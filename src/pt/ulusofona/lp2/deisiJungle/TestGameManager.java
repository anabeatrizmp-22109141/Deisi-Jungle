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
        Elefante elefante = new Elefante();
        Square posicao = new Square(30,"blank.png","Vazio","1,2,3,4");
        Jogador jogador = new Jogador(1,null,elefante,posicao);
        InitializationError erro = new InitializationError("Nome é inválido");
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[1][5];
        playersinfo[0] = jogador.getInfoJogador();

        //Teste para nome Invalido
        Assert.assertEquals(erro.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());


    }

    @Test
    public void test_004_verificacoesMapaAntigoIdJogador(){
        Passaro passaro = new Passaro();
        Tartaruga tartaruga = new Tartaruga();
        Square posicao = new Square(30,"blank.png","Vazio","1,2,3,4");
        Jogador jogador = new Jogador(1,"Antonio",passaro,posicao);
        Jogador jogador2 = new Jogador(1,"Joao",tartaruga,posicao);
        InitializationError erro2 = new InitializationError("Id do jogador é inválido");
        GameManager jogo = new GameManager();
        String[][] playersinfo = new String[2][5];
        playersinfo[0] = jogador.getInfoJogador();
        playersinfo[1] = jogador2.getInfoJogador();

        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

    }
    @Test
    public void test_005_verificacoesMapaAntigoIdEspecie(){
        GameManager jogo = new GameManager();
        Passaro passaro = new Passaro();
        Passaro passaro1 = new Passaro();
        passaro1.setId("ç");
        Square posicao = new Square(30,"blank.png","Vazio","1,2");
        Jogador jogador = new Jogador(1,"Antonio",passaro,posicao);
        Jogador jogador2 = new Jogador(2,"Joao",passaro1,posicao);

        InitializationError erro2 = new InitializationError("ID Especie é inválido");

        String[][] playersinfo = new String[2][5];
        playersinfo[0] = jogador.getInfoJogador();
        playersinfo[1] = jogador2.getInfoJogador();

        //Verifica se o idespecie não contém no getspecies
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        Tarzan tarzan = new Tarzan();
        jogador = new Jogador(1,"Antonio",tarzan,posicao);
        jogador2 = new Jogador(2,"Joao",tarzan,posicao);

        playersinfo = new String[2][5];
        playersinfo[0] = jogador.getInfoJogador();
        playersinfo[1] = jogador2.getInfoJogador();

        //verifica se existe mais que 1 tarzan
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        tarzan = new Tarzan();
        passaro = new Passaro();
        passaro.setId("");
        jogador = new Jogador(1,"Antonio",tarzan,posicao);
        jogador2 = new Jogador(2,"Joao",passaro,posicao);

        playersinfo = new String[2][5];
        playersinfo[0] = jogador.getInfoJogador();
        playersinfo[1] = jogador2.getInfoJogador();

        //verifica se é null
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        tarzan = new Tarzan();
        passaro = new Passaro();

        jogador = new Jogador(1,"Antonio",tarzan,posicao);
        jogador2 = new Jogador(2,"Joao",passaro,posicao);

        playersinfo = new String[2][5];
        playersinfo[0] = jogador.getInfoJogador();
        playersinfo[1] = jogador2.getInfoJogador();

        Assert.assertFalse(jogo.idEspecieInvalido(playersinfo));
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

