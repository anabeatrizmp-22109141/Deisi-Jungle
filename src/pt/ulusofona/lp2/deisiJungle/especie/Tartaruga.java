package pt.ulusofona.lp2.deisiJungle.especie;

public class Tartaruga extends Especie{

    public Tartaruga() {
        this.id = "T";
        this.nome = "Tartaruga";
        this.ficheiro = "turtle.png";
        this.energiaInicial = 150;
        this.consumoEnergia = 1;
        this.energiaEmDescanso = 5;
        this.velocidade = "1..3";
    }

    @Override
    public boolean eHerbivoro() {
        return false;
    }

    @Override
    public boolean eCarnivoro() {
        return false;
    }

    @Override
    public boolean eOmnivoro() {
        return true;
    }
}
