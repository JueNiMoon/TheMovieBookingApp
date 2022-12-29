package com.example.themoviebookingapp.viewpod

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.themoviebookingapp.delegate.LoginDelegate
import com.example.themoviebookingapp.delegate.LoginViewPodDelegate
import kotlinx.android.synthetic.main.view_pod_loginsigninbuttons.view.*

class LoginSignButtonViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mDelegate: LoginViewPodDelegate

    override fun onFinishInflate() {
        btnConfirm.setOnClickListener {
            mDelegate.login()
        }
        super.onFinishInflate()
    }

    fun setDelegate(delegate: LoginViewPodDelegate){
        this.mDelegate = delegate
    }

}