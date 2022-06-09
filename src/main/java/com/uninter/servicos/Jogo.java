package com.uninter.servicos;

import com.uninter.enums.status.SJogo;
import com.uninter.enums.types.TJogador;
import com.uninter.enums.types.TNivelDificuldade;
import com.uninter.interfaces.IJogador;
import com.uninter.jogadores.JogadorFactory;
import com.uninter.types.Constantes;
import com.uninter.types.Tupla;
import com.uninter.utils.Printer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classe responsável por gerenciar as Partidas entre os Jogadores Humano e Bot (Singleton)
public class Jogo {
    //Variável estática de implementação do Padrão Singleton
    private static Jogo _instance;
    private static TNivelDificuldade _nivelDificuldadeJogo = TNivelDificuldade.Facil;

    private SJogo _statusJogo = SJogo.NaoIniciado;
    private Tabuleiro _tabuleiro;

    private IJogador _jogador;
    private IJogador _bot;

    //Map com as Sequências de Posições (Situações de Vitória) que completas por um jogador resultam em Vitória
    private Map<String, List<ArrayList<String>>> _posicoesVitorias = new HashMap<String, List<ArrayList<String>>>();
    //Map com as Sequências de Posições que estão com duas Jogadas do mesmo Jogador e com mais uma resultarão em Vitória
    private Map<ArrayList<String>, TJogador> _pelaBoa = new HashMap<ArrayList<String>, TJogador>();

