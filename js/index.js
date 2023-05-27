// Use new api to check if the user is buyer or seller
// https://javascript.info/formdata
// https://developer.mozilla.org/en-US/docs/Web/API/FormData/FormData
async function loginCheck() {
	// Get data from the form and then call the user_check api to check if the user is a buyer or seller
	// Redirect the user to there profile page after that

	const form = document.getElementById("loginform");
	submitter = document.getElementById("login-button");
	let formData = new FormData(form, submitter);

	let formDataJSON = {};

	for (const [key, value] of formData) {
		formDataJSON[key] = value;
	}

	console.log(formDataJSON);

	let res = await fetch("http://localhost:8080/Agrify/api/user_check/check", {
		method: "POST",
		body: JSON.stringify(formDataJSON),
		headers: {
			"Content-type": "application/json"
		}
	});

	let result = await res.json();
	console.log(result);
}

function loginToggle() {
	document.querySelector(".login").classList.toggle("activelogin");
	document.querySelector(".images").classList.toggle("container3blur");
	document.querySelector(".container2").classList.toggle("container3blur");
	document.querySelector(".container1").classList.toggle("container3blur");
	document.querySelector(".container3").classList.toggle("container3blur");
	document.querySelector(".container4").classList.toggle("container3blur");
	document.querySelector(".container5").classList.toggle("container3blur");
	document.querySelector(".container6").classList.toggle("container3blur");

}

function myFunction(x) {
	x.classList.toggle("change");
	var y = document.querySelector(".sidebar");

	// y.classList.toggle("show")
	toggleStyle();
	function toggleStyle() {
		if (y.style["transform"] == "scaleX(1)")
			y.style.transform = "scaleX(0)";
		else
			y.style.transform = "scaleX(1)";
	}
}
