package framework;

public class GerenciadorPontuacao {
    private int pontuacaoTotal;
    private EstrategiaPontuacao estrategia;

    public GerenciadorPontuacao(EstrategiaPontuacao estrategia) {
        this.estrategia = estrategia;
        this.pontuacaoTotal = 0;
    }

    public void registrarResposta(boolean acertou, int tempoResposta) {
        int pontosGanhos = estrategia.calcularPontos(acertou, tempoResposta);
        this.pontuacaoTotal += pontosGanhos;
        //para a pontuacao nao ficar negativa
        if(this.pontuacaoTotal <= 0){
            this.pontuacaoTotal = 0;
        }
    }
    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }
}
