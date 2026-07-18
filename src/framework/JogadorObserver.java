package framework;

public interface JogadorObserver {
    void receberNovaPergunta(Pergunta pergunta);
    void atualizarPlacar(String nomeJogador, int novaPontuacao);
}