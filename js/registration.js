function reg(form) {
	let ml = document.getElementById("buyer");
	let fl = document.getElementById("seller");

	if (ml.checked == false && fl.checked == false) {
		alert("Selecting buyers and sellers are required");
		return false;
	}
	return true;
}

function check_empty_text_field() {
	var gender = true;
	if (document.registration.gender.value == "") {
		alert("enter the gender");
		gender = false;
	}
	else {
		gender = true;
	}
	return gender;
}
