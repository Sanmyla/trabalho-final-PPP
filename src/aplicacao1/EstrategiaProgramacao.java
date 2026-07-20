package aplicacao1;
import framework.EstrategiaPontuacao;

public class EstrategiaProgramacao implements EstrategiaPontuacao {
    @Override
    public int calcularPontos(boolean acertou, int tempoResposta) {
        // +10 pontos por acerto, penalização de -2 por erro
        return acertou ? 10 : -2;
    }
}