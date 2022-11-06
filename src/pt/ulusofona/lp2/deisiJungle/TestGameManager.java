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
        jogo.getSquareInfo(1);
        Assert.assertEquals(0,jogo.getSquareInfo(3).length);
    }
}
