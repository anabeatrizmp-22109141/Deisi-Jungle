package pt.ulusofona.lp2.deisiJungle;

public class Banana extends Alimento{
    private int nrBananas;

    public Banana() {
        this.id = 'b';
        this.nome = "Cacho de bananas";
        this.imagem = "bananas.png";
        this.nrBananas = 3;
    }

    @Override
    String getTipo(){
        return "Banana";
    }

    //É preciso <> ??
    @Override
    String getDescricaoTooltip() {
        return "Bananas : <" + this.nrBananas + "> : + 40 energia";
    }

    public boolean diminuiBanana() {
        if (this.nrBananas != 0) {
            this.nrBananas--;
            return true;
        }
        return false;
    }
}
