var searchUrl = '/'+AppName+'/customer/findRecharge';
$(function(){
	var customerId = $('#customerId').val();
	initTable(searchUrl,{'id':customerId});
});
function combiningTable(page){
	var trs = '';
	for( var i = 0; i < page.length; i++ ) {
		trs += '<tr>'
		trs += "<td>"+ getYMDHMSFormat(page[i].regdt) +"</td>";
		trs += "<td>"+ page[i].recharge +"</td>";
		trs += "<td>"+ page[i].regUsername +"</td>";
		trs += '</tr>'
    }
	return trs;
}