package pt.ulusofona.lp2.deisiJungle;

public class Jogadores {
    int id;
    String nome;
    Especie especie;
    int energia;
    boolean jogadorAtual;
    boolean ganhou;
    int casa_Atual;


    public Jogadores(int id, String nome, Especie especie, int energia, boolean jogadorAtual,int casa_Atual) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.energia = energia;
        this.jogadorAtual = jogadorAtual;
        this.ganhou = false;
        this.casa_Atual = casa_Atual;
    }

    public boolean isTurnoDoJogador(){
        return this.jogadorAtual;
    }

    public boolean ganhou(){
        return this.ganhou;
    }

    public int movimentacao(){
        return this.casa_Atual++;
    }

}
