import model.Categoria;
import model.Transacao;
import service.GerenciadorFinanceiro;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorFinanceiro financeiro = new GerenciadorFinanceiro();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Finanças Pessoais ---");
            System.out.println("1. Adicionar receita");
            System.out.println("2. Adicionar despesa");
            System.out.println("3. Listar transações");
            System.out.println("4. Ver saldo");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1, 2 -> {
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Categorias:");
                    for (Categoria c : Categoria.values()) {
                        System.out.println("- " + c);
                    }

                    System.out.print("Categoria: ");
                    String cat = scanner.nextLine().toUpperCase();

                    Categoria categoria = Categoria.valueOf(cat);
                    Transacao t = new Transacao(valor, desc, LocalDate.now(), categoria, opcao == 1);
                    financeiro.adicionarTransacao(t);
                    System.out.println("Transação registrada!");
                }
                case 3 -> financeiro.listarTransacoes();
                case 4 -> System.out.printf("Saldo atual: R$ %.2f\n", financeiro.calcularSaldo());
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }
}
