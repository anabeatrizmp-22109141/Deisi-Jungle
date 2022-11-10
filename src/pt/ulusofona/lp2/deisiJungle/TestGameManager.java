package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestGameManager {
    @Test
    public void test_getSquareInfo() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();

        jogo.createInitialJungle(47, 22, playersInfo);
        Assert.assertEquals("",jogo.getSquareInfo(3)[2]);
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

    @Test
    public void test_MoveJogadorInicio() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);
        jogo.moveCurrentPlayer(2, true);
        jogo.moveCurrentPlayer(2, true);

        String obtido = jogo.mapa.get(1).jogadoresNaPosicao;
        String esperado = "";

        Assert.assertEquals(esperado, obtido);
    }

    @Test
    public void test_MoveJogadorResetJogoAMeio() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";
        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        jogo.moveCurrentPlayer(2, true);
        jogo.moveCurrentPlayer(2, true);

        String obtido = jogo.mapa.get(3).jogadoresNaPosicao;
        String esperado = "1,2";

        Assert.assertEquals(esperado,obtido);

        jogo.createInitialJungle(47, 22, playersInfo);

        obtido = jogo.mapa.get(1).jogadoresNaPosicao;
        esperado = "1,2";

        Assert.assertEquals(esperado,obtido);
    }

    @Test
    public void test_getSquareInfoCasaMoveCasa() {
        String[][] playersInfo = new String[4][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "golias";
        playersInfo[2][2] = "Z";

        playersInfo[3][0] = "4";
        playersInfo[3][1] = "joca";
        playersInfo[3][2] = "T";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        String obtido = jogo.getSquareInfo(1)[2];
        String esperado = "1,2,3,4";
        Assert.assertEquals(esperado,obtido);
        jogo.moveCurrentPlayer(1, false);
        obtido = jogo.getSquareInfo(1)[2];
        esperado = "2,3,4";
        Assert.assertEquals(esperado,obtido);
    }

    @Test
    public void test_moveJogadorCasaInvalida() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";
        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        boolean obtido = jogo.moveCurrentPlayer(-1, false);
        boolean esperado = false;

        Assert.assertEquals(esperado,obtido);

        obtido = jogo.moveCurrentPlayer(10, false);
        esperado = false;

        Assert.assertEquals(esperado,obtido);
    }

    @Test
    public void test_moveJogadorSemEnergia() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";
        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        jogo.jogadores.get(0).energia = 0;

        boolean obtido = jogo.moveCurrentPlayer(3, false);
        boolean esperado = false;

        Assert.assertEquals(esperado,obtido);
    }

    @Test
    public void test_getPlayersId() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        int esperado = 2;
        int obtido = jogo.getPlayerIds(1).length;

        Assert.assertEquals(esperado,obtido);
    }
    @Test
    public void test_getPlayerInfo() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        Assert.assertEquals("1", jogo.getPlayerInfo(1)[0]);
        Assert.assertEquals("batata", jogo.getPlayerInfo(1)[1]);
        Assert.assertEquals("E", jogo.getPlayerInfo(1)[2]);
        Assert.assertEquals("22", jogo.getPlayerInfo(1)[3]);
    }

    @Test
    public void test_getPlayersInfo() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        Assert.assertEquals("1", jogo.getPlayersInfo()[0][0]);
        Assert.assertEquals("batata", jogo.getPlayersInfo()[0][1]);
        Assert.assertEquals("E", jogo.getPlayersInfo()[0][2]);
        Assert.assertEquals("22", jogo.getPlayersInfo()[0][3]);

        Assert.assertEquals("2", jogo.getPlayersInfo()[1][0]);
        Assert.assertEquals("banana", jogo.getPlayersInfo()[1][1]);
        Assert.assertEquals("L", jogo.getPlayersInfo()[1][2]);
        Assert.assertEquals("22", jogo.getPlayersInfo()[1][3]);

        jogo.moveCurrentPlayer(1, false);
        jogo.getPlayersInfo();

        Assert.assertEquals("1", jogo.getPlayersInfo()[0][0]);
        Assert.assertEquals("batata", jogo.getPlayersInfo()[0][1]);
        Assert.assertEquals("E", jogo.getPlayersInfo()[0][2]);
        Assert.assertEquals("20", jogo.getPlayersInfo()[0][3]);
    }

    @Test
    public void teste_funcTodosSemEnergia() {
        String[][] playersInfo = new String[2][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        jogo.jogadores.get(0).energia = 0;
        jogo.jogadores.get(1).energia = 0;

        Assert.assertEquals(true, jogo.verificaTodosSemEnergia());

        jogo.jogadores.get(0).energia = 2;
        jogo.jogadores.get(1).energia = 0;
        Assert.assertEquals(false, jogo.verificaTodosSemEnergia());

        jogo.jogadores.get(1).energia = 2;
        Assert.assertEquals(false, jogo.verificaTodosSemEnergia());
    }

    @Test
    public void teste_getResultados() {
        String[][] playersInfo = new String[4][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "almondega";
        playersInfo[2][2] = "T";

        playersInfo[3][0] = "4";
        playersInfo[3][1] = "tangerina";
        playersInfo[3][2] = "P";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        jogo.moveCurrentPlayer(6, false);
        jogo.moveCurrentPlayer(6, false);
        jogo.moveCurrentPlayer(4, false);
        jogo.moveCurrentPlayer(1, false);

        ArrayList<String> obtido = jogo.getGameResults();
        ArrayList<String> esperado = new ArrayList<>();
        esperado.add("#1 batata, Elefante, 7");
        esperado.add("#2 banana, Leão, 7");
        esperado.add("#3 almondega, Tartaruga, 5");
        esperado.add("#4 tangerina, Pássaro, 2");


        Assert.assertEquals(esperado,obtido);

    }

    @Test
    public void teste_getResultadosTudoEmpatado() {
        String[][] playersInfo = new String[3][3];
        playersInfo[0][0] = "1";
        playersInfo[0][1] = "batata";
        playersInfo[0][2] = "E";

        playersInfo[1][0] = "2";
        playersInfo[1][1] = "banana";
        playersInfo[1][2] = "L";

        playersInfo[2][0] = "3";
        playersInfo[2][1] = "almondega";
        playersInfo[2][2] = "T";

        GameManager jogo = new GameManager();
        jogo.createInitialJungle(47, 22, playersInfo);

        jogo.moveCurrentPlayer(6, false);
        jogo.moveCurrentPlayer(6, false);
        jogo.moveCurrentPlayer(4, false);

        jogo.jogadores.get(0).energia = 0;
        jogo.jogadores.get(1).energia = 0;
        jogo.jogadores.get(2).energia = 0;

        ArrayList<String> obtido = jogo.getGameResults();
        ArrayList<String> esperado = new ArrayList<>();
        esperado.add("#1 batata, Elefante, 7");
        esperado.add("#2 banana, Leão, 7");
        esperado.add("#3 almondega, Tartaruga, 5");

        Assert.assertEquals(3, esperado.size());
        Assert.assertEquals(obtido, esperado);

    }
}

