package pt.ulusofona.lp2.deisiJungle;

public class Square {
    int nrSquare;
    String imagem;
    String descricao;
    String jogadoresNaPosicao;

    public Square(int nrSquare, String descricao, String jogadoresNaPosicao) {
        this.nrSquare = nrSquare;
        this.descricao = descricao;
        this.jogadoresNaPosicao = jogadoresNaPosicao;
    }

    public int getNrSquare() {
        return nrSquare;
    }

    public String getImagem() {
        return imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getJogadoresNaPosicao() {
        return jogadoresNaPosicao;
    }

    public void setJogadoresAPosicao(int id) {

    }

    public boolean validaNrSquare(){
        return true;
    }
}
