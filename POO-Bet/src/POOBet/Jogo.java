package POOBet;

/**
 * A classe Jogo é responsável por armazenar os palpites realizados pelo usuário.
 * Ela encapsula os atributos e métodos necessários para gerenciar os
 * palpites dados pelo usuário.
 */
public class Jogo {

    private int idJogo;
    private String corApostada;
    private int qtdFichas;
    private static int proximoIdJogo = 1;

    /**
     * Construtor da classe Jogo.
     * Cria uma instância de Jogo com uma cor apostada e a quantidade de fichas apostadas.
     *
     * @param corApostada A cor na qual o jogador está apostando.
     * @param qtdFichasApostadas A quantidade de fichas apostadas.
     */
    public Jogo(String corApostada, int qtdFichasApostadas) {
        this.idJogo = proximoIdJogo;
        proximoIdJogo++;
        this.corApostada = corApostada;
        this.qtdFichas = qtdFichasApostadas;
    }
    /**
     * Calcula o prêmio com base na cor apostada e na quantidade de fichas apostadas.
     *
     * @return O valor do prêmio calculado.
     */
    public int calcularPremio() {
        if (this.corApostada.equals("Verde")) {
            return this.qtdFichas * 14;
        }
        return this.qtdFichas * 2;
    }

}
