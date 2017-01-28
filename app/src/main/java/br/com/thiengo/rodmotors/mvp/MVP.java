package br.com.thiengo.rodmotors.mvp;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

import br.com.thiengo.rodmotors.domain.Moto;

/**
 * Created by viniciusthiengo on 27/01/17.
 */

public interface MVP {
    interface ModelImpl {
        public void retrieveMotos();
        public void updateEhFavoritoMoto( Moto m );
    }

    interface PresenterImpl {
        public void retrieveMotos(Bundle savedInstanceState);
        public Context getContext();
        public void showProgressBar( boolean status );
        public void showToast( String mensagem );
        public void updateListaRecycler(ArrayList<Moto> motos);
        public void updateItemRecycler(Moto moto);
        public void updateEhFavoritoMoto( Moto moto );
        public ArrayList<Moto> getMotos();
        public void setView( ViewImpl view );
    }

    interface ViewImpl {
        String MOTOS_KEY = "motos";

        public void showProgressBar( int visibilidade );
        public void showToast( String mensagem );
        public void updateListaRecycler();
        public void updateItemRecycler( int posicao );
    }
}
