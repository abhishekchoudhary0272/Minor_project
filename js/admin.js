let api_url = "http://localhost:8080/Agrify/api/user/all";

function buildElement(data) {
	const usersTable = document.getElementById('users-table');
	const tbody = usersTable.querySelector('tbody');

	// Iterate over each user and create a table row
	data.forEach(user => {
		const row = document.createElement('tr');

		const userIdCell = document.createElement('td');
		userIdCell.textContent = user.id;
		row.appendChild(userIdCell);

		const firstNameCell = document.createElement('td');
		firstNameCell.textContent = user.first_name;
		row.appendChild(firstNameCell);
		
		const lastNameCell = document.createElement('td');
		lastNameCell.textContent = user.last_name;
		row.appendChild(lastNameCell);
		
		const roleCell = document.createElement('td');
		roleCell.textContent = user.role;
		row.appendChild(roleCell);
		
		const emailCell = document.createElement('td');
		emailCell.textContent = user.email;
		row.appendChild(emailCell);
		
		const passwordCell = document.createElement('td');
		passwordCell.textContent = user.password;
		row.appendChild(passwordCell);

		const phoneNumberCell = document.createElement('td');
		phoneNumberCell.textContent = user.phone_number;
		row.appendChild(phoneNumberCell);
		
		const birthDateCell = document.createElement('td');
		birthDateCell.textContent = user.birth;
		row.appendChild(birthDateCell);
		
		const aadhaarIDCell = document.createElement('td');
		aadhaarIDCell.textContent = user.aadhaar_id;
		row.appendChild(aadhaarIDCell);

		const actionsCell = document.createElement('td');
		const editButton = document.createElement('button');
		editButton.textContent = 'Edit';
		const deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		actionsCell.appendChild(editButton);
		actionsCell.appendChild(deleteButton);
		row.appendChild(actionsCell);

		tbody.appendChild(row);
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