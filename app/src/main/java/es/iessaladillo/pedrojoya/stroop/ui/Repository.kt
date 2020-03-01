package es.iessaladillo.pedrojoya.stroop.ui

interface Repository {
    fun deleteAllRankings()
    fun getCurrentPlayerAvatar(): Int
    fun getCurrentPlayerName(): String
    fun selectPlayer(playerId: Int)
    fun createPlayer(name: String)
}
