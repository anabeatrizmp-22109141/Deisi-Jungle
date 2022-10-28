package pt.ulusofona.lp2.deisiJungle;

public class Jogadores {
    int id;
    String nome;
    Especie especie;
    int energia;
    boolean jogadorAtual;
    boolean ganhou;
    int casa_atual;


    public Jogadores(int id, String nome, Especie especie, int energia, boolean jogadorAtual,int casa_atual) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.energia = energia;
        this.jogadorAtual = jogadorAtual;
        this.ganhou = false;
        this.casa_atual = casa_atual;
    }

    public boolean isTurnoDoJogador(){
        return this.jogadorAtual;
    }

    public boolean ganhou(){
        return this.ganhou;
    }

    public void movimentacao(){
        casa_atual++;
    }

}
