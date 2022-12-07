package pt.ulusofona.lp2.deisiJungle.comida;

abstract public class Alimento {
    protected char id;
    protected String nome;
    protected String imagem;
    protected int nrJogadas = 0;

    public Alimento() {
    }

    public int getNrJogadas() {
        return nrJogadas;
    }

    public char getId() {
        return this.id;
    }

    public String getImagem() {
        return this.imagem;
    }

    abstract String getTipo();

    abstract String getDescricaoTooltip();

    public String[] getInfo() {
        String[] info = new String[3];

        info[0] = id + "";
        info[1] = nome;
        info[2] = imagem;

        return info;
    }
}
