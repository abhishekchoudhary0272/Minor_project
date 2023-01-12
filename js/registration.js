function reg(form) {
	let ml = document.getElementById("buyer");
	let fl = document.getElementById("seller");

	if (ml.checked == false && fl.checked == false) {
		alert("Selecting buyers and sellers are required");
		return false;
	}
	return true;
}