import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        RBTree arvore = new RBTree();
        int op, num;
        Scanner input = new Scanner(System.in);
    
        do {
            exibirOpcoes();
            op = input.nextInt();
            switch(op) {
                case 1: 
                    System.out.println("Diga um numero");
                    num = input.nextInt();
                    arvore.insert(num);
                    break;
                case 2:
                    
                    break;
                case 3: 
                    System.out.println("Em ordem:\n");
                    arvore.emOrdem();
                    break;
                case 4:
                    System.out.println("Por nível:\n");
                    arvore.porNivel();
                    break;
                case 5:
                    System.out.println("Remoção: ");
                    num = input.nextInt();
                    arvore.removeLazy(num);
                    break;
                case 6:
                    System.out.println("Remoção efetiva:");
                    num = input.nextInt();
                    arvore.removeEfe(num);
                case 0:
                    System.out.println("Bye bye!");
                    break;                    
            }
        } while (op != 0);
        input.close();
    }
    public static void exibirOpcoes () {
        System.out.println("\nOpções");
        System.out.println("1 - Inserir valor");
        System.out.println("2 - Buscar valor");
        System.out.println("3 - Exibir em ordem");
        System.out.println("4 - Exibir por nível");
        System.out.println("5 - Remoção preguiçosa");
        System.out.println("6 - Remoção efetiva");
        System.out.print("Informe a opção: ");
    }
    
}
