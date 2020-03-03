package es.iessaladillo.pedrojoya.stroop.ui.assistant.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.stroop.R
import kotlinx.android.synthetic.main.assistant_fragment_eighth_page.*

class AssistantEighthPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.assistant_fragment_eighth_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lblFinish.setOnClickListener{
            (activity as AppCompatActivity).onBackPressed()
        }
    }
}
