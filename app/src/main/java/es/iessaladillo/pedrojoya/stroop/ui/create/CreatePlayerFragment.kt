package es.iessaladillo.pedrojoya.stroop.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.avatar.AvatarListAdapter
import kotlinx.android.synthetic.main.create_player_avatar_fragment.*
import kotlinx.android.synthetic.main.create_player_fragment.*

class CreatePlayerFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    private val listAdapter: AvatarListAdapter =
        AvatarListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.create_player_fragment, container, false)
        setupCreatePlayerAvatarFragment()
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
        btnCreatePlayer.setOnClickListener {
            if (RepositoryImpl.newPlayerAvatar.value != null && txtUserName.text.isNotEmpty()) {
                RepositoryImpl.createPlayer(txtUserName.text.toString())
                fragmentManager!!.popBackStack()
            }
        }
        rvAvatars.run {
            layoutManager = GridLayoutManager(
                context,
                resources.getInteger(R.integer.player_creation_numColumns)
            )
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
        btnHelp.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1)
                    .setTitle(getString(R.string.help_title))
                    .setMessage(getString(R.string.player_creation_help_description))
                    .setPositiveButton(getString(R.string.help_accept)) { _, _ -> }
                    .create()
                    .show()
            }
        }
    }


    private fun setupCreatePlayerAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(
            R.id.avatar_fragment,
            CreatePlayerAvatarFragment()
        )
        ft.commit()
    }

}
