package pt.ulusofona.lp2.deisiJungle.especie;

public class Leao extends Especie {
    public Leao() {
        this.id = "L";
        this.nome = "Leao";
        this.ficheiro = "lion.png";
        this.energiaInicial = 80;
        this.consumoEnergia = 2;
        this.energiaEmDescanso = 10;
        this.velocidade = "4..6";
    }

    @Override
    public boolean eHerbivoro() {
        return false;
    }

    @Override
    public boolean eCarnivoro() {
        return true;
    }

    @Override
    public boolean eOmnivoro() {
        return false;
    }
}
