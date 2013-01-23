function validateForm() {

	//caratteri espressione regolare e mail
	var mail_char = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-]{2,})+\.)+([a-zA-Z0-9]{2,})+$/;
	
	var mail = document.forms["form"]["email"].value;
	var password = document.forms["form"]["password"].value;
	var nome = document.forms["form"]["nome"].value;
	var cognome = document.forms["form"]["cognome"].value;
	
	if (!mail_char.test(mail) || mail == null || mail == "") {
		alert("Campo E-Mail non valido");
		return false;
	}

	if (password == null || password == "") {
		alert("Campo Password non valido");
		return false;
	}

	if (nome == null || nome == "") {
		alert("Campo Nome non valido!");
		return false;
	}

	if (cognome == "" || cognome == null) {
		alert("Campo Cognome non valido");
		return false;
	}

	return true;
}