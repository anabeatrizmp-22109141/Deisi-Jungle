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
    }

    @Test
    public void test_005_verificacoesMapaAntigoIdEspecie(){
        GameManager jogo = new GameManager();
        InitializationError erro2 = new InitializationError("ID Especie é inválido");

        String[][] playersinfo = new String[2][5];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Ç";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //Verifica se o idespecie não contém no getspecies
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][5];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "Z";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "Z";

        //verifica se existe mais que 1 tarzan
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][5];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = null;

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

        //verifica se é null
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][5];

        playersinfo[0][0] = "2";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "";

        playersinfo[1][0] = "1";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "";

        //Verifica vazio
        Assert.assertEquals(erro2.getMessage(),jogo.verificacoesMapaAntigo(30,playersinfo).getMessage());

        playersinfo = new String[2][5];

        playersinfo[0][0] = "1";
        playersinfo[0][1] = "Banana";
        playersinfo[0][2] = "E";

        playersinfo[1][0] = "2";
        playersinfo[1][1] = "Mantinhas";
        playersinfo[1][2] = "L";

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

