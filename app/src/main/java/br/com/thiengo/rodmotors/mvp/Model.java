package br.com.thiengo.rodmotors.mvp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import br.com.thiengo.rodmotors.domain.Moto;
import br.com.thiengo.rodmotors.network.JsonHttpRequest;

/**
 * Created by viniciusthiengo on 27/01/17.
 */

public class Model implements MVP.ModelImpl {

    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private MVP.PresenterImpl presenter;

    public Model( MVP.PresenterImpl presenter ){
        this.presenter = presenter;
    }

    @Override
    public void retrieveMotos() {
        RequestParams requestParams = new RequestParams(JsonHttpRequest.METODO_KEY, "get-motos");

        asyncHttpClient.post(
                presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }

    @Override
    public void updateEhFavoritoMoto( Moto moto ){
        RequestParams requestParams = new RequestParams();
        requestParams.put( JsonHttpRequest.METODO_KEY, "update-favorito-moto" );
        requestParams.put( Moto.ID_KEY, moto.getId() );
        requestParams.put( Moto.EH_FAVORITO_KEY, moto.isEhFavorito() );

        asyncHttpClient.post(presenter.getContext(),
                JsonHttpRequest.URI,
                requestParams,
                new JsonHttpRequest( presenter ));
    }
}
