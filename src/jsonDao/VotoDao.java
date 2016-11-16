package jsonDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Voto;

public class VotoDao implements JsonGenericDao{

	@Override
	public List<Voto> readJson() {
		Gson gson = new Gson();
		BufferedReader reader = null;
		List<Voto> votos = null;

		try {
			votos = new ArrayList<Voto>();

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("data/votos.json").getFile());

			reader = new BufferedReader(new FileReader(file.getPath()));
			gson = new GsonBuilder().create();
			Voto[] voto = gson.fromJson(reader, Voto[].class);
			
			
			for (int i = 0; i < voto.length; i++) {
				Voto v = new Voto();
				v.setCpf(voto[i].getCpf());
				v.setRestaurante(voto[i].getRestaurante());

				votos.add(v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return votos;
	}
	
	public void saveVotoIntoJson(List<Voto> obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		BufferedWriter writer = null;
		
		try {
			System.out.println("salvando votos");
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("data/votos.json").getFile());
			writer = new BufferedWriter(new FileWriter(file.getPath()));
			writer.write(json);
			System.out.println(json);
			writer.close();
			System.out.println("votos salvos");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
