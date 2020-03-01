package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import kotlinx.android.synthetic.main.create_player_avatar_fragment.*

class CreatePlayerAvatarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.create_player_avatar_fragment, container, false)
        /*setupToolbar(view)
        setupButtons(view)*/
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val nameObserver = Observer<Int> { avatarId ->
            imgAvatar.setImageResource(avatars[avatarId])
        }
        RepositoryImpl.newPlayer.observe(viewLifecycleOwner, nameObserver)
    }

}
