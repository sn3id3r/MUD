package e.lil.mudapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView textEmail;
    private TextView textPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        textEmail = (TextView) findViewById(R.id.txtEmail);
        textPassword = (TextView) findViewById(R.id.txtPassword);

    }

    public void Ingresar(View view) {
        // Obtener los datos de correo y contraseña ingresados
        final String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        int pos = email.indexOf("@");
        final String userName = email.substring(0,pos);

        //Verificamos que estos campos no esten vacios
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Ingrese correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        //Ingresamos al ususario mediante Firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Bienvenido "+ userName, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.user, userName);
                            startActivity(intent);

                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
