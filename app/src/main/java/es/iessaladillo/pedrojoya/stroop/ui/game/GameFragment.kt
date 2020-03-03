package es.iessaladillo.pedrojoya.stroop.ui.game

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.RESULT_KEY
import es.iessaladillo.pedrojoya.stroop.models.Word
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.result.ResultFragment
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {

    private val viewModel = GameViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        startGame()
    }

    private fun startGame() {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val gameMode = prefs.getString(getString(R.string.prefGameMode_key), getString(R.string.prefGameMode_defaultValue))
            ?.let { setGameMode(it) }
        val gameTime = prefs.getString(getString(R.string.prefGameTime_key), getString(R.string.prefGameTime_defaultValue))!!.toInt()
        val wordTime = prefs.getString(getString(R.string.prefWordTime_key), getString(R.string.prefWordTime_defaultValue))!!.toInt()
        val attempts = prefs.getString(getString(R.string.prefAttempts_key), getString(R.string.prefAttempts_defaultValue))!!.toInt()
        pbTimeBar.max = gameTime
        val timeObserver = Observer<Int> {
            pbTimeBar.progress = it
        }
        val wordObserver = Observer<Word>{ word ->
            lblWord.text = resources.getString(word.text)
            lblWord.setTextColor(resources.getColor(word.color))
        }
        val wordNumObserver = Observer<Int>{
            lblWords.text = it.toString()
        }
        val correctObserver = Observer<Int>{
            lblCorrect.text = it.toString()
        }
        val pointsOrAttemptsObserver = Observer<Int>{
            lblAttempts.text = it.toString()
        }
        val isGameFinishedObserver = Observer<Boolean> {
            if (it) {
                val result = gameMode?.let { mode ->
                    Ranking(
                        RepositoryImpl.playerList.value!![RepositoryImpl.currentPlayer.value!!],
                        mode, gameTime/60000, viewModel.wordsNum.value!!, viewModel.correct
                            .value!!, viewModel.points.value!!
                    )
                }
                val resultFragment = ResultFragment()
                val bundle = Bundle()
                bundle.putSerializable(RESULT_KEY, result)
                resultFragment.arguments = bundle
                val transaction = this.fragmentManager!!.beginTransaction()
                transaction.replace(R.id.frgContainer, resultFragment, "game")
                transaction.addToBackStack("game")
                transaction.commit()
            }
        }
        if (gameMode == RankingFilter.ATTEMPTS){
            viewModel.attemptsGameMode(attempts)
            lblPointsOrAttempts.text = getString(R.string.game_attempts)
            viewModel.attempts.observe(viewLifecycleOwner, pointsOrAttemptsObserver)
        } else {
            viewModel.points.observe(viewLifecycleOwner, pointsOrAttemptsObserver)
        }
        viewModel.word.observe(viewLifecycleOwner, wordObserver)
        viewModel.time.observe(viewLifecycleOwner, timeObserver)
        viewModel.wordsNum.observe(viewLifecycleOwner, wordNumObserver)
        viewModel.correct.observe(viewLifecycleOwner, correctObserver)
        viewModel.startGameThread(gameTime, wordTime)
        viewModel.isGameFinishedLiveData.observe(viewLifecycleOwner, isGameFinishedObserver)
    }

    private fun setGameMode(string: String): RankingFilter? {
        if (string == context!!.getString(R.string.ranking_spnGameMode_attempts)){
            return RankingFilter.ATTEMPTS
        } else if (string == context!!.getString(R.string.ranking_spnGameMode_time)){
            return RankingFilter.TIME
        }
        return null
    }

    private fun setupViews() {
        imgRight.setOnClickListener {
            viewModel.checkRight()
        }
        imgWrong.setOnClickListener {
            viewModel.checkWrong()
        }
    }

}
