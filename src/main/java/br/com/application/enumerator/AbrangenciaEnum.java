package br.com.application.enumerator;

public enum AbrangenciaEnum {

	NACIONAL, 
    INTERNACIONAL;  
	
	public static AbrangenciaEnum getFornecedorEnum(String fornecedor) {
		for(AbrangenciaEnum f: AbrangenciaEnum.values()) {
			if(f.toString().equals(fornecedor.toUpperCase())) {
				return f;
			}
		}
		return null;
	}

	
}
