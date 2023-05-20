let api_url = "http://localhost:8080/Agrify/api/auction/all";

function buildElement(data) {
	const auctionTable = document.getElementById('auction-list');

	// Iterate over each auction and create an element
	data.forEach(auction => {
		// Base element with class "elements"
		element = document.createElement("div");

		pDiv = document.createElement("p");

		pId = auction.id;
		pName = auction.name;

		/* Div for id */
		idDiv = document.createElement("div");
		idP = document.createElement("p");
		idText = document.createTextNode(pId);
		// Append the id text
		idP.append(idText);
		// Append the id text inside the id div
		idDiv.appendChild(idP);

		/* Div for name */
		nameDiv = document.createElement("div");
		nameP = document.createElement("p");
		nameText = document.createTextNode(pName);
		// Append the name text
		nameP.append(nameText);
		// Append the name text inside the id div
		nameDiv.appendChild(nameP);

		/* Div for button */
		buttonDiv = document.createElement("div");
		// Form element
		buttonForm = document.createElement("form");
		// Add attribute to the form
		buttonForm.setAttribute("action", "/Agrify/bidding");
		buttonForm.setAttribute("method", "post");
		// Form id input element
		butttonInputID = document.createElement("input");
		butttonInputID.style.display = "none";
		butttonInputID.setAttribute("name", "id");
		butttonInputID.setAttribute("value", pId);
		butttonInputID.setAttribute("type", "text");
		// Form button
		buttonButton = document.createElement("input");
		buttonButton.setAttribute("type", "submit");
		buttonButton.setAttribute("value", "Start Bidding");
		// Append button into the form element
		buttonForm.appendChild(butttonInputID);
		buttonForm.appendChild(buttonButton);
		// Append the form element contaning the button into the button div
		buttonDiv.appendChild(buttonForm);

		// Give base element class
		element.classList.add("elements");

		// Append id, name and button div into base element
		element.appendChild(idDiv);
		element.appendChild(nameDiv);
		element.appendChild(buttonDiv);

		auctionTable.appendChild(element);
		// auctionTable.insertBefore(element, document.getElementById("here"))
	})
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
			buildElement(data);
		})
		.catch((error) => console.error("FETCH ERROR:", error));
}

getData(api_url);