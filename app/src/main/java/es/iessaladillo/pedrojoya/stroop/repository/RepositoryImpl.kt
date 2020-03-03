@file:Suppress("ObjectPropertyName")

package es.iessaladillo.pedrojoya.stroop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.models.Player
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter

object RepositoryImpl : Repository {

    private val rankingMutableList = mutableListOf<Ranking>()

    private val playerMutableList: MutableList<Player> = mutableListOf()

    private val _playerList: MutableLiveData<List<Player>> =
        MutableLiveData(requeryPlayers())
    val playerList: LiveData<List<Player>>
        get() = _playerList

    private val _currentPlayer: MutableLiveData<Int> = MutableLiveData(NO_PLAYER.toInt())
    val currentPlayer: LiveData<Int>
        get() = _currentPlayer

    val _newPlayerAvatar: MutableLiveData<Int> = MutableLiveData()
    val newPlayerAvatar: LiveData<Int>
        get() = _newPlayerAvatar

    private val _rankings: MutableLiveData<List<Ranking>> =
        MutableLiveData(requeryRankings())
    val rankings: LiveData<List<Ranking>>
        get() = _rankings
// TODO User filter
    private val _rankingsFilter: MutableLiveData<RankingFilter> = MutableLiveData()
    val rankingsFilter: LiveData<RankingFilter>
        get() = _rankingsFilter

    private fun updateLiveData() {
        _rankings.value =
            requeryRankings()
    }

    private fun requeryRankings() =
        rankingMutableList.toList()

    private fun requeryPlayers() =
        playerMutableList.toList()

    override fun deleteAllRankings() {
        rankingMutableList.clear()
        updateLiveData()
    }

    override fun getCurrentPlayerAvatar(): Int {
        return if (currentPlayer.value != null) {
            playerList.value!![currentPlayer.value!!].avatarId
        } else {
            R.drawable.logo
        }
    }

    override fun getCurrentPlayerName(): String {
        return if (currentPlayer.value != null) {
            playerList.value!![currentPlayer.value!!].name
        } else {
            "No player selected"
        }
    }

    override fun selectPlayer(playerId: Int) {
        _currentPlayer.value = playerId
    }

    override fun createPlayer(name: String) {
        if (name != "" && newPlayerAvatar.value != null) {
            playerMutableList.add(
                Player(
                    name,
                    avatars[newPlayerAvatar.value!!]
                )
            )
            requeryPlayers()
            _playerList.value =
                playerMutableList
        }
    }

    override fun newRanking(ranking: Ranking) {
        rankingMutableList.add(ranking)
        requeryRankings()
        _rankings.value =
            rankingMutableList
    }

    override fun setRankingFilter(filter: RankingFilter) {
        _rankingsFilter.value = filter
    }

}