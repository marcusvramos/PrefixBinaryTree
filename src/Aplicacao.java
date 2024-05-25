public class Aplicacao {
    public static void main(String[] args) {
        ArvoreBinariaPrefixo arvore = new ArvoreBinariaPrefixo();
        arvore.construir("arquivo.txt");
        System.out.println("Árvore Binária de Prefixo:");
        arvore.imprimir();

        arvore.compactar();
        System.out.println("\nÁrvore Patricia:");
        arvore.imprimir();
    }
}