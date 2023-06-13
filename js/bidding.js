import { getCookieDataJSON } from "./cookie_data.js";

const getUserData = async (id) => {
	fetch('http://localhost:8080/Agrify/api/user/id/' + id)
		.then(response => {
			if (response.ok) {

				return response.json()
			} else {
				throw new Error("NETWORK RESPONSE ERROR");
			}
		})
		.then((data) => {
			console.log(data);
			copy_data = JSON.parse(JSON.stringify(data));
			show(data);
			return data;
		}).catch((err) => {
			console.log(err);
		})
};

const getUserDataJSON = async (id) => {
	let jsonData = await getUserData(id);
	return jsonData;
}

// console.log(getUserData(id));
// console.log(getUserDataJSON(id));

function show(data) {
	console.log(data);
}

// Display all the bids from the auction
function getBidData(auction_id) {
	let api_url = "http://localhost:8080/Agrify/api/bid/all/" + auction_id;
	
	console.log(getCookieDataJSON("auction_data_cookie"));

	const biddersTable = document.getElementById('bidders-table');
	const tbody = biddersTable.querySelector('tbody');
	tbody.innerHTML = ''; // Clear existing table data

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
	
	
	fetch(api_url)
	.then(response => {
		if (response.ok) {
				return response.json()
			} else {
				throw new Error("Network error")
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
				row.appendChild(bidderNameCell);
				
				const priceCell = document.createElement('td');
				priceCell.textContent = bid.offer;
				row.appendChild(priceCell);
				
				tbody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching user data:', error);
		});
}

let id = getCookieDataJSON("auction_data_cookie").auction_id;

// Reloading every 20 secs
setTimeout(getBidData(id), 20000);