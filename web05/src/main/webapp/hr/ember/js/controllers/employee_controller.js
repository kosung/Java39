'use strict';

App.EmployeeController = Ember.ObjectController.extend({
	no: null,
	name: null,
	job: null,
	managerNo: null,
	hireDate: null,
	salary: null,
	commission: null,
	departmentNo: null,
	
	empty: function() {
		this.set('no', null);
		this.set('name', null);
		this.set('job', null);
		this.set('managerNo', null);
		this.set('hireDate', null);
		this.set('salary', null);
		this.set('commission', null);
		this.set('departmentNo', null);
	}, 
	
	employee: function(key, value) {
		if (arguments.length == 1) {
			return this.get('no');
		} else {
			var no = this.get('no'); 
			if (value != no) {
				this.empty();
				
				var controller = this;
				jQuery.getJSON('../retrieveEmp.do?empno=' + value, function(result){
					if (result.status == "success") {
						var emp = result.data;
						controller.set('no', emp.no);
						controller.set('name', emp.name);
						controller.set('job', emp.job);
						controller.set('managerNo', emp.managerNo);
						controller.set('hireDate', emp.hireDate);
						controller.set('salary', emp.salary);
						controller.set('commission', emp.commission);
						controller.set('departmentNo', emp.departmentNo);
					} else {
						controller.transitionToRoute('employees', 1);
					}
				});
			}
		}
	}.property(),
	
	add: function() {
		var controller = this;
		$.post('../addEmp.do',
			{
				no: this.get('no'),
				name: this.get('name'),
				job: this.get('job'),
				managerNo: this.get('managerNo'),
				hireDate: this.get('hireDate'),
				salary: this.get('salary'),
				commission: this.get('commission'),
				departmentNo: this.get('departmentNo')
			},
			function(result) {
				if (result.status == 'success') {
					controller.transitionToRoute('employees', 1);
				} else {
					alert(result.message);
				}
			},
			'json'
		);
	},
	
	update: function() {
		var controller = this;
		$.post('../updateEmp.do', 
			{
				no: this.get('no'),
				name: this.get('name'),
				job: this.get('job'),
				managerNo: this.get('managerNo'),
				hireDate: this.get('hireDate'),
				salary: this.get('salary'),
				commission: this.get('commission'),
				departmentNo: this.get('departmentNo')
			}, 
		    function(result) {
				if (result.status == 'success') {
					controller.transitionToRoute('employees');
				} else {
					alert(result.message);
				}
			}, 
			'json'
		);
	},

	remove: function() {
		var controller = this;
		$.getJSON('../deleteEmp.do?empno=' + this.get('no'), 
			function(result) {
				if (result.status == 'success') {
					controller.transitionToRoute('employees');
				} else {
					alert(result.message);
				}
			}
		);
	},

});

