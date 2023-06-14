import { getCookieDataJSON } from "./cookie_data.js";

// Display all the bids from the auction
function getBidData(auction_id) {
	let api_url = "http://localhost:8080/Agrify/api/bid/all/" + auction_id;

	console.log(getCookieDataJSON("auction_data_cookie"));

	const biddersTable = document.getElementById('bidders-table');
	const tbody = biddersTable.querySelector('tbody');
	tbody.innerHTML = ''; // Clear existing table data

	// Fill the table with labels
	const default_row = document.createElement('tr');

	const default_serial_no_cell = document.createElement('td');
	default_serial_no_cell.textContent = "S.No";
	default_row.appendChild(default_serial_no_cell);

	const default_bidderNameCell = document.createElement('td');
	default_bidderNameCell.textContent = "Bidders Name";
	default_row.appendChild(default_bidderNameCell);

	const default_priceCell = document.createElement('td');
	default_priceCell.textContent = "Price";
	default_row.appendChild(default_priceCell);

	tbody.appendChild(default_row);

	// Get data from the API and add them to the add one by one
	fetch(api_url)
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error("Network error");
			}
		})
		.then(data => {
			console.log(data);
			let i = 1;
			data.forEach(bid => {
				const row = document.createElement('tr');

				const serial_no_cell = document.createElement('td');
				serial_no_cell.textContent = i;
				row.appendChild(serial_no_cell);
				i++;

				const bidderNameCell = document.createElement('td');
				bidderNameCell.textContent = "Name";
				
				// Get the name of each of the bidder and replace the place holder name
				fetch("http://localhost:8080/Agrify/api/user/id/" + bid.offerer_id)
					.then(response => {
						if (response.ok) {
							return response.json();
						} else {
							throw new Error("Network error");
						}
					})
					.then(data => {
						console.log(data);
						bidderNameCell.textContent = data.first_name + " " + data.last_name;
					}).catch(error => {
						console.log("Erro fetching user data: " + error);
					})
				row.appendChild(bidderNameCell);

				const priceCell = document.createElement('td');
				priceCell.textContent = bid.offer;
				row.appendChild(priceCell);

				tbody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching bid data:', error);
		});
}

let id = getCookieDataJSON("auction_data_cookie").auction_id;

// Reloading every 20 secs
setTimeout(getBidData(id), 20000);