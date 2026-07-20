package framework;
import java.util.ArrayList;

public abstract class JogoQuiz {
    private ArrayList<Pergunta> perguntas;
    private GerenciadorPontuacao pontuacao;
    private QuizMultiplayer mediadorMultiplayer; // <-- Adicionado
    private int acertos = 0;
    private int erros = 0;

    public JogoQuiz(ArrayList<Pergunta> perguntas, EstrategiaPontuacao estrategia) {
        this.perguntas = perguntas;
        this.pontuacao = new GerenciadorPontuacao(estrategia);
        this.mediadorMultiplayer = new QuizMultiplayer(); // <-- Inicializa o subject
    }

    public QuizMultiplayer getMultiplayerSystem() {
        return this.mediadorMultiplayer;
    }

    public final void iniciar() {
        for (Pergunta pergunta : perguntas) {
            mediadorMultiplayer.dispararPerguntaParaTodos(pergunta);

            exibirPergunta(pergunta);
            String respostaUsuario = capturarResposta();
            boolean acertou = pergunta.isCorreta(respostaUsuario);

            if (acertou) acertos++;
            else erros++;
            mediadorMultiplayer.notificarMudancaDePlacar(getNomeDoJogador(), pontuacao.getPontuacaoTotal());            pontuacao.registrarResposta(acertou, 0);
        }
        exibirResultadoFinal(acertos, erros, pontuacao.getPontuacaoTotal());
    }

    protected abstract String getNomeDoJogador();
    protected abstract void exibirPergunta(Pergunta pergunta);
    protected abstract String capturarResposta();
    protected abstract void exibirResultadoFinal(int totalAcertos, int totalErros, int pontuacaoFinal);
}