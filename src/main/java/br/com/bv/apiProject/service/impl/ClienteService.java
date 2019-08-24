package br.com.bv.apiProject.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bv.apiProject.model.Cliente;
import br.com.bv.apiProject.repository.ClienteRepository;
import br.com.bv.apiProject.service.IClienteService;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() {

		List<Cliente> list = new ArrayList<Cliente>();

		clienteRepository.findAll().forEach(list::add);

		return list;
	}

	@Override
	public void save(Cliente cliente, String ipAddress) {
		resolverPreDependenciasInclusao(cliente, ipAddress);
		clienteRepository.save(cliente);
	}

	private void resolverPreDependenciasInclusao(Cliente cliente, String ipAddress) {
		validarCamposObrigatorios(cliente);
		String geoLocalizacao = obterGeolocalizacao(ipAddress);
		resolverPreDependenciasClima(geoLocalizacao, cliente);
	}

	private void validarCamposObrigatorios(Cliente cliente) {
		if (cliente.getNome().isEmpty()) {
			//TODO throw exception
		}
		
		if (cliente.getIdade() == null) {
			//TODO throw exception
		}
	}

	private String obterGeolocalizacao(String ipAdress) {
		String retorno = null;

		StringBuilder sb = new StringBuilder();
		sb.append("https://ipvigilante.com/");
		sb.append(ipAdress);

		RestTemplate restTemplate = new RestTemplate();

		try {
			String result = restTemplate.getForObject(sb.toString(), String.class);
			JSONObject jsonObject = new JSONObject(result);
			String latitude = jsonObject.getJSONObject("data").getString("latitude");
			String longitude = jsonObject.getJSONObject("data").getString("longitude");

			retorno = latitude.concat(",").concat(longitude);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private void resolverPreDependenciasClima(String geolocalizcao, Cliente cliente) {

		StringBuilder sbwoeid = new StringBuilder();
		StringBuilder sbLocationDay = new StringBuilder();

		String path = "https://www.metaweather.com/api/location/";

		sbwoeid.append(path).append("search/?lattlong=").append(geolocalizcao);

		sbLocationDay.append(path);

		RestTemplate restTemplate = new RestTemplate();

		try {
			/** Obtendo o woeid mais próximo **/
			String result = restTemplate.getForObject(sbwoeid.toString(), String.class);

			JSONArray jsonArray = new JSONArray(result);

			String woeid = obterWoeidMaisProximo(jsonArray);
			
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			sbLocationDay.append(woeid).append("/").append(year).append("/").append(month).append("/").append(day);
			/**Obtendo temperatura máxima e miníma**/
			result = restTemplate.getForObject(sbLocationDay.toString(), String.class);
			jsonArray = new JSONArray(result);
			
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			BigDecimal minTemp =  new BigDecimal(jsonObject.getString("min_temp"));	
			BigDecimal maxTemp =  new BigDecimal(jsonObject.getString("max_temp"));	
			
			cliente.setTemperaturaMinima(minTemp);
			cliente.setTemperaturaMaxima(maxTemp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String obterWoeidMaisProximo(JSONArray jsonArray) throws JSONException {
		String woeid = null;
		BigDecimal distance = BigDecimal.ZERO;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			BigDecimal currentDistance = new BigDecimal(obj.getString("distance"));

			if (currentDistance.compareTo(BigDecimal.ZERO) == 0) { /** Se for a localização exata **/
				woeid = obj.getString("woeid"); 
				break;
			} else if (distance.compareTo(BigDecimal.ZERO) == 0) { /** Se não for a localização exata e a distância para comparação for 0 **/
				distance = new BigDecimal(obj.getString("distance"));
				woeid = obj.getString("woeid");
			} else {
				if (currentDistance.compareTo(distance) < 0) { /**Se a distância atual for maior do que a utilizada para comparação**/
					distance = new BigDecimal(obj.getString("distance"));
					woeid = obj.getString("woeid");
				}
			}
		}
		
		return woeid;
	}

	@Override
	public Cliente findByCodigo(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void updateCliente(Cliente cliente, Integer id) {
		Cliente clienteDB = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		
		resolverPreDependenciasAlteracao(cliente, clienteDB);
		
		clienteRepository.save(clienteDB);
	}

	private void resolverPreDependenciasAlteracao(Cliente cliente, Cliente clienteDB) {
		if (cliente.getNome() != null) {
			clienteDB.setNome(cliente.getNome());
		}

		if (cliente.getIdade() != null) {
			clienteDB.setIdade(cliente.getIdade());
		}
	}

	@Override
	public void deleteCliente(Integer id) {
		clienteRepository.deleteById(id);
	}

}
