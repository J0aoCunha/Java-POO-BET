package POOBet;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A classe Jogador encapsula informações e funcionalidades relacionadas a um jogador no contexto de um jogo.
 * Cada jogador possui um identificador único, informações pessoais, um saldo de crédito, fichas para apostas
 * e uma lista de apostas realizadas.
 */
public class Jogador {

    private int idJogador;
    private String nome;
    private String sobrenome;
    private String apelido;
    private String cpf;
    private String nacionalidade;
    private Date dataNascimento;
    private Credito credito;
    private int qtdFicha;
    private HashMap<Integer, Aposta> listaApostas;
    private static int proximoIdJogador = 1;
    private static Locale ptBr = new Locale("pt","BR");

    /**
     * Construtor da classe Jogador. Inicializa um jogador com informações básicas.
     *
     * @param nome              O nome do jogador.
     * @param sobrenome         O sobrenome do jogador.
     * @param apelido           O apelido do jogador.
     * @param cpf               O CPF do jogador.
     * @param nacionalidade     A nacionalidade do jogador.
     * @param dataNascimento    A data de nascimento do jogador.
     */
    public Jogador (String nome, String sobrenome, String apelido, String cpf, String nacionalidade, Date dataNascimento) {
        this.idJogador = proximoIdJogador;
        proximoIdJogador++;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.apelido = apelido;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.dataNascimento = dataNascimento;
        this.credito = new Credito();
        this.qtdFicha = 0;
        this.listaApostas = new HashMap<Integer, Aposta>();
    }

    /**
     * Método principal que cria um jogador de exemplo e inicia a interface gráfica do menu.
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {

        Jogador j1 = new Jogador("Fulano", "de Tal", "Fulano", "111.111.111-11", "Brasileiro", Date.from(Instant.now()));

        MenuGUI gui = new MenuGUI(Jogador.class.getClassLoader().getResource("resources/background.jpg").getPath(), j1);

    }

    /**
     * Cria uma nova aposta para o jogador e a adiciona à lista de apostas.
     */
    public void criarAposta() {
        if (!this.verificarDisponibilidadeFichas(1)) {
            InOut.MsgDeErro("Erro","A quantidade de fichas disponível não é suficiente para realizar a aposta.");
            return;
        }

        // cria a nova aposta e coloca ela na lista de apostas.
        Aposta aposta = new Aposta(this);
        listaApostas.put(aposta.getIdAposta(), aposta);
    }

    /**
     * Consulta e exibe informações sobre as apostas do jogador em uma tabela.
     */
    public void consultarApostas() {
        String[] cabecalhosTabela = {"ID da Aposta", "Cor Sorteada", "Quantidade de Jogos", "Prêmio Total (fichas)"};
        Object[][] dadosTabela = new Object[listaApostas.size()][4];
        int linhaAtual = 0;
        for (Map.Entry<Integer, Aposta> entry : listaApostas.entrySet()) {
            Integer chave = entry.getKey();
            Aposta valor = entry.getValue();
            dadosTabela[linhaAtual][0] = chave;
            dadosTabela[linhaAtual][1] = valor.getCorSorteada();
            dadosTabela[linhaAtual][2] = valor.getListaJogos().size();
            dadosTabela[linhaAtual][3] = valor.getPremioTotal();
            linhaAtual++;
        }

        InOut.ExibeTabela("Lista de Apostas", cabecalhosTabela, dadosTabela);
    }

    /**
     * Adiciona crédito à conta do jogador.
     */
    public void adicionarCredito() {
        double saldoAdicionar = InOut.leDouble("Insira o valor que deseja depositar em sua conta.");
        credito.adicionarSaldo(saldoAdicionar);
        consultarCredito();
    }

    /**
     *  Retira crédito da conta do jogador.
     */
    public void retirarCredito() {
        double saldoRetirar = InOut.leDouble("Insira o valor que deseja retirar da sua conta");
        credito.removerSaldo(saldoRetirar);
        consultarCredito();
    }

    /**
     * Consulta e exibe o saldo de crédito do jogador.
     */
    public void consultarCredito() {
        InOut.MsgDeInformacao("Consulta de Saldo", "Seu saldo atual é de " + credito.getMoeda() + " " + String.format(ptBr,"%,.2f", credito.getSaldo()));
    }

    /**
     * Obtém a quantidade de fichas do jogador.
     * @return A quantidade de fichas do jogador.
     */
    public int getQtdFicha() {
        return this.qtdFicha;
    }

    /**
     * Verifica se o jogador tem fichas disponíveis em quantidade suficiente.
     * @param qtdFichasAVerificar A quantidade de fichas a verificar.
     * @return true se houver fichas disponíveis em quantidade suficiente, caso contrário, false.
     */
    public boolean verificarDisponibilidadeFichas(int qtdFichasAVerificar) {
        return this.qtdFicha >= qtdFichasAVerificar;
    }

    /**
     * Adiciona fichas à conta do jogador.
     * @param qtd A quantidade de fichas a adicionar.
     */
    public void adicionarFichas(int qtd) {
        this.qtdFicha+=qtd;
    }

    /**
     * Consulta e exibe a quantidade de fichas disponíveis para o jogador.
     */
    public void consultarFichas() {
        InOut.MsgDeInformacao("Consulta de Fichas", "O saldo de fichas disponível é: " + this.qtdFicha + " fichas.");
    }

    /**
     * Remove fichas da conta do jogador.
     * @param qtd A quantidade de fichas a serem removidas.
     * @return true se a remoção for bem-sucedida, caso contrário, false.
     */
    public boolean removerFichas(int qtd) {
        if (verificarDisponibilidadeFichas(qtd)) {
            this.qtdFicha -= qtd;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Converte o saldo do jogador em fichas.
     */
    public void converterSaldoParaFichas() {

        double saldoAConverter = InOut.leDouble("Escolha o valor em " + this.credito.getMoeda() + " a ser convertido para fichas.");

        if (this.credito.getSaldo() >= 50 && this.credito.verificarDisponibilidade(saldoAConverter)) {
            int fichasParaAdicionar = (int) (saldoAConverter / 50);
            this.adicionarFichas(fichasParaAdicionar);
            this.credito.removerSaldo(fichasParaAdicionar * 50);
            consultarCredito();
            consultarFichas();
        } else {
            InOut.MsgDeErro("ERRO","Saldo insuficiente para converter em fichas.");
        }

    }

    /**
     * Converte fichas do jogador em saldo.
     */
    public void converterFichasParaSaldo() {

        int qtdFichasAConverter = InOut.leInt("Escolha a quantidade de fichas a ser convertida para saldo.");

        if (qtdFichasAConverter > this.qtdFicha) {
            InOut.MsgDeErro("Erro na Conversão", "A quantidade de fichas não pode ser menor que a quantidade disponível em sua conta");
            return;
        }

        double saldoAdicionar = qtdFichasAConverter * 50;
        this.credito.adicionarSaldo(saldoAdicionar);
        this.qtdFicha -= qtdFichasAConverter;

    }

}
