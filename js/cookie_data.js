// Retruns base64 encoded cookie data
function getCookieData(name) {
	const value = `; ${document.cookie}`;
	const parts = value.split(`; ${name}=`);
	if (parts.length === 2) return parts.pop().split(';').shift();
}

// Returns cookie data in JSON format
export function getCookieDataJSON(name) {
	let json_cookie_data = JSON.parse(atob(getCookieData(name)));
	return json_cookie_data;
}