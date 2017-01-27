package br.com.thiengo.rodmotors.network;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import br.com.thiengo.rodmotors.MainActivity;
import br.com.thiengo.rodmotors.domain.Moto;
import cz.msebera.android.httpclient.Header;

/**
 * Created by viniciusthiengo on 26/01/17.
 */

public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://192.168.25.221:8888/rod-motors/ctrl/CtrlMoto.php";
    public static final String METODO_KEY = "metodo";

    private WeakReference<MainActivity> activity;


    public JsonHttpRequest( MainActivity activity ){
        this.activity = new WeakReference<>(activity);
    }

    @Override
    public void onStart() {
        activity.get().showProgressBar( true );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Gson gson = new Gson();
        Moto m = gson.fromJson( response.toString(), Moto.class );
        activity.get().updateItemRecycler( m );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        Gson gson = new Gson();
        ArrayList<Moto> motos = new ArrayList<>();
        Moto m;

        for( int i = 0; i < response.length(); i++ ){
            try{
                m = gson.fromJson( response.getJSONObject( i ).toString(), Moto.class );
                motos.add( m );
            }
            catch(JSONException e){}
        }
        activity.get().updateListaRecycler( motos );
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        activity.get().showToast( responseString );
    }

    @Override
    public void onFinish() {
        activity.get().showProgressBar( false );
    }
}
