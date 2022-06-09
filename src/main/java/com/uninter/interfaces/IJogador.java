package com.uninter.interfaces;

import com.uninter.enums.types.TJogador;
import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.types.Tupla;

//Contrato de definição de Jogaro (Humano ou Bot)
public interface IJogador {
    TJogador getTipoJogador();
    void setNivelDificuldade(TNivelDificuldade nivelDificuldade);
    TNivelDificuldade getNivelDificuldade();
    
    Tupla<Boolean, String> play();
}
