package POOBet;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
/**
 * A classe Credito representa um saldo de moeda em uma conta de jogador, com data de validade.
 * Os jogadores podem adicionar ou remover saldo e verificar a disponibilidade do saldo.
 */
public class Credito {

    private double saldo;
    private Date validade;
    private String moeda = "BRL";
    private Jogador jogador;

    /**
     * Adiciona um valor ao saldo da conta do jogador.
     *
     * @param saldoAdicionar O valor a ser adicionado ao saldo.
     */
    public void adicionarSaldo(double saldoAdicionar) {
        this.saldo += saldoAdicionar;
        setValidade();
    }

    /**
     * Remove um valor do saldo da conta do jogador, desde que haja saldo disponível.
     *
     * @param saldoRemover O valor a ser removido do saldo.
     */
    public void removerSaldo(double saldoRemover) {
        if (verificarDisponibilidade(saldoRemover)) {
            this.saldo -= saldoRemover;
        } else {
            InOut.MsgDeErro("Erro na retirada","Você não pode retirar um valor maior que seu saldo atual.");
        }
    }

    /**
     * Verifica se há saldo disponível na conta do jogador para uma operação específica.
     *
     * @param valor O valor a ser verificado em relação ao saldo atual.
     * @return true se houver saldo disponível, false caso contrário.
     */
    public boolean verificarDisponibilidade(double valor) {
        return this.saldo >= valor;
    }

    /**
     * Obtém o saldo atual da conta do jogador.
     *
     * @return O saldo atual da conta.
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Obtém a data de validade do saldo.
     *
     * @return A data de validade do saldo.
     */
    public Date getValidade() {
        return validade;
    }

    /**
     * Define a data de validade do saldo, que é 90 dias a partir do momento da chamada.
     */
    public void setValidade() {
        Instant instanteAtual = Instant.now();
        Date dataValidade = Date.from(instanteAtual.plus(90, ChronoUnit.DAYS));
        this.validade = removerHora(dataValidade);
    }

    /**
     * Obtém a moeda utilizada na conta do jogador.
     *
     * @return A moeda da conta (por exemplo, "BRL").
     */
    public String getMoeda() {
        return moeda;
    }

    /**
     * Remove a informação de hora de uma data, definindo as horas, minutos, segundos e milissegundos para zero.
     *
     * @param data A data da qual se deseja remover a informação de hora.
     * @return A data sem informação de hora.
     */
    public static Date removerHora(Date data) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
}
