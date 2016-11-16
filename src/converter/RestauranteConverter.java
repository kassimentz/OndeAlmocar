package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Restaurante;
import service.RestauranteService;

@FacesConverter(value="restauranteConverter")
public class RestauranteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				RestauranteService service = (RestauranteService) fc
						.getExternalContext().getApplicationMap()
						.get("restauranteService");
				return service.getRestaurantes().get(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Erro de conversão",
						"Restaurante Inválido."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Restaurante) object).getIdRestaurante());
		} else {
			return null;
		}
	}

}
