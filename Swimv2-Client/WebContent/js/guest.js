function isValidDate(giorno, mese,anno){
	 mese++;
	 
	 if(giorno==""||mese==""||anno==""){
		 return true;
	 }
	 
	 var bisestile= (((anno % 4) == 0) && ((anno % 100) != 0) || ((anno % 400) == 0));
	 var ok= true;
	 
        if (mese == 2) {  
       	 ok = bisestile ? giorno <= 29 : giorno <= 28;
        } 
        else if ((mese == 4) || (mese == 6) || (mese == 9) || (mese == 11)) {
           	 ok = giorno <= 30;
        }
        else {
           	 ok = giorno <= 31;
        }
        
    return ok;
}
function validateForm() {

	//caratteri espressione regolare e mail
	var mail_char = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-]{2,})+\.)+([a-zA-Z0-9]{2,})+$/;
	
	var mail = document.forms["form"]["email"].value;
	var password = document.forms["form"]["password"].value;
	var nome = document.forms["form"]["nome"].value;
	var cognome = document.forms["form"]["cognome"].value;
	var giorno = document.forms["form"]["giornoNascita"].value;
	var mese = document.forms["form"]["meseNascita"].value;
	var anno = document.forms["form"]["annoNascita"].value;
	
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
	
	if (!isValidDate(giorno, mese,anno)) {
		alert("La Data non esiste");
		return false;
	}

	return true;
}

function validateFormCercaNominativo() {

	var nome = document.forms["form1"]["nome"].value;
	var cognome = document.forms["form1"]["cognome"].value;
	
	if ((nome == null || nome == "")&&(cognome == null || cognome == "")) {
		alert("I campi nome e cognome non possono essere entrambi nulli");
		return false;
	}

	return true;
}

function validateFormCercaSkill() {

	var skill = document.forms["form2"]["skill"].value;
	
	if (skill == null || skill == "") {
		alert("Campo Skill non può essere nullo");
		return false;
	}

	return true;
}