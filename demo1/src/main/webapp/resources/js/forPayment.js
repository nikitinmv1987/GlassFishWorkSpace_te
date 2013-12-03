shortcut.add("Esc", function() {
	blockClick(blockReturn);    
});

//shortcut.add("Enter", function() {
	//blockClick(blockSave);
//});

//next focus2
var idj = "service";

shortcut.add("Tab", function() {		
	if (idj == "number") {
		idj = "sum";
		document.getElementById("form1:number").focus();
	}
	else if (idj == "sum") {
		idj = "blockSave";
		document.getElementById("form1:sum").focus();
	} 
	else if (idj == "blockSave") {
		idj = "service_inp";
		document.getElementById("form1:blockSave").focus();
	}		
	else if (idj == "service") {
		idj = "service";
		document.getElementById("form1:service").getElementsByTagName("input")[0].focus();
		
		//$('#service').next().find('input').focus();
	}	
});

function nextfocus(idp) {
	if (idp=="service") {		
		idj = "number";
	}	
	if (idp=="number") {
		idj = "sum";
	}
	else if (idp=="sum") {
		idj = "blockSave";
	}
	else if (idp=="blockSave") {
		idj = "service";
	}
};
	