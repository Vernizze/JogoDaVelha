package com.uninter.utils;

import java.util.Scanner;

//Classe para interação com a digitação vinda do Jogador Humano (Singleton)
public class Teclado {
    //Variável estática de implementação do Padrão Singleton
    private static Teclado _instance;

    private final Scanner _teclado;

    private Teclado() {
        //Inicialização do Scanner
        _teclado = new Scanner(System.in);
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static Teclado instance() {
        if (_instance == null)
        _instance = new Teclado();

    return _instance;
    }

    //Método que retorna o que o Jogador Humano digitou
    public String getValorDigitado() {
        return _teclado.next();
    }

    //Dispose de memória do Scanner
    public void dispose() {
        _teclado.close();
    }
}
