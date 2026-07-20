package aplicacao2;

import framework.Pergunta;
import framework.EstrategiaPontuacao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainAplicacao2 {

    public static void main(String[] args) {

        Scanner entradaInicial = new Scanner(System.in);
        ArrayList<Pergunta> perguntasVolei = new ArrayList<>();

        // 1. Pergunta sobre o total de títulos
        perguntasVolei.add(new PerguntaFiel(
            "Com a conquista da temporada 2025/2026, quantas vezes o Dentil Praia Clube se sagrou campeão da Superliga Feminina?",
            new ArrayList<>(Arrays.asList("1 vez", "2 vezes", "3 vezes", "4 vezes")),
            "3 vezes"
        ));

        // 2. Pergunta sobre as estrangeiras da temporada
        perguntasVolei.add(new PerguntaFiel(
            "Quais foram as três jogadoras estrangeiras que integraram o elenco do Praia Clube na vitoriosa temporada 25/26?",
            new ArrayList<>(Arrays.asList(
                "Kuznetsova, Caffrey e Nia Reed",
                "Caffrey, Fingall e Maria Koleva", 
                "Fingall, Martinez e Bown", 
                "Kuznetsova, Klineman e Fawcett"
            )),
            "Caffrey, Fingall e Maria Koleva"
        ));

        // 3. Pergunta sobre a MVP da temporada
        perguntasVolei.add(new PerguntaFiel(
            "Quem foi eleita a MVP (Jogadora Mais Valiosa) de toda a Superliga Feminina da temporada 25/26 coroando a campanha do Praia Clube?",
            new ArrayList<>(Arrays.asList("Carol Gattaz", "Macris", "Adenízia", "Natinha")),
            "Adenízia"
        ));

        // 4. Pergunta sobre a melhor da partida da final (Michelle)
        perguntasVolei.add(new PerguntaFiel(
            "Quem brilhou na grande decisão contra o Minas, fechou o jogo do título e levou o Troféu VivaVôlei de melhor da partida na final da Superliga 25/26?",
            new ArrayList<>(Arrays.asList("Adenízia", "Michelle", "Payton Caffrey", "Macris")),
            "Michelle"
        ));

        EstrategiaPontuacao estrategia = new EstrategiaPontuacao() {
            @Override
            public int calcularPontos(boolean acertou, int tempoResposta) {
                if (acertou) return 10;
                else return -5;
            }
        };

        System.out.println("==================================================");
        System.out.println("💛🖤 BEM-VINDO AO QUIZ EXCLUSIVO DO DENTIL PRAIA CLUBE 🖤💛");
        System.out.println("==================================================");
        System.out.println("Prepare o seu bloqueio e vamos começar...\n");
        System.out.println("Antes de começarmos, digite o seu nome de jogador:");
        System.out.print("> ");
        String nomeJogador = entradaInicial.nextLine();

        if (nomeJogador.trim().isEmpty()) {
            nomeJogador = "Jogador Convidado"; // Fallback caso aperte enter sem digitar nada
        }

        // Passa o nome capturado dinamicamente para o jogo
        JogoQuizConsole jogo = new JogoQuizConsole(perguntasVolei, estrategia, nomeJogador);

        System.out.println("\nPrepare o seu bloqueio, " + nomeJogador + ", e vamos começar...\n");
        jogo.iniciar();
    }
}

