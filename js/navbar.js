function activelogin() {
	document.querySelector(".login").classList.toggle("activelogin");
	document.querySelector(".images").classList.toggle("container3blur");
	document.querySelector(".container2").classList.toggle("container3blur");
	document.querySelector(".container1").classList.toggle("container3blur");
	document.querySelector(".container3").classList.toggle("container3blur");
	document.querySelector(".container4").classList.toggle("container3blur");
	document.querySelector(".container5").classList.toggle("container3blur");
	document.querySelector(".container6").classList.toggle("container3blur");

}

function myFunction(x) {
	x.classList.toggle("change");
	var y=document.querySelector(".sidebar");
	
	// y.classList.toggle("show")
	toggleStyle();
	function toggleStyle(){
		if(y.style["transform"]=="scaleX(1)")
		y.style.transform="scaleX(0)";
		else
		y.style.transform="scaleX(1)";
	}
}

