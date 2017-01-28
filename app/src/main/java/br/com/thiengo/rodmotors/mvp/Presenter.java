package br.com.thiengo.rodmotors.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import br.com.thiengo.rodmotors.domain.Moto;

/**
 * Created by viniciusthiengo on 27/01/17.
 */

public class Presenter implements MVP.PresenterImpl {

    private ArrayList<Moto> motos = new ArrayList<>();
    private MVP.ModelImpl model;
    private MVP.ViewImpl view;

    public Presenter(){
        model = new Model(this);
    }

    @Override
    public void setView(MVP.ViewImpl view) {
        this.view = view;
    }

    @Override
    public void retrieveMotos( Bundle savedInstanceState ) {
        if( savedInstanceState != null ){
            motos = savedInstanceState.getParcelableArrayList( MVP.ViewImpl.MOTOS_KEY );
            return;
        }
        model.retrieveMotos();
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar( visibilidade );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast( mensagem );
    }

    @Override
    public void updateListaRecycler(ArrayList<Moto> m) {
        motos.clear();
        motos.addAll( m );
        view.updateListaRecycler();
    }

    @Override
    public void updateItemRecycler(Moto moto) {
        for( int i = 0; i < motos.size(); i++ ){
            if( motos.get(i).getId() == moto.getId() ){
                motos.get(i).setEhFavorito( moto.isEhFavorito() );
                view.updateItemRecycler( i );
            }
        }
    }

    @Override
    public void updateEhFavoritoMoto(Moto moto) {
        model.updateEhFavoritoMoto(moto);
    }

    @Override
    public ArrayList<Moto> getMotos() {
        return motos;
    }
}
