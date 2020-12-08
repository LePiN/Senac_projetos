package br.com.senac.leandro.pesquisaapp;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private String nome;
    private int idade;
    private String telefone;
    private String email;
    private int cep;
    private String Cidade;
    private String Estado;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String telefone, String email, int cep, String cidade, String estado) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        Cidade = cidade;
        Estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cep=" + cep +
                ", Cidade='" + Cidade + '\'' +
                ", Estado='" + Estado + '\'' +
                '}';
    }
}
