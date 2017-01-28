package br.com.thiengo.rodmotors.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import br.com.thiengo.rodmotors.domain.Moto;


public class Presenter implements MVP.PresenterImpl {

    private MVP.ModelImpl model;
    private MVP.ViewImpl view;
    private ArrayList<Moto> motos = new ArrayList<>();

    public Presenter(){
        model = new Model( this );
    }

    @Override
    public void setView( MVP.ViewImpl view ){
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void retrieveMotos(Bundle savedInstanceState) {
        if( savedInstanceState != null ){
            motos = savedInstanceState.getParcelableArrayList( MVP.ViewImpl.MOTOS_KEY );
            return;
        }
        model.retrieveMotos();
    }

    @Override
    public void updateEhFavoritoMoto(Moto moto) {
        moto.setEhFavorito( !moto.isEhFavorito() );
        model.updateEhFavoritoMoto( moto );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast( mensagem );
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar( visibilidade );
    }

    @Override
    public void updateListaRecycler(ArrayList<Moto> m) {
        motos.clear();
        motos.addAll( m );
        view.updateListaRecycler();
    }

    @Override
    public void updateItemRecycler(Moto m) {
        for( int i = 0; i < motos.size(); i++ ){
            if( motos.get(i).getId() == m.getId() ){
                motos.get(i).setEhFavorito( m.isEhFavorito() );
                view.updateItemRecycler( i );
                break;
            }
        }
    }

    @Override
    public ArrayList<Moto> getMotos() {
        return motos;
    }
}
