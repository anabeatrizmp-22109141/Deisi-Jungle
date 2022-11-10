package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    int id;
    String nome;
    String nomeEspecie;
    String idEspecie;
    int energia;
    boolean jogadorAtual;
    boolean ganhou;
    Square casaAtual;



    public Jogador(int id, String nome, String idEspecie, int energia, boolean jogadorAtual,Square casa_Atual, String nomeEspecie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.energia = energia;
        this.jogadorAtual = jogadorAtual;
        this.ganhou = false;
        this.casaAtual = casa_Atual;
        this.nomeEspecie = nomeEspecie;
    }



    public boolean isTurnoDoJogador(){
        return this.jogadorAtual;
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

    public String getIdEspecie() {
        return this.idEspecie;
    }

    public Square getCasaAtual() {
        return this.casaAtual;
    }

    public int getEnergia() {
        return this.energia;
    }

    public boolean temEnergiaParaMover() {
        return this.energia - 2 >= 0;
    }

    public void diminuiEnergia() {
        this.energia -= 2;
    }

    public void trocaJogadorAtual() {
        this.jogadorAtual = !isTurnoDoJogador();
    }

    public String[] getInfoJogador() {
        String[] informacaoJogador = new String[4];

        informacaoJogador[0] = getId() + "";
        informacaoJogador[1] = getNome();
        informacaoJogador[2] = getIdEspecie()+ "";
        informacaoJogador[3] = getEnergia() + "";

        return informacaoJogador;
    }

    public String getClassificacao() {
        return this.nome + ", " + this.nomeEspecie + ", " + this.casaAtual.getNrSquare();
    }
}
