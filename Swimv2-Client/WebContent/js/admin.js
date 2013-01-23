function validateFormSkill() {

	var skill = document.forms["form"]["skill"].value;
	
	if (skill == null || skill == "") {
		alert("Campo Skill non valido");
		return false;
	}

	return true;
}

function validateFormPassword() {

	var pass = document.forms["form"]["password"].value;
	
	if (pass == null || pass == "") {
		alert("Campo Password non valido");
		return false;
	}

	return true;
}