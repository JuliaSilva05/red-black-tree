import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class RBTree<T extends Comparable<T>> {

    private RBNode<T> root;
    private boolean status;
    
    public boolean isEmpty () {
        if (this.root == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
    public void insert(T value){
        if (this.isEmpty()==true){
            this.root = new RBNode<T>(value);
            this.root.setColor("black");
        }
        else{
            this.root = insertNode(this.root, value);
            this.status = false;
        }
    }
    private RBNode<T> insertNode(RBNode<T> r, T value){
        if (r==null){
            r = new RBNode<T>(value);
            r.setColor("red");
            this.status = true;
        }
        else if (r.getInfo().compareTo(value)>0){
            r.setLeft(insertNode(r.getLeft(),value));
            decideWhatToDo(r);
        }
        else {
            r.setRight(insertNode(r.getRight(), value));
            decideWhatToDo(r);
        }
        return r;
    }
    */

    
    public void insert(T value){
        RBNode<T> novo;
        novo = new RBNode<T>(value);
        novo.setColor("red");
        if (this.isEmpty()==true){
            this.root = novo;
            novo.setColor("black");
            novo.setFather(null);
            novo.setStatus(true);
            //createNils(root);
        }
        else{
            RBNode<T> aux = this.root;
            int retorno;
            while (true) {
                //////////
                retorno = value.compareTo(aux.getInfo());
                if (retorno == 0) {
                    System.out.println("Código repetido. Inserção não efetuada! \n");
                    return;
                }
                else if (retorno < 0) {
                    if (aux.getLeft() != null) {
                        aux = aux.getLeft();
                    }
                    else {
                        aux.setLeft(novo);
                            novo.setFather(aux);
                        System.out.println("Inserção efetuada! \n");
                        // aux = pai, novo = filho
                        novo.setStatus(true);
                        decideWhatToDo(novo);
                        return;
                    }
                }
                else {
                    if (aux.getRight() != null) {
                        aux = aux.getRight();
                    }
                    else {
                        aux.setRight(novo);
                            novo.setFather(aux);
                        System.out.println("Inserção efetuada! \n");
                        novo.setStatus(true);
                        decideWhatToDo(novo);
                        return;
                    }                   
                }
                ///////////
            }
        }
    }
    
    private void decideWhatToDo(RBNode<T> k){
        if (k != root){
            while ((k.getFather().getColor()).compareTo("red")==0){
                RBNode<T> p,g,s;
                p = k.getFather();
                g = p.getFather();
                if (g != null){
                    if (p==g.getLeft()){ // se o p for filho esquerdo
                        s = g.getRight();
                        // se o tio for vermelho
                        if (s!=null && s.getColor().compareTo("red")==0){
                            p.setColor("black");
                            g.setColor("red");
                            s.setColor("black");
                            //k = g;
                        }
                        else{
                            System.out.println("oi");
                            if (k==p.getRight()){
                                k = p;
                                rotationLeft(k);
                                p = k.getFather();
                                g = p.getFather();
                            }
                            p.setColor("black");
                            g.setColor("red");
                            rotationRight(g);
                        }
                    }
                    else if (p==g.getRight()) {
                        s = g.getLeft();
                        if (s!=null && s.getColor().compareTo("red")==0){
                            p.setColor("black");
                            g.setColor("red");
                            s.setColor("black");
                            //k = g;
                        }
                        else {
                            if (k == p.getLeft()){
                                k = p;
                                rotationRight(k);
                                p = k.getFather();
                                g = p.getFather();
                            }
                            p.setColor("black");
                            g.setColor("red");
                            rotationLeft(g);
                        }
                    }
                }
                else{
                    System.out.println("hgfh");
                    System.out.println(k.getInfo());
                    if (k == this.root.getLeft()){
                        System.out.println("hgfh");
                        if (this.root.getRight()!=null && this.root.getRight().getColor()=="red"){
                            this.root.getLeft().setColor("red");
                        } else {
                            this.root.getLeft().setColor("black");
                        }
                        
                    }else if (k == this.root.getRight()){
                        System.out.println();
                        if (this.root.getLeft()!=null && this.root.getLeft().getColor()=="red"){
                            this.root.getRight().setColor("red");
                        } else {
                            this.root.getRight().setColor("black");
                        }
                        
                    }
                }

                if (this.root.getColor().compareTo("red")==0){
                    this.root.setColor("black");
                }

            }
        }
    }

    


    private void rotationLeft(RBNode<T> a){
        RBNode<T> b = a.getRight();
        a.setRight(b.getLeft());
        if(b.getLeft()!=null){
            b.getLeft().setFather(a);
        }
        if (a == this.root){
            this.root = b;
            b.setFather(null);
        }
        else if (a == a.getFather().getLeft()){
            a.getFather().setLeft(b);
            b.setFather(a.getFather());
        }
        else{
            a.getFather().setRight(b);
            b.setFather(a.getFather());
        }
 
        b.setLeft(a);
        a.setFather(b);
        
    }

    private void rotationRight(RBNode<T> a){
        RBNode<T> b = a.getLeft();
        a.setLeft(b.getRight());
        if (b.getRight()!=null && b.getLeft()!=null){
            b.getRight().setFather(a);
        }
        if (a == this.root){
            this.root = b;
            b.setFather(null);
        }
        else if (a == a.getFather().getLeft()){
            a.getFather().setLeft(b);
            b.setFather(a.getFather());
        }
        else {
            a.getFather().setRight(b);
            b.setFather(a.getFather());
        }
        b.setRight(a);
        a.setFather(b);
    }

    private void createNils(RBNode<T> r){
        RBNode<T> leaf1 = new RBNode<T>(null);
        RBNode<T> leaf2 = new RBNode<T>(null);
        leaf1.setFather(r);
        leaf2.setFather(r);
        leaf1.setColor("black");
        leaf2.setColor("black");
        leaf1.setLeft(null);
        leaf1.setRight(null);
        leaf2.setLeft(null);
        leaf2.setRight(null);
        
        r.setLeft(leaf1);
        r.setRight(leaf2);
    }


    

    public void emOrdem(){
        if (this.isEmpty()==true){
            System.out.println("Arvore vazia!");
        } else{
            this.passeioEmOrdem(root);
        }
    }
    private void passeioEmOrdem(RBNode<T> r){
        if (r != null){
            passeioEmOrdem(r.getLeft());
            if (r.getStatus()==true){
                System.out.println(r.getInfo() +" "+ r.getColor());    
            } else {
                System.out.println("null");
            }
            passeioEmOrdem(r.getRight());
        }
    }

    public void porNivel(){
        if (this.isEmpty()==true){
            System.out.println("Arvore vazia!");
        } else{
            this.passeioPorNivel(this.root);
        }
    }
    private void passeioPorNivel(RBNode<T> r){
        if (r != null){
            Queue<RBNode<T>> fila = new ArrayDeque<>();
            fila.add(r);
            while (fila.isEmpty() == false) {        
                RBNode<T> aux = fila.remove();
                    if (aux.getLeft()==null){
                        System.out.print("NIL ");
                    }

                    System.out.print(aux.getInfo() +" "+ aux.getColor() +" "+ aux.getStatus());
                    
                    if (aux.getLeft() != null){
                        fila.add(aux.getLeft());
                    } 
                    if (aux.getRight() != null){
                        fila.add(aux.getRight());
                    }else {
                        System.out.print(" NIL");
                    }

                    
                    if (aux.getFather() != null){
                        System.out.print(" father:"+aux.getFather().getInfo());
                    }else {
                        System.out.print(" father:null");
                    }

                    System.out.println("");
                
            }
        }
        
    }

    private RBNode<T> search(RBNode<T> r, T value){
        RBNode<T> aux = r;
        while (aux.getInfo().compareTo(value) != 0){
            if (aux.getInfo().compareTo(value) > 0){
                aux = aux.getLeft();
            } else{
                aux = aux.getRight();
            }
            if (aux == null){
                return null;
            }
        }
        return aux;
    }

    public void removeLazy (T value){
        if (this.isEmpty()==true){
            System.out.println("Árvore vazia!");
        } else {
            removeNodeLazy(root, value);
        }
    }
    private void removeNodeLazy(RBNode<T> r, T value){
        RBNode<T> aux;
        if (r != null){
            aux = search(this.root, value);
            // se value for achado
            if (aux != null){
                aux.setStatus(false);
            }
            else {
                System.out.println("Valor não encontrado!");
            }
        }
    }

    public void removeEfe(T value){
        if (this.isEmpty()==true){
            System.out.println("Árvore vazia!");
        } else {
            removeNodeEfe(root, value);
        }
    }
    
    private void removeNodeEfe(RBNode<T> r, T value){
        RBNode<T> aux;
        if (r != null){
            aux = search(this.root, value);
        
        // se value for achado
            if (aux != null){
                // se for uma folha
                if (aux.getLeft()==null && aux.getRight()==null){
                    // se for uma folha vermelha
                    if (aux.getColor()=="red"){
                        aux.setStatus(false);
                        // se for uma folha vermelha direita
                        if (aux == aux.getFather().getRight()){
                            aux.getFather().setRight(null);
                        }
                        // se for uma folha vermelha esquerda
                        else if (aux == aux.getFather().getLeft()){
                            aux.getFather().setLeft(null);
                        }
                    }
                    // se for uma folha preta
                    else if (aux.getColor()=="black") {
                        System.out.println("folha preta");
                        if (aux == this.root){
                            this.root = null;
                        }
                        else {
                            // se for uma folha preta direita
                            if (aux == aux.getFather().getRight()){
                                aux.getFather().setRight(null);
                                RBNode<T> test = aux.getFather();
                                while(test.getLeft()!= null){
                                    test = test.getLeft();
                                }
                                if (test.getFather().getColor()=="black"){
                                    test.getFather().setColor("red");
                                }
                                decideWhatToDo(test);
                            }
                            
                            else if (aux == aux.getFather().getLeft()) {
                                aux.getFather().setLeft(null);
                                RBNode<T> test = aux.getFather();
                                while(test.getRight()!= null){
                                    test = test.getRight();
                                }
                                if (test.getFather().getColor()=="black"){
                                    test.getFather().setColor("red");
                                }
                                decideWhatToDo(test);                                
                            }                            
                        }
                    }                    
                }
                
                // se tiver 1 subarvore à direita
                else if (aux.getLeft()==null){                    
                    // se aux for a raiz
                    if (aux == this.root){
                        this.root = aux.getRight();
                        this.root.setColor("black");
                        this.root.setFather(null);
                    }
                    else {
                        //aux.getFather().setColor("red");
                        // se aux for filho direito
                        if (aux == aux.getFather().getRight()){  
                            aux.getFather().setRight(aux.getRight());
                            aux.getRight().setFather(aux.getFather());
                            aux = aux.getFather().getRight();
                            System.out.println(aux.getInfo()+" "+aux.getColor());
                            System.out.println(aux.getFather().getInfo()+" "+aux.getFather().getColor());
                            
                                aux.setColor("black");
                                                                                    
                        // se aux for filho esquerdo
                        } else if (aux == aux.getFather().getLeft()){
                            aux.getRight().setFather(aux.getFather());
                            aux.getFather().setLeft(aux.getRight());
                            aux = aux.getFather().getLeft();

                            
                                aux.setColor("black");
                            
                        }
                    }
                }
                
                // se tiver 1 subarvore à esquerda
                else if (aux.getRight()==null){
                    if (aux == this.root){
                        this.root = aux.getLeft();
                        this.root.setColor("black");
                        this.root.setFather(null);
                    } 
                    else {
                        //aux.getFather().setColor("red");
                        // se aux for filho direito
                        if (aux == aux.getFather().getRight()){
                            aux.getLeft().setFather(aux.getFather());
                            aux.getFather().setRight(aux.getLeft());
                            aux = aux.getFather().getRight();
                            
                                aux.setColor("black");                            
                        
                        // se aux for filho esquerdo
                        } else if (aux == aux.getFather().getLeft()){
                            aux.getLeft().setFather(aux.getFather());
                            aux.getFather().setLeft(aux.getLeft());
                            aux = aux.getFather().getLeft();
                            
                                aux.setColor("black");
                            
                        }
                    }
                }
                
                // se tiver 2 subarvores
                else {
                    RBNode<T> subs = aux;
                    subs = aux.getLeft();
                    while (subs.getRight() != null){
                        subs = subs.getRight();
                    }
                    System.out.println("Achado:"+subs.getInfo());
                    if (subs.getColor()=="red"){
                        aux.setInfo(subs.getInfo());
                        if (subs == subs.getFather().getRight()){
                            subs.getFather().setRight(null);
                        }
                        else if (subs == subs.getFather().getLeft()){
                            subs.getFather().setLeft(null);
                        }
                    }
                    
                    //se a cor do substituto for preto
                    else if (subs.getColor()=="black") {
                        System.out.println("Substituir "+aux.getInfo()+"("+aux.getColor()+") por "+subs.getInfo()+"("+subs.getColor()+")");
                        aux.setInfo(subs.getInfo());
                        // se o substituto tiver uma subarvore esquerda
                        if (subs.getLeft() != null){
                            aux.setColor("red");
                            // subs estiver a direita
                            if (subs == subs.getFather().getRight()){
                                subs.getFather().setRight(subs.getLeft());
                                subs.getFather().getRight().setFather(subs.getFather());
                                subs = subs.getFather().getRight();
                            // subs estiver na esquerda
                            } else if (subs == subs.getFather().getLeft()){
                                subs.getFather().setLeft(subs.getLeft());
                                subs.getFather().getLeft().setFather(subs.getFather());
                                subs = subs.getFather().getLeft();
                                System.out.println("mahoe");
                            }
                            System.out.println(subs.getInfo()+" "+subs.getColor());
                            decideWhatToDo(subs);
                        }
                        // se o substituto não tiver subárvore
                        else {
                            System.out.println("sem subarvore");
                            // substituto é filho direito
                            if (subs == subs.getFather().getRight()){
                                System.out.println("subs é fihlo direito");
                                subs = subs.getFather();
                                subs.setRight(null);
                                while (subs.getLeft()!=null) {
                                    subs = subs.getLeft();
                                }
                                while (subs.getRight()!=null) {
                                    subs = subs.getRight();
                                }
                                subs.getFather().setColor("red");
                                decideWhatToDo(subs);
                            // substituto é filho esquerdo
                            } else if (subs == subs.getFather().getLeft()){
                                subs = subs.getFather();
                                subs.setLeft(null);
                                subs.getRight().setColor("red");
                                subs.setColor("red");
                                System.out.println("askjfh");
                                while (subs.getRight()!=null){
                                    subs = subs.getRight();
                                }
                                System.out.println(subs.getInfo());
                                decideWhatToDo(subs);
                            }                            
                        }
                    }
                }
            } else {
                System.out.println("Valor não achado!");
            }
        }
    }
    

    private void afterRemoval(RBNode<T> k){
        RBNode<T> p, g, s;
        p = k.getFather();

    }
}
