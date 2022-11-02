package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    int id;
    String nome;
    String idEspecie;
    int energia;
    boolean jogadorAtual;
    boolean ganhou;
    Square casa_Atual;


    public Jogador(int id, String nome, String idEspecie, int energia, boolean jogadorAtual,Square casa_Atual) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
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
        return this.casa_Atual.nrSquare++;
    }

}