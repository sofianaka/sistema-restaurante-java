package app;

import cardapio.ItemCardapio;
import pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();

        int opcao = -1;
        while (opcao != 0) {
            menu.exibirMenu();
            try {
                opcao = menu.lerOpcao();
                switch (opcao) {
                    case 1:
                        String nome = menu.lerNomeCliente();
                        Pedido pedido = sistema.criarPedido(nome);
                        boolean continuar = true;
                        while (continuar) {
                            sistema.adicionarItem(pedido, montarItem(menu, sistema));
                            continuar = menu.perguntarMaisItem();
                        }
                        menu.exibirPedido(pedido);
                        break;
                    case 2:
                        menu.exibirPedidos(sistema.listarPedidos());
                        break;
                    case 3:
                        double total = sistema.calcularTotalDoDia();
                        menu.exibirFechamentoDoDia(total, sistema.getCombinacoes());
                        menu.exibirLanchesBase(sistema.getLanchesBase());
                        break;
                    case 4:
                        int numero = menu.lerNumeroPedido();
                        Pedido p = sistema.buscarPedido(numero);
                        menu.exibirPedido(p);
                        menu.exibirMenuModificar();
                        int acao = menu.lerOpcao();
                        switch (acao) {
                            case 1:
                                sistema.adicionarItem(p, montarItem(menu, sistema));
                                menu.exibirPedido(p);
                                break;
                            case 2:
                                menu.exibirItensNumerados(p);
                                sistema.removerItem(p, menu.lerNumeroItem());
                                menu.exibirPedido(p);
                                break;
                            case 3:
                                sistema.cancelarPedido(numero);
                                menu.exibirMensagem("Pedido " + numero + " cancelado.");
                                break;
                        }
                        break;
                    case 0:
                        break;
                    default:
                        menu.exibirMensagem("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                menu.exibirMensagem("Erro: " + e.getMessage());
            }
        }
    }

    private static ItemCardapio montarItem(MenuConsole menu, RestauranteFacade sistema) {
        int escolha = menu.lerOpcaoLanche();
        boolean semQueijo = menu.perguntarRetirar("queijo");
        boolean semAlface = menu.perguntarRetirar("alface");
        boolean semTomate = menu.perguntarRetirar("tomate");
        boolean semCebola = menu.perguntarRetirar("cebola");
        boolean comCheddar = menu.perguntarAdicionar("cheddar (+R$ 4,00)");
        boolean comBaconExtra = menu.perguntarAdicionar("bacon extra (+R$ 6,00)");
        return sistema.montarItem(escolha, semQueijo, semAlface, semTomate, semCebola,
                comCheddar, comBaconExtra);
    }
}