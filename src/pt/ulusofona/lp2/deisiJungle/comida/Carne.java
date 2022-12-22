package pt.ulusofona.lp2.deisiJungle.comida;

public class Carne extends Alimento {
    private int nrJogadas = 0;

    public Carne() {
        this.id = "c";
        this.nome = "Carne";
        this.imagem = "meat.png";
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


    public void aumentaNrJogadas(){
        nrJogadas++;
    }
}
