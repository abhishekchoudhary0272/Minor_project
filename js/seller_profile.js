import {getCookieDataJSON} from "./cookie_data.js";

let json_cookie_data = getCookieDataJSON("user_data_cookie");

let api_url = "http://localhost:8080/Agrify/api/user/id/" + json_cookie_data.id;

fetch(api_url)
	.then((response) => {
		if (response.ok) {
			return response.json();
		} else {
			throw new Error("NETWORK RESPONSE ERROR");
		}
	})
	.then(data => {
		// console.log(data); // Debug
		displayData(data)
	})
	.catch((error) => console.error("FETCH ERROR:", error));

function displayData(data) {
	const name = data.first_name + " " + data.last_name;
	const dob = data.birth;
	const email = data.email;
	const phone = data.phone_number;
	document.getElementsByClassName("profile-detail")[0].querySelector("#seller-name").innerHTML = name;
	document.getElementsByClassName("profile-detail")[0].querySelector("#seller-dob").innerHTML = dob;
	document.getElementsByClassName("profile-detail")[0].querySelector("#seller-email").innerHTML = email;
	document.getElementsByClassName("profile-detail")[0].querySelector("#seller-phone").innerHTML = phone;
}