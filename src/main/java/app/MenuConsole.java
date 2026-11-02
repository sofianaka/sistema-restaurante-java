package app;

import cardapio.ItemCardapio;
import cardapio.Lanche;
import pedido.Pedido;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println("\n=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        return lerInteiro("Escolha: ");
    }

    public String lerNomeCliente() {
        System.out.print("Nome do cliente: ");
        return scanner.nextLine();
    }

    public int lerOpcaoLanche() {
        System.out.println("1 - X-Burger (R$ 16,00)");
        System.out.println("2 - X-Salada (R$ 18,00)");
        System.out.println("3 - X-Bacon  (R$ 22,00)");
        return lerInteiro("Qual lanche? ", 1, 3);
    }

    public boolean perguntarMaisItem() {
        System.out.print("Adicionar mais um item? (s/n) ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("Pedido " + pedido.getNumero() + " - " + pedido.getNomeCliente());
        for (ItemCardapio l : pedido.getItens()) {
            System.out.printf("  %s - R$ %.2f%n", l.getDescricao(), l.getPreco());
        }
        System.out.printf("  Total: R$ %.2f%n", pedido.calcularTotal());
    }

    public void exibirPedidos(ArrayList<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido hoje.");
            return;
        }
        for (Pedido p : pedidos) {
            exibirPedido(p);
        }
    }

    public void exibirFechamentoDoDia(double total, HashSet<String> combinacoes) {
        System.out.printf("Fechamento do dia: R$ %.2f%n", total);
        System.out.println("Combinações diferentes: " + combinacoes.size());
        for (String c : combinacoes) {
            System.out.println("  " + c);
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }


    public  boolean perguntarRetirar(String ingrediente){
        System.out.print("Retirar " + ingrediente + "? (s/n) ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }
    public boolean perguntarAdicionar(String adicional) {
        System.out.print("Adicionar " + adicional + "? (s/n) ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }

    public int lerNumeroPedido() {
        return lerInteiro("Número do pedido: ");
    }


    public void exibirMenuModificar() {
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");
    }

    public void exibirItensNumerados(Pedido pedido) {
        ArrayList<ItemCardapio> itens = pedido.getItens();
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf("%d - %s - R$ %.2f%n",
                    i + 1, itens.get(i).getDescricao(), itens.get(i).getPreco());
        }
    }


    public int lerNumeroItem() {
        return lerInteiro("Número do item: ") - 1;
    }
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
    private int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.println("Opção inválida. Escolha entre " + min + " e " + max + ".");
        }
    }
    public void exibirLanchesBase(HashSet<String> bases) {
        System.out.println("Lanches base diferentes: " + bases.size());
        for (String b : bases) {
            System.out.println("  " + b);
        }
    }
}