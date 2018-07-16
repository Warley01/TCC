package br.edu.ifg.model;

public class Produto {

    //Atributos
    private int id;
    private String nome;
    private float vlUnitario;
    private int estoque;


    public int getId() {return id; }

    public void setId(int id) { this.id = id;  }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(float vlUnitario) {
        this.vlUnitario = vlUnitario;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return  "ID: " + "\t\t\t\t\t\t\t\t\t"+ id +
                "\nNome: " + "\t\t\t\t\t\t"+ nome +
                "\nEstoque:" + "\t\t\t\t\t"+ estoque +
                "\nValor unitário: " + "\t"+ vlUnitario;
    }
}//fim da classe
