package es.iessaladillo.pedrojoya.stroop.ui.rankings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.models.Ranking
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl.rankings
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ranking_list_item.*

class RankingListAdapter : RecyclerView.Adapter<RankingListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ranking_list_item, parent, false)
        )

    override fun getItemCount(): Int {
        return rankings.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rankings.value!![position])
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(ranking: Ranking) {
            ranking.run {
                imgAvatar.setImageResource(player.avatarId)
                lblGameMode.text = setMode(gameMode)
                lblMinutes.text = minutes.toString()
                lblCorrect.text = correct.toString()
                lblWords.text = words.toString()
                lblPoints.text = points.toString()
            }

        }

        private fun setMode(gameMode: RankingFilter): String {
            if (gameMode == RankingFilter.ATTEMPTS){
                return "Attempts"
            } else if (gameMode == RankingFilter.TIME){
                return "Time"
            }
            return ""
        }

    }
}