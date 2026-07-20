package aplicacao1;

import framework.FabricaPerguntas;
import framework.Pergunta;
import framework.EstrategiaPontuacao;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.UIManager;

public class MainAplicacao1 {
    public static void main(String[] args) {
        // Configurando o Look and Feel para o padrão do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // 1. Criando as perguntas de Programação usando a Fábrica
        ArrayList<Pergunta> perguntas = new ArrayList<>();

        perguntas.add(FabricaPerguntas.criarMultiplaEscolha(
                "Qual das seguintes opções NÃO é uma palavra-chave em Java?",
                new ArrayList<>(Arrays.asList("class", "string", "volatile", "transient")),
                "string"
        ));

        perguntas.add(FabricaPerguntas.criarMultiplaEscolha(
                "Qual padrão de projeto garante que uma classe tenha apenas uma instância?",
                new ArrayList<>(Arrays.asList("Factory", "Observer", "Singleton", "Strategy")),
                "Singleton"
        ));

        perguntas.add(FabricaPerguntas.criarMultiplaEscolha(
                "No paradigma Orientado a Objetos, o ato de ocultar detalhes internos de um objeto é chamado de:",
                new ArrayList<>(Arrays.asList("Polimorfismo", "Herança", "Encapsulamento", "Abstração")),
                "Encapsulamento"
        ));

        // 2. Definindo a estratégia de pontuação da aplicação cliente
        EstrategiaPontuacao estrategia = new EstrategiaProgramacao();

        // 3. Criando e iniciando o Quiz em Swing
        JogoQuizSwing quiz = new JogoQuizSwing(perguntas, estrategia);
        quiz.iniciar();
    }
}