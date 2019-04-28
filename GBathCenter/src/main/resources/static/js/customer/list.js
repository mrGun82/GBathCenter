var searchUrl = '/'+AppName+'/customer/find';
var $ncm;
var $icm;
function init(){
	$ncm = $('#newCustomer');
	$icm = $('#customerInfo');
	$('[data-toggle="tooltip"]').tooltip();
}
$(function(){
	init();
	initTable(searchUrl);
	$('.searchType').click(function(){
		$('#searchType').val($(this).attr('type'));
		$('#showSearchType').text($(this).text())
	});
	$('#doSearch').click(doSearch);
	$('#newCustomerBtn').click(showNew);
	$('#saveBtn').click(doSave);
	$('#expenseType').change(function(){
		$('#expense').val($('#expenseType option:selected').attr('price'));
	});
	$('.newExpenseType').change(function(){
		var total = 0;
		$('.newExpenseType').each(function(){
			if($(this).prop('checked')){
				total+=parseInt($(this).attr('price'));
			}
		});
		$('#newExpense').val(total);
	});
	$('.infoExpenseType').change(function(){
		var total = 0;
		$('.infoExpenseType').each(function(){
			if($(this).prop('checked')){
				total+=parseInt($(this).attr('price'));
			}
		});
		$('#infoExpense').val(total);
	});
	
	$('.hisTabLink').click(function(){
		var isActive = $(this).is('.active');
		if(!isActive){
			var src = $(this).attr('href')+'?customerId='+$('#histCustomerId').val();
			$('#histFram').attr('src',src);
		}
	});
	
	$('#updateInfoBtn').click(doUpdateInfo);
	$('#hideNewBtn').click(hideNew);
	$('#modifInfoBtn').click(modifInfo);
	$('#hideInfoBtn').click(hideInfo);
	$('#hideHistoryBtn').click(hideHistory);
	$ncm.on('hidden.bs.modal', resetNew);
	$icm.on('hidden.bs.modal', resetInfo);
});

function checkCustomerInfo($ph){
	var ph = $ph.val();
	var re=/^\d{8,}/;
	if(!re.test(ph)){
		alert('请输入手机号码');
		$ph.focus();
		return false;
	}
	return true;
}
function doSave(){
	if(checkCustomerInfo($('#newPhoneNumber')))
		$.ajax({
			url:'/'+AppName+'/customer/save',
			contentType: "application/json;charset=utf-8",
			data:JSON.stringify(getSaveJson()),
			type:'POST',
			success:function(){
				reloadTable();
				hideNew();
			},
			error:function(){
				alert('保存失败！');
			}
		});
}

function getSaveJson(){
	var newExpenseTypeIds = new Array();
	var newExpenseTypeNames = new Array();
	$('.newExpenseType:checked').each(function(){
		newExpenseTypeIds.push($(this).val());
		newExpenseTypeNames.push($(this).attr('names'));
	});
	return {
		name:$('#newName').val(),
		gender:$('#newGender').val(),
		phoneNumber:$('#newPhoneNumber').val(),
		level:$('#newLevel').val(),
		expense :$('#newExpense').val(),
		expenseTypeId:$('#newExpenseType').val(),
		expenseTypeName:$('#newExpenseType option:selected').text(),
		recharge:$('#newRecharge').val(),
		expenseTypeIds:newExpenseTypeIds.join(','),
		expenseTypeNames:newExpenseTypeNames.join(',')
	}
}

function doSearch(){
	var sv = $('#searchValue').val();
	if($.trim(sv).length<1){
		initTable(searchUrl);
	}else{
		var json = {};
		var st = $('#searchType').val();
		if(st=='name'){
			json.name=sv;
		}else{
			json.phoneNumber=sv;
		}
		initTable(searchUrl,json);
	}
	return;
}

function showNew(){
	$ncm.modal({});
}

function hideNew(){
	resetNew();
	$ncm.modal('hide');
}
function resetNew(){
	newCustomerForm.reset();
}

function resetInfo(){
	$('.modifInfo').prop('disabled',true);
	infoCustomerForm.reset();
}

function showInfo(id){
	$.ajax({
		url:'/'+AppName+'/customer/findById',
		type:'POST',
		dataType:'JSON',
		data:{id:id},
		success:function(info){
			setInfo(info);
			$icm.modal();
		},
		error:function(){
			alert('操作失败！');
		}
	})
}
function setInfo(info){
	$('#infoId').val(info.id);
	$('#infoName').val(info.name);
	$('#infoGender option[value='+info.gender+']').prop('selected',true);
	$('#infoPhoneNumber').val(info.phoneNumber);
	$('#infoLevel option[value='+info.level+']').prop('selected',true);
	$('#balance').text(info.balance);
}
function modifInfo(){
	$('.modifInfo').prop('disabled',false);
}
function hideInfo(){
	$icm.modal('hide');
}

function doUpdateInfo(){
	if(checkCustomerInfo($('#infoPhoneNumber'))){
		$.ajax({
			url:'/'+AppName+'/customer/update',
			data:getUpdateJson(),
			type:'POST',
			success:function(){
				reloadTable();
				hideInfo();
			},
			error:function(){
				alert('保存失败！');
			}
		});
	}
}
function getUpdateJson(){
	var infoExpenseTypeIds = new Array();
	var infoExpenseTypeNames = new Array();
	$('.infoExpenseType:checked').each(function(){
		infoExpenseTypeIds.push($(this).val());
		infoExpenseTypeNames.push($(this).attr('names'));
	});
	var r = parseInt($('#infoRecharge').val())?parseInt($('#infoRecharge').val()):0;
	var e = parseInt($('#infoExpense').val())?parseInt($('#infoExpense').val()):0;
	
	return {
		id: $('#infoId').val(),
		name: $('#infoName').val(),
		gender: $('#infoGender').val(),
		phoneNumber: $('#infoPhoneNumber').val(),
		level: $('#infoLevel').val(),
		recharge: r,
		expense: e,
		expenseTypeIds:infoExpenseTypeIds.join(','),
		expenseTypeNames:infoExpenseTypeNames.join(',')
	}
}

function combiningTable(json){
	var trs = '';
	for( var i = 0; i < json.length; i++ ) {
		trs += '<tr>'
		trs += "<td>"+ json[i].name +"</td>";
		trs += "<td>"+ json[i].phoneNumber +"</td>";
		trs += "<td>"+ json[i].balance +"</td>";
		trs += '<td align="center" nowrap="nowrap"><button onclick="showInfo('+json[i].id+')" type="button" class="btn btn-primary btn-sm showInfo">详情</button>&nbsp;&nbsp;'+
		'<button onclick="showHistory('+json[i].id+')" type="button" class="btn btn-primary btn-sm showInfo">记录</button></td>';
		trs += '</tr>'
    }
	return trs;
}

function showHistory(customerId){
	$('.hisTabLink').removeClass('active').eq(0).addClass('active');
	$('#histCustomerId').val(customerId);
	$('#historyModal').modal();
	$('#histFram').attr('src','/'+AppName+'/customer/historyRecharge?customerId='+customerId);
}
function hideHistory(){
	$('#historyModal').modal('hide');
}