package br.com.thiengo.rodmotors.network;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.thiengo.rodmotors.domain.Moto;
import br.com.thiengo.rodmotors.mvp.MVP;
import cz.msebera.android.httpclient.Header;


public class JsonHttpRequest extends JsonHttpResponseHandler {
    public static final String URI = "http://192.168.25.221:8888/rod-motors/ctrl/CtrlMoto.php";
    public static final String METODO_KEY = "metodo";

    private MVP.PresenterImpl presenter;


    public JsonHttpRequest( MVP.PresenterImpl presenter ){
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        presenter.showProgressBar( true );
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Gson gson = new Gson();
        Moto m = gson.fromJson( response.toString(), Moto.class );
        presenter.updateItemRecycler( m );
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
        presenter.updateListaRecycler( motos );
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        presenter.showToast( responseString );
    }

    @Override
    public void onFinish() {
        presenter.showProgressBar( false );
    }
}

