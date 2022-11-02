package pt.ulusofona.lp2.deisiJungle;

public class Especie {
    int id;
    String nome;
    String imagem;

    public Especie(int id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public boolean verificaEspecie(int id, String nome){
        return true;
    }
}
