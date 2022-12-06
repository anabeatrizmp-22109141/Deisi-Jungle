package pt.ulusofona.lp2.deisiJungle;

public class Passaro extends Especie{

    public Passaro() {
        this.id = 'P';
        this.nome = "Passaro";
        this.ficheiro = "bird.png";
        this.energiaInicial = 70;
        this.consumoEnergia = 4;
        this.energiaEmDescanso = 50;
        this.velocidade = "5..6";
    }

    @Override
    boolean eHerbivoro() {
        return true;
    }

    @Override
    boolean eCarnivoro() {
        return true;
    }
}
