package es.iessaladillo.pedrojoya.stroop.models

import java.io.Serializable

class Ranking(val player: Player, val gameMode: RankingFilter, val minutes: Int, val words: Int, val correct: Int, val points: Int) :
    Serializable
