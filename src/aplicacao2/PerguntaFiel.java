package aplicacao2;

import framework.Pergunta;
import java.util.ArrayList;

class PerguntaFiel implements Pergunta {
    private String enunciado;
    private ArrayList<String> alternativas;
    private String respostaCorreta;

    public PerguntaFiel(String enunciado, ArrayList<String> alternativas, String respostaCorreta) {
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
    }

    @Override
    public String getenunciado() {
        return this.enunciado;
    }

    @Override
    public ArrayList<String> getAlternativas() {
        return this.alternativas;
    }

    @Override
    public boolean isCorreta(String respostaFornecida) {
        return this.respostaCorreta.equalsIgnoreCase(respostaFornecida.trim());
    }

    public String getRespostaCorreta() {
        return this.respostaCorreta;
    }
}