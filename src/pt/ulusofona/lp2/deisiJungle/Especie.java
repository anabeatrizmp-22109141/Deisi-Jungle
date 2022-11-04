package pt.ulusofona.lp2.deisiJungle;

public class Especie {
    String id;
    String nome;
    String imagem;

    public Especie(String id, String nome, String imagem) {
        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getImagem() {
        return this.imagem;
    }


    public boolean verificaEspecie(int id, String nome){
        return true;
    }
}
