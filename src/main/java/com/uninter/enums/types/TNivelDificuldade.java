package com.uninter.enums.types;

//Enum de definição da Dificuldade que a partida terá
public enum TNivelDificuldade {
    Facil(1), 
    Medio(2), 
    Dificil(3);

    public final int value;

    TNivelDificuldade(int val)
    {
        this.value = val;
    }

    static public boolean isMember(int checkValue) {
        TNivelDificuldade[] niveisDificuldade = TNivelDificuldade.values();
        for (TNivelDificuldade nivelDificuldade : niveisDificuldade)
            if (nivelDificuldade.value == checkValue)
                return true;
        return false;
    }
}