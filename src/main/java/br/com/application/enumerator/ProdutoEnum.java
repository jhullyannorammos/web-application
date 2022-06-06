package br.com.application.enumerator;

public enum ProdutoEnum {
   
	ARCONDICIONADO,
	ESTABILIZADOR,
    COMPUTADOR,
    IMPRESSORA,
    HEADPHONE,
    SERVIDOR,
    NOTEBOOK,
    TELEFONE,
    SCANNER,
    CELULAR,
    TECLADO,
    MONITOR,
    NOBREAK,
    IPHONE,
    SWITCH,
    MOUSE,
    SSD,
    TV,
    HD;
	
	public static ProdutoEnum getProdutoEnum(String menu) {
		for(ProdutoEnum f: ProdutoEnum.values()) {
			if(f.toString().equals(menu.toUpperCase())) {
				return f;
			}
		}
		return null;
	}
     
}
