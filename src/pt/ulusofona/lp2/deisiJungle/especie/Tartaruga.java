package pt.ulusofona.lp2.deisiJungle.especie;

public class Tartaruga extends Especie{

    public Tartaruga() {
        this.id = 'T';
        this.nome = "Tartaruga";
        this.ficheiro = "turtle.png";
        this.energiaInicial = 150;
        this.consumoEnergia = 1;
        this.energiaEmDescanso = 5;
        this.velocidade = "1..3";
    }

    @Override
    boolean eHerbivoro() {
        return true;
    }

    @Override
    boolean eCarnivoro() {
        return false;
    }
}
