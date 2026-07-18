package framework;
import java.util.ArrayList;


public interface Pergunta {
    String getenunciado();
    ArrayList<String>getAlternativas();
    boolean isCorreta(String respostaFornecida);
}
