package com.uninter.interfaces;

import com.uninter.enums.types.TNivelDificuldade;

//Contrato de definição do Nível em que o Bot atuará na Partida
public interface INivelDificuldadeBot {
    TNivelDificuldade getNivelDificuldade();
    String getJogada();
}
