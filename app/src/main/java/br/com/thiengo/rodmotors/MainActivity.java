package br.com.thiengo.rodmotors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import br.com.thiengo.rodmotors.adapter.MotosAdapter;
import br.com.thiengo.rodmotors.domain.Moto;
import br.com.thiengo.rodmotors.network.JsonHttpRequest;

public class MainActivity extends AppCompatActivity {
    private static final String MOTOS_KEY = "motos";

    private MotosAdapter adapter;
    private ArrayList<Moto> motos = new ArrayList<>();
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        retrieveMotos( savedInstanceState );
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView rvMotos = (RecyclerView) findViewById(R.id.rv_motos);
        rvMotos.setHasFixedSize(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);
        rvMotos.setLayoutManager( layoutManager );

        adapter = new MotosAdapter( this, motos );
        rvMotos.setAdapter( adapter );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOTOS_KEY, motos);
        super.onSaveInstanceState(outState);
    }

    private void retrieveMotos( Bundle savedInstanceState ){
        if( savedInstanceState != null ){
            motos = savedInstanceState.getParcelableArrayList( MOTOS_KEY );
            return;
        }

        RequestParams requestParams = new RequestParams(JsonHttpRequest.METODO_KEY, "get-motos");
        asyncHttpClient.post(this,
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest(this));
    }

    public void updateEhFavoritoMoto( Moto moto ){
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, "update-favorito-moto" );
        requestParams.put( Moto.ID_KEY, moto.getId() );
        requestParams.put( Moto.EH_FAVORITO_KEY, moto.isEhFavorito() );

        asyncHttpClient.post(this,
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest(this));
    }

    public void updateListaRecycler( ArrayList<Moto> m ){
        this.motos.clear();
        this.motos.addAll( m );
        adapter.notifyDataSetChanged();
    }

    public void updateItemRecycler( Moto m ){
        for( int i = 0; i < motos.size(); i++ ){
            if( motos.get(i).getId() == m.getId() ){
                motos.get(i).setEhFavorito( m.isEhFavorito() );
                adapter.notifyItemChanged( i );
            }
        }
    }

    public void showToast( String mensagem ){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar( boolean status ){
        int visibilidade = status ? View.VISIBLE : View.GONE;
        findViewById(R.id.pb_loading).setVisibility( visibilidade );
    }
}
