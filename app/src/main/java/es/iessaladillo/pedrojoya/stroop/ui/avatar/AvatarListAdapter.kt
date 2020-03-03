package es.iessaladillo.pedrojoya.stroop.ui.avatar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.avatars
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.avatar_list_item.*

class AvatarListAdapter : RecyclerView.Adapter<AvatarListAdapter.ViewHolder>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.avatar_list_item, parent, false)
        )

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(avatars[position])
        if (selectedPosition == position) holder.itemView.setBackgroundColor(
            R.color.gameGreen
        )
        holder.itemView.setOnClickListener {
            RepositoryImpl._newPlayerAvatar.value = position
            selectedPosition = position
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return avatars.size
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(avatar: Int) {
            avatar.run {
                imgAvatar.setImageResource(this)
            }

        }

    }

}