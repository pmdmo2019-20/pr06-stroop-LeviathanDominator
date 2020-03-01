package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.create_player_avatar_fragment.*
import kotlinx.android.synthetic.main.create_player_fragment.*

class CreatePlayerFragment : Fragment() {

    private val listAdapter: AvatarListAdapter = AvatarListAdapter()

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
    }

    private fun setupViews() {
        btnCreatePlayer.setOnClickListener{
            RepositoryImpl.createPlayer(txtUserName.text.toString())
        }
        rvAvatars.run {
            layoutManager = GridLayoutManager(context, 3)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }


    private fun setupCreatePlayerAvatarFragment() {
        val ft: FragmentTransaction = fragmentManager!!.beginTransaction()
        ft.replace(R.id.avatar_fragment, CreatePlayerAvatarFragment())
        ft.commit()
    }

}
