package com.plancontrol.api.enums;

public enum ETypeMessage {

	ADESAO(1, "Adesão"), WIFI(2, "Wifi");

	private final Integer id;
	private final String descricao;

	private ETypeMessage(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao.concat(": ");
	}

	public static ETypeMessage parse(Integer id) {
		for (ETypeMessage item : ETypeMessage.values()) {
			if (item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}
}
