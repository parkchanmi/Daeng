<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link href='/DaengDaeng/fullcalendar-3.4.0/fullcalendar.min.css'
	rel='stylesheet' />
<link href='/DaengDaeng/fullcalendar-3.4.0/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<script src='/DaengDaeng/fullcalendar-3.4.0/lib/moment.min.js'></script>
<script src='/DaengDaeng/fullcalendar-3.4.0/lib/jquery.min.js'></script>
<script src='/DaengDaeng/fullcalendar-3.4.0/fullcalendar.min.js'></script>
<script>

 $(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'listDay,listWeek,month'
			},

			// customize the button names,
			// otherwise they'd all just say "list"
			views: {
				listDay: { buttonText: 'list day' },
				listWeek: { buttonText: 'list week' }
			},

			defaultView: 'month',
			defaultDate: '2017-08-26',
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			eventLimit: true, // allow "more" link when too many events
		/* 	events: [
				{
					title: 'All Day Event',
					start: '2017-08-01'
				},
				{
					title: 'Long Event',
					start: '2017-08-07',
					end: '2017-08-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2017-08-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2017-08-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2017-08-11',
					end: '2017-08-13'
				},
				{
					title: 'Meeting',
					start: '2017-08-12T10:30:00',
					end: '2017-08-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2017-08-12T12:00:00'
				},
				{
					title: 'Meeting',
					start: '2017-08-12T14:30:00'
				},
				{
					title: 'Happy Hour',
					start: '2017-08-12T17:30:00'
				},
				{
					title: 'Dinner',
					start: '2017-07-12T20:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2017-07-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2017-09-07'
				}
			] */
		});
		
	});

 
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>
