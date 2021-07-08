
package presenter;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Contato;
import persistence.ContatoPersistence;
import view.ListagemContatosView;

public final class ListagemContatosPresenter extends Presenter {
    
    private ListagemContatosView view;
    
    private ArrayList<Contato> listaContatos;
    
    public ListagemContatosPresenter() throws IOException {
        listaContatos = ContatoPersistence.carregarListaContatos();
        
        initView();
        
        view.setVisible(true);
    }
    
    @Override
    protected void initView() {
        view = ListagemContatosView.getInstance();
        
        view.getBotaoVisualizar().addActionListener((ActionEvent e) -> {
            botaoVisualizarPressionado();
        });
        
        view.getBotaoExcluir().addActionListener((ActionEvent e) -> {
            botaoExcluirPressionado();
        });
        
        view.getBotaoFechar().addActionListener((ActionEvent e) -> {
            botaoFecharPressionado();
        });

        preencherTabela();
    }
    
    private void preencherTabela() {
        DefaultTableModel model = (DefaultTableModel) view.getTabelaContatos().getModel();
        model.getDataVector().removeAllElements();
        for (Contato c : listaContatos)
            model.addRow(c.toObjectArray());
    }
    
    // -------------------------------------------------------------------------
    // Botões
    // -------------------------------------------------------------------------
    private void botaoVisualizarPressionado() {
        // TODO
    }
    
    private void botaoExcluirPressionado() {
        // TODO
    }
    
    private void botaoFecharPressionado() {
        // TODO
    }
    
}
