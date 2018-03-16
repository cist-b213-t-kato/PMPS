$(document).ready(function() {
	
		$('#editCalendar').fullCalendar({
			header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: roomList
		
		});
		
	});