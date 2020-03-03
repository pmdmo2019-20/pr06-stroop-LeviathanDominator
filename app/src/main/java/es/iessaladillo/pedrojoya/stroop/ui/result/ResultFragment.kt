package es.iessaladillo.pedrojoya.stroop.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.RESULT_KEY
import es.iessaladillo.pedrojoya.stroop.ui.avatar.AvatarFragment
import es.iessaladillo.pedrojoya.stroop.ui.main.MainFragment
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import kotlinx.android.synthetic.main.result_fragment.*

class ResultFragment : Fragment() {

    private lateinit var toolbar: Toolbar
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
        ft.replace(R.id.avatar_fragment,
            AvatarFragment()
        )
        ft.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ranking = arguments?.getSerializable(RESULT_KEY) as Ranking
        lblCorrectAnswers.text = ranking.correct.toString()
        lblIncorrectAnswers.text = (ranking.words - ranking.correct).toString()
        lblPoints.text = ranking.points.toString()
        RepositoryImpl.newRanking(ranking)
        setupToolbar()
    }

    private fun setupToolbar() {
        val activity = (activity as AppCompatActivity)
        toolbar = view!!.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
