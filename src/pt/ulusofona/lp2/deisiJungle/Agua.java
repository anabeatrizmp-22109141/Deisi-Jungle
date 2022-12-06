package pt.ulusofona.lp2.deisiJungle;

public class Agua extends Alimento{
    public Agua() {
        this.id = 'a';
        this.nome = "Agua";
        this.imagem = "water.png";
    }

    @Override
    String getTipo() {
        return nome;
    }

    @Override
    String getDescricaoTooltip() {
        return "Agua : + 10U|20% energia";
    }
}
