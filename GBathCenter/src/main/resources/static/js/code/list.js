var $csm;
$(function(){
	initCode();
	$csm=$('#addCodeSetModal');
	$csm.on('hidden.bs.modal', resetNewCode);
	$('#saveCodeSet').click(saveCodeSet);
});
function initCode(){
	$.ajax({
		url:'/'+AppName+'/code/find',
		type:'POST',
		success:function(codes){
			createCodeCards(codes);
		}
	});
}

function createCodeCards(codes){
	var codeHtml="";
	for(var i=0;i<codes.length;i++){
//		alert(JSON.stringify(codes[i].codeSets.length));
		var cs = codes[i].codeSets;
		itemHtml="";
		for(var j=0;j<cs.length;j++){
			itemHtml+="<span codeSetId=\""+cs[j].id+"\" onclick=\"deleteCodeSet('"+cs[j].id+"','"+cs[j].name+"')\" class=\"badge badge-pill badge-primary\" style='padding:7px;'>"+codes[i].codeSets[j].name+"<span style='cursor:pointer;font-size:20px;'> &times; </span></span> ";
		}
		
		codeHtml+="<div class=\"card\">"+
	    "<div class=\"card-header\" id=\"heading"+codes[i].id+"\">"+
	        "<div data-toggle=\"collapse\" data-target=\"#collapse"+codes[i].id+"\" aria-expanded=\"true\" aria-controls=\"collapseOne\" style=\"cursor:pointer\">"+
	        codes[i].name+
	          "<span class=\"badge badge-primary badge-pill\">"+cs.length+"</span>"+
	          "<button type=\"button\" onclick=\"showSaveCodeSet('"+codes[i].id+"')\" class=\"addbtn btn btn-success float-right\">✚</button>"+
	        "</div>"+
	    "</div>"+
	    "<div id=\"collapse"+codes[i].id+"\" class=\"collapse "+(i==0?"show":"")+"\" aria-labelledby=\"heading"+codes[i].id+"\" data-parent=\"#codeAccordion\">"+
	      "<div id=\"cardBody\" class=\"card-body\">"+
	      itemHtml+
	      "</div>"+
	    "</div>"+
	  "</div>";
	}
	$('#codeAccordion').html(codeHtml);
}
function showSaveCodeSet(id){
	stopBubble();
	$('#addCodeSetModal').modal();
	$('#codeId').val(id);
}

function saveCodeSet(){
	$.ajax({
		url:'/'+AppName+'/code/saveCodeSet',
		data:{
			codeId:$('#codeId').val(),
			name:$('#name').val(),
			value:$('#value').val()
		},
		dataType:'JSON',
		type:'POST',
		success:function(cs){
			$('#cardBody').append("<span onclick=\"deleteCodeSet('"+cs.id+"','"+cs.name+"')\" class=\"badge badge-pill badge-primary\" style='padding:7px;'>"+cs.name+"<span style='cursor:pointer;font-size:20px;'> &times; </span></span> ");
			$('#addCodeSetModal').modal('hide');
		},
		error:function(){
			alert('保存失败！');
		}
	});
}

function deleteCodeSet(id,name){
	if(confirm('删除：'+name+'?')){
		$.ajax({
			url:'/'+AppName+'/code/removeCodeSet',
			data:{
				id:id
			},
			type:'POST',
			success:function(){
				$('.badge[codeSetId='+id+']').remove();
			},
			error:function(){
				alert('保存失败！');
			}
		});
	}
}
function resetNewCode(){
	newCodeForm.reset();
}