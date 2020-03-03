package es.iessaladillo.pedrojoya.stroop.ui.assistant.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.stroop.R

class AssistantFirstPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.assistant_fragment_first_page, container, false)
    }

}
