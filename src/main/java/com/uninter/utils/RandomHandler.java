package com.uninter.utils;

import java.util.Random;

//Classe com gerações de dados Random (Singleton)
public class RandomHandler {
    //Variável estática de implementação do Padrão Singleton
    private static RandomHandler _instance;

    private Random _rand;

    private RandomHandler() {
        //Inicialização do gerador de Random
        _rand = new Random();
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static RandomHandler instance(){
        if (_instance == null)
            _instance = new RandomHandler();

        return _instance;
    }

    //Método que retorna um valor Integer Random maior que Zero (0)
    public int getRandomMaiorQueZero(int qtt) {
        if (qtt > 0)
            qtt--;

        return _rand.nextInt(qtt);
    }

    //Método que retorna um valor Booleano
    public boolean getRandomBoolean() {
        return _rand.nextBoolean();
    }
}
