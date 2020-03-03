package es.iessaladillo.pedrojoya.stroop.ui.game

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.models.Word
import kotlin.concurrent.thread
import kotlin.random.Random


class GameViewModel(
    // TODO
) : ViewModel() {

    private val NUM_WORDS = 4
    private val INVALID_RETURN = -1

    @Volatile
    private var isGameFinished: Boolean = false
    @Volatile
    private var currentWordMillis: Int = 0
    @Volatile
    private var millisUntilFinished: Int = 0
    private val handler: Handler = Handler()

    // TODO

    private val _time: MutableLiveData<Int> =
        MutableLiveData(millisUntilFinished)
    val time: LiveData<Int>
        get() = _time

    private val _word: MutableLiveData<Word> =
        MutableLiveData(newWord())
    val word: LiveData<Word>
        get() = _word

    private val _wordsNum: MutableLiveData<Int> =
        MutableLiveData(0)
    val wordsNum: LiveData<Int>
        get() = _wordsNum

    private val _correct: MutableLiveData<Int> =
        MutableLiveData(0)
    val correct: LiveData<Int>
        get() = _correct

    private val _points: MutableLiveData<Int> =
        MutableLiveData(0)
    val points: LiveData<Int>
        get() = _points

    private val _attempts: MutableLiveData<Int> =
        MutableLiveData(-1)
    val attempts: LiveData<Int>
        get() = _attempts

    private val _isGameFinishedLiveData: MutableLiveData<Boolean> =
        MutableLiveData(isGameFinished)
    val isGameFinishedLiveData: LiveData<Boolean>
        get() = _isGameFinishedLiveData

    private fun newWord(): Word {
        val numWord = Random.nextInt(NUM_WORDS)
        val numColor = Random.nextInt(NUM_WORDS)

        return Word(
            randomWordText(
                numWord
            ), randomWordColor(numColor), numWord == numColor
        )
    }

    private fun randomWordColor(numColor: Int): Int {
        when(numColor){
            0 -> return R.color.gameGreen
            1 -> return R.color.gameRed
            2 -> return R.color.gameBlue
            3 -> return R.color.gameYellow
        }
        return INVALID_RETURN
    }

    private fun randomWordText(numWord: Int): Int {
        when(numWord){
            0 -> return R.string.game_green
            1 -> return R.string.game_red
            2 -> return R.string.game_blue
            3 -> return R.string.game_yellow
        }
        return INVALID_RETURN
    }

    private fun onGameTimeTick(millisUntilFinished: Int) {
        // TODO
            _time.value = millisUntilFinished
    }

    private fun onGameTimeFinish() {
        isGameFinished = true
        _isGameFinishedLiveData.value = isGameFinished
        // TODO
    }

    fun nextWord() {
        // TODO
        if (attempts.value!! == 0){
            onGameTimeFinish()
        }
        _wordsNum.value = wordsNum.value!! + 1
        _word.value = newWord()
    }

    fun checkRight() {
        currentWordMillis = 0
        checkWord(true)
        nextWord()
        // TODO
    }

    fun checkWrong() {
        currentWordMillis = 0
        checkWord(false)
        nextWord()
        // TODO
    }

    private fun checkWord(right: Boolean) {
        if (word.value!!.correct == right){
            _correct.value = correct.value!! + 1
            _points.value = points.value!! + 10
        } else {
            _attempts.value = attempts.value!! - 1
        }
    }

    fun startGameThread(gameTime: Int, wordTime: Int) {
        millisUntilFinished = gameTime
        _time.value = gameTime
        currentWordMillis = 0
        isGameFinished = false
        val checkTimeMillis: Int = wordTime / 2
        thread {
            try {
                while (!isGameFinished) {
                    Thread.sleep(checkTimeMillis.toLong())
                    // Check and publish on main thread.
                    handler.post {
                        if (!isGameFinished) {
                            if (currentWordMillis >= wordTime) {
                                currentWordMillis = 0
                                nextWord()
                            }
                            currentWordMillis += checkTimeMillis
                            millisUntilFinished -= checkTimeMillis
                            onGameTimeTick(millisUntilFinished)
                            if (millisUntilFinished <= 0) {
                                onGameTimeFinish()
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        isGameFinished = true
    }

    fun attemptsGameMode(attempts: Int) {
        _attempts.value = attempts
    }

}