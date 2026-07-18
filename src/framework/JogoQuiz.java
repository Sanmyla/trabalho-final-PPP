package framework;
import java.util.ArrayList;
public abstract class JogoQuiz {
    private ArrayList<Pergunta>perguntas;
    private GerenciadorPontuacao pontuacao;
    private int acertos=0;
    private int erros=0;

    public JogoQuiz(ArrayList<Pergunta>perguntas,EstrategiaPontuacao estrategia ){
        this.perguntas = perguntas;
        this.pontuacao = new GerenciadorPontuacao(estrategia);
    }
    public final void iniciar (){
        for(Pergunta pergunta : perguntas){
            exibirPergunta(pergunta);
            String respostaUsuario = capturarResposta();
            boolean acertou = pergunta.isCorreta(respostaUsuario);
            if(acertou)acertos++;
            else erros++;
            pontuacao.registrarResposta(acertou,0);
        }
        exibirResultadoFinal(acertos,erros,pontuacao.getPontuacaoTotal());
    }

    //partes da interface grafica
    protected abstract void exibirPergunta(Pergunta pergunta);

    protected abstract String capturarResposta();

    protected abstract void exibirResultadoFinal(int totalAcertos, int totalErros, int pontuacaoFinal);
}
