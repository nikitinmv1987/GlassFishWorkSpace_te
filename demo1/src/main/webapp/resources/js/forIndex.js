window.onload = function() {
	document.getElementById("formNav:block1").focus();
	idj = "block2";
};

function setDefFocus() {
	//document.getElementById("formNav:block1").focus();
	//idj = "block2";
}

function buttonClick1() {
	alert("button1 click");
};

function buttonClick2() {
	alert("button2 click");
};

//next focus2
var idj = "block2";

shortcut.add("Tab", function() {		
	if (idj == "block1") {
		idj = "block2";
		document.getElementById("formNav:block1").focus();
	}
	else if (idj == "block2") {
		idj = "block3";
		document.getElementById("formNav:block2").focus();
	} 
	else if (idj == "block3") {
		idj = "block4";
		document.getElementById("formNav:block3").focus();
	}		
	else if (idj == "block4") {
		idj = "block1";
		document.getElementById("formNav:block4").focus();
	}
});

function nextfocus(idp) {
	if (idp=="block1") {
		idj = "block2";
	}
	else if (idp=="block2") {
		idj = "block3";
	}
	else if (idp=="block3") {
		idj = "block4";
	}
	else if (idp=="block4") {
		idj = "block1";
	}
};
	
