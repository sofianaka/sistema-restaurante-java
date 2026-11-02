package pedido;

import cardapio.ComCheddar;
import cardapio.LancheFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GerenciadorPedidosTest {

    @Test
    void combinacoesRepetidasContamUmaVez() {
        GerenciadorPedidos g = new GerenciadorPedidos();


        Pedido p1 = g.criarPedido("Ana");
        p1.adicionarItem(LancheFactory.xBacon());

        Pedido p2 = g.criarPedido("Bia");
        p2.adicionarItem(LancheFactory.xBacon());
        p2.adicionarItem(new ComCheddar(LancheFactory.xBacon()));

        assertEquals(2, g.getCombinacoes().size());
    }

    @Test
    void fechamentoSomaTodosOsPedidos() {
        GerenciadorPedidos g = new GerenciadorPedidos();

        Pedido p1 = g.criarPedido("Ana");
        p1.adicionarItem(LancheFactory.xBurger());

        Pedido p2 = g.criarPedido("Bia");
        p2.adicionarItem(new ComCheddar(LancheFactory.xBacon()));

        assertEquals(42.0, g.calcularTotalDoDia(), 0.001);
    }

    @Test
    void cancelarPedidoRemoveDoTotal() {
        GerenciadorPedidos g = new GerenciadorPedidos();

        Pedido p1 = g.criarPedido("Ana");
        p1.adicionarItem(LancheFactory.xBurger());

        Pedido p2 = g.criarPedido("Bia");
        p2.adicionarItem(LancheFactory.xBacon());

        g.cancelarPedido(p2.getNumero());

        assertEquals(16.0, g.calcularTotalDoDia(), 0.001);
        assertNull(g.buscarPedido(p2.getNumero()));
    }
}