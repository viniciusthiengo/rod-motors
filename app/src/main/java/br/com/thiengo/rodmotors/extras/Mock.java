package br.com.thiengo.rodmotors.extras;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.rodmotors.domain.Moto;

/**
 * Created by viniciusthiengo on 26/01/17.
 */

public class Mock {
    public static Moto criarMoto( int indice ){
        String[] imagens = {
            "http://motordream.uol.com.br/upload/noticia/13895757499537.jpg",
            "http://www.motoriorentals.com.br/es/media/k2/items/cache/6f43b5263fbba79c5962514b85d34738_L.jpg",
            "http://www.moto.com.br/img/2014/03/12/img75160-1394633809-v580x435.jpg",
            "http://www.memoriamotor.r7.com/wp-content/uploads/2015/06/Forty-Eight.jpg",
            "http://motorcycle.com.vsassets.com/blog/wp-content/uploads/2016/05/050316-Harley-Davidson-Roadster-1-9.jpg",
            "https://i.ytimg.com/vi/bYHiaKMpSaE/maxresdefault.jpg",
            "https://cloud.yamahamotorsports.com/library/img.jpg?id=26284&w=840",
            "http://s1.cdn.autoevolution.com/images/moto_gallery/YAMAHABoltR-4399_10.jpg",
            "http://moto.zombdrive.com/images/yamaha-v-star-950-tourer-1.jpg",
        };
        String[] modelos = {
            "V-Rod",
            "IRON 883",
            "1200 CUSTOM CB",
            "FORTY-EIGHT",
            "ROADSTER",
            "STREET BOB",
            "V Star 1300",
            "Bolt R-Spec",
            "V Star 950 Tourer",
        };
        String[] marcas = {
            "Harley-Davidson",
            "Harley-Davidson",
            "Harley-Davidson",
            "Harley-Davidson",
            "Harley-Davidson",
            "Harley-Davidson",
            "Yamaha",
            "Yamaha",
            "Yamaha",
        };

        Moto moto = new Moto();
        moto.setImagem( imagens[indice] );
        moto.setModelo( modelos[indice] );
        moto.setMarca( marcas[indice] );
        moto.setEhFavorito( false );
        return moto;
    }

    public static List<Moto> criarListaMotos(){
        List<Moto> motos = new ArrayList<>();

        motos.add( criarMoto(0) );
        motos.add( criarMoto(1) );
        motos.add( criarMoto(2) );
        motos.add( criarMoto(3) );
        motos.add( criarMoto(4) );
        motos.add( criarMoto(5) );
        motos.add( criarMoto(6) );
        motos.add( criarMoto(7) );
        motos.add( criarMoto(8) );

        return motos;
    }
}
