shortcut.add("Esc", function() {
	blockClick(blockReturn);    
});

shortcut.add("Enter", function() {
	blockClick(blockSave);
});

//next focus2
var idj = "summ";

shortcut.add("Tab", function() {		
	if (idj == "number") {
		idj = "summ";
		document.getElementById("form1:number").focus();
	}
	else if (idj == "summ") {
		idj = "blockSave";
		document.getElementById("form1:summ").focus();
	} 
	else if (idj == "blockSave") {
		idj = "number";
		document.getElementById("form1:blockSave").focus();
	}		
});

function nextfocus(idp) {
	if (idp=="number") {
		idj = "summ";
	}
	else if (idp=="summ") {
		idj = "blockSave";
	}
	else if (idp=="blockSave") {
		idj = "number";
	}
};
	