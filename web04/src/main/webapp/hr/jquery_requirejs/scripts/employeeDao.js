define(['jquery'], function($) {
	"use strict";
	console.log('loading employeeDao.js'); 
	
	return {
		list: function (pageNo, callback) {
			if (pageNo == undefined) {
				pageNo = 1;
			}
			
			$.getJSON('../searchEmp.do?pageNo=' + pageNo, function(result) {
				if (result.status == 'success') {
					callback(result.employeeList, result.totalPage);
				} else {
					throw result.message;
				}
			});
		}, // end list()
		
		add: function(data, callback) {
			$.post('../addEmp.do', data, function(result) {
				if (result.status == 'success') {
					if (callback) {
						callback();
					}
				} else {
					throw result.message;
				}
			}, 'json');
		}, // end add()
		
		update: function(data, callback){
			$.post('../updateEmp.do', data, function(result) {
				if (result.status == 'success') {
					if (callback) {
						callback();
					}
				} else {
					throw result.message;
				}
			}, 'json');
		}, // end update()
		
		remove: function(empNo, callback){
			$.getJSON('../deleteEmp.do?empno=' + empNo, function(result) {
				if (result.status == 'success') {
					if (callback) {
						callback();
					}
				} else {
					throw result.message;
				}
			});
		}, // end remove()
		
		
		detail: function(empNo, callback) { 
			$.getJSON('../retrieveEmp.do?empno=' + empNo, function(result) {
				if (result.status == 'success') {
					if (callback) {
						callback(result.data);
					}
				} else {
					throw result.message;
				}
			});
		} // end detail()
	}; // return object
});













