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
        if(nrJogadas <= 12) {
            return "Carne : + 50 energia : " + nrJogadas + " jogadas";
        }
        else {
            return "Carne toxica";
        }
    }


    public void aumentaNrJogadas(){
        nrJogadas++;
    }
}
