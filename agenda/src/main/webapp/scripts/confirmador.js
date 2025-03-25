/**
 * confirmacao de exclusao  de contacto
 * @autor Manuel Muchanga
 * @param idcon
 */

//Confirmacao de remocao de contacto padrao
function confirmar(idcon){
	let resposta = confirm("Confirma s exclusao deste contacto?")
	if(resposta === true){
	
	window.location.href = "delete?idcon=" + idcon//encaminhar id de confirmacao ao para servilet denominado delete para contacto contacto que sera excluido
		                        
		
	}
}

//confirmar usando sweetAlert
function confirmar2(idcon) {
    Swal.fire({
        title: "Tem certeza?",
        text: "Essa ação não pode ser desfeita!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sim, excluir!",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "delete?idcon=" + idcon;
        }
    });
}

 