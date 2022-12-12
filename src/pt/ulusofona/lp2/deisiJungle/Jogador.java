package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especie.Especie;
import static java.lang.Math.abs;

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

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Square getCasaAtual() {
        return this.casaAtual;
    }

    public String[] getInfoJogador() {
        String[] informacaoJogador = new String[5];

        informacaoJogador[0] = getId() + "";
        informacaoJogador[1] = getNome();
        informacaoJogador[2] = especie.getId() + "";
        informacaoJogador[3] = especie.getEnergiaInicial() + "";
        informacaoJogador[4] = especie.getVelocidade();

        return informacaoJogador;
    }

    public int getInfoEnergiaAtual() {
        return energia;
    }

    public int getInfoEnergiaGastaSeMover(int nrCasas) {
        return abs(nrCasas) * especie.getConsumoEnergia();
    }

    public int getInfoEnergiaConseguidaSeFicar() {
        return especie.getEnergiaEmDescanso();
    }

    public String getClassificacao() {
        return this.nome + ", " + this.especie.getNome() + ", " + this.casaAtual.getNrSquare();
    }

    public boolean isTurnoDoJogador(){
        return this.isJogadorAtual;
    }

    public boolean ganhou(){
        return this.ganhou;
    }

    public boolean temEnergiaParaMover(int nrCasas) {
        return this.energia - getInfoEnergiaGastaSeMover(nrCasas) >= 0;
    }

    public void diminuiEnergiaMovimento(int nrCasas) {
        this.energia -= getInfoEnergiaGastaSeMover(nrCasas);
    }

    public int getProximoNrSquare(int nrCasas) {
        return this.casaAtual.getNrSquare() + nrCasas;
    }

    public void descansa() {

        if(this.energia + especie.getEnergiaEmDescanso() >= 200) {
            this.energia = 200;
        }
        else {
            this.energia += especie.getEnergiaEmDescanso();
        }
    }

    public void trocaJogadorAtual() {
        this.isJogadorAtual = !isTurnoDoJogador();
    }

    public void setEnergia(int valor){ //Para fins de teste
        this.energia = valor;
    }

    public void setGanhou (boolean valor){
        ganhou=valor;
    }

    public void setCasaAtual(Square casa){
        this.casaAtual = casa;
    }
}
