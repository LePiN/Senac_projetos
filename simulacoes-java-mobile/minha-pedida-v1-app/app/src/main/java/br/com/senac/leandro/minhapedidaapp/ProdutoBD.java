package br.com.senac.leandro.minhapedidaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProdutoBD {

    private Produto produto;
    private SQLiteDatabase banco;

    //Definir o nome do banco de dados
    private static final String BANCO_NOME = "banco-produtos.db";

    //Definir o tipo de acesso ao banco
    // 0 => Modo Privado
    // 1 => Outros aplicativos podem ler
    // 2 => Outros aplicativos podem ler e escrever
    private static final int BANCO_ACESSO = 0;

    //Definir o nome da tabela
    private static final String TABELA_NOME ="produto";

    //Definir o SQL para criação da tabla de produtos
    private static final String SQL_CRIACAO_TABELA = "" +
            "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + "(" +
            "id integer not null primary key autoincrement," +
            "nome varchar(45) not null," +
            "valor float not null)";

    //Definir SQL para selecionar todos os produto
    private static final String SQL_SELECT_ALL = "" +
            "SELECT id, nome, valor FROM " + TABELA_NOME + " ORDER BY id ASC";

    //Variável para armazenar respostas de select´s
    private Cursor cursor;

    //Classe responsável pela conexão com o Banco de Dados
    public ProdutoBD(Context context){
        this.banco = context.openOrCreateDatabase(BANCO_NOME, BANCO_ACESSO, null);
        this.banco.execSQL(SQL_CRIACAO_TABELA);
    }

    //Método responsável pelo cadastro de um produto
    public boolean cadastrar(Produto produto){
        //Monta valores para parâmetros no insert
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("valor", produto.getValor());

        //Realiza o cadastro no banco
        long res = this.banco.insert(TABELA_NOME, null, values);

        if(res==1)
            return false;
        else
            return true;
    }

    //Método responsável por deletar um produto
    public boolean deletar(Produto produto){
        //?Vetor de string com valores de parâmetros?
        String[] argumentos = new String[]{String.valueOf(produto.getId())};

        //Deletar o produto do banco de dados
        int res = this.banco.delete(TABELA_NOME, "id=?", argumentos);

        if(res==1)
            return false;
        else
            return true;
    }

    //Método para atualizar um produto
    public boolean atualizar(Produto produto){
        //Monta valores para parâmetros no insert
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("valor", produto.getValor());

        //Valores de parâmetros para cláusula where
        String[] args = new String[]{String.valueOf(produto.getId())};
        int res = this.banco.update(TABELA_NOME, values, "id=?", args);
        if(res==1)
            return false;
        else
            return true;
    }

    //Método para retornar todos os produtos
    public ArrayList<Produto> listarTodos(){

        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

        //Executa o sql all
        this.cursor = this.banco.rawQuery(SQL_SELECT_ALL, null);

        while(this.cursor.moveToNext()){
            this.produto = new Produto();
            this.produto.setId(this.cursor.getInt(this.cursor.getColumnIndex("id")));
            this.produto.setNome(this.cursor.getString(this.cursor.getColumnIndex("nome")));
            this.produto.setValor(this.cursor.getDouble(this.cursor.getColumnIndex("valor")));

            listaProdutos.add(this.produto);

        }

        return listaProdutos;
    }

    //Método responsável por popular o Banco de Dados
    public void popularBD(){
        if(listarTodos().isEmpty()){
            Produto refrigerante = new Produto("Refrigerante", 3.0);
            cadastrar(refrigerante);
            Produto cerveja = new Produto("Cerveja", 5.0);
            cadastrar(cerveja);
            Produto batataFrita = new Produto("Batata Frita", 10.0);
            cadastrar(batataFrita);
            Produto agua = new Produto("Água", 2.5);
            cadastrar(agua);
            Produto pastel = new Produto("Pastel", 3.5);
            cadastrar(pastel);
            Produto petiscos = new Produto("Petiscos", 6.0);
            cadastrar(petiscos);
        }



    }



}
