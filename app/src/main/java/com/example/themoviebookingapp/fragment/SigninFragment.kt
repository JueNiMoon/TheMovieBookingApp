package com.example.themoviebookingapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themoviebookingapp.R
import com.example.themoviebookingapp.delegate.LoginDelegate
import com.example.themoviebookingapp.delegate.LoginViewPodDelegate
import com.example.themoviebookingapp.viewpod.LoginSignButtonViewPod
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SigninFragment : Fragment(), LoginViewPodDelegate {

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
        mLoginSignButtonViewPod = vpLoginButton as LoginSignButtonViewPod
        mLoginSignButtonViewPod.setDelegate(this)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun login() {
        val name = edittextName.text.toString() ?: ""
        val email = edittextemail.text.toString() ?: ""
        val phone = edittextPhone.text.toString() ?: ""
        val password = edittextPassword.text.toString() ?: ""
        mDelegate.signWithEmail(name,email,phone,password)
    }
}