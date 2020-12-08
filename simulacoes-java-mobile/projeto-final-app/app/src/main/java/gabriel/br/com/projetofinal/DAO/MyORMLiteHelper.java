package gabriel.br.com.projetofinal.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import gabriel.br.com.projetofinal.Entity.Shopping;
import gabriel.br.com.projetofinal.Entity.User;

/**
 * Created by Aluno on 16/04/2018.
 */

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {


    //nome do banco de dados
    private static final String DATABASE_NAME = "projetoFinal.txt";

    //Versão do banco
    private static final int DATABASE_VERSION = 2;

    //Váriavel Singleton
    private static MyORMLiteHelper mInstance = null;

    // Dao para tabelas e objetos
    Dao<Shopping, Integer> shoppingDAO;
    Dao<User, Integer> userDAO;


    public MyORMLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static MyORMLiteHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new MyORMLiteHelper(ctx);

        }
        return mInstance;
    }


//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
//
//        // Crias Tabelas no Banco
//
//        try {
//            TableUtils.createTable(connectionSource, Categoria.class);
//            TableUtils.createTable(connectionSource, Produto.class);
//            TableUtils.createTable(connectionSource, Item.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
//        try {
//            TableUtils.dropTable(connectionSource, Categoria.class, true);
//            TableUtils.dropTable(connectionSource, Produto.class, true);
//            TableUtils.dropTable(connectionSource, Item.class, true);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        onCreate(sqLiteDatabase, connectionSource);
//    }
//
//    public Dao<Categoria, Integer> getDaoCategoria() throws SQLException {
//        if (daoCategoria == null) {
//            daoCategoria = getDao(Categoria.class);
//        }
//        return daoCategoria;
//    }
//
//    public Dao<Produto, Integer> getDaoProduto() throws SQLException {
//        if (daoProduto == null) {
//            daoProduto = getDao(Produto.class);
//        }
//        return daoProduto;
//    }
//
//    public Dao<Item, Integer> getDaoItem() throws SQLException {
//        if (daoItem == null) {
//            daoItem = getDao(Item.class);
//        }
//        return daoItem;
//
//
//    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        // Criar tabelas no banco de dados
        try {
           TableUtils.createTable(connectionSource, Shopping.class);
           TableUtils.createTable(connectionSource, User.class);
       } catch (SQLException e) {
           e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,User.class, true);
            TableUtils.dropTable(connectionSource,Shopping.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);
    }

    // Para cada tabela criada deve ser criado o seu DAO

    public Dao<User,Integer> getUserDAO() throws SQLException {
        if(userDAO == null) {
            userDAO = getDao(User.class);
        }
        return userDAO;

    }

    public Dao<Shopping,Integer> getShoppingDAO() throws SQLException {
        if (shoppingDAO == null) {
            shoppingDAO = getDao(Shopping.class);
        }
        return shoppingDAO;
    }
}


