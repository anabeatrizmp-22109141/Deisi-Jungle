package pt.ulusofona.lp2.deisiJungle.comida;

import pt.ulusofona.lp2.deisiJungle.comida.Alimento;

import java.util.concurrent.ThreadLocalRandom;

public class CogumelosMagicos extends Alimento {
    private int numeroAleatorio;

    public CogumelosMagicos() {
        this.id = "m";
        this.nome = "Cogumelo Magico";
        this.imagem = "mushroom.png";
        this.numeroAleatorio = ThreadLocalRandom.current().nextInt(10, 50 + 1);
    }

    @Override
    public String getDescricaoTooltip() {
        return "Cogumelo Magico : +- " + numeroAleatorio + "% energia";
    }

    public int getNumeroAleatorio() {
        return numeroAleatorio;
    }

    //Para fins de teste
    public void setNumeroAleatorio(int valor){
        numeroAleatorio = valor;
    }
}
