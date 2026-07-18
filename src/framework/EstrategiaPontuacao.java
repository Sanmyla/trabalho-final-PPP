package framework;

public interface EstrategiaPontuacao {
    /**
     * Pontuacao de cada resposta
     * @param acertou Verdadeiro se a alternativa escolhida é a correta.
     * @param tempoResposta Em segundos (útil para quizzes baseados em tempo).
     * @return A quantidade de pontos a somar (ou subtrair, se for negativo).
     */
    int calcularPontos(boolean acertou, int tempoResposta);
}