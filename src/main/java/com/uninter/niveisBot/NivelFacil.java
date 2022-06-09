package com.uninter.niveisBot;

import java.util.ArrayList;
import java.util.Collections;

import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.INivelDificuldadeBot;
import com.uninter.servicos.Jogo;

//Implementação do Nivel Fácil de Jogo
public class NivelFacil implements INivelDificuldadeBot {
    //Getter de obtenção do Nível de Dificuldade
    @Override
    public TNivelDificuldade getNivelDificuldade() {
        return TNivelDificuldade.Facil;
    }

    //Método que obtéma Posição da Jogada no Nível Fácil
    @Override
    public String getJogada() {
        //Obtém as Posições Livres do Tabuleiro
        ArrayList<String> posicoesLivres = Jogo.instance().getTabuleiro().getPosicoesLivres();

        //Verifica-se se há Posições Livres disponíveis
        if (!posicoesLivres.isEmpty()) {
            Collections.sort(posicoesLivres);

            //Se sim, pega-se a primeira da lista e retorna-a
            return posicoesLivres.get(0);
        }        

        //Caso não haja Posições Livres, retorna vazio
        return "";
    }
}
