package pt.ulusofona.lp2.deisiJungle

fun fazCoisasComGet(jogo : GameManager, argumentos : List<String>) : String? {

    return when(argumentos[0]) {

        "PLAYER_INFO" -> jogo.getJogadores().filter{ argumentos[1] == it.nome }
            .map{"${it.id} | ${it.nome} | ${it.especie.nome} | ${it.infoEnergiaAtual} | ${it.casaAtual.nrSquare}"}.firstOrNull() ?:
            "Inexistent player"

        "PLAYERS_BY_SPECIE" -> jogo.getJogadores().filter { argumentos[1] == it.especie.id }.map{ it.nome }.
        joinToString(",") { it }

        "MOST_TRAVELED" -> jogo.getJogadores().sortedWith{n1, n2 -> n1.nrMovimentacoes - n2.nrMovimentacoes}
            .map {"${it.nome}:${it.especie.id}:${it.nrMovimentacoes}"}.joinToString("\n"){ it }

        "TOP_ENERGETIC_OMNIVORES" -> jogo.getJogadores().filter { it.especie.eOmnivoro()}
            .sortedWith{ e1,e2 -> e2.infoEnergiaAtual - e1.infoEnergiaAtual }
            .map{ "${it.nome} : ${it.infoEnergiaAtual}"}.take(argumentos[1].toInt()).joinToString("\n") { it }

        "CONSUMED_FOODS" -> jogo.alimentos.sorted().joinToString("\n") { it }

        else -> null
    }
}

fun fazCoisasComPost(jogo : GameManager, argumentos : List<String>) : String? {
    return when (argumentos[0]) {
        "MOVE" ->
            return when (jogo.moveCurrentPlayer(Integer.parseInt(argumentos[1]), true).code) {
                MovementResultCode.INVALID_MOVEMENT -> {
                    "Movimento invalido"
                }

                MovementResultCode.CAUGHT_FOOD -> {
                    "Apanhou comida"
                }

                MovementResultCode.NO_ENERGY -> {
                    "Sem energia"
                }

                else -> {
                    "OK"
                }
            }
        else -> null
    }
}


fun comando(comando : CommandType) : Function2<GameManager,List<String>, String?> {
    return if(comando == CommandType.GET) {
        :: fazCoisasComGet
    }
    else {
        :: fazCoisasComPost
    }
}

fun router() : (CommandType) -> (GameManager,List<String>) -> String? {
    return ::comando
}


