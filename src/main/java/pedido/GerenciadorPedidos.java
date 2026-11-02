package pedido;

import cardapio.ItemCardapio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorPedidos {

    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;


    public Pedido criarPedido(String nomeCliente) {
        Pedido novo = new Pedido(contadorPedidos, nomeCliente);
        pedidos.put(novo.getNumero(), novo);
        contadorPedidos++;
        return novo;
    }


    public ArrayList<Pedido> getPedidos() {
        return new ArrayList<>(pedidos.values());
    }


    public double calcularTotalDoDia() {
        double total = 0;
        for (Pedido p : pedidos.values()) {
            total += p.calcularTotal();
        }
        return total;
    }
    public void cancelarPedido(int numero) {
        if (!pedidos.containsKey(numero)) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedidos.remove(numero);
    }

    public HashSet<String> getCombinacoes() {
        HashSet<String> combinacoes = new HashSet<>();
        for (Pedido p : pedidos.values()) {
            for (ItemCardapio item : p.getItens()) {
                combinacoes.add(item.getDescricao());
            }
        }
        return combinacoes;
    }

    public HashSet<String> getLanchesBase() {
        HashSet<String> bases = new HashSet<>();
        for (Pedido p : pedidos.values()) {
            for (ItemCardapio item : p.getItens()) {
                bases.add(item.getNomeBase());
            }
        }
        return bases;
    }

    public Pedido buscarPedido(int numero) {
        return pedidos.get(numero);
    }

}
