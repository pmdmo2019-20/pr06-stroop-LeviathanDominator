package es.iessaladillo.pedrojoya.stroop.repository

import android.graphics.drawable.Drawable
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter

interface Repository {
    fun deleteAllRankings()
    fun getCurrentPlayerAvatar(): Int
    fun getCurrentPlayerName(): String
    fun selectPlayer(playerId: Int)
    fun createPlayer(name: String)
    fun newRanking(ranking: Ranking)
    fun setRankingFilter(filter: RankingFilter)
}
