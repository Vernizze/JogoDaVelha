package com.uninter.jogadores;

import com.uninter.enums.types.*;
import com.uninter.interfaces.IJogador;
import com.uninter.servicos.Jogo;
import com.uninter.types.Tupla;
import com.uninter.utils.Printer;
import com.uninter.utils.Teclado;

//Jogador (IJogador) que eserá controlado pelo Computador
public class Humano implements IJogador {

    //Getter de obtenção do Tipo de Jogador 
    @Override
    public TJogador getTipoJogador() {
        return TJogador.Humano;
    }

    //Setter de configuração do Nível de Difilidade do Jogador
    //Observação! Não implementado devido ao Nível de dificuldade ser oriundo do próprio Jogador Humano
    @Override
    public void setNivelDificuldade(TNivelDificuldade nivelDificuldade) {
    }

    //Getter de obtenção do Nível de Difilidade do Jogador
    //Observação! Não implementado de forma fixa por se inferir que o Jogador Humano sempre tem o Níve de Dificuldade 'Difícil'
    @Override
    public TNivelDificuldade getNivelDificuldade() {
        return TNivelDificuldade.Dificil;
    }

    //Método que executa a jogada do Jogador
    //Retorna um Boolean com a informação se o jogador conseguiu realizar a jogada 
    //e um String com a Posição selecionada no Tabuleiro
    @Override
    public Tupla<Boolean, String> play() {
        Printer.imprimirLinha("Jogada do: Humano");

        //Pede-se que o Jogador Humano digite a Posição que deseja
        Printer.imprimirLinha("Digite a posição:");

        //Captura-se a digitação do Jogador Humano
		String posicaoDigitada = Teclado.instance().getValorDigitado();

        //Transforma-se o valor caputado em 'Upper Case'
        String posicao = posicaoDigitada.toUpperCase();

        //Passa-se a Posição informada ao Tabuleiro para efetivação da Jogada
        //Se a jogada for Inválida, o Tabuleiro retornará False
        return new Tupla<Boolean, String>(Jogo.instance().getTabuleiro().setJogada(getTipoJogador(), posicao), posicao);
    }
}
