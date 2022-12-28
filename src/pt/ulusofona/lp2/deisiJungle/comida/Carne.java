package pt.ulusofona.lp2.deisiJungle.comida;

public class Carne extends Alimento {

    public Carne() {
        this.id = "c";
        this.nome = "Carne";
        this.imagem = "meat.png";
    }

    public Carne(int nrJogadasCarne) {
        this.id = "c";
        this.nome = "Carne";
        this.imagem = "meat.png";
        this.nrJogadasCarne = nrJogadasCarne;
    }
    @Override
    public String getDescricaoTooltip() {
        if(nrJogadasCarne <= 12) {
            return "Carne : + 50 energia : " + nrJogadasCarne + " jogadas";
        }
        else {
            return "Carne toxica";
        }
    }

    @Override
    public String getAlimentoInfoSaveLoad() {
        return super.getAlimentoInfoSaveLoad() + ";" + nrJogadasCarne;
    }
}
