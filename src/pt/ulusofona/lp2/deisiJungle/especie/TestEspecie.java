package pt.ulusofona.lp2.deisiJungle.especie;

import org.junit.Assert;
import org.junit.Test;

public class TestEspecie {

    @Test
    public void test_001_getInfoEspecie(){
        Elefante elefante = new Elefante();
        String[] info = new String[7];

        info[0] = 'E' + "";
        info[1] = "Elefante";
        info[2] = "elephant.png";
        info[3] = 180 + "";
        info[4] = 4 + "";
        info[5] = 10 + "";
        info[6] = "1..6";

        Assert.assertEquals(info,elefante.getInfo());

    }
}
