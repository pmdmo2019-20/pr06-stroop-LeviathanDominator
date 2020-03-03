package es.iessaladillo.pedrojoya.stroop.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER
import es.iessaladillo.pedrojoya.stroop.PREF_KEY_FIRST_TIME
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.TAG_DETAIL_FRAGMENT
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
        setupButtons(view)
        setupAvatarFragment()
        return view
    }

    // If first time shows Assistant.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        if (prefs.getString(PREF_KEY_FIRST_TIME,
                null) == null) {
            val assistantFragment = AssistantFragment()
            fragmentManager!!.beginTransaction()
                .replace(R.id.frgContainer, assistantFragment,TAG_DETAIL_FRAGMENT)
                .addToBackStack(null)
                .commit()
        }
        prefs.edit().putString(PREF_KEY_FIRST_TIME, PREF_KEY_FIRST_TIME).apply()
    }

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(
            R.id.avatar_fragment,
            AvatarFragment()
        )
        ft.commit()
    }

    private fun setupButtons(view: View) {
        view.btnPlay?.setOnClickListener {
            if (RepositoryImpl.currentPlayer.value == NO_PLAYER.toInt()) {
                val playerFragment =
                    PlayerFragment()
                changeFragment(playerFragment)
            } else {
                val gameFragment =
                    GameFragment()
                changeFragment(gameFragment)
            }
        }
        view.btnSettings?.setOnClickListener {
            val settingsFragment =
                SettingsFragment()
            changeFragment(settingsFragment)
        }
        view.btnRankings?.setOnClickListener {
            val rankingsFragment =
                RankingsFragment()
            changeFragment(rankingsFragment)
        }
        view.btnPlayer?.setOnClickListener {
            val playerFragment = PlayerFragment()
            changeFragment(playerFragment)
        }
        view.btnAbout?.setOnClickListener {
            val aboutFragment =
                AboutFragment()
            changeFragment(aboutFragment)
        }
        view.btnAssistant?.setOnClickListener {
            val assistantFragment =
                AssistantFragment()
            changeFragment(assistantFragment)
        }
        view.btnHelp.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.help_title))
                .setMessage(getString(R.string.dashboard_help_description))
                .setPositiveButton(getString(R.string.help_accept)) { _, _ -> }
                .create()
                .show()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = this.fragmentManager!!.beginTransaction()
        transaction.replace(R.id.frgContainer, fragment, "main")
        transaction.addToBackStack("main")
        transaction.commit()
    }

}