package POOBet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A classe MenuGUI é responsável pela interface gráfica
 * do programa. Ela encapsula os métodos responsáveis pela
 * exibição de elemento, além de possuir alguns métodos
 * estáticos utilitários.
 */
public class MenuGUI {

    private JFrame janela;
    private JLayeredPane container = new JLayeredPane();
    private JPanel menu = new JPanel();
    protected static Dimension dimensoesTela = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Instancia um novo objeto da classe e inicializa seus principais atributos.
     * @param caminhoBackground caminho do arquivo para a imagem do plano de fundo.
     */
    public MenuGUI(String caminhoBackground, Jogador jogador) {

        // Cria a janela e define seu background.
        setJanela("POO Bet");
        JLabel imagemFundo = criarImagemFundo(caminhoBackground);

        // Modifica o tamanho do menu
        menu.setLayout(new FlowLayout(FlowLayout.CENTER));
        int widthMenu = 300;
        int heightMenu = 280;
        int xMenu = (dimensoesTela.width - widthMenu) / 2;
        int yMenu = (dimensoesTela.height - heightMenu) / 2;
        menu.setBounds(xMenu,yMenu,widthMenu,heightMenu);

        // Cria o botão de adição de saldo
        adicionarBotao("Adicionar Saldo", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.adicionarCredito();}});

        // Cria o botão de consulta de saldo
        adicionarBotao("Consultar Saldo", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.consultarCredito();}});

        // Cria o botão de retirada de saldo
        adicionarBotao("Retirar Saldo", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.retirarCredito();}});

        // Cria o botão de conversão de saldo
        adicionarBotao("Converter Saldo em Fichas", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.converterSaldoParaFichas();}});

        // Cria o botão de consulta de fichas
        adicionarBotao("Consultar Fichas", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.consultarFichas();}});

        // Cria o botão de conversão de fichas
        adicionarBotao("Converter Fichas em Saldo", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.converterFichasParaSaldo();}});

        // Cria o botão de adicionar nova aposta
        adicionarBotao("Adicionar Nova Aposta", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.criarAposta();}});

        // Cria o botão de consultar apostas
        adicionarBotao("Consultar Apostas", new ActionListener() {@Override public void actionPerformed(ActionEvent e) {jogador.consultarApostas();}});

        // Adiciona os elementos criados ao container.
        container.add(imagemFundo,999);
        container.add(menu);

        // Adiciona o container a janela e deixa ela visível.
        janela.add(container);
        janela.setVisible(true);

    }

    /**
     * Define o atributo janela da interface gráfica.
     * Além de deixá-la maximizada por padrão e finalizar
     * o programa ao fechá-la.
     * @param titulo da janela
     */
    private void setJanela(String titulo) {

        janela = new JFrame();
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setTitle(titulo);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Essa função é responsável por adicionar um botão no menu.
     * @param texto             do botão.
     * @param actionListener    função a ser executada quando o botão é clicado.
     * @see ActionListener
     */
    private void adicionarBotao(String texto, ActionListener actionListener) {
        JButton botao = new JButton();
        botao.setText(texto);
        botao.addActionListener(actionListener);
        botao.setPreferredSize(new Dimension(menu.getSize().width, 30));
        menu.add(botao);
    }

    /**
     * Fecha a janela.
     */
    public void fecharJanela() {
        janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Retorna um objeto ImageIcon a partir de um caminho de arquivo de imagem.
     * @param caminhoArquivo caminho do arquivo da imagem.
     * @return o ImageIcon correspondente a imagem.
     * @see ImageIcon
     */
    public static ImageIcon criarImageIcon(String caminhoArquivo) {

        BufferedImage imagem = null;
        try {
            imagem = ImageIO.read(new File(caminhoArquivo));
        } catch (IOException e) {
            InOut.MsgDeErro("Erro na imagem","A imagem selecionada para o fundo não conseguiu ser localizada, confira o erro:\n" + e.getMessage());
        }

        return new ImageIcon(imagem);

    }

    /**
     * Retorna um ImageIcon redimensionado de acordo com os parâmetros repassados.
     * @param imagem o objeto ImageIcon da imagem a ser redimensionada.
     * @param dimensoes as dimensões para qual a imagem será redimensionada.
     * @return o objeto ImageIcon da imagem redimensionada.
     * @see Dimension
     * @see ImageIcon
     */
    public static ImageIcon redimensionarImagemParaTela(ImageIcon imagem, Dimension dimensoes) {

        Image imgRedimensionada = imagem.getImage().getScaledInstance(dimensoes.width, dimensoes.height, Image.SCALE_DEFAULT);

        return new ImageIcon(imgRedimensionada);
    }

    /**
     * Retorna um componente de imagem de fundo a partir de um caminho de arquivo.
     * @param caminhoArquivo caminho do arquivo da imagem de fundo.
     * @return o componente que contêm a imagem de fundo.
     * @see JLabel
     */
    private static JLabel criarImagemFundo (String caminhoArquivo) {

        JLabel imagemFundo = new JLabel();
        ImageIcon imagem = criarImageIcon(caminhoArquivo);
        ImageIcon imagemRedimensionada = redimensionarImagemParaTela(imagem, dimensoesTela);
        imagemFundo.setIcon(imagemRedimensionada);
        imagemFundo.setBounds(0,0,dimensoesTela.width, dimensoesTela.height);

        return imagemFundo;
    }

}