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

    private var logoutPreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)

        logoutPreference = preferenceScreen.findPreference("logout")
        logoutPreference?.setOnPreferenceClickListener {
            signOut()
        }
    }

    private fun signOut(): Boolean {
        val action = SettingFragmentDirections.actionGoToLoginFragment()
        Firebase.auth.signOut()
        findNavController().navigate(action)
        Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()
        return true
    }
}