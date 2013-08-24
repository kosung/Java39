"use strict";

var currPageNo = 1;
var totalPage = 50;

$(document).ready( function() {
	console.log('loading Employee_list.js');
	
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
	
	$('#newBtn').click( function(event) {
		event.preventDefault();
		$(this).trigger('employeeNewForm');
	});
	
	$('body').on('click', '.detailLink', function(event){
		event.preventDefault();
		$(this).trigger('employeeDetail', [$(this).attr('href')]);
	} );
	
});

function listEmployee(pageNo) {
	if (pageNo == undefined) {
		pageNo = 1;
	}
	
	$.getJSON('../searchEmp.do?pageNo=' + pageNo, function(result) {
		if (result.status == 'success') {
			var empList = result.employeeList;
			totalPage = result.totalPage;
			
			$('.dataRow').remove();
			var table = $("#table");
			$.each(empList, function(index, employee){
				$('<tr>')
					.addClass('dataRow')
					.append($('<td>').append(
						$('<a>', {
							class: 'detailLink',
							href: employee.no
						}).html(employee.no)
					 ))
					.append($('<td>').html( employee.name ))
					.append($('<td>').html( employee.job ))
					.append($('<td>').html( employee.salary ))
					.append($('<td>').html( employee.departmentName ))
					.append($('<td>').html( employee.departmentLocation ))
					.append($('<td>').html( employee.managerName ))
					.appendTo(table);
			});
		} else {
			window.alert(result.message);
		}
	});
}

window.EmployeeListView  = {
	listEmployee: listEmployee
};







