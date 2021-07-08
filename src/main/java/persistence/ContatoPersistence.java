
package persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Contato;

public class ContatoPersistence {
    
    private static int nextId = 0;
    
    public static class ContatoNotFoundException extends Exception {

        private ContatoNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    };
    
    public static ArrayList<Contato> carregarListaContatos() throws IOException {
        try {
            JsonReader reader = new JsonReader(new FileReader("contatos.json"));
            return new ArrayList<>(new Gson().fromJson(reader, Contato[].class));
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }
    
    public static void salvarListaContatos(ArrayList<Contato> listaContatos) throws IOException {
        new Gson().toJson(listaContatos, new FileWriter("contatos.json"));
    }
    
    public static void salvarContato(Contato contato) throws IOException {
        ArrayList<Contato> lista = carregarListaContatos();
        lista.add(contato);
        salvarListaContatos(lista);
    }
    
    @SuppressWarnings("empty-statement")
    public static void atualizarContato(Contato contato) throws IOException, ContatoNotFoundException {
        ArrayList<Contato> lista = carregarListaContatos();
        
        int i;
        for (i = 0; i < lista.size() && lista.get(i).getId() != contato.getId(); i++);
        
        if (i == lista.size())
            throw new ContatoNotFoundException("Não foi possível encontrar um contato com este ID para ser atualizado");
        
        lista.get(i).setNome(contato.getNome());
        lista.get(i).setTelefone(contato.getTelefone());
        
        salvarListaContatos(lista);
    }
    
    @SuppressWarnings("empty-statement")
    public static void excluirContato(Contato contato) throws IOException, ContatoNotFoundException {
        ArrayList<Contato> lista = carregarListaContatos();
        
        int i;
        for (i = 0; i < lista.size() && lista.get(i).getId() != contato.getId(); i++);
        
        if (i == lista.size())
            throw new ContatoNotFoundException("Não foi possível encontrar um contato com este ID para ser atualizado");
        
        lista.remove(i);
        
        salvarListaContatos(lista);
    }
    
}
