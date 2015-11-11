package org.uqbar.holamundo_rest_android_studio;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;

public interface GreetingService {
    @GET("/greeting")
    Call<Greeting> getGreeting();
}
