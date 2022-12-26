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

    @Test
    public void test_002_Elefante(){
        Elefante elefante = new Elefante();
        Assert.assertTrue(elefante.eHerbivoro());
        Assert.assertFalse(elefante.eCarnivoro());
        Assert.assertFalse(elefante.eOmnivoro());
    }

    @Test
    public void test_002_Leao(){
        Leao leao = new Leao();
        Assert.assertFalse(leao.eHerbivoro());
        Assert.assertTrue(leao.eCarnivoro());
        Assert.assertFalse(leao.eOmnivoro());
    }

    @Test
    public void test_003_Passaro(){
        Passaro passaro = new Passaro();
        Assert.assertFalse(passaro.eHerbivoro());
        Assert.assertFalse(passaro.eCarnivoro());
        Assert.assertTrue(passaro.eOmnivoro());
    }

    @Test
    public void test_003_Tartaruga(){
        Tartaruga tartaruga = new Tartaruga();
        Assert.assertFalse(tartaruga.eHerbivoro());
        Assert.assertFalse(tartaruga.eCarnivoro());
        Assert.assertTrue(tartaruga.eOmnivoro());
    }

    @Test
    public void test_004_Tarzan(){
        Tarzan tarzan = new Tarzan();
        Assert.assertFalse(tarzan.eHerbivoro());
        Assert.assertFalse(tarzan.eCarnivoro());
        Assert.assertTrue(tarzan.eOmnivoro());
    }

    @Test
    public void test_005_EspecieGeral(){
        Especie especie = new Elefante();
        String[] info = new String[7];

        info[0] = "E";
        info[1] = "Elefante";
        info[2] = "elephant.png";
        info[3] = 180 + "";
        info[4] = 4 + "";
        info[5] = 10 + "";
        info[6] = "1..6";

        Assert.assertEquals(info[0],especie.getId());
        Assert.assertEquals(info[1],especie.getNome());
        Assert.assertEquals(info[2],especie.getFicheiro());
        Assert.assertEquals(Integer.parseInt(info[3]),especie.getEnergiaInicial());
        Assert.assertEquals(Integer.parseInt(info[4]),especie.getConsumoEnergia());
        Assert.assertEquals(Integer.parseInt(info[5]),especie.getEnergiaEmDescanso());
        Assert.assertEquals(info[6],especie.getVelocidade());
    }
}
