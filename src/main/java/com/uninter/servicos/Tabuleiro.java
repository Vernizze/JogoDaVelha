package com.uninter.servicos;

import com.uninter.enums.types.*;
import com.uninter.types.Constantes;
import com.uninter.types.Tupla;
import com.uninter.utils.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Classe que controla o Tabuleiro da Partida (Singleton)
public class Tabuleiro {
	//Variável estática de implementação do Padrão Singleton
	private static Tabuleiro _instance;

	//Map com todas as Posições do Tabuleiro
	private Map<String, String> _posicoesTabuleiro = new HashMap<String, String>();
	//Map com somente as Posições Livres do Tabuleiro
	private ArrayList<String> _posicoesLivres = new ArrayList<String>();

	private Tabuleiro() {
		//Carga do Map com todas as Posições
		_posicoesTabuleiro.put("A1", Constantes.TABULEIRO_POSICAO_A1);
		_posicoesTabuleiro.put("A2", Constantes.TABULEIRO_POSICAO_A2);
		_posicoesTabuleiro.put("A3", Constantes.TABULEIRO_POSICAO_A3);

		_posicoesTabuleiro.put("B1", Constantes.TABULEIRO_POSICAO_B1);
		_posicoesTabuleiro.put("B2", Constantes.TABULEIRO_POSICAO_B2);
		_posicoesTabuleiro.put("B3", Constantes.TABULEIRO_POSICAO_B3);

		_posicoesTabuleiro.put("C1", Constantes.TABULEIRO_POSICAO_C1);
		_posicoesTabuleiro.put("C2", Constantes.TABULEIRO_POSICAO_C2);
		_posicoesTabuleiro.put("C3", Constantes.TABULEIRO_POSICAO_C3);

		//Carga do Map com as Posições Livres
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_A1);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_A2);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_A3);

		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_B1);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_B2);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_B3);

		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_C1);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_C2);
		_posicoesLivres.add(Constantes.TABULEIRO_POSICAO_C3);
	}
	
	//Método estático que expõe e inicializa o Padrão Singleton
	public static Tabuleiro instance() {
        if (_instance == null)
            _instance = new Tabuleiro();

        return _instance;
    }

	//Getter de obtenção das Posições Livres do Tabuleiro
	public ArrayList<String> getPosicoesLivres() {
		return _posicoesLivres;
	}

	//Método que atribui a um Jogador uma Posição no Tabuleiro
	public Boolean setJogada(TJogador tipoJogador, String posicao) {
		//Verifica se a Posição informada é válida
		Tupla<Boolean, TJogador> posicaoValida = posicaoValida(posicao);

		//Se a Posição for válida e ela não estiver atribuída a nenhum dos Jogadores ainda
		if (posicaoValida.x && posicaoValida.y.equals(TJogador.None)) {
			//Busca a identificação do Jogador para a Posição do Tabuleiro
			String posicaoJogador = getPosicaoJogador(tipoJogador);

			//Se o valor da identificação foi encontrada
			if (posicaoJogador != "") {
				//Atribui à Posição do Tabuleiro a Identificação do Jogador
				_posicoesTabuleiro.put(posicao, posicaoJogador);

				//Remove a Posição do Map de Posições Livres
				_posicoesLivres.remove(posicao);
				
				//Retorna que a atribuição da Posição é válida
				return true;
			}
			else {
				//Indica que o Tipo de Jogador é Inválido
				Printer.imprimirLinha("Jogador Inválido!");
			}
		}

		//Retorna que a atribuição da Posição não é válida
		return false;
	}

	//Método que verifica se uma Posição é válida
	public Tupla<Boolean, TJogador> posicaoValida(String posicao) {		
		//Se a Poisição consta no Map com Todas as Posições
		if (_posicoesTabuleiro.containsKey(posicao)) {
			String posicaoEncontrada = _posicoesTabuleiro.get(posicao);
						
			switch (posicaoEncontrada) {
				//Se a Posição Encontrada for do Jogador Humano, retorna 'true' e 'TJogador.Humano'
				case (Constantes.TABULEIRO_POSICAO_HUMANO):
					return new Tupla<Boolean, TJogador>(true, TJogador.Humano);
				//Se a Posição Encontrada for do Jogador Bot, retorna 'true' e 'TJogador.Bot'
				case (Constantes.TABULEIRO_POSICAO_BOT):
					return new Tupla<Boolean, TJogador>(true, TJogador.Bot);
				//Se a Posição Encontrada for de um Jogador não esperado, retorna 'true' e 'TJogador.None'
				default:
					return new Tupla<Boolean, TJogador>(true, TJogador.None);
			}			
		}

		//Se não foi encontrada a Posição no Map com Todas as Posições, retorna 'false' e 'TJogador.None'
		return new Tupla<Boolean, TJogador>(false, TJogador.None);
	}

	//Método que retorna o Identificador do Jogador informado pelo parâmetro 'tipoJogador'
	private String getPosicaoJogador(TJogador tipoJogador) {
		switch (tipoJogador) {
			case Humano:
				//Se o Tipo de Jogador for 'TJogador.Humano', retorna o Identificador do Jogador Humano
				return Constantes.TABULEIRO_POSICAO_HUMANO;
			case Bot:
				//Se o Tipo de Jogador for 'TJogador.Bot', retorna o Identificador do Jogador Bot
				return Constantes.TABULEIRO_POSICAO_BOT;
			default:
				//Em caso de um Jogador não esperado, retona uma 'String' vazia
				return "";
		}
	}

	//Método que visualiza o situação do Tabuleiro de Jogo
	public void visualizar() {
		//Declaração do Contador de Posições provindas do Map de Todas as Posições
		int count = 0;
		//Lê as chaves do Map de Todas as Posições do Tabuleiro
		Set<String> keySets = _posicoesTabuleiro.keySet();
		//Cria um Array de 'String' com o tamanho relativo ao Map de Todas as Posições
		String[] posicoes = new String[keySets.size()];
		//Popula o Array de String com as chaves do Map
		keySets.toArray(posicoes);
		//Ordena o Array
		Arrays.sort(posicoes);
		
		//Imprime a parte de cima do Tabuleiro
		Printer.imprimirLinha(Constantes.TABULEIRO_LINHA_INICIO);
		
		//Itera pelas Linhas do Tabuleiro
		for (int i = 0; i < 3; i++) {
			//Imprime uma Linha em branco
			Printer.imprimirLinha("");

			//Itera pelas Colunas do Tabuleiro
			for (int j = 0; j < 3; j++) {
				//Imprime o Separador do lado esquerdo do Tabuleiro
				Printer.imprimir(Constantes.TABULEIRO_SEPARADOR_ESQUERDO);
				//Imprime o valor da Posição do Tabuleiro
				Printer.imprimir(_posicoesTabuleiro.get(posicoes[count]));
				//Imprime o Separador do lado direito do Tabuleiro
				Printer.imprimir(Constantes.TABULEIRO_SEPARADOR_DIREITO);
				//Incrementa o Contador de Posições
				count++;
			}
		}

		//Imprime a parte de baixo do Tabuleiro
		Printer.imprimirLinha(Constantes.TABULEIRO_LINHA_FIM);
	}
}
