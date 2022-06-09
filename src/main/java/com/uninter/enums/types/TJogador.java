package com.uninter.enums.types;

//Enum de definição dos Tipo de Jogadores da Partida
public enum TJogador  {
    None(0),
    Humano(1), 
    Bot(-1);

    public final int value;

    TJogador(int val)
    {
        this.value = val;
    }

    static public boolean isMember(int checkValue) {
        TJogador[] jogadores = TJogador.values();
        for (TJogador jogador : jogadores)
            if (jogador.value == checkValue)
                return true;
        return false;
    }
}
