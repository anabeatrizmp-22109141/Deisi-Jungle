package pt.ulusofona.lp2.deisiJungle.especie;

public class Tarzan extends Especie{
    public Tarzan(){
        this.id = "Z";
        this.nome = "Tarzan";
        this.ficheiro = "tarzan.png";
        this.energiaInicial = 70;
        this.consumoEnergia = 2;
        this.energiaEmDescanso = 20;
        this.velocidade = "1..6";
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
