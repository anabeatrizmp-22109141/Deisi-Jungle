package pt.ulusofona.lp2.deisiJungle.comida;

import org.junit.Assert;
import org.junit.Test;
import pt.ulusofona.lp2.deisiJungle.GameManager;

public class TestComida {
    @Test
    public void test_001_getInfoAlimento(){
        Agua agua = new Agua();
        String[] info = new String[3];

        info[0] = "a";
        info[1] = "Agua";
        info[2] = "water.png";

        Assert.assertEquals(info,agua.getInfo());
        Assert.assertEquals(info[0],agua.getId());
        Assert.assertEquals(info[1],agua.getNome());
        Assert.assertEquals(info[2],agua.getImagem());
    }

    @Test
    public void test_002_getDescricaoTooltip(){
        String desc;
        Agua agua = new Agua();
        desc = "Agua : + 15U|20% energia";
        Assert.assertEquals(desc,agua.getDescricaoTooltip());

        Banana banana = new Banana();
        desc =  "Bananas : " + 3 + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        CogumelosMagicos cogumelosMagicos  = new CogumelosMagicos();
        cogumelosMagicos.setNumeroAleatorio(10);
        desc =  "Cogumelo Magico: +- " + 10 + "% energia";
        Assert.assertEquals(desc,cogumelosMagicos.getDescricaoTooltip());
        Assert.assertEquals(10,cogumelosMagicos.getNumeroAleatorio());

        Erva erva = new Erva();
        desc =  "Erva : +- 20 energia";
        Assert.assertEquals(desc,erva.getDescricaoTooltip());

        Carne carne = new Carne();
        desc = "Carne : + 50 energia : 0 jogadas";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());
    }

    @Test
    public void test_003_diminuiBananas(){
        Banana banana = new Banana();
        String desc =  "Bananas : " + 3 + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        Assert.assertTrue(banana.diminuiBanana());
        Assert.assertTrue(banana.temBananas());

        desc =  "Bananas : " + 2 + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        Assert.assertTrue(banana.diminuiBanana());
        Assert.assertTrue(banana.temBananas());

        desc =  "Bananas : " + 1 + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        banana.diminuiBanana();
        Assert.assertFalse(banana.temBananas());

        desc =  "Bananas : " + 0 + " : + 40 energia";
        Assert.assertEquals(desc,banana.getDescricaoTooltip());

        //Quando  nrbananas já é 0
        Assert.assertFalse(banana.diminuiBanana());
        Assert.assertFalse(banana.temBananas());

        Assert.assertEquals(0,banana.getNrBananas());

    }
    @Test
    public void test_004_getDescricaoTooltipDaCarneToxica(){
        Carne carne = new Carne();
        String desc =  "Carne : + 50 energia : 0 jogadas";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());

        for(int i = 0; i< 10; i++) {
            carne.aumentaNrJogadasCarne();
        }

        desc =  "Carne : + 50 energia : 10 jogadas";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());

        for(int i = 0; i< 10; i++) {
            carne.aumentaNrJogadasCarne();
        }

        desc =  "Carne toxica";
        Assert.assertEquals(desc,carne.getDescricaoTooltip());

    }
}
