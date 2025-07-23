package service;

import model.Transacao;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorFinanceiro {
    private List<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao t) {
        transacoes.add(t);
    }

    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
            return;
        }

        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }

    public double calcularSaldo() {
        double saldo = 0;
        for (Transacao t : transacoes) {
            saldo += t.isReceita() ? t.getValor() : -t.getValor();
        }
        return saldo;
    }
}
