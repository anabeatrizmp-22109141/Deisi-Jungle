package pt.ulusofona.lp2.deisiJungle.comida;

public class Banana extends Alimento {
    private int nrBananas;

    public Banana() {
        this.id = "b";
        this.nome = "Cacho de bananas";
        this.imagem = "bananas.png";
        this.nrBananas = 3;
    }

    @Override
    public String getDescricaoTooltip() {
        return "Bananas : " + this.nrBananas + " : +40 energia";
    }

    public boolean diminuiBanana() {
        if (this.nrBananas != 0) {
            this.nrBananas--;
            return true;
        }
        return false;
    }

    public boolean temBananas() {
        return nrBananas > 0;
    }

    public int getNrBananas(){
        return this.nrBananas;
    }
}
