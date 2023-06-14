import { getCookieDataJSON } from "./cookie_data.js";

let json_cookie_data = getCookieDataJSON("user_data_cookie");

document.getElementById("profile-button").addEventListener("click", show_profile);

function show_profile() {
	if (json_cookie_data.user_role == "Buyer") {
		window.location.href = "http://localhost:8080/Agrify/buyer_profile.html";
	}
	else if (json_cookie_data.user_role == "Seller") {
		window.location.href = "http://localhost:8080/Agrify/seller_profile.html";
	}
	else {
		window.location.href = "http://localhost:8080/Agrify/index.html";
	}
}