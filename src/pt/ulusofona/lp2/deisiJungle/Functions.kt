package pt.ulusofona.lp2.deisiJungle

fun fazCoisasComGet(jogo : GameManager, argumentos : List<String>) : String? {

    return when(argumentos[0]) {

        "PLAYER_INFO" -> jogo.getJogadores().filter{ argumentos[1] == it.nome }
            .map{"${it.id} | ${it.nome} | ${it.especie.nome} | ${it.infoEnergiaAtual} | ${it.casaAtual}"}.firstOrNull() ?:
            "Inexistent player"

        "PLAYERS_BY_SPECIE" -> jogo.getJogadores().filter { argumentos[1] == it.especie.id }.map{ it.nome }
            .joinToString { "," }

        "MOST_TRAVELED" -> jogo.getJogadores().sortedWith{n1, n2 -> n1.nrMovimentacoes - n2.nrMovimentacoes}
            .map {"${it.nome}:${it.especie.id}:${it.nrMovimentacoes}"}.joinToString { "\n" }

        "TOP_ENERGETIC_OMNIVORES" -> jogo.getJogadores().filter { argumentos[1] == it.especie.omnivoro()}
            .sortedWith{ e1,e2 -> e2.infoEnergiaAtual - e1.infoEnergiaAtual }
            .map{ "${it.nome} : ${it.infoEnergiaAtual}"}.joinToString { "\n" }

        "CONSUMED_FOODS" -> jogo.alimentos.sorted().joinToString { "\n" }

        else -> null
    }
}

/*fun fazCoisasComPost(jogo : GameManager, argumentos : List<String>) : String? {
    when(argumentos[0]) {
        //"MOVE" ->
    }
}
*/

fun comando(comando : CommandType) : Function2<GameManager,List<String>, String?> {
    if(comando == CommandType.GET) {
       return :: fazCoisasComGet
    }
  return :: fazCoisasComGet
}

fun router() : (CommandType) -> (GameManager,List<String>) -> String? {
    return ::comando
}



