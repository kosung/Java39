'use strict';

App.EmployeesController = Ember.ObjectController.extend({
	currPage: 1,
	model: {},
	
	page: function(key, value) {
		if (arguments.length == 1) {
			return this.get('currPage');
		} else {
			var controller = this;
			jQuery.getJSON('../searchEmp.do?pageNo=' + value, function(result){
				if (result.employeeList.length > 0) {
					controller.set('model', result);
					controller.set('currPage', value);
					App.currPage = value;
				} else {
					controller.transitionToRoute('employees', 1);
				}
			});
		}
	}.property(),
	
	prevPage: function() {
		var currPage = this.get('currPage');
		if (currPage > 1) {
			this.transitionToRoute('employees', --currPage);
		}
	},
	
	nextPage: function() {
		var currPage = this.get('currPage');
		var totalPage = this.get('model.totalPage');
		
		if (currPage < totalPage) {
			this.transitionToRoute('employees', ++currPage);
		}
	}
});

