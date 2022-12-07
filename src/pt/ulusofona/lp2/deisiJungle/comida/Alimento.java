package pt.ulusofona.lp2.deisiJungle.comida;

abstract public class Alimento {
    protected String id;
    protected String nome;
    protected String imagem;
    protected int nrJogadas = 0;

    public Alimento() {
    }

    public int getNrJogadas() {
        return nrJogadas;
    }

    public String getId() {
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getImagem() {
        return this.imagem;
    }

    abstract String getDescricaoTooltip();

    public String[] getInfo() {
        String[] info = new String[3];

        info[0] = id + "";
        info[1] = nome;
        info[2] = imagem;

        return info;
    }

    public void setnrJogadas(int valor){};
}
