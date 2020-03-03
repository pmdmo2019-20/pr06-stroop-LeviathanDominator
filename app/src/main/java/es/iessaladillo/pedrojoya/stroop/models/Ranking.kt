package es.iessaladillo.pedrojoya.stroop.models

import es.iessaladillo.pedrojoya.stroop.models.Player
import java.io.Serializable

class Ranking(val player: Player, val gameMode: String, val minutes: Int, val words: Int, val correct: Int, val points: Int) :
    Serializable
