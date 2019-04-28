var searchUrl = '/'+AppName+'/customer/findExpense';
$(function(){
	var customerId = $('#customerId').val();
	initTable(searchUrl,{'id':customerId});
});
function combiningTable(page){
	var trs = '';
	for( var i = 0; i < page.length; i++ ) {
		trs += '<tr>'
		trs += "<td>"+ getYMDHMSFormat(page[i].regdt) +"</td>";
		trs += "<td>"+ page[i].expense +"</td>";
		trs += "<td>"+ page[i].expenseTypeNames +"</td>";
		trs += "<td>"+ page[i].regUsername +"</td>";
		trs += '</tr>'
    }
	return trs;
}