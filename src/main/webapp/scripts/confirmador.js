/**
 * Confirmação de exclusão de um contato 
 * @author Pablo Henrique Souza
 */
 
 
 
 function confirmar(idcon){
	let resposta = confirm("Deseja excluir excluir este contato?")
	if (resposta === true){
		window.location.href = "delete?idcon=" + idcon
	} else {
		
	}
	
	
}