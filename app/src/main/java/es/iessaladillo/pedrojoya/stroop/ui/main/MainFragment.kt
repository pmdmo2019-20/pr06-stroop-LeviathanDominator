package es.iessaladillo.pedrojoya.stroop.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.about.AboutFragment
import es.iessaladillo.pedrojoya.stroop.ui.assistant.AssistantFragment
import es.iessaladillo.pedrojoya.stroop.ui.avatar.AvatarFragment
import es.iessaladillo.pedrojoya.stroop.ui.game.GameFragment
import es.iessaladillo.pedrojoya.stroop.ui.player.PlayerFragment
import es.iessaladillo.pedrojoya.stroop.ui.rankings.RankingsFragment
import es.iessaladillo.pedrojoya.stroop.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        setupToolbar(view)
        setupButtons(view)
        setupAvatarFragment()
        return view
    }

    private fun setupToolbar(view: View?) {
       // view?.tlbMain?.title = "wa"
    }

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment,
            AvatarFragment()
        )
        ft.commit()
    }

    private fun setupButtons(view: View) {
        view.btnPlay?.setOnClickListener{
            if (RepositoryImpl.currentPlayer.value == null){
                val playerFragment =
                    PlayerFragment()
                changeFragment(playerFragment)
            } else {
                val gameFragment =
                    GameFragment()
                changeFragment(gameFragment)
            }
        }
        view.btnSettings?.setOnClickListener{
            val settingsFragment =
                SettingsFragment()
            changeFragment(settingsFragment)
        }
        view.btnRankings?.setOnClickListener{
            val rankingsFragment =
                RankingsFragment()
            changeFragment(rankingsFragment)
        }
        view.btnPlayer?.setOnClickListener{
            val playerFragment = PlayerFragment()
            changeFragment(playerFragment)
        }
        view.btnAbout?.setOnClickListener{
            val aboutFragment =
                AboutFragment()
            changeFragment(aboutFragment)
        }
        view.btnAssistant?.setOnClickListener {
            val assistantFragment =
                AssistantFragment()
            changeFragment(assistantFragment)
        }
        view.btnHelp?.setOnClickListener {
            val dialog: Dialog = AlertDialog.Builder(context)
                .setTitle("Test")
                .setMessage("Testtest")
                .create()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = this.fragmentManager!!.beginTransaction()
        transaction.replace(R.id.frgContainer, fragment, "main")
        transaction.addToBackStack("main")
        transaction.commit()
    }

}