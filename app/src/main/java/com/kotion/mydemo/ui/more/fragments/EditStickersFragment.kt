package com.kotion.mydemo.ui.more.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotion.mydemo.R

/**
 * 编辑图片贴标签
 */
class EditStickersFragment: Fragment() {
    companion object{
        val instance:EditStickersFragment by lazy {
            EditStickersFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var  view =inflater.inflate(R.layout.fragment_editstickers,container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}