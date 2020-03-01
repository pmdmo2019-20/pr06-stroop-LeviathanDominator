package es.iessaladillo.pedrojoya.stroop.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.assistant.AssistantFragment

private const val TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT"

class MainActivity : AppCompatActivity() {

    // TODO

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initialFragment()
    }

    private fun initialFragment() {
        if (false){
            val assistantFragment =
                AssistantFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, assistantFragment, TAG_DETAIL_FRAGMENT)
                .commit()
        } else {
            val mainFragment = MainFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, mainFragment, TAG_DETAIL_FRAGMENT)
                .commit()
        }
    }

}
