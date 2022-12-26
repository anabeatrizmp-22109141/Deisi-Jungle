package pt.ulusofona.lp2.deisiJungle.especie;

public class Sapo extends Especie{

    public Sapo() {
        this.id = "S";
        this.nome = "Sapo";
        this.ficheiro = "frog.png";
        this.energiaInicial = 65;
        this.consumoEnergia = 5;
        this.energiaEmDescanso = 60;
        this.velocidade = "4..4";
    }

    @Override
    public boolean eOmnivoro() {
        return false;
    }

    @Override
    public boolean eHerbivoro() {
        return false;
    }

    @Override
    public boolean eCarnivoro() {
        return true;
    }
}
