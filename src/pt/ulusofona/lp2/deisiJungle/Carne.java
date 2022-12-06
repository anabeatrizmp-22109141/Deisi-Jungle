package pt.ulusofona.lp2.deisiJungle;

public class Carne extends Alimento{

    public Carne() {
        this.id = 'c';
        this.nome = "Carne";
        this.imagem = "meat.png";
    }

    @Override
    String getTipo() {
        return nome;
    }

    @Override
    String getDescricaoTooltip() {
        if(nrJogadas <= 12) {
            return "Carne : +- 50 energia : " + nrJogadas + " jogadas";
        }
        else {
            return "Carne toxica";
        }
    }

}
