package pt.ulusofona.lp2.deisiJungle.comida;

import pt.ulusofona.lp2.deisiJungle.comida.Alimento;

public class Erva extends Alimento {

    public Erva() {
        this.id = 'e';
        this.nome = "Erva";
        this.imagem = "grass.png";
    }

    @Override
    String getTipo() {
        return nome;
    }

    @Override
    String getDescricaoTooltip() {
        return  "Erva : +- 20 energia";
    }
}
