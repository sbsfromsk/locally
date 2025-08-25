const createEntryBtn = document.querySelector("#goToCreateEntry");

createEntryBtn.addEventListener("click", function(e) {
		e.preventDefault();
		
		window.location.href="/entry/create";
});