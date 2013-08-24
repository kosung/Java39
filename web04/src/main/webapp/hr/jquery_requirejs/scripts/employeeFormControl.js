define(['jquery', 'employeeDao', 'util', 'text!../views/EmployeeForm.html'], 
		function($, employeeDao, util, html){
	"use strict";
	console.log('loading employeeFormControl.js');

	function clearForm() {
		$('#cancelBtn').trigger('click');
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
	
	return { 
		create: function() {
			return $(html);
		},
		init: function() {
			setButtonState('add');
			
			$('#addBtn').click(function() {
				employeeDao.add({
						no: $('#no').val(),
						name: $('#name').val(),
						job: $('#job').val(),
						managerNo: $('#managerNo').val(),
						hireDate: $('#hireDate').val(),
						salary: $('#salary').val(),
						commission: $('#commission').val(),
						departmentNo: $('#departmentNo').val()
					}, function() {
						clearForm();
						$('#addBtn').trigger('addComplete');
				});
			});
			
			$('#updateBtn').click(function() {
				employeeDao.update({
						no: $('#no').val(),
						name: $('#name').val(),
						job: $('#job').val(),
						managerNo: $('#managerNo').val(),
						hireDate: $('#hireDate').val(),
						salary: $('#salary').val(),
						commission: $('#commission').val(),
						departmentNo: $('#departmentNo').val()
					}, function() {
						clearForm();
						$('#updateBtn').trigger('updateComplete');
				});
			});
			
			$('#deleteBtn').click(function() {
				employeeDao.remove($('#no').val(), function(result) {
					clearForm();
					$('#deleteBtn').trigger('deleteComplete');
				});
			});
			
			$('#cancelBtn').click(function() {
				$(this).trigger('cancelForm');
			});
		},
		setVisible: function(isVisible) {
			if (isVisible) {
				$('#detailDiv').css('display', '');
			} else {
				$('#detailDiv').css('display', 'none');
			}
		},
		getEmployee: function(employeeNo) {
			if (employeeNo > 0) {
				setButtonState('view');
				employeeDao.detail(employeeNo, function(emp) {
					$('#no').val(emp.no);
					$('#name').val(emp.name);
					$('#job').val(emp.job);
					$('#salary').val(emp.salary);
					$('#managerNo').val(emp.managerNo);
					$('#hireDate').val(new Date(emp.hireDate).format('yyyy-MM-dd'));
					$('#commission').val(emp.commission);
					$('#departmentNo').val(emp.departmentNo);
				});
			} else {
				setButtonState('add');
			}
		}
	};
});



