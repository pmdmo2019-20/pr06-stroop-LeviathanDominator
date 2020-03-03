package es.iessaladillo.pedrojoya.stroop.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.AvatarFragment
import es.iessaladillo.pedrojoya.stroop.ui.CreatePlayerFragment
import kotlinx.android.synthetic.main.player_fragment.*

class PlayerFragment : Fragment() {

    private val listAdapter: PlayerListAdapter =
        PlayerListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.player_fragment, container, false)
        setupButtons(view)
        setupAvatarFragment()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        rvPlayers.run {
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.player_selection_numColumns))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

    private fun setupButtons(view: View?) {
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
