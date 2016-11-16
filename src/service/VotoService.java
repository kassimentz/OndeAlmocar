package service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jsonDao.VotoDao;
import model.Restaurante;
import model.Voto;
import util.Utils;

@ManagedBean(name = "votoService", eager = true)
@ApplicationScoped
public class VotoService {


	VotoDao dao;
	List<Voto> votos;
	private Restaurante restauranteVencedor = new Restaurante();;
	

	public Restaurante getRestauranteVencedor() {
		return restauranteVencedor;
	}

	public void setRestauranteVencedor(Restaurante restauranteVencedor) {
		this.restauranteVencedor = restauranteVencedor;
	}

	public VotoService() {
		dao = new VotoDao();
		votos = getVotos();
	}

	public void Salvar(Voto voto) {
		// leio o arquivo, add o obj ao que retornou e mando o list
		// novamente para salvar o list
		if(!voto.getRestaurante().isEscolhidoSemana()){
			votos.add(voto);
			dao.saveVotoIntoJson(votos);
		}
	}

	public List<Voto> getVotos() {
		// ler do json
		return dao.readJson();

	}

	public boolean verificaEleitor(Voto v) {
		// verificar se o cpf ja se encontra na relacao de votantes da semana -
		// verificar a data
		boolean encontrou = false;
		for (Voto voto : votos) {
			if ((voto.getCpf().equalsIgnoreCase(v.getCpf()))
					&& (voto.getData() == v.getData())) {
				encontrou = true;
			}
		}
		return encontrou;
	}

	public Restaurante getVencedor() {
		Restaurante vencedor = new Restaurante();
		if(verificaTerminoVotacao()){
			if(restauranteVencedor == null && !votos.isEmpty()) {
				
				Voto maisVotado = Utils.getMostOccoringElement(votos);
				setaRestauranteVencedor(maisVotado.getRestaurante());
				setRestauranteVencedor(maisVotado.getRestaurante());
				vencedor = maisVotado.getRestaurante();
//				vencedor.setEscolhidoSemana(true);
//				this.setRestauranteVencedor(vencedor);
			} else {
				vencedor = getRestauranteVencedor();
			}
		}

		votos.clear();
		return vencedor;
	}

	private void setaRestauranteVencedor(Restaurante vencedor) {
		for (Voto voto : votos) {
			if (voto.getRestaurante().getNome().equals(vencedor.getNome())) {
				System.out.println("escolhido da semana "+ voto.getRestaurante().getNome());
				voto.getRestaurante().setEscolhidoSemana(true);
			}
		}

	}

	public int getNroVotos() {
		return dao.readJson().size();
	}

	public boolean verificaTerminoVotacao() {
		/*
		 * configurei o termino da votacao para 11:15. a partir deste horario os
		 * votos contam para o proximo dia
		 */

		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm"); 
		Date horaMinima = null;
		Date horaMaxima = null;
		Date horaAtualFormatada = null;
		Date horaAtual = new Date();
		String horaAtualString = formatador.format(horaAtual);
		try {
			horaMinima = formatador.parse("7:00");
			horaMaxima = formatador.parse("23:17");
			horaAtualFormatada = formatador.parse(horaAtualString);
		} catch (Exception e) {
			// TODO: handle exception
		}	 
		System.out.println(horaAtualFormatada.getTime() +" horaAtualFormatada");
		System.out.println(horaMinima.getTime() +" horaMinima");
		System.out.println(horaMaxima.getTime() +" horaMaxima");
		
		if(horaAtualFormatada.getTime() > horaMinima.getTime() && horaAtualFormatada.getTime() < horaMaxima.getTime()){
			
			System.out.println("hora minima");
			return false;
		} else {
			System.out.println("true hora");
			return true;
		}
	}


}