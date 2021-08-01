import java.io.IOException;
import java.util.Scanner;

import classes.Venda;

public class App {
    public static void main(String[] args) throws Exception {
        int MAX_ELEMENTOS = 50;
        int opcao, qtdPdtCadastrados = 0;
        Venda[] produtos = new Venda[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);
        
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Incluir produto ");
            System.out.println("2 - Consultar produtos");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período - detalhado");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine();

            if (opcao == 1) {

             if (qtdPdtCadastrados == MAX_ELEMENTOS) {
                 System.out.println("\nLimite de produtos cadastrados preenchido.");
                 voltarMenu(in);
                 continue;

             }   
            
            Venda pdt = new Venda();

            System.out.print("Código: ");
            pdt.setCodigo(in.nextLine());

            System.out.print("Nome: ");
            pdt.setNome(in.nextLine());

            System.out.print("Valor: ");
            pdt.setValor(Float.parseFloat(in.nextLine()));

            System.out.print("Quantidade no estoque: ");
            pdt.setQtdEstoque(Integer.parseInt(in.nextLine()));
           
            produtos[qtdPdtCadastrados] = pdt;
            qtdPdtCadastrados = qtdPdtCadastrados + 1;

            System.out.println("\nProduto cadastrado com sucesso.");
            voltarMenu(in);
            } else if (opcao ==2 ) {
                if (qtdPdtCadastrados == 0) {
                    System.out.println("\n Não há produtos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                 }

                 System.out.print("\n Código para buscar: ");
                 String codigo = in.nextLine();
               
                 
               for (int i = 0; i < qtdPdtCadastrados; i++){
                if (codigo.equals(produtos[i].getCodigo())) {
                    exibirProduto(produtos[i]);
               
                } else { 
                    System.out.println("Produto não localizado!");
                    voltarMenu(in);
                        continue;
                    }
               }
                    voltarMenu(in);
                    continue;
             } else if (opcao == 3) {
                if (qtdPdtCadastrados == 0) {
                    System.out.println ("\n Não há produtos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("\nPRODUTOS CADASTRADOS:"); 
                System.out.println("*********************");
                for (int i = 0; i < qtdPdtCadastrados; i++){
                    exibirProduto(produtos[i]);
                }
                voltarMenu(in);
            }else if (opcao == 4) {

            }else if (opcao == 5) {
                if (qtdPdtCadastrados == 0) {
                    System.out.println("\n Não há produtos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                 }

                 System.out.print("\n Informe o nome do produto que deseja comprar: ");
                 String nomePdt = in.nextLine();
                 System.out.print("\nInforme a quantidade do produto: ");
                 int auxQtd = (Integer.parseInt(in.nextLine()));
               
                 
               for (int i = 0; i < qtdPdtCadastrados; i++){
                if (nomePdt.equals(produtos[i].getNome())) {
                    exibirProduto(produtos[i]);
                    if(auxQtd > produtos[i].getQtdEstoque()){
                        System.out.print("Quantidade acima do estoque.");
                        voltarMenu(in);
                        continue;
                    } else if(produtos[i].getNome()==null){
                        voltarMenu(in);
                    }
                    produtos[i].setValorVenda(produtos[i].getValor() * auxQtd);

                    System.out.println("\nVenda concluida.\n");
                    System.out.println("*********************");
                    System.out.printf("\nDETALHES\n");
                    System.out.printf("Nome do Produto %s", produtos[i].getNome());
                    System.out.printf("\nQuatidade vendida: %d", auxQtd);
                    System.out.printf("\nValor total: %.2f", produtos[i].getValorVenda());
                    produtos[i].setQtdEstoque( produtos[i].getQtdEstoque() - auxQtd);
                    System.out.printf("\nQuantidade restante de produtos: %d",produtos[i].getQtdEstoque());

                }else{
                    System.out.println("\n Não há produtos cadastrados para exibir.");
                    voltarMenu(in);
                }
            }
            voltarMenu(in);
                
            }else{
                System.out.println("Fim do programa!");
                in.close();
            } 
            
        }    while (opcao != 0);
        
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }

    private static void exibirProduto(Venda produto) {
        System.out.printf("\nNome do produto: %s\n", produto.getNome());
        System.out.printf("Código: %s\n", produto.getCodigo());
        System.out.printf("Valor: %.2f\n", produto.getValor());
        System.out.printf("Quantidade do produto em estoque : %d\n", produto.getQtdEstoque());
    }

}

