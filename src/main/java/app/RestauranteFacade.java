package app;

import cardapio.*;
import pedido.GerenciadorPedidos;
import pedido.Pedido;
import java.util.ArrayList;
import java.util.HashSet;

public class RestauranteFacade {
    private GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public Pedido criarPedido(String nomeCliente) {
        return gerenciador.criarPedido(nomeCliente);
    }

    public Lanche criarLanche(int opcao) {
        switch (opcao) {
            case 1: return LancheFactory.xBurger();
            case 2: return LancheFactory.xSalada();
            case 3: return LancheFactory.xBacon();
            default: throw new IllegalArgumentException("Lanche inválido");
        }
    }

    public ItemCardapio montarItem(int opcao,
                                   boolean semQueijo, boolean semAlface,
                                   boolean semTomate, boolean semCebola,
                                   boolean comCheddar, boolean comBaconExtra) {
        Lanche lanche = criarLanche(opcao);

        if (semQueijo) lanche.setQueijo(false);
        if (semAlface) lanche.setAlface(false);
        if (semTomate) lanche.setTomate(false);
        if (semCebola) lanche.setCebola(false);

        ItemCardapio item = lanche;
        if (comCheddar) item = new ComCheddar(item);
        if (comBaconExtra) item = new ComBaconExtra(item);
        return item;
    }

    public void adicionarItem(Pedido pedido, ItemCardapio item) {
        pedido.adicionarItem(item);
    }

    public ArrayList<Pedido> listarPedidos() {
        return gerenciador.getPedidos();
    }

    public Pedido buscarPedido(int numero) {
        Pedido pedido = gerenciador.buscarPedido(numero);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido " + numero + " não encontrado");
        }
        return pedido;
    }

    public void removerItem(Pedido pedido, int indice) {
        pedido.removerItem(indice);
    }

    public void cancelarPedido(int numero) {
        gerenciador.cancelarPedido(numero);
    }

    public HashSet<String> getCombinacoes() {
        return gerenciador.getCombinacoes();
    }

    public double calcularTotalDoDia() {
        return gerenciador.calcularTotalDoDia();
    }
    public HashSet<String> getLanchesBase() {
        return gerenciador.getLanchesBase();
    }
}