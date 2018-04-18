package co.devhack.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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
}
