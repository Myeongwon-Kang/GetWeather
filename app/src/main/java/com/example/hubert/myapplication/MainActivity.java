package com.example.hubert.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tem)
    TextView tem;
    @Bind(R.id.getWeatherBtn)
    Button getWeatherBtn;
    @Bind(R.id.tvLatitude)
    TextView tvLatitude;
    @Bind(R.id.tvLongtitude)
    TextView tvLongtitude;
    @Bind(R.id.lat)
    EditText mlat;
    @Bind(R.id.lon)
    EditText mlon;

    @OnClick(R.id.getWeatherBtn)
    public void setGetWeatherBtn(View view){

        String lat= mlat.getText().toString();
        String lot = mlon.getText().toString();

        Retrofit client = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").addConverterFactory(GsonConverterFactory.create()).build();

        ApiInterface service = client.create(ApiInterface.class);
        Call<Repo> call = service.repo("684b98e21b4f35b7d52abe9ff6279349", Double.valueOf(lat), Double.valueOf(lot));
        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Response<Repo> response) {
                if (response.isSuccess()) {
                    Repo repo = response.body();
                    tem.setText(String.valueOf(repo.getMain().getTemp()));
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
