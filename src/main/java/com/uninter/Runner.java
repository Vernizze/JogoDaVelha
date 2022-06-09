package com.uninter;

import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.servicos.Jogo;
import com.uninter.types.Tupla;
import com.uninter.utils.Parsers;
import com.uninter.utils.Printer;
import com.uninter.utils.Teclado;

//Classe que inicia o serviço do Jogo da Velha
public class Runner {
    public static void main(String[] args) {
		Printer.imprimirLinha("Jogo da velha");

		//Busca o Nível de dificuldae que a Partida terá
		Tupla<Boolean, TNivelDificuldade> nivelDificuldade = getNivelDificuldade();

		//Inicia o Serviço de Interação com o Teclado
		Teclado.instance();
		
		//Se o Nível de Dificuldade foi obtido, inicia a Partida
		if (nivelDificuldade.x)			
			Jogo.instance(nivelDificuldade.y).go();
		else
			System.out.println("Saindo...");
		
		//Interação com o Teclado é finalizada
		Teclado.instance().dispose();
	}
    
	//Método que obtém o Nível de Dificuldade da Partida
    private static Tupla<Boolean, TNivelDificuldade> getNivelDificuldade() {
        Tupla<Boolean, TNivelDificuldade> result;

		Printer.imprimirLinha("Escolha a dificuldade 1, 2 ou 3 (0 para Sair):");

		//Lê o Nível de Dificuldade que o Jogador Humano quer jogar
		String opcaoDigitada = Teclado.instance().getValorDigitado();

		//Converte o input de tipo 'String' para 'Integer'
		Integer opcao = Parsers.parseIntOrNull(opcaoDigitada);
		
		//Um valor que NÃO seja do tipo 'Integer', solicita novamente um valor válido (recursivamente)
		if (opcao == null) {
			return getNivelDificuldade();
		}
		//Se a opção informada for '0', encerra a aplicação
        else if (opcao == 0) {
			result = new Tupla<Boolean, TNivelDificuldade>(false, TNivelDificuldade.Facil);
        }
		//Se o valor informado estiver contido na lista de valores do Enum 'TNivelDificuldade',
		//inicia a Partida com o respectivo Nível de Dificuldade
        else if (TNivelDificuldade.isMember(opcao)) {
            result = new Tupla<Boolean, TNivelDificuldade>(true, TNivelDificuldade.values()[opcao - 1]);
        }
		//Em qualquer outra situação, solicita novamente um valor válido (recursivamente)
        else {
            return getNivelDificuldade();
        }

		//Retorna a 'Tupla<Boolean, TNivelDificuldade>' com o sucesso ou não da verificação e o Nível que a Partida terá
        return result;
	}
}
