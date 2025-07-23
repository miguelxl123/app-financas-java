package view;

import model.Categoria;
import model.Transacao;
import service.GerenciadorFinanceiro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class TelaPrincipal extends JFrame {
    private GerenciadorFinanceiro financeiro = new GerenciadorFinanceiro();
    private DefaultTableModel tabelaModelo;
    private JTable tabela;

    public TelaPrincipal() {
        setTitle("Finanças Pessoais");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout principal
        setLayout(new BorderLayout());

        // Formulário de entrada
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2));
        JTextField campoDescricao = new JTextField();
        JTextField campoValor = new JTextField();
        JComboBox<Categoria> campoCategoria = new JComboBox<>(Categoria.values());
        JCheckBox checkReceita = new JCheckBox("Receita?");

        JButton btnAdicionar = new JButton("Adicionar");
        JLabel labelSaldo = new JLabel("Saldo: R$ 0.00");

        painelFormulario.add(new JLabel("Descrição:"));
        painelFormulario.add(campoDescricao);
        painelFormulario.add(new JLabel("Valor:"));
        painelFormulario.add(campoValor);
        painelFormulario.add(new JLabel("Categoria:"));
        painelFormulario.add(campoCategoria);
        painelFormulario.add(new JLabel("Tipo:"));
        painelFormulario.add(checkReceita);
        painelFormulario.add(new JLabel(""));
        painelFormulario.add(btnAdicionar);
        painelFormulario.add(new JLabel(""));
        painelFormulario.add(labelSaldo);

        add(painelFormulario, BorderLayout.NORTH);

        // Tabela de transações
        tabelaModelo = new DefaultTableModel(new Object[]{"Tipo", "Valor", "Descrição", "Data", "Categoria"}, 0);
        tabela = new JTable(tabelaModelo);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        // Ação do botão
        btnAdicionar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                String descricao = campoDescricao.getText();
                Categoria categoria = (Categoria) campoCategoria.getSelectedItem();
                boolean receita = checkReceita.isSelected();

                Transacao t = new Transacao(valor, descricao, LocalDate.now(), categoria, receita);
                financeiro.adicionarTransacao(t);

                tabelaModelo.addRow(new Object[]{
                        receita ? "Receita" : "Despesa",
                        String.format("R$ %.2f", valor),
                        descricao,
                        t.getData(),
                        categoria
                });

                campoDescricao.setText("");
                campoValor.setText("");
                checkReceita.setSelected(false);
                campoCategoria.setSelectedIndex(0);

                labelSaldo.setText(String.format("Saldo: R$ %.2f", financeiro.calcularSaldo()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor válido.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipal::new);
    }
}
