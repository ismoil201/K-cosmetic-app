package kr.dev.bureger_house

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions

open class BaseFragment : Fragment() {

    val navAnimOptions = NavOptions.Builder()
        .setPopEnterAnim(R.anim.activity_open_from_right_to_left)
        .setPopExitAnim(R.anim.activity_close_from_left_right_to_left).build()



}