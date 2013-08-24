'use strict';

require(['jquery'],  
		function($) {
	
	$(document).ready(function(){
		$(document).on('click', '*[data-view]', function(event){
			event.preventDefault();
			var params = $(this).attr('data-view').trim().split(':');
			window.xx.load(params[0].trim(), params[1].trim());
		});
	});
	
	var xx = {};
	xx.load = function (parent, pageUrl) {
		require(['text!view/' + pageUrl], function(html) {
			var pageId = pageUrl.substr(0, pageUrl.lastIndexOf('.'));
			if ($('#' + pageId).length == 0) {
				var element = $(html);
				element.appendTo(parent);
				require(['control/' + element.attr('data-control') + '.js'], function(control) {
					control.init();
				});
			}
		});
	};
	
	window.xx = xx;
	
	return xx; 
});
