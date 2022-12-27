package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especie.Especie;
import static java.lang.Math.abs;

public class Jogador {
    private final int id;
    private final String nome;
    private final Especie especie;
    private boolean isJogadorAtual;
    private boolean ganhou;
    private Square casaAtual;
    private int energia;
    private int nrBananasComidas = 0;
    private int nrMovimentacoes = 0;
    private int nrAlimentos = 0;

    public Jogador(int id, String nome, Especie especie,Square casaAtual) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.isJogadorAtual = false;
        this.ganhou = false;
        this.casaAtual = casaAtual;
        this.energia = especie.getEnergiaInicial();
    }

    public Jogador(int id, String nome, Especie especie, boolean isJogadorAtual, boolean ganhou, Square casaAtual,
                   int energia, int nrBananasComidas, int nrMovimentacoes, int nrAlimentos) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.isJogadorAtual = isJogadorAtual;
        this.ganhou = ganhou;
        this.casaAtual = casaAtual;
        this.energia = energia;
        this.nrBananasComidas = nrBananasComidas;
        this.nrMovimentacoes = nrMovimentacoes;
        this.nrAlimentos = nrAlimentos;
    }

    /*
----------------------------------------------------------------------------------
                                   GETTERS
----------------------------------------------------------------------------------
     */

    public String getPlayerInfoSaveLoad() {
        return id + "|" + nome + "|" + especie.getId() + "|" + isJogadorAtual + "|" +
                ganhou + "|" + casaAtual.getNrSquare() + "|"  + energia + "|" +
                nrBananasComidas + "|" + nrMovimentacoes + "|" + nrMovimentacoes;
    }

    public int getId() {
        return this.id;
    }

    public Especie getEspecie() {
        return this.especie;
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
        informacaoJogador[3] = energia + "";
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
        return nome + ", " + especie.getNome() + ", " + casaAtual.getNrSquare() + ", "
                + nrMovimentacoes + ", " + nrAlimentos;
    }

    public boolean getJogadorAtual(){
        return this.isJogadorAtual;
    }

    public int getProximoNrSquare(int nrCasas) {
        return this.casaAtual.getNrSquare() + nrCasas;
    }

    public int getNrBananasComidas() {
        return this.nrBananasComidas;
    }

    public void adicionaNrMovimentacoes(int valor){
        this.nrMovimentacoes+=valor;
    }

    public int getNrMovimentacoes(){
        return nrMovimentacoes;
    }

    public void aumentaNrAlimentos(){
        this.nrAlimentos++;
    }

    public int getNrAlimentos(){
        return nrAlimentos;
    }

    /*
----------------------------------------------------------------------------------
     */

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
        if(this.energia - getInfoEnergiaGastaSeMover(nrCasas) <= 0) {
            this.energia = 0;
        }else {
            this.energia -= getInfoEnergiaGastaSeMover(nrCasas);
        }
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

    public void aumentaNrBananasComidas() {
        this.nrBananasComidas++;
    }

    public void mudaEnergiaComidaValorInteiro(int nrEnergia) {
        if(this.energia + nrEnergia >= 200) {
            this.energia = 200;
        }
        else {
            this.energia += nrEnergia;
        }
    }

    public void mudaEnergiaComidaPercentagem(int valorPercentagem) {
        if(this.energia * (1 + valorPercentagem/100) >= 200) {
            this.energia = 200;
        }
        else {
            this.energia *=  (1 + (double) valorPercentagem/100);
        }
    }

    public void reduzEnergiaComidaPercentagem(int valorPercentagem) {
        if(this.energia * (1 + valorPercentagem/100) >= 200) {
            this.energia = 200;
        }
        else {
            double energia = this.energia;
            energia *= (1 - (double) valorPercentagem/100);
            this.energia=(int) Math.ceil(energia);
        }
    }

    /*
----------------------------------------------------------------------------------
                                SETTERS
----------------------------------------------------------------------------------
     */

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
