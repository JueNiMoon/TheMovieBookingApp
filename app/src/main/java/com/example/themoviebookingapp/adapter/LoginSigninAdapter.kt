package com.example.themoviebookingapp.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.themoviebookingapp.fragment.LoginFragment
import com.example.themoviebookingapp.fragment.SigninFragment

class LoginSigninAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> {
                return LoginFragment()
            }
            else -> {
                return  SigninFragment()
            }
        }
    }
}