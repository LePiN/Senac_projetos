package gabriel.br.com.projetofinal.Entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Aluno on 26/06/2018.
 */

@DatabaseTable
public class Shopping {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    String nome;

    @DatabaseField
    String endereco;

    @DatabaseField
    Boolean favorito;

    public Shopping(String nome) {
        this.nome = nome;
    }

    public Shopping() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return nome;
    }
}
