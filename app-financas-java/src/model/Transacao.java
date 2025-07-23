package model;

import java.time.LocalDate;

public class Transacao {
    private double valor;
    private String descricao;
    private LocalDate data;
    private Categoria categoria;
    private boolean receita;

    public Transacao(double valor, String descricao, LocalDate data, Categoria categoria, boolean receita) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.categoria = categoria;
        this.receita = receita;
    }

    public double getValor() { return valor; }
    public String getDescricao() { return descricao; }
    public LocalDate getData() { return data; }
    public Categoria getCategoria() { return categoria; }
    public boolean isReceita() { return receita; }

    @Override
    public String toString() {
        return (receita ? "[RECEITA]" : "[DESPESA]") + " R$" + valor + " - " + descricao + " (" + categoria + ") em " + data;
    }
}
