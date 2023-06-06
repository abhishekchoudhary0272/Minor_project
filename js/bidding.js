import { getCookieDataJSON } from "./cookie_data.js";

const getUserData = async (id) => {
	return fetch('http://localhost:8080/api/user/id/' + id)
		.then((response) => {
			return response.json().then((data) => {
				console.log(data);
				return data;
			}).catch((err) => {
				console.log(err);
			})
		});
}

const getUserDataJSON = async (id) => {
	let jsonData = await getUserData(id);
	return jsonData
}

console.log(getUserData(3));
console.log(getUserDataJSON(3));

let api_url = "http://localhost:8080/Agrify/api/bid/all";

function getBidData() {
	const usersTable = document.getElementById('users-table');
	const tbody = usersTable.querySelector('tbody');
	tbody.innerHTML = ''; // Clear existing table data

	fetch(api_url)
		.then(response => response.json())
		.then(data => {
			let i = 0;
			data.forEach(bid => {
				const row = document.createElement('tr');

				const bidderNameCell = document.createElement('td');
				bidderNameCell.textContent = get(bid.offerer_id);
				row.appendChild(bidderNameCell);

				const priceCell = document.createElement('td');
				priceCell.textContent = bid.first_name;
				row.appendChild(priceCell);

				tbody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching user data:', error);
		});
}

// Call to initially load the user data
// getBidData();

// Reloading every 10 secs
// const reloadButton = document.getElementById('reload-button');
// reloadButton.addEventListener('click', reloadAllUsers);
