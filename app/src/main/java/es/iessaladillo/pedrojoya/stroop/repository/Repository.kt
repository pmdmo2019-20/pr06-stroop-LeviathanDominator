package es.iessaladillo.pedrojoya.stroop.repository

import es.iessaladillo.pedrojoya.stroop.models.Ranking

interface Repository {
    fun deleteAllRankings()
    fun getCurrentPlayerAvatar(): Int
    fun getCurrentPlayerName(): String
    fun selectPlayer(playerId: Int)
    fun createPlayer(name: String)
    fun newRanking(ranking: Ranking)
}
