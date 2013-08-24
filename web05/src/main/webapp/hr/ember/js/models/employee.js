'use strict';

App.Employee = DS.Model.extend({
	no: DS.attr('number'),
	name: DS.attr('string'),
	job: DS.attr('string'),
	managerNo: DS.attr('string'),
	hireDate: DS.attr('string'),
	salary: DS.attr('string'),
	commission: DS.attr('string'),
	departmentNo: DS.attr('string'),
});

