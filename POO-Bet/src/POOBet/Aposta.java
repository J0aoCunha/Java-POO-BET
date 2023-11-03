package POOBet;

import java.util.*;

/**
 * A classe Aposta é responsável por armazenar
 * a mesa de apostas que o jogador criou. Ela
 * encapsula os métodos responsáveis por realizar
 * o sorteio, verificar qual dos palpites (Jogo)
 * ganharam, premiar o jogador, entre outros.
 */
public class Aposta {

    private int idAposta;
    private int premioTotal;
    private HashMap<String, Jogo> listaJogos;

    private String corSorteada;
    private static int proximoIdAposta = 1;
    private static int[] numerosVermelhos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    private static int[] numerosPretos = { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
    private static int[] roleta = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};

    /**
     * Método construtor da classe Aposta, que instancia
     * seus principais valores e faz a criação dos jogos
     * que compõem a aposta.
     * @param jogador o usuário que está realizando a ação.
     */
    public Aposta (Jogador jogador) {

        // define o ID da Aposta
        this.idAposta = proximoIdAposta;
        proximoIdAposta++;

        // inicializa a lista de jogos associada a aposta.
        this.listaJogos = new HashMap<String, Jogo>();

        // menu de opções para o jogador criar palpites (Jogo).
        int opcaoSelecionada = 0;
        
        do {
            // se o jogador já tiver o primeiro palpite incluído, perguntar se deseja adicionar mais.
            if (this.listaJogos.size() > 0) {
                String[] opcoes = {"Sim", "Não"};
                opcaoSelecionada = InOut.leOpcoes("Nova Aposta", "Você deseja incluir um novo palpite?", opcoes);
                if (opcaoSelecionada == 0) {
                    criarJogo(jogador);
                }
            } else {
                criarJogo(jogador);
            }
        } while (opcaoSelecionada == 0 && jogador.getQtdFicha() > 0);

        // realizar o sorteio da aposta
        this.corSorteada = sortearCor();

        setPremioTotal();
        anunciarGanhador();
        jogador.adicionarFichas(this.premioTotal);

    }

    /**
     * Método responsável por realizar a criação de um
     * novo jogo, lendo opções do jogador.
     * @param jogador jogador responsável pela ação.
     */
    public void criarJogo(Jogador jogador) {

        // lê a cor que o jogador deseja palpitar
        String[] opcoes = {"Vermelho", "Preto", "Verde"};
        int opcaoSelecionada = InOut.leOpcoes("Criar novo jogo", "Em qual cor você deseja apostar?", opcoes);

        // define a quantidade de fichas que será apostada nessa cor.
        int qtdFichasApostadas = 0;
        boolean indisponivel;

        do {
            qtdFichasApostadas = InOut.leInt("Qual a quantidade de fichas que você deseja apostar na cor " + opcoes[opcaoSelecionada] + "?\nVocê possui " + jogador.getQtdFicha() + " fichas.");
            indisponivel = !jogador.verificarDisponibilidadeFichas(qtdFichasApostadas);
            if (indisponivel) {
                InOut.MsgDeErro("Erro", "Essa quantidade de fichas não está disponível para apostar, insira uma quantidade menor ou igual ao seu saldo.");
            }
        } while (indisponivel);

        // se a cor selecionada já tiver sido palpitada, erro.
        if (listaJogos.containsKey(opcoes[opcaoSelecionada])) {
            InOut.MsgDeErro("Erro", "Não é possível apostar na mesma cor duas vezes.");
            return;
        }

        // remove as fichas e coloca o palpite na lista de jogos da aposta.
        jogador.removerFichas(qtdFichasApostadas);
        listaJogos.put(opcoes[opcaoSelecionada], new Jogo(opcoes[opcaoSelecionada], qtdFichasApostadas));

    }

    /**
     * Método utilizado para realizar o sorteio da
     * cor baseado na roleta.
     * @return String com a cor sorteada.
     */
    public static String sortearCor () {
        // sorteia uma posição aleatória na roleta entre 0 e 36.
        Random rd = new Random();
        int posicaoSorteada = rd.nextInt(0,37);

        // avisa ao jogador que o sorteio está sendo realizado.
        InOut.MsgDeAviso("Aposta","O sorteio está acontecendo agora!");

        // retorna a cor correspondente ao número armazenado nessa posição.
        return verificarCor(roleta[posicaoSorteada]);
    }

    /**
     * Método responsável por verificar qual cor foi sorteada,
     * a partir do número sorteado na roleta.
     * @param numero
     * @return
     */
    public static String verificarCor(int numero) {
        if (Arrays.stream(numerosVermelhos).anyMatch(n -> n == numero)) {
            return "Vermelho";
        } else if (Arrays.stream(numerosPretos).anyMatch(n -> n == numero)) {
            return "Preto";
        } else {
            return "Verde";
        }
    }

    /**
     * Método responsável por verificar qual jogo (palpite)
     * da aposta foi o ganhador.
     * @return o jogo ganhador ou um valor nulo.
     */
    private Jogo verificarJogoGanhador(){
        Jogo jogo =  this.listaJogos.get(this.corSorteada);
        return jogo;
    }

    /**
     * Método responsável por anunciar para o usuário
     * o resultado da aposta, se o jogador ganhou ou perdeu
     * e seu saldo.
     */
    private void anunciarGanhador() {
        if (verificarJogoGanhador() != null) {
            InOut.MsgDeInformacao("Resultado da Aposta", "Parabéns! Você ganhou, a cor sorteada foi " + this.corSorteada + ". Seu prêmio total foi de " + this.premioTotal + " fichas.");
        } else {
            InOut.MsgDeInformacao("Resultado da Aposta", "Poxa! Não foi dessa vez, tente apostar novamente.");
        }
    }

    /**
     * Método responsável por definir qual o prêmio total
     * da Aposta, com base no jogo ganhador.
     */
    private void setPremioTotal() {
        Jogo jogoGanhador = verificarJogoGanhador();
        if (jogoGanhador != null) {
            this.premioTotal = jogoGanhador.calcularPremio();
        } else {
            this.premioTotal = 0;
        }
    }

    /**
     * Método para obter o ID da Aposta.
     * @return o ID da Aposta.
     */
    public int getIdAposta() {
        return idAposta;
    }

    /**
     * Método para obter a cor sorteada.
     * @return a cor sorteada.
     */
    public String getCorSorteada() {
        return corSorteada;
    }

    /**
     * Método para obter o prêmio total.
     * @return o prêmio total.
     */
    public int getPremioTotal() {
        return premioTotal;
    }

    /**
     * Método para obter a lista de jogos.
     * @return a lista de jogos.
     * @see HashMap
     */
    public HashMap<String, Jogo> getListaJogos() {
        return listaJogos;
    }

}
