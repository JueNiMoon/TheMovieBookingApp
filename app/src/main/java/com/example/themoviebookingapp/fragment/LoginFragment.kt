package com.example.themoviebookingapp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.activity.HomeActivity
import com.example.themoviebookingapp.delegate.LoginDelegate
import com.example.themoviebookingapp.delegate.LoginViewPodDelegate
import com.example.themoviebookingapp.viewpod.LoginSignButtonViewPod
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.view_pod_loginsigninbuttons.*
import kotlinx.android.synthetic.main.view_pod_loginsigninbuttons.view.*

class LoginFragment : Fragment(),LoginViewPodDelegate {

    lateinit var mDelegate: LoginDelegate
    lateinit var mLoginSignButtonViewPod: LoginSignButtonViewPod

    override fun onAttach(context: Context) {
        mDelegate = context as LoginDelegate
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mLoginSignButtonViewPod = vpLoginBtn as LoginSignButtonViewPod
        mLoginSignButtonViewPod.setDelegate(this)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun login() {
        val email = ipedittxtEmail.text.toString() ?: ""
        val password = ipedittxtPassword.text.toString() ?: ""
        mDelegate.login(email,password)
    }


}