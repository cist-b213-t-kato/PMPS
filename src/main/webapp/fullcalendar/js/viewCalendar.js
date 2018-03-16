$(document).ready(function() {
	
		$('#calendar').fullCalendar({
			header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,basicWeek,basicDay'
			},
			editable: false,
			eventLimit: true, // allow "more" link when too many events
			events: roomList
		});
		
	});