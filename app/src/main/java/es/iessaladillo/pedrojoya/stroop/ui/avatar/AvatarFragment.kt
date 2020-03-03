package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.models.Player
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import kotlinx.android.synthetic.main.avatar_fragment.*

class AvatarFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.avatar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val playerObserver = Observer<Player> { newPlayer ->
            imgAvatar.setImageResource(newPlayer.avatarId)
            lblUserName.text = newPlayer.name
        }
        RepositoryImpl.currentPlayer.observe(viewLifecycleOwner, playerObserver)
    }

}