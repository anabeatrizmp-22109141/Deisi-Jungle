package pt.ulusofona.lp2.deisiJungle;

public class Square {
    int nrSquare;
    String imagem;
    String descricao;
    String jogadoresNaPosicao;

    public Square(int nrSquare, String imagem ,String descricao, String jogadoresNaPosicao) {
        this.nrSquare = nrSquare;
        this.imagem = imagem;
        this.descricao = descricao;
        this.jogadoresNaPosicao = jogadoresNaPosicao;
    }

    public int getNrSquare() {
        return this.nrSquare;
    }

    public String getImagem() {
        return this.imagem;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getJogadoresNaPosicao() {
        return this.jogadoresNaPosicao;
    }

    public void adicionaJogadorAPosicao(int id) {

    }

    public boolean validaNrSquare(){
        return true;
    }
}
