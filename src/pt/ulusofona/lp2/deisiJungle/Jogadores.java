package pt.ulusofona.lp2.deisiJungle;

public class Jogadores {
    int id;
    String nome;
    Especie especie;
    int energia;
    boolean jogadorAtual;

    public Jogadores(int id, String nome, Especie especie, int energia) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.energia = energia;
    }

    public boolean isTurnoDoJogador() {
        return this.jogadorAtual;
    }


}
