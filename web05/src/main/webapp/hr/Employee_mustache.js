"use strict";

window.onload = function() {
	listEmployee();
	
	$('#detailForm').onsubmit = function() {
		$.ajax({
			type: 'POST',
			url: 'addEmp.do',
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
					$('#cancelBtn').dispatchEvent(new MouseEvent('click', {
					    'view': window,
					    'bubbles': true,
					    'cancelable': true
					}));
					listEmployee();
				} else {
					alert(result.message);
				}
			},
			error: function(msg) {
				window.alert(msg);
			}
		});
		return false;
	};
};


function listEmployee() {
	$('.dataRow').remove();
	$.ajax({
		type: 'GET',
		url: 'searchEmp.do',
		dataType: 'json',
		success: function(empList) {
			var template = $('#listDivTamplate').html();
			var result = Mustache.to_html(template, {
				employeeList: empList
			});
			$('#listDiv').html(result);
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
			console.log(result);
		},
		error: function(msg) {
			window.alert(msg);
		}
	});
}


























