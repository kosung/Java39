var listDiv, detailDiv;

require.config({
    baseUrl: 'scripts',
    paths: {
        // the left side is the module ID,
        // the right side is the path to
        // the jQuery file, relative to baseUrl.
        // Also, the path should NOT include
        // the '.js' file extension. This example
        // is using jQuery 1.9.0 located at
        // js/lib/jquery-1.9.0.js, relative to
        // the HTML page.
        jquery: '../../../js/jquery-1.10.2',
        mustache: '../../../js/mustache',
        text: '../../../js/text-2.0.7',
        util: '../../../js/util',
    }
});

require(['jquery', 'employeeListControl'], 
		function($, employeeListControl) {
	$(document).ready( function() {
		console.log('loading main.js');
		
		listDiv = employeeListControl.create();
		$('#main').append(listDiv);
		employeeListControl.init();
		
		$('#newBtn').click( function(event) {
			event.preventDefault();
			employeeListControl.setVisible(false);
			require(['employeeFormControl'], function(employeeFormControl){
				if (detailDiv == undefined) {
					detailDiv = employeeFormControl.create();
					$('#main').append(detailDiv);
					employeeFormControl.init();
				}	
				employeeFormControl.setVisible(true);
			});
		});
		
		$('#main').on('addComplete updateComplete deleteComplete cancelForm', function(event){
			if (event.type != 'cancelForm') {
				employeeListControl.refreshList();
			}
			require(['employeeFormControl'], function(employeeFormControl){
				employeeFormControl.setVisible(false);
			});
			employeeListControl.setVisible(true);
		});
		
		$('#main').on('clickEmployee', function(event, employeeNo){
			employeeListControl.setVisible(false);
			require(['employeeFormControl'], function(employeeFormControl){
				if (detailDiv == undefined) {
					detailDiv = employeeFormControl.create();
					$('#main').append(detailDiv);
					employeeFormControl.init();
				}
				employeeFormControl.getEmployee(employeeNo);
				employeeFormControl.setVisible(true);
			});
		});
	});
});