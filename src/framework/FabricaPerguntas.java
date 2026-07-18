package framework;
import java.util.ArrayList;

public class FabricaPerguntas {

    public static Pergunta criarMultiplaEscolha(String enunciado, ArrayList<String> alternativas, String respostaCorreta) {
        if (alternativas.size() < 2) {
            throw new IllegalArgumentException("Uma pergunta precisa de pelo menos 2 alternativas.");
        }
        if (!alternativas.contains(respostaCorreta)) {
            throw new IllegalArgumentException("A resposta correta deve estar entre as alternativas.");
        }

        return new PerguntaMultiplaEscolha(enunciado, alternativas, respostaCorreta);
    }
}