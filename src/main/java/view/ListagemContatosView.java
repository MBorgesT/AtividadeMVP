
package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;

public class ListagemContatosView extends javax.swing.JFrame {
    
    private static ListagemContatosView instancia;
    
    public ListagemContatosView() {
        initComponents();
    }
    
    public static ListagemContatosView getInstance() {
        if (instancia == null) {
            instancia = new ListagemContatosView();
        }
        return instancia;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaContatos = new javax.swing.JTable();
        botaoFechar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoVisualizar = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        checkBoxOrdenarPeloTelefone = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem de Contatos");

        tabelaContatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Telefone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaContatos);
        if (tabelaContatos.getColumnModel().getColumnCount() > 0) {
            tabelaContatos.getColumnModel().getColumn(0).setResizable(false);
        }

        botaoFechar.setText("Fechar");

        botaoExcluir.setText("Excluir");

        botaoVisualizar.setText("Visualizar");

        label1.setText("Total:");

        labelTotal.setText("blank");

        checkBoxOrdenarPeloTelefone.setText("Ordenar pelo telefone");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botaoVisualizar)
                                .addGap(18, 18, 18)
                                .addComponent(botaoExcluir)
                                .addGap(18, 18, 18)
                                .addComponent(botaoFechar))
                            .addComponent(checkBoxOrdenarPeloTelefone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(label1)
                                .addGap(18, 18, 18)
                                .addComponent(labelTotal)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxOrdenarPeloTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoFechar)
                    .addComponent(botaoExcluir)
                    .addComponent(botaoVisualizar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(labelTotal))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JCheckBox checkBoxOrdenarPeloTelefone;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tabelaContatos;
    // End of variables declaration//GEN-END:variables

    public JButton getBotaoExcluir() {
        return botaoExcluir;
    }

    public JButton getBotaoFechar() {
        return botaoFechar;
    }

    public JButton getBotaoVisualizar() {
        return botaoVisualizar;
    }

    public JCheckBox getCheckBoxOrdenarPeloTelefone() {
        return checkBoxOrdenarPeloTelefone;
    }

    public JLabel getLabelTotal() {
        return labelTotal;
    }

    public JTable getTabelaContatos() {
        return tabelaContatos;
    }

}
