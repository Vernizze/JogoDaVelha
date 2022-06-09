package com.uninter.jogadores;

import com.uninter.enums.types.TJogador;
import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.IJogador;

import java.util.HashMap;
import java.util.Map;

//Factory Singleton que provê instâncias de Jogadores com base no parâmetro do Tipo do Jogador (TJogador) e Nível de Dificuldade (TNivelDificuldade)
public class JogadorFactory {
    //Variável estática de implementação do Padrão Singleton
    private static JogadorFactory _instance;

    //Map que conterá as instâncias providas pela Factory
    private Map<TJogador, IJogador> _jogadores = new HashMap<TJogador, IJogador>();
    
    private JogadorFactory() {
        //Carga do Map com as respectivas instâncias de Tipo do Jogador
        _jogadores.put(TJogador.Humano, new Humano());
        _jogadores.put(TJogador.Bot, new Bot());
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static JogadorFactory instance(){
        if (_instance == null)
            _instance = new JogadorFactory();

        return _instance;
    }

    //Retorna uma instância de IJogador com base no Tipo de Jogador (tipoJogador), já configurado com o Nível de Dificuldade (nivelDificuldade) em que atuará
    public IJogador getJogador(TJogador tipoJogador, TNivelDificuldade nivelDificuldade) {
        //Busca o Tipo de Jogador no Map
        IJogador result = _jogadores.getOrDefault(tipoJogador, null);

        //Se encontrado, seta a Dificuldade
        if (result != null)
            result.setNivelDificuldade(nivelDificuldade);

        //Retorna o Jogador
        return result;
    }
}
