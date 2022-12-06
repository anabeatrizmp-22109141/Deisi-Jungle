package pt.ulusofona.lp2.deisiJungle;

public class Elefante extends Especie{
    public Elefante() {
        this.id = 'E';
        this.nome = "Elefante";
        this.ficheiro = "elephant.png";
        this.energiaInicial = 180;
        this.consumoEnergia = 4;
        this.energiaEmDescanso = 10;
        this.velocidade = "1..6";
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
