package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.assistant.pages.*

private const val NUM_PAGES = 8

class AssistantFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.assistant_fragment, container, false)
        val pagerAdapter = fragmentManager?.let { ScreenSlidePagerAdapter(it) }
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = pagerAdapter
        tabLayout = view.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        toolbar = view.findViewById(R.id.toolbar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = (activity as AppCompatActivity)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /*val upArrow =  context!!.getDrawable(R.drawable.ic_launcher_background);
        activity.supportActionBar?.setHomeAsUpIndicator(upArrow);*/
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES
        override fun getItem(position: Int): Fragment {
            when (position) {
                1 -> return AssistantSecondPageFragment()
                2 -> return AssistantThirdPageFragment()
                3 -> return AssistantFourthPageFragment()
                4 -> return AssistantFifthPageFragment()
                5 -> return AssistantSixthPageFragment()
                6 -> return AssistantSeventhPageFragment()
                7 -> return AssistantEighthPageFragment()
            }
            return AssistantFirstPageFragment()
        }
    }


}