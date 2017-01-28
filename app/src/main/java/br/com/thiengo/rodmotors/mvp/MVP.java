package br.com.thiengo.rodmotors.mvp;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import br.com.thiengo.rodmotors.domain.Moto;


public interface MVP {
    interface ModelImpl{
        public void retrieveMotos();
        public void updateEhFavoritoMoto( Moto moto );
    }

    interface PresenterImpl{
        public void retrieveMotos( Bundle savedInstanceState );
        public void updateEhFavoritoMoto( Moto moto );
        public void showToast( String mensagem );
        public void showProgressBar( boolean status );
        public void setView( MVP.ViewImpl view );
        public Context getContext();
        public void updateListaRecycler( ArrayList<Moto> m );
        public void updateItemRecycler( Moto m );
        public ArrayList<Moto> getMotos();
    }

    interface ViewImpl{
        String MOTOS_KEY = "motos";

        public void showToast( String mensagem );
        public void showProgressBar( int visibilidade );
        public void updateListaRecycler();
        public void updateItemRecycler( int posicao );
    }
}
