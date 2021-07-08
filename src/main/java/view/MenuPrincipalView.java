
package view;

import javax.swing.JMenuItem;

public class MenuPrincipalView extends javax.swing.JFrame {
    
    private static MenuPrincipalView instancia;

    private MenuPrincipalView() {
        initComponents();
    }
    
    public static MenuPrincipalView getInstancia() {
        if (instancia == null) {
            instancia = new MenuPrincipalView();
        }
        return instancia;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuOpcoes = new javax.swing.JMenu();
        menuItemNovoContato = new javax.swing.JMenuItem();
        menuItemListarContatos = new javax.swing.JMenuItem();
        menuItemFecharSistema = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");

        menuOpcoes.setText("Opções");

        menuItemNovoContato.setText("Novo contato");
        menuOpcoes.add(menuItemNovoContato);

        menuItemListarContatos.setText("Listar contatos");
        menuOpcoes.add(menuItemListarContatos);

        menuItemFecharSistema.setText("Fechar o sistema");
        menuOpcoes.add(menuItemFecharSistema);

        jMenuBar1.add(menuOpcoes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemFecharSistema;
    private javax.swing.JMenuItem menuItemListarContatos;
    private javax.swing.JMenuItem menuItemNovoContato;
    private javax.swing.JMenu menuOpcoes;
    // End of variables declaration//GEN-END:variables

    public JMenuItem getMenuItemFecharSistema() {
        return menuItemFecharSistema;
    }

    public JMenuItem getMenuItemListarContatos() {
        return menuItemListarContatos;
    }

    public JMenuItem getMenuItemNovoContato() {
        return menuItemNovoContato;
    }

}
