package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contato;
import persistence.ContatoPersistence;
import view.ManterContatoView;

public final class ManterContatoPresenter extends Presenter {
    
    private static ManterContatoPresenter instancia;

    private ManterContatoView view;
    private Operacao operacao;
    private Contato contato;

    private ManterContatoPresenter() {
        initView();
    }

    @Override
    protected void initView() {

        view = ManterContatoView.getInstancia();

        view.getBotaoFechar().addActionListener((ActionEvent e) -> {
            botaoFecharPressionado();
        });

    }
    
    @Override
    public void setViewVisible(boolean b) {
        view.setVisible(b);
    }

    public void setOperacao(Operacao operacao, Contato contato) {

        for (ActionListener al : view.getBotaoSalvar().getActionListeners()) {
            view.getBotaoSalvar().removeActionListener(al);
        }

        switch (operacao) {

            case CREATE:

                view.setTitle("Inclusão de contato");
                
                this.contato = null;
                esvaziarCampos();
                view.getBotaoSalvar().setText("Salvar");
                
                view.getCampoNome().setEditable(true);
                view.getCampoTelefone().setEditable(true);
                
                view.getBotaoSalvar().addActionListener((ActionEvent e) -> {
                    botaoSalvarPressionadoCreate();
                });
                
                break;
                
            case READ:
                
                view.setTitle("Visualização de contato");
                
                this.contato = contato;
                preencherCampos();
                view.getBotaoSalvar().setText("Alterar");
                
                view.getCampoNome().setEditable(false);
                view.getCampoTelefone().setEditable(false);
                
                view.getBotaoSalvar().addActionListener((ActionEvent e) -> {
                    botaoSalvarPressionadoRead();
                });
                
                break;
                
            case UPDATE:
                
                view.setTitle("Alteração de contato");
                
                this.contato = contato;
                preencherCampos();
                view.getBotaoSalvar().setText("Salvar");
                
                view.getCampoNome().setEditable(true);
                view.getCampoTelefone().setEditable(true);
                
                view.getBotaoSalvar().addActionListener((ActionEvent e) -> {
                    botaoSalvarPressionadoUpdate();
                });
                
                break;

        }

    }
    
    public static ManterContatoPresenter getInstancia() {
        if (instancia == null) {
            instancia = new ManterContatoPresenter();
        }
        return instancia;
    }

    private void preencherCampos() {
        view.getCampoNome().setText(contato.getNome());
        view.getCampoTelefone().setText(contato.getTelefone());
    }
    
    private void esvaziarCampos() {
        view.getCampoNome().setText("");
        view.getCampoTelefone().setText("");
    }

    private boolean validarCampos() {
        if (view.getCampoNome().getText().isEmpty() || view.getCampoTelefone().getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Favor preencher os campos corretamente",
                    "Valores não válidos",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return false;
        } else {
            return true;
        }
    }

    // -------------------------------------------------------------------------
    // Botões
    // -------------------------------------------------------------------------
    private void botaoFecharPressionado() {
        view.dispose();
    }

    private void botaoSalvarPressionadoCreate() {
        if (validarCampos()) {
            try {
                new ContatoPersistence().salvarContato(new Contato(
                        view.getCampoNome().getText(),
                        view.getCampoTelefone().getText()
                ));
                
                ListagemContatosPresenter.getInstancia().carregarListaContatos();

                JOptionPane.showMessageDialog(
                        view,
                        "Contato cadastrado com sucesso",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                
                view.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ManterContatoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }private void botaoSalvarPressionadoRead() {
        setOperacao(Operacao.UPDATE, contato);
    }

    private void botaoSalvarPressionadoUpdate() {
        if (validarCampos()) {
            try {
                new ContatoPersistence().atualizarContato(new Contato(
                        contato.getId(),
                        view.getCampoNome().getText(),
                        view.getCampoTelefone().getText()
                ));
                
                ListagemContatosPresenter.getInstancia().carregarListaContatos();

                JOptionPane.showMessageDialog(
                        view,
                        "Contato atualizado com sucesso",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                
            } catch (SQLException ex) {
                preencherCampos();
                Logger.getLogger(ManterContatoPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
