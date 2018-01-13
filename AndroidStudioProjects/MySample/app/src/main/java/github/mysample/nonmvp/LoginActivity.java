package github.mysample.nonmvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import github.mysample.R;

public class LoginActivity extends Activity {

    EditText mLoginText, mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginText = findViewById(R.id.login_text);
        mPassword = findViewById(R.id.password_text);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginText != null && mPassword != null) {
                    Intent intent = new Intent(LoginActivity.this, LoginWelcomePage.class);
                    String str= mLoginText.getText().toString();
                    intent.putExtra("GETME", str);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
