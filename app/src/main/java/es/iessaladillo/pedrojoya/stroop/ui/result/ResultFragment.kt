package es.iessaladillo.pedrojoya.stroop.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.AvatarFragment
import es.iessaladillo.pedrojoya.stroop.ui.MainFragment
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import kotlinx.android.synthetic.main.result_fragment.*

class ResultFragment : Fragment() {

    private lateinit var ranking: Ranking

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.result_fragment, container, false)
        setupAvatarFragment()
        return view
    }

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment, AvatarFragment())
        ft.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ranking = arguments?.getSerializable("Result") as Ranking
        lblCorrectAnswers.text = ranking.correct.toString()
        lblIncorrectAnswers.text = (ranking.words - ranking.correct).toString()
        lblPoints.text = ranking.points.toString()
        RepositoryImpl.newRanking(ranking)
    }

    override fun onPause() {
        super.onPause()
        fragmentManager!!.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        val fragment = MainFragment()
        val transaction = this.fragmentManager!!.beginTransaction()
        transaction.replace(R.id.frgContainer, fragment, "game")
        transaction.commit()
    }

}
