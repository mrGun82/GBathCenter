$(function(){
	initCode();
});
function initCode(){
	$.ajax({
		url:'/'+AppName+'/code/find',
		type:'POST',
		success:function(codes){
			alert(codes)
		}
	});
}

function createCodeCards(){
	var codeHtml="<div class=\"card\">"+
			    "<div class=\"card-header\" id=\"headingOne\">"+
			        "<div data-toggle=\"collapse\" data-target=\"#collapseOne\" aria-expanded=\"true\" aria-controls=\"collapseOne\" style=\"cursor:pointer\">"+
			          "code1 <span class=\"badge badge-primary badge-pill\">14</span>"+
			        "</div>"+
			    "</div>"+
			    "<div id=\"collapseOne\" class=\"collapse show\" aria-labelledby=\"headingOne\" data-parent=\"#accordionExample\">"+
			      "<div class=\"card-body\">"+
			        "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS."+
			      "</div>"+
			    "</div>"+
			  "</div>"
}