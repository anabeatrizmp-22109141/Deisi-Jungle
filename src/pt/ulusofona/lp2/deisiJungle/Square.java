package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.comida.Alimento;

import java.util.Arrays;
import java.util.Objects;

public class Square {
    int nrSquare;
    String imagem;
    String descricao;
    String jogadoresNaPosicao;
    Alimento alimento;

    public Square(int nrSquare, String imagem ,String descricao, String jogadoresNaPosicao) {
        this.nrSquare = nrSquare;
        this.imagem = imagem;
        this.descricao = descricao;
        this.jogadoresNaPosicao = jogadoresNaPosicao;
    }

    public void colocaAlimentoNaCasa(Alimento alimento) {
        this.alimento = alimento;
        this.descricao = alimento.getDescricaoTooltip();
        this.imagem = alimento.getImagem();
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

    public Alimento getAlimento() {
        return this.alimento;
    }

    public void adicionaJogadorAPosicao(int id) {
        if(Objects.equals(this.jogadoresNaPosicao, "")) {
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
        if(jogadoresNaPosicaoNovo.length() == 2){
            jogadoresNaPosicaoNovo = jogadoresNaPosicaoNovo.replaceFirst(".$","");
        }
        //QUE ALDRABAGEM -> culpa do Bruno (mas resulta)
        if(jogadoresNaPosicaoNovo.length() == 2) {
            jogadoresNaPosicaoNovo = jogadoresNaPosicaoNovo.replaceFirst(".$", "");
        }
        this.jogadoresNaPosicao = jogadoresNaPosicaoNovo;
    }

    public int[] getJogadoresNaPosicaoPorOrdem() {

        String[] jogadoresSeparados = this.jogadoresNaPosicao.split(",");
        int[] jogadoresOrdenados = new int[jogadoresSeparados.length];
        for(int i = 0; i < jogadoresSeparados.length ; i++) {
            jogadoresOrdenados[i] = Integer.parseInt(jogadoresSeparados[i]);
        }
        Arrays.sort(jogadoresOrdenados);
        return jogadoresOrdenados;
    }

}
