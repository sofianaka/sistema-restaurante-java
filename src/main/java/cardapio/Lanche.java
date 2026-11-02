package cardapio;

public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;

    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate  = true;
    private boolean cebola = true;


    public Lanche(String nome, double preco){
        setNome(nome);
        setPreco(preco) ;
    }
    @Override
    public String getNomeBase() {
        return nome;
    }

    @Override
    public String getDescricao() {
        String desc = nome;

        if (!queijo) desc += " sem queijo";

        if (!alface) desc += " sem alface";

        if (!tomate) desc += " sem tomate";

        if (!cebola) desc += " sem cebola";
        return desc;
    }

    public double getPreco(){
        return preco;


    }

    public void setPreco(double preco){
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
    }


    public String getNome(){
        return nome;
    }

    public void setNome( String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }

    public boolean getQueijo(){
        return queijo;
    }

    public void setQueijo(boolean queijo){
        this.queijo = queijo;
    }


    public boolean getAlface(){
        return  alface;
    }
    public  void setAlface(boolean alface){
        this.alface =alface;
    }


    public boolean getTomate(){
        return tomate;
    }


    public void setTomate(boolean tomate){
        this.tomate = tomate;
    }


    public boolean getCebola(){
        return  cebola;
    }

    public void setCebola(boolean cebola){
        this.cebola = cebola;
    }
}
