'use strict';

App.currPage = 1;

App.Router.map(function() {
	this.route('employees', {path: '/employees/:pageNo'});
	this.route('employee', {path: '/employee/:empNo'});
	this.route('employee.new', {path: '/employee/new'});
});

App.EmployeesRoute = Ember.Route.extend({
	model: function(params) {
		if (params.pageNo == undefined) {
			return App.currPage;
		} else {
			return params.pageNo;
		}
	},

	setupController: function(controller, model) {
		controller.set('page', model);
	}
});

App.EmployeeRoute = Ember.Route.extend({
	model: function(params) {
		return params.empNo;
	},
	
	setupController: function(controller, model) {
		controller.set('employee', model);
	}
});

App.EmployeeNewRoute = Ember.Route.extend({
	renderTemplate: function() {
		this.controllerFor('employee').empty();
		this.render('employee');
	}
});
