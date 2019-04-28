$(function(){
	$('.nav-link').click(function(){
		$('#mainFrame').prop('src',$(this).attr('href'))
	});
})