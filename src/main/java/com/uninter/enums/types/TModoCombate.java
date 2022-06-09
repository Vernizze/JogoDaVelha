package com.uninter.enums.types;

//Enum de definição do Modo de Combate utilizado pelo Bot no Nível Difícil
public enum TModoCombate {
    Defensivo(0), 
    Agressivo(1);

    public final int value;

    TModoCombate(int val)
    {
        this.value = val;
    }

    static public boolean isMember(int checkValue) {
        TModoCombate[] modosAtaqueBot = TModoCombate.values();
        for (TModoCombate modoAtaqueBot : modosAtaqueBot)
            if (modoAtaqueBot.value == checkValue)
                return true;
        return false;
    }
}
