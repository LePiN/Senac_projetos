package br.com.senac.leandro.correcaominhapedidaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "minhaPedida.sql";
    private static final int DATABASE_VERSION = 1;
    private static MyORMLiteHelper instance = null;

    Dao<Produto, Integer> daoProduto;
    Dao<Item, Integer> daoItem;
    Dao<Categoria, Integer> daoCategoria;

    public MyORMLiteHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyORMLiteHelper getInstance(Context ctx){
       if(instance == null ) {
           return new MyORMLiteHelper(ctx);
       }
       return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Categoria.class);
            TableUtils.createTable(connectionSource, Item.class);
            TableUtils.createTable(connectionSource, Produto.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Categoria.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<Categoria, Integer> getCategoriaDao(){
        if(daoCategoria==null){
            try {
                daoCategoria = getDao(Categoria.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return daoCategoria;
    }

    public int deleteAllItens() throws SQLException {

       return getItemDao().deleteBuilder().delete();

    }



    public Dao<Item, Integer> getItemDao(){
        if(daoItem==null){
            try {
                daoItem = getDao(Item.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return daoItem;
    }

    public Dao<Produto, Integer> getProdutoDao(){
        if(daoProduto==null){
            try {
                daoProduto = getDao(Produto.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return daoProduto;
    }
}
