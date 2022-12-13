package pt.ulusofona.lp2.deisiJungle.especie;

abstract public class Especie {
    protected String id;
    protected String nome;
    protected String ficheiro;
    protected int energiaInicial;
    protected int consumoEnergia;
    protected int energiaEmDescanso;
    protected String velocidade;

    public Especie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String valor){ //para fins de teste
        id = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getFicheiro() {
        return ficheiro;
    }

    public int getEnergiaInicial() {
        return energiaInicial;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getEnergiaEmDescanso() {
        return energiaEmDescanso;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public abstract boolean eHerbivoro();

    public abstract boolean eCarnivoro();

    public abstract boolean eOmnivoro();

    public String[] getInfo() {
        String[] info = new String[7];
        info[0] = id + "";
        info[1] = nome;
        info[2] = ficheiro;
        info[3] = energiaInicial + "";
        info[4] = consumoEnergia + "";
        info[5] = energiaEmDescanso + "";
        info[6] = velocidade;

        return info;
    }
}
