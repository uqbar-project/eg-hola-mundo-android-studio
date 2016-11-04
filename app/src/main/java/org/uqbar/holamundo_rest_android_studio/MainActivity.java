package org.uqbar.holamundo_rest_android_studio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final String BASE_URL = "http://rest-service.guides.spring.io";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GreetingService service = retrofit.create(GreetingService.class);

        Call<Greeting> call = service.getGreeting();
        call.enqueue(new Callback<Greeting>() {
            @Override
            public void onResponse(Response<Greeting> response, Retrofit retrofit) {
                Greeting greeting = response.body();
                TextView greetingIdText = (TextView) findViewById(R.id.lblId);
                TextView greetingContentText = (TextView) findViewById(R.id.lblContent);
                greetingIdText.setText(greeting.getId());
                greetingContentText.setText(greeting.getContent());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("HelloWorld", t.getMessage());
                Toast.makeText(MainActivity.this, "Ha ocurrido un error al llamar al servicio", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
