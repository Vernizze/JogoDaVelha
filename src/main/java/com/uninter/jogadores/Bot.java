package com.uninter.jogadores;

import com.uninter.enums.types.TJogador;
import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.IJogador;
import com.uninter.interfaces.INivelDificuldadeBot;
import com.uninter.niveisBot.NivelDificuldadeBotFactory;
import com.uninter.servicos.Jogo;
import com.uninter.types.Tupla;
import com.uninter.utils.Printer;

//Jogador (IJogador) que eserá controlado pelo Computador
public class Bot implements IJogador {
    private TNivelDificuldade _nivelDificuldade = TNivelDificuldade.Facil;
    private INivelDificuldadeBot _nivelDificuldadeBot;

    //Getter de obtenção do Tipo de Jogador 
    @Override
    public TJogador getTipoJogador() {
        return TJogador.Bot;
    }

    //Setter de configuração do Nível de Difilidade do Jogador
    @Override
    public void setNivelDificuldade(TNivelDificuldade nivelDificuldade) {
        _nivelDificuldade = nivelDificuldade;
        _nivelDificuldadeBot = NivelDificuldadeBotFactory.instance().getNivelBot(_nivelDificuldade);
    }

    //Getter de obtenção do Nível de Difilidade do Jogador
    @Override
    public TNivelDificuldade getNivelDificuldade() {
        return _nivelDificuldade;
    }

    //Método que executa a jogada do Jogador
    //Retorna um Boolean com a informação se o jogador conseguiu realizar a jogada 
    //e um String com a Posição selecionada no Tabuleiro
    @Override
    public Tupla<Boolean, String> play() {        
        Printer.imprimirLinha("Jogada do: Bot");

        //Pede-se a jogada ao Resolvedor de Níveis de Dificuldade da Partida
        String posicao = _nivelDificuldadeBot.getJogada();

        //Se o Resolvedor retornou uma posição, pede-se ao Tabuleiro para gravá-la como efetuada
        //O retorno dado pelo Tabuleiro e a Posição jogada são retornados
        if (posicao != "")
            return new Tupla<Boolean, String> (Jogo.instance().getTabuleiro().setJogada(getTipoJogador(), posicao), posicao);

        //Em caso de não ser possível realizar a jogada, retoan-se 'False' e uma string vazia
        return new Tupla<Boolean, String> (false, "");
    }
}
