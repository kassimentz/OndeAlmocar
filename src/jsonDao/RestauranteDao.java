package jsonDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Restaurante;

public class RestauranteDao implements JsonGenericDao{

	@Override
	public List<?> readJson() {
		
		Gson gson = new Gson();
		BufferedReader reader = null;
		List<Restaurante> restaurantes = null;
		
		try {
			restaurantes = new ArrayList<Restaurante>();
			
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("data/restaurantes.json").getFile());
			
			reader = new BufferedReader(new FileReader(file.getPath()));
			gson = new GsonBuilder().create();
			Restaurante[] restaurante = gson.fromJson(reader,
					Restaurante[].class);

			for (int i = 0; i < restaurante.length; i++) {
				Restaurante r = new Restaurante();
				r.setIdRestaurante(restaurante[i].getIdRestaurante());
				r.setNome(restaurante[i].getNome());
				r.setEscolhidoSemana(restaurante[i].isEscolhidoSemana());

				restaurantes.add(r);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return restaurantes;
	}

}
