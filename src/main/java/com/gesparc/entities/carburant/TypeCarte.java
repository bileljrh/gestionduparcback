package com.gesparc.entities.carburant;

public enum TypeCarte {
	p741(741), p742(742), p744(744);

	private final int typeCarte;

	TypeCarte(int typeCarte) {
		this.typeCarte = typeCarte;
	}

	public int getValue() {
		return typeCarte;
	}

}
