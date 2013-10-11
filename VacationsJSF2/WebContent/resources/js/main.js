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