package br.sc.senac.aula10.bo;

import br.sc.senac.aula10.entity.Pessoa;

public class PermissaoBO {
	
	public String obterDestino(Pessoa pessoa) {
		String destino = "";
		
		if(pessoa != null) {
			//Com sessão e sem permissão
			if(!temPermissao(pessoa)) {
				destino = "/aula10/semPermissao.xhtml";
			}
		}else {
			//Sem sessão
			destino = "/aula10/login.xhtml";
		}
		
		return destino; 
	}

	public boolean temPermissao(Pessoa pessoa) {
		return (pessoa != null && pessoa.getTipoPermissao() == 1);
	}
}