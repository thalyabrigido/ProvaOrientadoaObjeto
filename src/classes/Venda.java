package classes;

public class Venda extends Produto {
    private int qtdVendida;
    private float valorVenda;

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQtdVendida() {
        return qtdVendida;
    
    }

    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

}
