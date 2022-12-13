package pt.ulusofona.lp2.deisiJungle.especie;

public class Passaro extends Especie{

    public Passaro() {
        this.id = "P";
        this.nome = "Passaro";
        this.ficheiro = "bird.png";
        this.energiaInicial = 70;
        this.consumoEnergia = 4;
        this.energiaEmDescanso = 50;
        this.velocidade = "5..6";
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
