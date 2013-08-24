define(['jquery', 'employeeDao', 'text!../views/EmployeeList.html'], 
		function($, employeeDao, html){
	"use strict";
	console.log('loading employeeListControl.js');

	var currPageNo = 1;
	var totalPage = 50;
	
	function listEmployee (pageNo) {
		employeeDao.list(pageNo, function(empList, countPage) {
			totalPage = countPage;
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
			currPageNo = pageNo;
			$('#pageNo').html(currPageNo);
		});
	} 
	
	return { 
		create: function() {
			return $(html); 
		},
		init: function() {
			listEmployee(1);
			
			$('#prevPage').click(function(event){
				event.preventDefault();
				if (currPageNo > 1) {
					listEmployee(--currPageNo);
				}
				
			});
			
			$('#nextPage').click(function(event){
				event.preventDefault();
				if (currPageNo < totalPage) {
					listEmployee(++currPageNo);
				}
			});
			
			$('body').on('click', '.detailLink', function(event){
				event.preventDefault();
				$(this).trigger('clickEmployee', [$(this).attr('href')]);
			} );
		},
		setVisible: function(isVisible) {
			if (isVisible) {
				$('#listDiv').css('display', '');
			} else {
				$('#listDiv').css('display', 'none');
			}
		},
		refreshList: function() {
			listEmployee(1);
		}
	}; // return object
});











