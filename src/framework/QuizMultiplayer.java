package framework;

import java.util.ArrayList;
import java.util.List;

public class QuizMultiplayer {

    // Lista de observadores inscritos
    private List<JogadorObserver> jogadoresInscritos = new ArrayList<>();

    public void adicionarJogador(JogadorObserver jogador) {
        jogadoresInscritos.add(jogador);
    }

    public void removerJogador(JogadorObserver jogador) {
        jogadoresInscritos.remove(jogador);
    }

    public void dispararPerguntaParaTodos(Pergunta pergunta) {
        for (JogadorObserver jogador : jogadoresInscritos) {
            jogador.receberNovaPergunta(pergunta);
        }
    }

    public void notificarMudancaDePlacar(String nomeJogador, int pontos) {
        for (JogadorObserver jogador : jogadoresInscritos) {
            jogador.atualizarPlacar(nomeJogador, pontos);
        }
    }
}