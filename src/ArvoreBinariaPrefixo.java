import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ArvoreBinariaPrefixo {
    private No raiz;

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
        compactar(raiz, -1);
    }

    private void compactar(No no, int bitPos) {
        if (no != null) {
            no.setBitPos(bitPos);
            if (!no.isLeaf()) {
                compactar(no.getLeft(), bitPos + 1);
                compactar(no.getRight(), bitPos + 1);
            }
        }
    }
}
