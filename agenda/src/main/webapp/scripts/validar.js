
/**
 * Validar campos obribrigatorios
 * 
 * @author Manuel Muchanga
 */

function validarContacto() {
    // Captura os elementos do formulário
    let nome = document.forms["frmContacto"]["nome"].value;
    let fone = document.forms["frmContacto"]["fone"].value;

    // Valida o nome
    if (nome.trim() === "") {
        alert('Preencha o nome');
        document.forms["frmContacto"]["nome"].focus();
        return false;

    // Valida o telefone
    } else if (fone.trim() === "") {
        alert('Preencha o fone');
        document.forms["frmContacto"]["fone"].focus();
        return false;

    // Se tudo estiver ok, envia o formulário
    } else {
        document.forms["frmContacto"].submit();
    }
}


