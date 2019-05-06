var AppName = 'GBathCenter';
function stopBubble(){
	window.event? window.event.cancelBubble = true : e.stopPropagation();
}