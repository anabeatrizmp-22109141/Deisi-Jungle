package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    int id;
    String nome;
    String idEspecie;
    int energia;
    boolean jogadorAtual;
    boolean ganhou;
    Square casaAtual;



    public Jogador(int id, String nome, String idEspecie, int energia, boolean jogadorAtual,Square casa_Atual) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.energia = energia;
        this.jogadorAtual = jogadorAtual;
        this.ganhou = false;
        this.casaAtual = casa_Atual;
    }

    public boolean isTurnoDoJogador(){
        return this.jogadorAtual;
    }

    public boolean ganhou(){
        return this.ganhou;
    }

    public int movimentacao(){
        return this.casaAtual.nrSquare++;
    }

    public int getEnergia() {
        return energia;
    }

    public void diminuiEnergia(int energia) {
        this.energia -= energia;
    }
}
