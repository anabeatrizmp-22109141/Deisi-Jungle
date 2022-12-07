package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especie.Especie;

public class Jogador {
    private int id;
    private String nome;
    private Especie especie;
    private boolean isJogadorAtual;
    private boolean ganhou;
    private Square casaAtual;
    private int energia;

    public Jogador(int id, String nome, Especie especie,Square casaAtual) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.isJogadorAtual = false;
        this.ganhou = false;
        this.casaAtual = casaAtual;
        this.energia = especie.getEnergiaInicial();
    }

    public boolean isTurnoDoJogador(){
        return this.isJogadorAtual;
    }

    public boolean ganhou(){
        return this.ganhou;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Square getCasaAtual() {
        return this.casaAtual;
    }

    public boolean temEnergiaParaMover() {
        return this.energia - especie.getConsumoEnergia() >= 0;
    }

    public void diminuiEnergia() {
        this.energia -= especie.getConsumoEnergia();
    }

    public void aumentaEnergiaEmDescanso() {this.energia += especie.getEnergiaEmDescanso();}

    public void trocaJogadorAtual() {
        this.isJogadorAtual = !isTurnoDoJogador();
    }

    public String[] getInfoJogador() {
        String[] informacaoJogador = new String[4];

        informacaoJogador[0] = getId() + "";
        informacaoJogador[1] = getNome();
        informacaoJogador[2] = especie.getId() + "";
        informacaoJogador[3] = especie.getEnergiaInicial() + "";

        return informacaoJogador;
    }

    public String getClassificacao() {
        return this.nome + ", " + this.especie.getNome() + ", " + this.casaAtual.getNrSquare();
    }
}
