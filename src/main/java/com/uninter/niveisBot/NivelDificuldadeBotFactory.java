package com.uninter.niveisBot;

import java.util.HashMap;
import java.util.Map;

import com.uninter.enums.types.*;
import com.uninter.interfaces.INivelDificuldadeBot;

//Factory Singleton que provê instâncias de Níveis de Dificuldade de Partidas
public class NivelDificuldadeBotFactory {
    //Variável estática de implementação do Padrão Singleton
    private static NivelDificuldadeBotFactory _instance;

    //Map que conterá as instâncias providas pela Factory
    private Map<TNivelDificuldade, INivelDificuldadeBot> _niveis = new HashMap<TNivelDificuldade, INivelDificuldadeBot>();
    
    private NivelDificuldadeBotFactory() {
        //Carga do Map com as respectivas instâncias de Nível de Dificuldade
        _niveis.put(TNivelDificuldade.Facil, new NivelFacil());
        _niveis.put(TNivelDificuldade.Medio, new NivelMedio());
        _niveis.put(TNivelDificuldade.Dificil, new NivelDificil());
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static NivelDificuldadeBotFactory instance(){
        if (_instance == null)
            _instance = new NivelDificuldadeBotFactory();

        return _instance;
    }

    //Retorna uma instância de INivelDificuldadeBot com base no Nível de Dificuldade (nivelDificuldade) passado
    public INivelDificuldadeBot getNivelBot(TNivelDificuldade tipoNivel) {
        //Busca o Nível de Dificuldade no Map
        INivelDificuldadeBot result = _niveis.getOrDefault(tipoNivel, null);

        //Retorna o Jogador
        return result;
    }
    
}
