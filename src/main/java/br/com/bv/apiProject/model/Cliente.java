package br.com.bv.apiProject.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tbcliente") 
public class Cliente implements Serializable {

	private static final long serialVersionUID = -4670616447185887146L;

	@Id
    @SequenceGenerator(name="tbcliente_codigo_seq", sequenceName="tbcliente_codigo_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tbcliente_codigo_seq")
	@Column(name = "codigo", unique = true, nullable = false)
	private Integer codigo;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "idade", nullable = false)
	private Integer idade;

	@Column(name = "temperatura_minima", nullable = false)
	private BigDecimal temperaturaMinima;
	
	@Column(name = "temperatura_maxima", nullable = false)
	private BigDecimal temperaturaMaxima;

	
	public Cliente() {
		super();
	}

	public Cliente(Integer codigo, String nome, Integer idade, BigDecimal temperaturaMinima,
			BigDecimal temperaturaMaxima) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.idade = idade;
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public BigDecimal getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(BigDecimal temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public BigDecimal getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(BigDecimal temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((idade == null) ? 0 : idade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
