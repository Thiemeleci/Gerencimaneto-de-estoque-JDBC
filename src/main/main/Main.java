package main.main;

import main.Protutos.Produto;
import main.Protutos.ProdutoDAO;
import main.conexao.ConexaoDB;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (Connection conexao = ConexaoDB.conectar()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

            //Listar todos os produtos
            mostrarProdutos(produtoDAO);

            // Exemplo de inserção
            Produto novoProduto1 = new Produto("Notbook", 10, 1999.99, "Em estoque");
            Produto novoProduto2 = new Produto("Smartphone", 20, 1499.99, "Em estoque");
            Produto novoProduto3 = new Produto("Tablet", 15, 799.99, "Estoque baixo");

            produtoDAO.inserir(novoProduto1);
            produtoDAO.inserir(novoProduto2);
            produtoDAO.inserir(novoProduto3);

            //Listar todos
            mostrarProdutos(produtoDAO);

            //Exemplo de consulta por ID
            Produto produtoConsultado = produtoDAO.consultarPorId(1);
            if (produtoConsultado != null) {
                System.out.println("Produto encontrado: " + produtoConsultado.getNome());
            } else {
                System.out.println("Produto não encontrado");
            }
        } catch (Exception e) {
            System.err.println("Erro geral: " + e.getMessage());
        }
    }

    //Metodo para listar os produtos
    private static void mostrarProdutos(ProdutoDAO produtoDAO) {
        List<Produto> todosProdutos = produtoDAO.listarTodos();
        if (todosProdutos.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
        } else {
            System.out.println("Lista de produtos:");
            for (Produto p : todosProdutos) {
                System.out.println(p.getId() + ": " + p.getNome() + " - " + p.getPreco());
            }
        }
    }
}