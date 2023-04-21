let api_url = "http://localhost:8080/Agrify/api/auction/1";

function buildElement(data) {
	// Base element with calls elements
	element = document.createElement("div");

	pDiv = document.createElement("p");

	pId = data.id;
	pName = data.name;

	// Div for id
	idDiv = document.createElement("div");
	idP = document.createElement("p");
	idText = document.createTextNode(pId);
	// Append the id text
	idP.append(idText);
	// Append the id text inside the id div
	idDiv.appendChild(idP);

	// Div for name
	nameDiv = document.createElement("div");
	nameP = document.createElement("p");
	nameText = document.createTextNode(pName);
	// Append the name text
	nameP.append(nameText);
	// Append the name text inside the id div
	nameDiv.appendChild(nameP);

	// Div for button
	buttonDiv = document.createElement("div");
	buttonA = document.createElement("a");
	// Add href to the a element
	buttonA.setAttribute("href", "buyer_auction_page.html");
	// Button
	buttonButton = document.createElement("button");
	// Append the button text
	buttonText = document.createTextNode("Start bidding");
	// Insert button text into the button
	buttonButton.append(buttonText);
	// Append button into the a tag
	buttonA.appendChild(buttonButton);
	// Append the a tag and button into the button div
	buttonDiv.appendChild(buttonA);

	// Give base element class
	element.classList.add("elements");

	// Append id div into base element
	element.appendChild(idDiv);
	element.appendChild(nameDiv);
	element.appendChild(buttonDiv);

	document.getElementById("auction-list").insertBefore(element, document.getElementById("here"))
}

function getData(api_url) {
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
			// Due to funcion being asynchronous the data might not load in the proper order
			if (data.empty == "true") {
				return;
			}
			buildElement(data);
		})
		.catch((error) => console.error("FETCH ERROR:", error));
}

for (i = 1; i <= 3; i++) {
	api_url = "http://localhost:8080/Agrify/api/auction/" + i;
	getData(api_url);
}
