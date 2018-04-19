package co.devhack.musicapp.presetation.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import co.devhack.musicapp.R;
import co.devhack.musicapp.presetation.contract.LoginContract;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    TextView etUser;
    TextView etPassword;
    Switch swRememberUser;
    Button btnStartSession;
    Button btnDotnHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        swRememberUser = findViewById(R.id.swRememberUser);
        btnStartSession = findViewById(R.id.btnStartSession);
        btnDotnHaveAccount = findViewById(R.id.btnDotnHaveAccount);

        btnStartSession.setOnClickListener(this);
        btnDotnHaveAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStartSession:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("param1", "Un parametro");
                startActivity(intent);
                break;
            case R.id.btnDotnHaveAccount:
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

    }

    @Override
    public void showLoginErrorMessage(Throwable error) {

    }

    @Override
    public void goToCreateAccount() {

    }
}
