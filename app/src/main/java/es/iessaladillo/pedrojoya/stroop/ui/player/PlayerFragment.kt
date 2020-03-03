package es.iessaladillo.pedrojoya.stroop.ui.player

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.stroop.NO_PLAYER
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.models.Player
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.avatar.AvatarFragment
import es.iessaladillo.pedrojoya.stroop.ui.create.CreatePlayerFragment
import kotlinx.android.synthetic.main.player_fragment.*

class PlayerFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private val listAdapter: PlayerListAdapter =
        PlayerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.player_fragment, container, false)
        setupFloatingButton(view)
        setupAvatarFragment()
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
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

    private fun setupViews() {
        rvPlayers.run {
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.player_selection_numColumns))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }

        val playersObserver = Observer<List<Player>> {
            if (it.isEmpty()){
                clEmptyPlayerList.visibility = View.VISIBLE
            } else {
                clEmptyPlayerList.visibility = View.INVISIBLE
            }
        }
        RepositoryImpl.playerList.observe(viewLifecycleOwner, playersObserver)

        val playerSelectedObserver = Observer<Int>{
            if (it != NO_PLAYER.toInt()){
                imgEdit.visibility = View.VISIBLE
                btnEdit.visibility = View.VISIBLE
            } else {
                imgEdit.visibility = View.INVISIBLE
                btnEdit.visibility = View.INVISIBLE
            }
        }
        RepositoryImpl.currentPlayer.observe(viewLifecycleOwner, playerSelectedObserver)

        btnHelp.setOnClickListener{
            AlertDialog.Builder(context)
                .setTitle(getString(R.string.help_title))
                .setMessage(getString(R.string.player_selection_help_description))
                .setPositiveButton(getString(R.string.help_accept)){ _, _ -> }
                .create()
                .show()
        }
    }

    private fun setupFloatingButton(view: View?) {
        val btn: FloatingActionButton = view!!.findViewById(R.id.btnCreatePlayer)
        btn.setOnClickListener {
            val transaction = this.fragmentManager!!.beginTransaction()
            transaction.replace(R.id.frgContainer,
                CreatePlayerFragment(), "createPlayerFragment")
            transaction.addToBackStack("playFragment")
            transaction.commit()
        }
    }

    private fun setupAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment,
            AvatarFragment()
        )
        ft.commit()
    }
}
