package aplicacao2;

import framework.JogadorObserver;
import framework.JogoQuiz;
import framework.Pergunta;
import framework.EstrategiaPontuacao;
import java.util.Scanner;
import java.util.ArrayList;

public class JogoQuizConsole extends JogoQuiz implements JogadorObserver {
    private Scanner scanner;
    private Pergunta perguntaAtual;
    private String nomeDoJogador;

    public JogoQuizConsole(ArrayList<Pergunta> perguntas, EstrategiaPontuacao estrategia,String nomeDoJogador) {
        super(perguntas, estrategia);
        this.scanner = new Scanner(System.in);
        this.nomeDoJogador = nomeDoJogador;
        this.getMultiplayerSystem().adicionarJogador(this);
    }
    @Override
    public void receberNovaPergunta(Pergunta pergunta) {
        System.out.println("\n📢 [NOTIFICAÇÃO OBSERVER] Preparando rodada para a pergunta contendo: " + pergunta.getAlternativas().size() + " alternativas.");
    }

    @Override
    public void atualizarPlacar(String nomeJogador, int novaPontuacao) {
        System.out.println("📢 [NOTIFICAÇÃO OBSERVER] " + nomeDoJogador + " agora possui " + novaPontuacao + " pontos!");
    }

    @Override
    protected String getNomeDoJogador() {
        return this.nomeDoJogador;
    }

    @Override
    protected void exibirPergunta(Pergunta pergunta) {
        this.perguntaAtual = pergunta;
        System.out.println("\n==================================================");
        System.out.println(pergunta.getenunciado());
        System.out.println("==================================================");
        
        int i = 1;
        for (String alternativa : pergunta.getAlternativas()) {
            System.out.println("[" + i + "] " + alternativa);
            i++;
        }
        
        System.out.println("\nDigite o número da sua resposta abaixo:");
        System.out.print("> ");
    }

    @Override
    protected String capturarResposta() {
        int escolhaUsuario = -1;
        int totalAlternativas = perguntaAtual.getAlternativas().size();
        
        while (escolhaUsuario < 1 || escolhaUsuario > totalAlternativas) {
            try {
                String entrada = scanner.nextLine();
                escolhaUsuario = Integer.parseInt(entrada);
                
                if (escolhaUsuario < 1 || escolhaUsuario > totalAlternativas) {
                    System.out.print("Opção inválida! Digite um número entre 1 e " + totalAlternativas + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Por favor, digite apenas o número da opção (ex: 1): ");
            }
        }
        
        String respostaEscolhida = perguntaAtual.getAlternativas().get(escolhaUsuario - 1);
        boolean acertou = perguntaAtual.isCorreta(respostaEscolhida);
        
        System.out.println("\n--------------------------------------------------");
        if (acertou) {
            System.out.println("🟢 RESPOSTA CORRETA! Muito bem.");
        } else {
            String gabarito = "Desconhecido";
            if (perguntaAtual instanceof PerguntaFiel) {
                gabarito = ((PerguntaFiel) perguntaAtual).getRespostaCorreta();
            }
            System.out.println("🔴 RESPOSTA INCORRETA!");
            System.out.println("👉 A resposta certa era: " + gabarito);
        }
        System.out.println("--------------------------------------------------");
        
        return respostaEscolhida;
    }

    @Override
    protected void exibirResultadoFinal(int totalAcertos, int totalErros, int pontuacaoFinal) {
        System.out.println("\n==================================================");
        System.out.println("🏆 RESULTADO FINAL 🏆");
        System.out.println("==================================================");
        System.out.println("Quantidade de acertos: " + totalAcertos);
        System.out.println("Quantidade de erros: " + totalErros);
        System.out.println("Pontuação final: " + pontuacaoFinal);
        System.out.println("==================================================");
    }
}