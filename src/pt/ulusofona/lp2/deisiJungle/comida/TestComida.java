package pt.ulusofona.lp2.deisiJungle.comida;

import org.junit.Assert;
import org.junit.Test;

public class TestComida {
    @Test
    public void test_001_getInfoAlimento(){
        Agua agua = new Agua();
        String[] info = new String[3];

        info[0] = 'a' + "";
        info[1] = "Agua";
        info[2] = "water.png";

        Assert.assertEquals(info,agua.getInfo());

    }

    @Test
    public void test_002_getDescricaoTooltip(){
        String desc;
        Agua agua = new Agua();
        desc = "Agua : + 10U|20% energia";
        Assert.assertEquals(desc,agua.getDescricaoTooltip());

        Banana banana = new Banana();
        desc =  "Bananas : " + banana.getNrBananas() + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        //CogumelosMagicos cogumelosMagicos  = new CogumelosMagicos();
        //desc =  "Cogumelo Magico: +- " + cogumelosMagicos.getNrJogadas() + "% energia";
        //Assert.assertEquals(desc,cogumelosMagicos.getDescricaoTooltip());

        Erva erva = new Erva();
        desc =  "Erva : +- 20 energia";
        Assert.assertEquals(desc,erva.getDescricaoTooltip());

        Carne carne = new Carne();
        desc = "Carne : +- 50 energia : " + carne.getNrJogadas() + " jogadas";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());

        carne.setnrJogadas(13);
        desc = "Carne toxica";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());

    }

    @Test
    public void test_003_diminuiBananas(){
        Banana banana = new Banana();
        String desc =  "Bananas : " + banana.getNrBananas() + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        Assert.assertTrue(banana.diminuiBanana());
        Assert.assertTrue(banana.diminuiBanana());
        Assert.assertTrue(banana.diminuiBanana());
        Assert.assertFalse(banana.diminuiBanana());
    }


}
