package pt.ulusofona.lp2.deisiJungle

fun fazCoisasComGet(jogo : GameManager, argumentos : List<String>) : String? {

    return when(argumentos[0]) {

        "PLAYER_INFO" -> jogo.getJogadores().filter{ argumentos[1] == it.nome }
            .map{"${it.id} | ${it.nome} | ${it.especie.nome} | ${it.infoEnergiaAtual} | ${it.casaAtual}"}.firstOrNull() ?:
            "Inexistent player"

        "PLAYERS_BY_SPECIE" -> jogo.getJogadores().filter { argumentos[1] == it.especie.id }.map{ it.nome }
            .joinToString { "," }

        else -> null;
    }
}

fun fazCoisasComPost(jogo : GameManager, argumentos : List<String>) : String? {
    when(argumentos[0]) {
        "MOVE" ->
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


