package dev.timatifey.harmony.screen.home.settings

import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import dev.timatifey.harmony.R
import dev.timatifey.harmony.common.mvp.MvpViewObservableBase
import kotlinx.coroutines.delay

class SettingsMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<SettingsMvpView.Listener>(), SettingsMvpView {
    override var rootView: View = layoutInflater.inflate(R.layout.fragment_settings, parent, false)

    private val tvEmail: AppCompatTextView = findViewById(R.id.fragment_settings_email)
    private val tvPassword: AppCompatTextView = findViewById(R.id.fragment_settings_password)
    private val tvEmailEdit: AppCompatTextView = findViewById(R.id.fragment_settings_email_edit)
    private val tvPasswordEdit: AppCompatTextView =
        findViewById(R.id.fragment_settings_password_edit)
    private val switchSpotify: SwitchCompat =
        findViewById(R.id.fragment_settings__connections_spotify_switch)

    init {
        tvEmailEdit.setOnClickListener { listeners.forEach { it.onEmailEditClicked() } }
        tvPassword.apply {
            setOnLongClickListener {
                tvPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                true
            }
            setOnFocusChangeListener { _, _ ->
                if (tvPassword.isFocused.not()) {
                    tvPassword.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            }
        }
        tvPasswordEdit.setOnClickListener { listeners.forEach { it.onPasswordEditClicked() } }
        switchSpotify.setOnCheckedChangeListener { _, b ->
            listeners.forEach {
                it.onSpotifySwitchClicked(b)
            }
        }

    }

    override fun setEmailText(text: String) {
        tvEmail.text = text
    }

    override fun setPasswordText(text: String) {
        tvPassword.text = text
    }

    override fun setSpotifySwitchState(state: Boolean) {
        switchSpotify.isChecked = state
    }

    override fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(msgId: Int) {
        Toast.makeText(context, getString(msgId), Toast.LENGTH_SHORT).show()
    }
}