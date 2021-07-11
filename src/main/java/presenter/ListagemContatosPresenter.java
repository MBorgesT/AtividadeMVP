package presenter;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Contato;
import persistence.ContatoPersistence;
import view.ListagemContatosView;

public final class ListagemContatosPresenter extends Presenter {

    private static ListagemContatosPresenter instancia;

    private ListagemContatosView view;
    private ArrayList<Contato> listaContatos;

    public ListagemContatosPresenter() {
        initView();
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

        view.getCheckBoxOrdenarPeloTelefone().addActionListener((ActionEvent e) -> {
            checkBoxOrdenarPorTelefone();
        });

        view.getTabelaContatos().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tabelaContatosValorMudado();
            }
        });

        view.getBotaoVisualizar().setEnabled(false);
        view.getBotaoExcluir().setEnabled(false);
        
        carregarListaContatos();
    }

    @Override
    public void setViewVisible(boolean b) {
        view.setVisible(b);
    }

    public static ListagemContatosPresenter getInstancia() {
        if (instancia == null) {
            instancia = new ListagemContatosPresenter();
        }
        return instancia;
    }

    public void carregarListaContatos() {
        try {
            listaContatos = new ContatoPersistence().getListaContatos();

            if (view.getCheckBoxOrdenarPeloTelefone().isSelected()) {
                ordenarListaContatosPorTelefone();
            }

            preencherTabela();
            view.getLabelTotal().setText(String.valueOf(listaContatos.size()));
        } catch (SQLException ex) {
            listaContatos = new ArrayList<>();
            Logger.getLogger(ListagemContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTabela() {
        DefaultTableModel model = (DefaultTableModel) view.getTabelaContatos().getModel();
        model.getDataVector().removeAllElements();
        for (Contato c : listaContatos) {
            model.addRow(c.toObjectArray());
        }
    }

    private void ordenarListaContatosPorId() {
        Contato[] contatos = new Contato[listaContatos.size()];
        contatos = listaContatos.toArray(contatos);
        for (int i = 1; i < listaContatos.size(); i++) {

            Contato aux = contatos[i];
            int j = i;

            while ((j > 0) && (contatos[j - 1].getId() > aux.getId())) {
                contatos[j] = contatos[j - 1];
                j -= 1;
            }
            contatos[j] = aux;

        }
        listaContatos = new ArrayList<>(Arrays.asList(contatos));
    }

    private void ordenarListaContatosPorTelefone() {
        Contato[] contatos = new Contato[listaContatos.size()];
        contatos = listaContatos.toArray(contatos);
        for (int i = 1; i < listaContatos.size(); i++) {

            Contato aux = contatos[i];
            int j = i;

            while ((j > 0) && (contatos[j - 1].getTelefone().compareTo(aux.getTelefone()) > 0)) {
                contatos[j] = contatos[j - 1];
                j -= 1;
            }
            contatos[j] = aux;

        }
        listaContatos = new ArrayList<>(Arrays.asList(contatos));
    }

    private void tabelaContatosValorMudado() {
        int selectedRow = view.getTabelaContatos().getSelectedRow();
        boolean b = selectedRow >= 0 && selectedRow < listaContatos.size();

        view.getBotaoVisualizar().setEnabled(b);
        view.getBotaoExcluir().setEnabled(b);
    }

    // -------------------------------------------------------------------------
    // Botões
    // -------------------------------------------------------------------------
    private void botaoVisualizarPressionado() {
        int selectedRow = view.getTabelaContatos().getSelectedRow();
        
        ManterContatoPresenter.getInstancia().setOperacao(Operacao.READ, listaContatos.get(selectedRow));
        ManterContatoPresenter.getInstancia().setViewVisible(true);
    }

    private void botaoExcluirPressionado() {
        int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
                "Realmente deseja excluir o contato?",
                "Escolha",
                JOptionPane.YES_NO_OPTION);

        if (opcaoSelecionada == JOptionPane.YES_OPTION) {
            int selectedRow = view.getTabelaContatos().getSelectedRow();
            
            try {
                new ContatoPersistence().excluirContato(listaContatos.get(selectedRow));
                carregarListaContatos();
            } catch (SQLException ex) {
                Logger.getLogger(ListagemContatosPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void botaoFecharPressionado() {
        view.dispose();
    }

    private void checkBoxOrdenarPorTelefone() {
        if (view.getCheckBoxOrdenarPeloTelefone().isSelected()) {
            ordenarListaContatosPorTelefone();
        } else {
            ordenarListaContatosPorId();
        }
        preencherTabela();
    }

}
