@file:Suppress("ObjectPropertyName")

package es.iessaladillo.pedrojoya.stroop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.models.Player
import es.iessaladillo.pedrojoya.stroop.models.Ranking

object RepositoryImpl : Repository {

    private val rankingMutableList = mutableListOf<Ranking>()

    private val playerMutableList: MutableList<Player> = mutableListOf(
        Player("Baldomero", avatars[1])
    )

    private val _playerList: MutableLiveData<List<Player>> =
        MutableLiveData(requeryPlayers())
    val playerList: LiveData<List<Player>>
        get() = _playerList

    private val _currentPlayer: MutableLiveData<Player> = MutableLiveData()
    val currentPlayer: LiveData<Player>
        get() = _currentPlayer

    val _newPlayerAvatar: MutableLiveData<Int> = MutableLiveData()
    val newPlayerAvatar: LiveData<Int>
        get() = _newPlayerAvatar

    private val _rankings: MutableLiveData<List<Ranking>> =
        MutableLiveData(requeryRankings())

    val rankings: LiveData<List<Ranking>>
        get() = _rankings

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
            currentPlayer.value!!.avatarId
        } else {
            R.drawable.logo
        }
    }

    override fun getCurrentPlayerName(): String {
        return if (currentPlayer.value != null) {
            currentPlayer.value!!.name
        } else {
            "No player selected"
        }
    }

    override fun selectPlayer(playerId: Int) {
        _currentPlayer.value = playerList.value!![playerId]
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
            //_newPlayerAvatar.value = null
        }
    }

    override fun newRanking(ranking: Ranking) {
        rankingMutableList.add(ranking)
        requeryRankings()
        _rankings.value =
            rankingMutableList
    }

}