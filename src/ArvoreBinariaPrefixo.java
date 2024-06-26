import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

class ArvoreBinariaPrefixo {
    private final No raiz;

    public ArvoreBinariaPrefixo() {
        this.raiz = new No(null, false);
    }

    public void construir(String arq) {
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(" ");
                String string = partes[0];
                String codigoBinario = partes[1];
                inserir(string, codigoBinario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inserir(String string, String codigoBinario) {
        No atual = raiz;
        for (int i = 0; i < codigoBinario.length(); i++) {
            char bit = codigoBinario.charAt(i);
            if (bit == '0') {
                if (atual.getLeft() == null) {
                    atual.setLeft(new No(null, false));
                }
                atual = atual.getLeft();
            } else {
                if (atual.getRight() == null) {
                    atual.setRight(new No(null, false));
                }
                atual = atual.getRight();
            }
        }
        atual.setCaracter(string);
        atual.setLeaf(true);
    }

    public void imprimir() {
        imprimir(raiz, "");
    }

    private void imprimir(No no, String prefix) {
        if (no != null) {
            if (no.isLeaf()) {
                System.out.println(prefix + ": " + no.getCaracter());
            }
            imprimir(no.getLeft(), prefix + "0");
            imprimir(no.getRight(), prefix + "1");
        }
    }

    public void compactar() {
        Stack<No> pilha = new Stack<>();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No no = pilha.pop();

            if (no != null && !no.isLeaf())  {
                No esquerda = no.getLeft();
                No direita = no.getRight();

                if (esquerda != null && direita == null) {
                    substituirNo(no, esquerda);
                    pilha.push(no);
                } else if (esquerda == null && direita != null) {
                    substituirNo(no, direita);
                    pilha.push(no);
                } else {
                    if (direita != null) {
                        pilha.push(direita);
                    }
                    if (esquerda != null) {
                        pilha.push(esquerda);
                    }
                }
            }
        }
    }

    private void substituirNo(No no, No novo) {
        no.setCaracter(null);
        no.setLeaf(false);
        no.setLeft(novo.getLeft());
        no.setRight(novo.getRight());
        no.setCaracter(novo.getCaracter());
        no.setLeaf(novo.isLeaf());
    }
}
