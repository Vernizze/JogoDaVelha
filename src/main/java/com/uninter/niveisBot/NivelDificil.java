package com.uninter.niveisBot;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;

import com.uninter.enums.types.TModoCombate;
import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.INivelDificuldadeBot;
import com.uninter.servicos.Jogo;
import com.uninter.utils.Printer;
import com.uninter.utils.RandomHandler;

//Implementação do Nivel Difícil de Jogo
public class NivelDificil  implements INivelDificuldadeBot {
    private TModoCombate _modoAtaque = TModoCombate.Defensivo;

    public NivelDificil() {
        //Busca de forma randômica se o Modo de Jogo será Agressivo ou Defensivo
        boolean utilizarModoAgressivo = RandomHandler.instance().getRandomBoolean();

        //Seta o Modo de Jogo Agressivo
        if (utilizarModoAgressivo)
            _modoAtaque = TModoCombate.Agressivo;

        Printer.imprimirLinha(MessageFormat.format("Modo de Combate => {0}", _modoAtaque.name()));
    }

    //Getter de obtenção do Nível de Dificuldade
    @Override
    public TNivelDificuldade getNivelDificuldade() {
        return TNivelDificuldade.Dificil;
    }

    //Método que obtéma Posição da Jogada no Nível Difícil
    @Override
    public String getJogada() {
        String jogada = "";

        //Obtém as possibilidades 'Pela Boa' do Jogador Humano
        ArrayList<String> pelaBoaHumano = Jogo.instance().GetPelaBoaHumano();
        //Obtém as possibilidades 'Pela Boa' do Jogador Bot
        ArrayList<String> pelaBoaBot = Jogo.instance().GetPelaBoaBot();
        //Obtém as Posições Livres do Tabuleiro
        ArrayList<String> posicoesLivres = Jogo.instance().getTabuleiro().getPosicoesLivres();

        switch (_modoAtaque) {
            case Defensivo:
                //Obtém a Jogada no Modo Defensivo
                jogada = GetJogadaDefensiva(pelaBoaHumano, pelaBoaBot);
                break;
            case Agressivo:
                //Obtém a Jogada no Modo Agressivo
                jogada = GetJogadaAgressiva(pelaBoaHumano, pelaBoaBot);
                break;
        }

        //Se não forem encontradas Jogadas com base no 'Pela Boa'
        if (jogada == "" && !posicoesLivres.isEmpty()) {
            Collections.sort(posicoesLivres);
            
            //Escolhe-se uma posição Random dente as Posições Livres do Tabuleiro
            jogada = posicoesLivres.get(RandomHandler.instance().getRandomMaiorQueZero(posicoesLivres.size()));
        }
        
        //Retorna a Posição da Jogada
        return jogada;
    }

    //Definição da Posição da Jogada em Modo Agressivo
    private String GetJogadaAgressiva(ArrayList<String> pelaBoaHumano, ArrayList<String> pelaBoaBot) {
        //Primeiro se verifica se há um 'Pela Boa' do Jogador Bot
        //Se sim, retona-o
        if (!pelaBoaBot.isEmpty())
            return pelaBoaBot.get(0);

        //Se não houver um 'Pela Boa' do Bot, busca-se um do Jogador Humano
        //Caso haja, retona-o
        if (!pelaBoaHumano.isEmpty())
            return pelaBoaHumano.get(0);

        //Em caso de não encontrar nem para o Jogador Bot nem Humano, 
        //retona vazio
        return "";
    }

    //Definição da Posição da Jogada em Modo Defensivo
    private String GetJogadaDefensiva(ArrayList<String> pelaBoaHumano, ArrayList<String> pelaBoaBot) {
        //Primeiro se verifica se há um 'Pela Boa' do Jogador Humano
        //Se sim, retona-o
        if (!pelaBoaHumano.isEmpty())
            return pelaBoaHumano.get(0);

        //Se não houver um 'Pela Boa' do Bot, busca-se um do Jogador Bot
        //Caso haja, retona-o
        if (!pelaBoaBot.isEmpty())
            return pelaBoaBot.get(0);

        //Em caso de não encontrar nem para o Jogador Bot nem Humano, 
        //retona vazio
        return "";
    }
}
