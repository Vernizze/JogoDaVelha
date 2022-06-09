package com.uninter.enums.status;

//Enum de definição do Status em que encontra-se a Partida
public enum SJogo {
    NaoIniciado(0), 
    Rodada01(1),
    Rodada02(2),
    Rodada03(3),
    Rodada04(4),
    Rodada05(5),
    Rodada06(6),
    Rodada07(7),
    Rodada08(8),
    Rodada09(9),
    Empate(10),
    Vitoria(11),    
    Finalizado(12);

    public final int value;

    SJogo(int val)
    {
        this.value = val;
    }

    static public boolean isMember(int checkValue) {
        SJogo[] statusJogo = SJogo.values();
        for (SJogo status : statusJogo)
            if (status.value == checkValue)
                return true;
        return false;
    }
}