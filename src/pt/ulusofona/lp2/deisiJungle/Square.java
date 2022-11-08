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
        if(this.jogadoresNaPosicao == "") {
            this.jogadoresNaPosicao += id;
        }
        else {
            this.jogadoresNaPosicao += "," + id;
        }

    }

    public void retiraJogadorAPosicao(int id) {
        String[] jogadoresSeparados = this.jogadoresNaPosicao.split(",");
        String jogadoresNaPosicaoNovo = "";

        for(int i = 0 ; i < jogadoresSeparados.length ; i++) {

            if(!jogadoresSeparados[i].equals(String.valueOf(id))) {
                if(i == 0) {
                    jogadoresNaPosicaoNovo += jogadoresSeparados[i] + ",";
                }
                else if(i == jogadoresSeparados.length-1){
                    jogadoresNaPosicaoNovo +=  jogadoresSeparados[i] + "";
                }
                else {
                    jogadoresNaPosicaoNovo += jogadoresSeparados[i] + ",";
                }
            }

        }
        //QUE ALDRABAGEM -> culpa do Bruno (mas resulta)
        if(jogadoresNaPosicaoNovo.length() == 2) {
            jogadoresNaPosicaoNovo = jogadoresNaPosicaoNovo.replaceFirst(".$", "");
        }
        this.jogadoresNaPosicao = jogadoresNaPosicaoNovo;
    }

}
