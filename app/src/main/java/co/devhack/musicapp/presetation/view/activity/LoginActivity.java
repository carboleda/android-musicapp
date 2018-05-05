package co.devhack.musicapp.presetation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import co.devhack.musicapp.R;
import co.devhack.musicapp.presetation.contract.LoginContract;
import co.devhack.musicapp.presetation.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    TextInputEditText etUser;
    TextInputEditText etPassword;
    Switch swRememberUser;
    Button btnStartSession;
    Button btnDotnHaveAccount;
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        swRememberUser = findViewById(R.id.swRememberUser);
        btnStartSession = findViewById(R.id.btnStartSession);
        btnDotnHaveAccount = findViewById(R.id.btnDontHaveAccount);

        btnStartSession.setOnClickListener(this);
        btnDotnHaveAccount.setOnClickListener(this);

        presenter.validateSession();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartSession:
                onLogin();
                break;
            case R.id.btnDontHaveAccount:
                presenter.onDontHaveAccount();
                break;
        }
    }

    @Override
    public void disableButtons() {
        btnStartSession.setEnabled(false);
        btnDotnHaveAccount.setEnabled(false);
        swRememberUser.setEnabled(false);
    }

    @Override
    public void enableButtons() {
        btnStartSession.setEnabled(true);
        btnDotnHaveAccount.setEnabled(true);
        swRememberUser.setEnabled(true);
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginErrorMessage(Throwable error) {
        //TODO PENDIENTE AGREGAR UN TEXTVIEW EN EL LAYOUT PARA MOSTRAR EL ERRORS
    }

    @Override
    public void goToCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRememberedUser(String username) {
        etUser.setText(username);
    }

    private void onLogin() {
        String username = etUser.getText().toString();
        String password = etPassword.getText().toString();
        boolean remember = swRememberUser.isChecked();
        presenter.onLogin(username, password, remember);
    }
}
