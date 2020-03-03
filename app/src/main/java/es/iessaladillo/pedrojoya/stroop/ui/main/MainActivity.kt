package es.iessaladillo.pedrojoya.stroop.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.PREF_KEY_FIRST_TIME
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.TAG_DETAIL_FRAGMENT
import es.iessaladillo.pedrojoya.stroop.models.RankingFilter
import es.iessaladillo.pedrojoya.stroop.repository.RepositoryImpl
import es.iessaladillo.pedrojoya.stroop.ui.assistant.AssistantFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        RepositoryImpl.setRankingFilter(rankingFilter())
        initialFragment()
    }

    private fun rankingFilter(): RankingFilter {
        val filter = PreferenceManager.getDefaultSharedPreferences(baseContext).getString(
            getString(R.string.prefRankingFilter_key),
            getString(R.string.prefRankingFilter_defaultValue)
        )
        return when {
            filter.equals(baseContext.getString(R.string.ranking_spnGameMode_attempts)) -> {
                RankingFilter.ATTEMPTS
            }
            filter.equals(baseContext.getString(R.string.ranking_spnGameMode_time)) -> {
                RankingFilter.TIME
            }
            else -> {
                RankingFilter.ALL
            }
        }
    }

    private fun initialFragment() {
        val mainFragment =
            MainFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.frgContainer, mainFragment,
                TAG_DETAIL_FRAGMENT
            )
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
