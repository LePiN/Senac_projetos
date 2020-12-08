package br.com.senac.leandro.mediadisciplinasapp;

import java.io.Serializable;

public class Disciplina implements Serializable {

    //private static final long serialVersionUID = 1L;
    private String nomeDisciplina;
    private int nota1;
    private int nota2;
    private int nota3;

    public Disciplina() {
    }

    public Disciplina(String nomeDisciplina, int nota1, int nota2, int nota3) {
        this.nomeDisciplina = nomeDisciplina;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

    public double calcularMedia(){
        double media = (nota1+nota2+nota3)/3;
        return media;
    }

    public String verificarSituacao(){
        String situacao;
        if(this.calcularMedia()>= 6){
            situacao = "Aprovado";
        }else{
            situacao = "Reprovado";
        }

        return situacao;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nomeDisciplina='" + nomeDisciplina + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                '}';
    }
}
