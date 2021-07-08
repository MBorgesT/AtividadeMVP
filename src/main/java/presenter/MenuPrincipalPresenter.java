
package presenter;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        view.getMenuItemNovoContato().addActionListener(((ActionEvent e) -> {
            botaoNovoContatoPressionado();
        }));
        
        view.getMenuItemListarContatos().addActionListener(((ActionEvent e) -> {
            botaoListarContatosPressionado();
        }));
        
        view.getMenuItemFecharSistema().addActionListener(((ActionEvent e) -> {
            botaoFecharSistema();
        }));
    }
    
    // -------------------------------------------------------------------------
    // Botões
    // -------------------------------------------------------------------------
    private void botaoNovoContatoPressionado() {
        new ManterContatoPresenter(Operacao.CREATE, null);
    }
    
    private void botaoListarContatosPressionado() {
        try {
            new ListagemContatosPresenter();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void botaoFecharSistema() {
        view.dispose();
    }
    
}
