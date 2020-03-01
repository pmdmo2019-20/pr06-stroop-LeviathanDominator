package es.iessaladillo.pedrojoya.stroop.ui.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.Player
import es.iessaladillo.pedrojoya.stroop.ui.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.RepositoryImpl.playerList
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_list_item.*

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.player_list_item, parent, false)
        )

    override fun getItemCount(): Int {
        return playerList.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playerList.value!![position])
        if (position < playerList.value!!.size) {
            // Bind your view here
            holder.itemView.setOnClickListener {
                it.setBackgroundResource(R.color.gameGreen)
                RepositoryImpl.selectPlayer(position)
            }
        }}

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer  {
        fun bind(player: Player) {
            player.run {
                imgAvatar.setImageResource(player.avatarId)
                lblName.text = player.name
            }

        }
    }

}
