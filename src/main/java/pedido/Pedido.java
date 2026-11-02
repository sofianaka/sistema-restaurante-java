package pedido;

import cardapio.ItemCardapio;


import java.util.ArrayList;

public class Pedido {
   private int numero;
   private String nomeCliente;
   private ArrayList<ItemCardapio> itens = new ArrayList<>();


   public Pedido(int numero, String nomeCliente){
       this.numero  = numero;
       setNomeCliente(nomeCliente);
   }

   public int getNumero(){
       return numero;
   }

   public String getNomeCliente(){
       return nomeCliente;
   }

   public void setNomeCliente(String nomeCliente){
       if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
           throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
       }
       this.nomeCliente = nomeCliente;
   }

   public void adicionarItem(ItemCardapio item ){
       if (itens == null) {
           throw new IllegalArgumentException("Lanche não pode ficar vazio");
       }

       itens.add(item);
   }public void removerItem(int indice) {
        if (indice < 0 || indice >= itens.size()) {
            throw new IllegalArgumentException("Item inválido");
        }
        itens.remove(indice);
    }


   public  ArrayList<ItemCardapio> getItens(){
       return new ArrayList<>(itens);
   }

   public double calcularTotal(){
       double total = 0;
       for(ItemCardapio item: itens){
           total+= item.getPreco();
       }
       return total;
   }
}
