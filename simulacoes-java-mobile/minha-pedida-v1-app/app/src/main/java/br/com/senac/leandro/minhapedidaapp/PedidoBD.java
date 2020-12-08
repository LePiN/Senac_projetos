package br.com.senac.leandro.minhapedidaapp;

import android.database.sqlite.SQLiteDatabase;

public class PedidoBD {

    private Pedido pedido;
    private SQLiteDatabase banco;

    private static final String BANCO_NOME = "banco-produtos.db";

    private static final int BANCO_ACESSO = 0;

    private static final String TABELA_NOME = "pedido";

    private static final String SQL_CRIACAO_TABELA = "" +
    "CREATE TABLE IF NOT EXISTS " + TABELA_NOME + "(" +
            "id integer not null primary key autoincrement," +
            "id_produto integer not null" +
            "quantidade integer not null, " +
            "valorParcial float not null, " +
            "constraint fk_pedido foreign key (id_produto) references produto(id)" +
            ");";
    ;

    private static final String SQL_SELECT_ALL = "" + "SELECT id, quantidade, ";
}
