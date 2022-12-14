package pt.ulusofona.lp2.deisiJungle.especie;

public class Elefante extends Especie{

    public Elefante() {
        this.id = "E";
        this.nome = "Elefante";
        this.ficheiro = "elephant.png";
        this.energiaInicial = 180;
        this.consumoEnergia = 4;
        this.energiaEmDescanso = 10;
        this.velocidade = "1..6";
    }

    @Override
    public boolean eHerbivoro() {
        return true;
    }

    @Override
    public boolean eCarnivoro() {
        return false;
    }

    @Override
    public boolean eOmnivoro() {
        return false;
    }


}
