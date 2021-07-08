package presenter;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.Contato;
import persistence.ContatoPersistence;
import view.ManterContatoView;

public final class ManterContatoPresenter extends Presenter {

    private ManterContatoView view;

    private Operacao operacao;

    private Contato contato;

    public ManterContatoPresenter(Operacao operacao, Contato contato) {
        this.operacao = operacao;
        this.contato = contato;

        initView();

        view.setVisible(true);
    }

    @Override
    protected void initView() {
        view = ManterContatoView.getInstancia();

        view.getBotaoFechar().addActionListener((ActionEvent e) -> {
            botaoFecharPressionado();
        });

        switch (operacao) {
            case CREATE:

                view.getBotaoSalvar().addActionListener((ActionEvent e) -> {
                    botaoSalvarPressionadoCreate();
                });

            case READ:
                
                preencherCampos();
                view.getBotaoSalvar().setEnabled(false);

            case UPDATE:
                
                preencherCampos();
                view.getBotaoSalvar().addActionListener((ActionEvent e) -> {
                    botaoSalvarPressionadoUpdate();
                });

            default:

        }
    }

    private void preencherCampos() {
        view.getCampoNome().setText(contato.getNome());
        view.getCampoTelefone().setText(contato.getTelefone());
    }
    
    private boolean validarCampos() {
        if (view.getCampoNome().getText().isEmpty() || view.getCampoTelefone().getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    view, 
                    "Favor preencher os campos corretamente",
                    "Valores não válidos",
                    JOptionPane.WARNING_MESSAGE
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
            
        }
    }

    private void botaoSalvarPressionadoUpdate() {
        // TODO
    }

}
