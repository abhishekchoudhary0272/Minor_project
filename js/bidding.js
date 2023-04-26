if (base64_cookie_data = (document.cookie).split('=')[0] == "auction_data_cookie") {
	json_cookie_data = JSON.parse(atob(base64_cookie_data));
}

api_url = "http://localhost:8080/Agrify/api/bid/" + json_cookie_data.id;

fetch(api_url)
	.then((response) => {
		if (response.ok) {
			return response.json();
		} else {
			throw new Error("NETWORK RESPONSE ERROR");
		}
	})
	.then(data => {
		console.log(data);
		displayData(data);
	})
	.catch((error) => console.error("FETCH ERROR:", error));

function displayData(data) {

}