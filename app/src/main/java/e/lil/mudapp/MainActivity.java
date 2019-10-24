package e.lil.mudapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textUser;
    public static final String user="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUser = (TextView) findViewById(R.id.txtUser);

        String usuario = getIntent().getStringExtra("name");
        textUser.setText("¡Bienvenido " + usuario + "!");

    }
}
