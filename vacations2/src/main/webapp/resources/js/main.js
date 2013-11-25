function addOnclickToDatatableRows() {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        trs[i].onclick = new Function("highlightAndSelectRow(this)");
    }
}

function highlightAndSelectRow(tr) {
    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
        .getElementsByTagName('tr');
    for (var i = 0; i < trs.length; i++) {
        if (trs[i] == tr) {
            trs[i].bgColor = '#f5f5f5';
            document.getElementById("rowIndex").value = trs[i].rowIndex;
        } else {
            trs[i].bgColor = '#ffffff';
        }
    }
}

window.onload = function highlightSelectedRow() {
	if (document.getElementById('form:dataTable')!=null) {
	    var trs = document.getElementById('form:dataTable').getElementsByTagName('tbody')[0]
	        .getElementsByTagName('tr');
	    var i = document.getElementById("rowIndex").value -1; 
	    trs[i].bgColor = '#f5f5f5';
	};	
}

$(function(){
	 var url = window.location.pathname;  
	 var activePage = url.substring(url.lastIndexOf('/')+1);
	$('.nav li a').each(function(){  
	var currentPage = this.href.substring(this.href.lastIndexOf('/')+1);

	if (activePage == currentPage) {
	$(this).parent().addClass('active'); 
	} 
	});
	 })