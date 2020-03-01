package es.iessaladillo.pedrojoya.stroop.ui.assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import es.iessaladillo.pedrojoya.stroop.R

class AssistantFragment: Fragment(){

    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.assistant_fragment, container, false)
        val pagerAdapter = fragmentManager?.let { ScreenSlidePagerAdapter(it) }
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = pagerAdapter
        return view
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 5
        override fun getItem(position: Int): Fragment =
            AssistantPageFragment()
    }



}