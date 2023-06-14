let api_url = "http://localhost:8080/Agrify/api/user/all";

function reloadAllUsers() {
	const usersTable = document.getElementById('users-table');
	const tbody = usersTable.querySelector('tbody');
	tbody.innerHTML = ''; // Clear existing table data

	fetch(api_url)
		.then(response => response.json())
		.then(data => {
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
				deleteButton.setAttribute("onclick", "deleteUser("+ user.id +", this)");
				deleteButton.textContent = 'Delete';
				actionsCell.appendChild(editButton);
				actionsCell.appendChild(deleteButton);
				row.appendChild(actionsCell);

				tbody.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Error fetching user data:', error);
		});
}

// Call reloadAllUsers() to initially load the user data
reloadAllUsers();

// Reloading the "All Users" section on a button click
const reloadButton = document.getElementById('reload-button');
reloadButton.addEventListener('click', reloadAllUsers);

function deleteUser(id, button) {
	let api_url = "http://localhost:8080/Agrify/api/user/delete/" + id;
	fetch(api_url)
		.then(response => response.text())
		.then((response) => {
			console.log(response);
		})
		.catch(err => console.log(err));

	// Traverse the DOM to find the closest <tr> element
	let row = button.closest('tr');
	
	row.remove();
}