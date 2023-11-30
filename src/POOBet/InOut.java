package POOBet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A classe leDadosWin permite a leitura de dados de tipos nativos e de 
 * instancias da classe String a partir do teclado, atraves de uma 
 * janela. Basicamente esta classe encapsula o funcionamento dos metodos da clase
 * JOptionPane, que pode ser usado como entrada padrao de dados.
 * <ul>
 * <li>Os metodos sao estaticos para facilitar o uso da classe</li>
 * <li>Criacao de metodos sobrecarregados para que valores <i>default</i> possam ser 
 *     usados</li>
 * <li>Tratamento das excecoes</li>
 * </ul>
 */
public class InOut {
    /**
     * Este metodo eh para entrada de uma String. Tem como parametro de entrada
     * uma String que indicara para o usuario qual o dado que sera lido naquele
     * momento por aquela caixa de texto e retorna a String lida
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return String que foi lida
     */
    public static String leString (String frase){
        String Entrada;

        Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                JOptionPane.QUESTION_MESSAGE);
        return Entrada;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo byte. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo byte.
     * Se na conversao ocorrer algum erro, o processo sera feito novamente,
     * ou seja, sera lida outra String e convertida para byte. O processo soh
     * para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo byte
     */
    public static byte leByte (String frase){
        byte num = 0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Byte.parseByte(Entrada);
                ERRO = false;
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "BYTE", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo short. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo short.
     * O processo so para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo short
     */
    public static short leShort (String frase){
        short num=0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Short.parseShort(Entrada);
                ERRO = false;
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "SHORT", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo int. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo int.
     * O processo so para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo int
     */
    public static int leInt (String frase){
        int num = 0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Integer.parseInt(Entrada);
                ERRO = false;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "INTEIRO ", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo long. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo long.
     * O processo so para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo long
     */
    public static long leLong (String frase){
        long num = 0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Long.parseLong(Entrada);
                ERRO = false;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "LONG ", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo float. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo float.
     * O processo so para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo float
     */
    public static float leFloat (String frase){
        float num = 0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Float.parseFloat(Entrada);
                ERRO = false;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "FLOAT", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo double. Tem como paraametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e a converte para um objeto do tipo double.
     * O processo so para quando a conversao for bem sucedida.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo double
     */
    public static double leDouble (String frase){
        double num = 0;
        String Entrada;
        boolean ERRO;

        do{
            try{
                Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                        JOptionPane.QUESTION_MESSAGE);
                num = Double.parseDouble(Entrada);
                ERRO = false;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "VALOR DEVE SER UM NUMERO DO TIPO " +
                                "DOUBLE", "   >>>      ERRO     <<<",
                        JOptionPane.ERROR_MESSAGE);
                ERRO = true;
            }
        }while (ERRO);
        return num;
    }

    /**
     * Este metodo eh para entrada de um objeto do tipo char. Tem como parametro
     * de entrada uma String que indicara para o usuario qual o dado que sera
     * lido naquele momento por aquela caixa de texto.
     * Le entao uma String e retorna apenas o primeiro caracter da String.
     *
     * @param frase que sera usada para o usuario saber qual dado sera lido
     * @return um objeto do tipo char
     */
    public static char leChar (String frase){
        String Entrada;
        boolean ERRO;

        do{
            Entrada = JOptionPane.showInputDialog (null, frase, "Entrada de dados",
                    JOptionPane.QUESTION_MESSAGE);
            if (Entrada.length()!= 0)
                ERRO = false;
            else
                ERRO = true;
        }while (ERRO);
        return Entrada.charAt(0);
    }

    /**
     * Este metodo eh para entrada de uma opção desejada. Tem como parametros
     * de entrada uma String que indica o cabecalho da janela, uma String que
     * indicara para o usuário o que está sendo solicitado por essa janela e
     * uma lista de opções em String.
     * Le uma opção e retorna o valor inteiro correspondente ao indice dessa opcao.
     *
     * @param cabecalho define o cabecalho da janela
     * @param frase indica o que será lido
     * @param opcoes indica a lista de opções
     * @return inteiro correspondente ao índice da lista de opções
     */
    public static int leOpcoes(String cabecalho, String frase, String[] opcoes) {

        return JOptionPane.showOptionDialog(null, frase, cabecalho,JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }

    /**
     * Este metodo foi criado para mandar uma mensagem com o icone de
     * ERRO
     *
     * @param cabecalho que aparecera no topo da mensagem
     * @param frase     que aparecera dentro da caixa de mensagem
     */
    public static void MsgDeErro(String cabecalho, String frase){
        JOptionPane.showMessageDialog(null, frase, cabecalho,  JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Este metodo foi criado para mandar uma mensagem com o icone de
     * INFORMACAO
     *
     * @param cabecalho que aparecer no topo da mensagem
     * @param frase     que aparecera dentro da caixa de mensagem
     */
    public static void MsgDeInformacao(String cabecalho, String frase){
        JOptionPane.showMessageDialog(null, frase, cabecalho,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Este metodo foi criado para mandar uma mensagem sem icone
     *
     * @param cabecalho que aparecera no topo da mensagem
     * @param frase     que aparecera dentro da caixa de mensagem
     */
    public static void MsgSemIcone(String cabecalho, String frase){
        JOptionPane.showMessageDialog(null, frase, cabecalho,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Este metodo foi criado para mandar uma mensagem com o icone de
     * AVISO
     *
     * @param cabecalho que aparecera no topo da mensagem
     * @param frase     que aparecera dentro da caixa de mensagem
     */
    public static void MsgDeAviso (String cabecalho, String frase){
        JOptionPane.showMessageDialog(null, frase, cabecalho,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Este método foi criado para exibir uma tabela de dados em um modal.
     * @param cabecalho         título do modal.
     * @param cabecalhosTabela  lista de cabeçalhos da tabela.
     * @param dados             lista de dados
     */
    public static void ExibeTabela(String cabecalho, String[] cabecalhosTabela, Object[][] dados) {

        try {
            JDialog dialog = new JDialog();
            dialog.setTitle(cabecalho);
            dialog.setModal(true);

            DefaultTableModel model = new DefaultTableModel(dados, cabecalhosTabela);
            JTable table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            dialog.add(scrollPane);

            dialog.setSize(300, 200);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir tabela. " + e.getMessage(), "   >>>      ERRO     <<<", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void main(String [] args){


        String nome = leString("Digite o seu nome: ");
        int idade = leInt("Digite a sua idade: ");


        MsgDeInformacao("MsgDeInformacao", nome + " tem " + idade + " anos." );
        MsgSemIcone("MsgSemIcone", nome + " tem " + idade + " anos." );
        MsgDeAviso("MsgDeAviso", nome + " tem " + idade + " anos." );


    }


}