    private Jogo(TNivelDificuldade nivelDificuldade) {
        _nivelDificuldadeJogo = nivelDificuldade;

        Init(_nivelDificuldadeJogo);
        InitPosicoesVitorias();
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static Jogo instance() {
        if (_instance == null)
            _instance = new Jogo(_nivelDificuldadeJogo);

        return _instance;
    }

    //Método estático que expõe e inicializa o Padrão Singleton
    public static Jogo instance(TNivelDificuldade nivelDificuldade) {
        if (_instance == null || (_instance != null && !nivelDificuldade.equals(_nivelDificuldadeJogo)))
            _instance = new Jogo(nivelDificuldade);

        return _instance;
    }

    //Inicialização geral da Classe
    public void Init(TNivelDificuldade nivelDificuldade) {
        _tabuleiro = Tabuleiro.instance();
        
        Printer.imprimirLinha(MessageFormat.format("Dificuldade do Bot: {0}", nivelDificuldade));
        Printer.imprimirLinha("");
        
        _jogador = JogadorFactory.instance().getJogador(TJogador.Humano, TNivelDificuldade.Dificil);
        _bot  = JogadorFactory.instance().getJogador(TJogador.Bot, nivelDificuldade);
    }

    //Inicialização do Map com as Situações de Vitória
    public void InitPosicoesVitorias() {
        _posicoesVitorias.clear();

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_A1, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_A3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_C1)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C3)))));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_A2, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_A3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C2))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_A3, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_A3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B3, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C1))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_B1, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_B3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_C1))
            )));

    	_posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_B2, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_B3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C2)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C1))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_B3, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_B3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B3, Constantes.TABULEIRO_POSICAO_C3))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_C1, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_C1, Constantes.TABULEIRO_POSICAO_C2, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B1, Constantes.TABULEIRO_POSICAO_C1)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C1))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_C2, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_C1, Constantes.TABULEIRO_POSICAO_C2, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A2, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C2))
            )));

        _posicoesVitorias.put(Constantes.TABULEIRO_POSICAO_C3, new ArrayList<ArrayList<String>>(Arrays.asList(
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_C1, Constantes.TABULEIRO_POSICAO_C2, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A3, Constantes.TABULEIRO_POSICAO_B3, Constantes.TABULEIRO_POSICAO_C3)),
            new ArrayList<String>(Arrays.asList(Constantes.TABULEIRO_POSICAO_A1, Constantes.TABULEIRO_POSICAO_B2, Constantes.TABULEIRO_POSICAO_C3))
            )));
    }

    //Getter de obtenção do Nível de Dificuldade da Partida
    public static TNivelDificuldade getNivelDificuldade() {
        return _nivelDificuldadeJogo;
    }

    //Getter de obtenção do Status da Partida
    public SJogo getStatusJogo() {
        return _statusJogo;
    }

    //Getter de obtenção do Tabuleiro instanciado para a Partida
    public Tabuleiro getTabuleiro() {
        return _tabuleiro;
    }

    //Getter de obtenção das possibilidades 'Pela Boa' do Jogador Humano
    public ArrayList<String> GetPelaBoaHumano() {
        ArrayList<String> posicoesPelaBoaHumano = new ArrayList<>();
        
        for (Map.Entry<ArrayList<String>, TJogador> entry : _pelaBoa.entrySet()) {
            if (entry.getValue().equals(TJogador.Humano)) {
                for (String p : entry.getKey()) {
                    Tupla<Boolean, TJogador> estadoPosicao = _tabuleiro.posicaoValida(p);

                    if (estadoPosicao.x && TJogador.None.equals(estadoPosicao.y))
                        posicoesPelaBoaHumano.add(p);
                }
            }
        }

        return posicoesPelaBoaHumano;
    }

    //Getter de obtenção das possibilidades 'Pela Boa' do Jogador Bot
    public ArrayList<String> GetPelaBoaBot() {
        ArrayList<String> posicoesPelaBoaBot = new ArrayList<>();
        
        for (Map.Entry<ArrayList<String>, TJogador> entry : _pelaBoa.entrySet()) {
            if (entry.getValue().equals(TJogador.Bot)) {
                for (String p : entry.getKey()) {
                    Tupla<Boolean, TJogador> estadoPosicao = _tabuleiro.posicaoValida(p);

                    if (estadoPosicao.x && TJogador.None.equals(estadoPosicao.y))
                        posicoesPelaBoaBot.add(p);
                }
            }
        }

        return posicoesPelaBoaBot;
    }    

    //Método de execução da Partida
    public void go() {
        //Contador de Rodadas
        int count = 0;

        //Status Inicial
        _statusJogo = SJogo.Rodada01;

        //Visualização do Tabuleiro antes do início da Partida
        _tabuleiro.visualizar();

        //Enquanto o Status da Partida NÃO for 'Vitoria' de um dos Jogadores ou 'Empate' ela continuará
        while(_statusJogo != SJogo.Vitoria && _statusJogo != SJogo.Empate) {
            Tupla<Boolean, String> resultadoJogada = new Tupla<Boolean,String>(false, "");

            Printer.imprimirLinha(MessageFormat.format("Status do Jogo: {0}", _statusJogo));

            //Se o Resto de Divisão do Contador de Rodadas for igual a 0 (valor Par), é a vez do Jogador Humano
            if (count % 2 == 0) {
                //Busca-se a Jogada do Jogador Humano
                resultadoJogada = _jogador.play();
            }
            //Se o Resto de Divisão do Contador de Rodadas for diferente de 0 (valor Ímpar), é a vez do Jogador Bot
            else {
                //Busca-se a Jogada do Jogador Bot
                resultadoJogada = _bot.play();
            }

            //Se a Jogada foi feita com sucesso
            if (resultadoJogada.x) {
                //Visualiza o estado do Tabuleiro após a Jogada
                _tabuleiro.visualizar();
            
                //Analisa-se se ocorreu uma Vitória de um dos Jogadores
                Tupla<Boolean, TJogador> jogadorGanhou = analiseJogadas(resultadoJogada.y, Constantes.JOGO_ANALISE_JOGADAS_JOGADOR_VENCEU);

                //Se um Jogador ganhou
                if (jogadorGanhou.x) {                    
                    Printer.imprimirLinha(MessageFormat.format("Jogador {0} ganhou!!!!", jogadorGanhou.y));

                    //Altera-se o Status da Partida para 'Vitoria'
                    _statusJogo = SJogo.Vitoria;
                }

                //Analisa-se a ocorrência de Jogadas 'Pela Boa'
                analiseJogadas(resultadoJogada.y, Constantes.JOGO_ANALISE_JOGADAS_JOGADOR_PELABOA);

                //Seta-se o próximo lance da Partida
                setProximoLance();
    
                //Incrementa-se o Contador de Rodadas
                count++;
            }
            else {
                //Se a Jogada for inválida, NÃO se incrementa o Contador de Rodadas, NÃO se altera o Status e pede-se uma nova Jogada ao Jogador
                Printer.imprimirLinha("Jogada inválida!");
            }
		}

        //Se acabaram as Posições de Jogadas e não houve Jogador Vencedor, inform-se o Empate
        if (SJogo.Empate.equals(_statusJogo))
            Printer.imprimirLinha("Acabaram as jogadas! Jogo empatado!");

        //Muda-se o Status da PArtida para 'Finalizado'
        _statusJogo = SJogo.Finalizado;

        Printer.imprimirLinha("Fim de jogo!");
    }

    //Métodop de incremento da Partida para o próxima Rodada
    private void setProximoLance() {
        if (!SJogo.Vitoria.equals(_statusJogo) && !SJogo.Empate.equals(_statusJogo))
            _statusJogo = SJogo.values()[_statusJogo.value + 1];
    }
    
    //Método de Análise das Jogadas
    private Tupla<Boolean, TJogador> analiseJogadas(String posicao, int valorReferencia) {
        TJogador tipoJogador = TJogador.None;
        boolean resultado = false;

        //Se obtém todas as Situações de Vitória
        List<ArrayList<String>> todasPosicoesVitoriasDaPosicao = _posicoesVitorias.get(posicao);

        //Se houverem Situações de Vitória
        if (!todasPosicoesVitoriasDaPosicao.isEmpty()) {      
            //Itera-se nas Situações de Vitória      
            for (ArrayList<String> posicoesVitoria : todasPosicoesVitoriasDaPosicao) {
                ArrayList<String> posicoesVazias = new ArrayList<>();
                int quantidadePosicoesDoBot = 0;
                int quantidadePosicoesDoHumano = 0;

                //Itera-se nas nas Posições das Situações de Vitória
                for (String posicaoVitoria : posicoesVitoria) {
                    //Verifica-se se é uma Posição Válida
                    Tupla<Boolean, TJogador> jogadorPosicao = _tabuleiro.posicaoValida(posicaoVitoria);

                    switch (jogadorPosicao.y) {
                        //Se for uma Posição de Jogada do Jogador Humano, incrementa-se o Contador de Jogadas do Humano
                        case Humano:
                            quantidadePosicoesDoHumano++;
                            break;
                        //Se for uma Posição de Jogada do Jogador Bot, incrementa-se o Contador de Jogadas do Bot
                        case Bot:
                            quantidadePosicoesDoBot++;
                            break;
                        //Se for uma Posição sem Jogadas, adiciona-se a lista das mesmas
                        default:
                            posicoesVazias.add(posicaoVitoria);
                            break;
                    }
                }

                //Se a Quantidade de Posições jogadas pelo Humano for igual a Referência passada por parâmetro
                if (quantidadePosicoesDoHumano == valorReferencia) {
                    resultado = true;
                    tipoJogador = TJogador.Humano;
                //Se a Quantidade de Posições jogadas pelo Bot for igual a Referência passada por parâmetro
                } else if (quantidadePosicoesDoBot == valorReferencia) {
                    resultado = true;
                    tipoJogador = TJogador.Bot;
                }
            }
        }

        return new Tupla<Boolean, TJogador>(resultado, tipoJogador);
    }
}
