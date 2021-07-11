
package presenter;

import java.awt.event.ActionEvent;
import view.MenuPrincipalView;

public final class MenuPrincipalPresenter extends Presenter {
    
    private MenuPrincipalView view;
    
    public MenuPrincipalPresenter() {
        initView();
        
        view.setVisible(true);
    }

    @Override
    protected void initView() {
        view = MenuPrincipalView.getInstancia();
        
        view.getMenuItemNovoContato().addActionListener((ActionEvent e) -> {
            botaoNovoContatoPressionado();
        });
        
        view.getMenuItemListarContatos().addActionListener((ActionEvent e) -> {
            botaoListarContatosPressionado();
        });
        
        view.getMenuItemFecharSistema().addActionListener((ActionEvent e) -> {
            botaoFecharSistema();
        });
    }
    
    @Override
    public void setViewVisible(boolean b) {
        view.setVisible(b);
    }
    
    // -------------------------------------------------------------------------
    // Botões
    // -------------------------------------------------------------------------
    private void botaoNovoContatoPressionado() {
        ManterContatoPresenter.getInstancia().setOperacao(Operacao.CREATE, null);
        ManterContatoPresenter.getInstancia().setViewVisible(true);
    }
    
    private void botaoListarContatosPressionado() {
        ListagemContatosPresenter.getInstancia().setViewVisible(true);
    }
    
    private void botaoFecharSistema() {
        view.dispose();
    }
    
}
