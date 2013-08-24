"use strict";

var currPageNo = 1;
var totalPage = 50;

window.onload = function() {
	listEmployee();
	
	$('#prevPage').click(function(event){
		event.preventDefault();
		if (currPageNo > 1) {
			listEmployee(--currPageNo);
			$('#pageNo').html(currPageNo);
		}
		
	});
	
	$('#nextPage').click(function(event){
		event.preventDefault();
		if (currPageNo < totalPage) {
			listEmployee(++currPageNo);
			$('#pageNo').html(currPageNo);
		}
	});
	
	$('#addBtn').click( function() {
		addEmployee();
	});
	
	$('#updateBtn').click(function(){
		updateEmployee();
	});
	
	$('#deleteBtn').click(function(){
		deleteEmployee( $('#no').val() );
	});
	
	$('#cancelBtn').click(function() {
		setButtonState('add');
	});
	
	setButtonState('add');
};

function clearForm() {
	$('#cancelBtn').dispatchEvent(new MouseEvent('click', {
	    'view': window,
	    'bubbles': true,
	    'cancelable': true
	}));
}

function setButtonState(state) {
	if (state == 'add') {
		$('#addBtn').css('display', '');
		$('#updateBtn').css('display', 'none');
		$('#deleteBtn').css('display', 'none');
	} else { // == 'view'
		$('#addBtn').css('display', 'none');
		$('#updateBtn').css('display', '');
		$('#deleteBtn').css('display', '');
	}
}

function listEmployee(pageNo) {
	if (pageNo == undefined) {
		pageNo = 1;
	}
	
	$.ajax({
		type: 'GET',
		url: 'searchEmp.do?pageNo=' + pageNo,
		dataType: 'json',
		success: function(result) {
			if (result.status == 'success') {
				var empList = result.employeeList;
				totalPage = result.totalPage;
				
				$('.dataRow').remove();
				var table = $("#table");
				$.each(empList, function(index, employee){
					$('<tr>')
						.addClass('dataRow')
						.append($('<td>').html( '<a class="empLink" href="' + employee.no + '">' + employee.no + '</a>' ))
						.append($('<td>').html( employee.name ))
						.append($('<td>').html( employee.job ))
						.append($('<td>').html( employee.salary ))
						.append($('<td>').html( employee.departmentName ))
						.append($('<td>').html( employee.departmentLocation ))
						.append($('<td>').html( employee.managerName ))
						.appendTo(table);
				});
				
				$('.empLink').click( function(event){
					event.preventDefault();
					getEmployee( $(this).attr('href') );
					
					//return false;
				} );
			} else if (result.status == 'not_authorized') {
				location.href = '../auth/login.do';
				return;
			} else {
				window.alert(result.message);
			}
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}

function getEmployee(empno) {
	$.ajax({
		type: 'GET',
		url: 'retrieveEmp.do?empno=' + empno,
		dataType: 'json',
		success: function(result) {
			if (result.status == 'success') {
				var emp = result.data;
				$('#no').val(emp.no);
				$('#name').val(emp.name);
				$('#job').val(emp.job);
				$('#salary').val(emp.salary);
				$('#managerNo').val(emp.managerNo);
				$('#hireDate').val(new Date(emp.hireDate).format('yyyy-MM-dd'));
				$('#commission').val(emp.commission);
				$('#departmentNo').val(emp.departmentNo);
				
				setButtonState('view');
			} else {
				window.alert(result.message);
			}
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}

function deleteEmployee(empno) {
	$.ajax({
		type: 'GET',
		url: 'deleteEmp.do?empno=' + empno,
		dataType: 'json',
		success: function(result) {
			if (result.status == 'success') {
				clearForm();
				listEmployee();
			} else {
				window.alert(result.message);
			}
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}

function updateEmployee() {
	$.ajax({
		type: 'POST',
		url: 'updateEmp.do',
		data: {
			no: $('#no').value,
			name: $('#name').value,
			job: $('#job').value,
			managerNo: $('#managerNo').value,
			hireDate: $('#hireDate').value,
			salary: $('#salary').value,
			commission: $('#commission').value,
			departmentNo: $('#departmentNo').value
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 'success') {
				clearForm();
				listEmployee();
			} else {
				alert(result.message);
			}
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}

function addEmployee() {
	$.ajax({
		type: 'POST',
		url: 'addEmp.do',
		data: {
			no: $('#no').val(),
			name: $('#name').val(),
			job: $('#job').val(),
			managerNo: $('#managerNo').val(),
			hireDate: $('#hireDate').val(),
			salary: $('#salary').val(),
			commission: $('#commission').val(),
			departmentNo: $('#departmentNo').val()
		},
		dataType: 'json',
		success: function(result) {
			if (result.status == 'success') {
				clearForm();
				listEmployee();
			} else {
				alert(result.message);
			}
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}






















