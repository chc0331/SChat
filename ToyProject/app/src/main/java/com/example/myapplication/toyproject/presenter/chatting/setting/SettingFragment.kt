package com.example.myapplication.toyproject.presenter.chatting.setting

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.myapplication.toyproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingFragment : PreferenceFragmentCompat() {

    private val logoutPreference: Preference? by lazy {
        preferenceScreen.findPreference("logout")
    }
    private val profileDataPreference: Preference? by lazy {
        preferenceScreen.findPreference("profile")
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)
        logoutPreference?.setOnPreferenceClickListener {
            signOut()
        }
        profileDataPreference?.setOnPreferenceClickListener {
            goToOwnerProfile()
        }
    }

    private fun signOut(): Boolean {
        val action = SettingFragmentDirections.actionGoToLoginFragment()
        Firebase.auth.signOut()
        findNavController().navigate(action)
        Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun goToOwnerProfile(): Boolean {
        val action = SettingFragmentDirections.actionGoToOwnerProfileFragment()
        findNavController().navigate(action)
        return true
    }

}