package framework;
import java.util.ArrayList;

public class PerguntaMultiplaEscolha implements Pergunta{
    private String enunciado;
    private ArrayList<String> alternativas;
    private String alternativaCorreta;

    protected PerguntaMultiplaEscolha(String enunciado, ArrayList<String> alternativas, String alternativaCorreta) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.alternativaCorreta = alternativaCorreta;
    }

    @Override
    public String getenunciado() {
        return enunciado;
    }

    @Override
    public ArrayList<String> getAlternativas() {
        return alternativas;
    }

    @Override
    public boolean isCorreta(String respostaFornecida) {
        return this.alternativaCorreta.equalsIgnoreCase(respostaFornecida.trim());
    }
}