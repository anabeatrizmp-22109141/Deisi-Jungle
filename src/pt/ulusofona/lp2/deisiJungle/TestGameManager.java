package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

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
        Square posicao = new Square(1, "blank.png" , "Vazio" , "1,2");
        posicao.retiraJogadorAPosicao(2);

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

}
