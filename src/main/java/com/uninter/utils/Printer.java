package com.uninter.utils;

//Classe que representa a Impressora na stdout
public class Printer {
    //Método que imprime uma nova linha em branco mais a mensagem passada por parâmetro em outra
    public static void imprimirLinha(String mensagem) {
        System.out.println();
        imprimir(mensagem);
    }

    //Imprime a mensagem passada por parâmetro em uma linha
    public static void imprimir(String mensagem) {
        System.out.printf(mensagem);
    }
}
