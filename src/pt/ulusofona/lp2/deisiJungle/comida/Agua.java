package pt.ulusofona.lp2.deisiJungle.comida;

import pt.ulusofona.lp2.deisiJungle.comida.Alimento;

public class Agua extends Alimento {
    public Agua() {
        this.id = "a";
        this.nome = "Agua";
        this.imagem = "water.png";
    }

    @Override
    public String getDescricaoTooltip() {
        return "Agua : + 15U|20% energia";
    }
}
