package com.uninter.niveisBot;

import java.util.ArrayList;
import java.util.Collections;

import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.INivelDificuldadeBot;
import com.uninter.servicos.Jogo;
import com.uninter.utils.RandomHandler;

//Implementação do Nivel Fácil de Jogo
public class NivelMedio implements INivelDificuldadeBot {
    //Getter de obtenção do Nível de Dificuldade
    @Override
    public TNivelDificuldade getNivelDificuldade() {
        return TNivelDificuldade.Medio;
    }

    //Método que obtéma Posição da Jogada no Nível Médio
    @Override
    public String getJogada() {
        //Obtém as Posições Livres do Tabuleiro
        ArrayList<String> posicoesLivres = Jogo.instance().getTabuleiro().getPosicoesLivres();

        //Verifica-se se há Posições Livres disponíveis
        if (!posicoesLivres.isEmpty()) {
            Collections.sort(posicoesLivres);
            
            //Se sim, define-se um valor random dentro da quantidade de índices 
            //da Lista de Posições Livres e retorna seu valor correspondente
            return posicoesLivres.get(RandomHandler.instance().getRandomMaiorQueZero(posicoesLivres.size()));
        }

        //Caso não haja Posições Livres, retorna vazio
        return "";
    }    
}
