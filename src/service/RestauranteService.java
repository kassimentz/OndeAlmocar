package service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jsonDao.RestauranteDao;
import model.Restaurante;

@ManagedBean(name = "restauranteService", eager = true)
@ApplicationScoped
public class RestauranteService {

	private List<Restaurante> restaurantes;
	private RestauranteDao dao = new RestauranteDao();
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		restaurantes = (List<Restaurante>) dao.readJson();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public Restaurante getRestauranteById(Integer id) {
		Restaurante restaurante = null;
		for (Restaurante r : restaurantes)
			if (r.getIdRestaurante() == id) {
				restaurante = r;
			}
		return restaurante;
	}
}