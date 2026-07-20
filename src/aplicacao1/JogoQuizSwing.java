package aplicacao1;

import framework.JogadorObserver;
import framework.JogoQuiz;
import framework.Pergunta;
import framework.EstrategiaPontuacao;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JogoQuizSwing extends JogoQuiz implements JogadorObserver {
    private Pergunta perguntaAtual;

    public JogoQuizSwing(ArrayList<Pergunta> perguntas, EstrategiaPontuacao estrategia) {
        super(perguntas, estrategia);
        this.getMultiplayerSystem().adicionarJogador(this);
    }

    @Override
    protected String getNomeDoJogador() {
        return "Jogador Swing";
    }

    @Override
    public void receberNovaPergunta(Pergunta pergunta) {
        System.out.println("[Observer UI] Nova pergunta enviada para a tela: " + pergunta.getenunciado());
    }

    @Override
    public void atualizarPlacar(String nomeJogador, int novaPontuacao) {
        JOptionPane.showMessageDialog(
                null,
                "Placar Atualizado!\n" + nomeJogador + " está com " + novaPontuacao + " pontos.",
                "Notificação do Observer",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    @Override
    protected void exibirPergunta(Pergunta pergunta) {
        // Guarda a pergunta atual para ser renderizada no diálogo de captura
        this.perguntaAtual = pergunta;
    }

    @Override
    protected String capturarResposta() {
        // Cria uma caixa de diálogo modal (bloqueia a execução até ser fechada)
        JDialog dialog = new JDialog((Frame) null, "Quiz de Programação - Pergunta", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Enunciado da Pergunta
        // Nota: O método no framework possui uma variação de grafia (getenunciado / getEnunciado)
        // Ajuste aqui conforme a assinatura exata da sua interface Pergunta
        JLabel lblEnunciado = new JLabel("<html><body><h3>" + perguntaAtual.getenunciado() + "</h3></body></html>");
        lblEnunciado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dialog.add(lblEnunciado, BorderLayout.NORTH);

        // Alternativas (Radio Buttons)
        JPanel panelAlternativas = new JPanel();
        panelAlternativas.setLayout(new BoxLayout(panelAlternativas, BoxLayout.Y_AXIS));
        panelAlternativas.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        ButtonGroup grupoOpcoes = new ButtonGroup();
        ArrayList<JRadioButton> radioButtons = new ArrayList<>();

        for (String alternativa : perguntaAtual.getAlternativas()) {
            JRadioButton rb = new JRadioButton(alternativa);
            rb.setFont(new Font("Arial", Font.PLAIN, 14));
            grupoOpcoes.add(rb);
            panelAlternativas.add(rb);
            radioButtons.add(rb);
        }

        // Seleciona a primeira por padrão para evitar respostas nulas
        if (!radioButtons.isEmpty()) {
            radioButtons.get(0).setSelected(true);
        }

        JScrollPane scrollPane = new JScrollPane(panelAlternativas);
        scrollPane.setBorder(null);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Botão de Confirmação
        JButton btnConfirmar = new JButton("Confirmar Resposta");
        final String[] resposta = { "" };

        btnConfirmar.addActionListener(e -> {
            for (JRadioButton rb : radioButtons) {
                if (rb.isSelected()) {
                    resposta[0] = rb.getText();
                    break;
                }
            }
            dialog.dispose(); // Fecha o diálogo e desbloqueia o método
        });

        JPanel panelBotao = new JPanel();
        panelBotao.add(btnConfirmar);
        dialog.add(panelBotao, BorderLayout.SOUTH);

        // Exibe o diálogo (trava a execução aqui até o dispose())
        dialog.setVisible(true);

        return resposta[0];
    }

    @Override
    protected void exibirResultadoFinal(int totalAcertos, int totalErros, int pontuacaoFinal) {
        String mensagem = String.format(
                "Fim do Quiz!\n\n" +
                        "Total de Acertos: %d\n" +
                        "Total de Erros: %d\n" +
                        "Pontuação Final: %d pontos",
                totalAcertos, totalErros, pontuacaoFinal
        );

        JOptionPane.showMessageDialog(null, mensagem, "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }
}