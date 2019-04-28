var pageUrl;
var searchJson;
var table;

function initTable(url,json,tableId){
	pageUrl=url;
	searchJson=json;
	table=tableId;
	tableId = tableId?tableId:'table';
	$.ajax({
		url:url,
		type:"POST",
		data:json,
		dataType:'JSON',
		success:function(json){
			try {
				$('#'+tableId+' tbody').empty().append(combiningTable(json.list));
				initPager(json);
			} catch (e) {
				console.log(e);
			}
		},error : function(XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus+"----"+errorThrown);
		}
	});
}

function reloadTable(tableId){
	if(tableId)
		initTable(pageUrl,searchJson,tableId);
	else
		initTable(pageUrl,searchJson,table);
}

function toPage(pageNum){
	searchJson.pageNum = pageNum ;
	initTable(pageUrl,searchJson,table);
}

function previousPage(){
	if(currentPage==1)
		return;
	searchJson.pageNum = currentPage-1 ;
	initTable(pageUrl,searchJson,table);
}

function nextPage(){
	if(currentPage==pages)
		return;
	searchJson.pageNum = currentPage+1 ;
	initTable(pageUrl,searchJson,table);
}

var currentPage = 0;
var pages = 0
function initPager(pageInfo){
	currentPage = pageInfo.pageNum;
	pages = pageInfo.pages;
	
	var pageNums = '';
	for(var i=0;i<pageInfo.navigatepageNums.length;i++){
		pageNums+='<li onclick="toPage('+pageInfo.navigatepageNums[i]+')" class="page-item '+(pageInfo.navigatepageNums[i]==pageInfo.pageNum?'active':'')+'"><a class="page-link" href="#">'+pageInfo.navigatepageNums[i]+'</a></li>';
    }
	var pagerHTML = '<ul class="pagination">'+
    '<li onclick="previousPage()" class="page-item '+(pageInfo.hasPreviousPage?'':'disabled')+'">'+
      '<a class="page-link" href="#" aria-label="Previous">'+
        '<span aria-hidden="true">&laquo;</span>'+
      '</a>'+
    '</li>'+pageNums+
    '<li onclick="nextPage()" class="page-item  '+(pageInfo.hasNextPage?'':'disabled')+'">'+
      '<a class="page-link" href="#" aria-label="Next">'+
        '<span aria-hidden="true">&raquo;</span>'+
      '</a>'+
    '</li>'+
  '</ul>';
	$('#pager').html(pagerHTML);
	$('#currentPage').html(currentPage);
	$('#pages').html(pages);
	$('#total').html(pageInfo.total);
}