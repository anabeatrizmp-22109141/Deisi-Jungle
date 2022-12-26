package pt.ulusofona.lp2.deisiJungle.comida;

public class Chocolate extends Alimento {
    public Chocolate() {
        this.id = "h";
        this.nome = "Chocolate";
        this.imagem = "chocolate.png";
    }

    @Override
    public String getDescricaoTooltip() {
        return "Chocolate : + 80| -100% energia";
    }
}
