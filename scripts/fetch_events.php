<?php

// GOOGLE API KEY
// GOTO: https://console.developers.google.com/apis/library?project=youth-exploring-science
// Click Calendar API
// In API Manager, GOTO Credentials
// Click Create Credentials Button and SELECT API key
// Select Server key
// Give a name and create

$API_key = "AIzaSyBwsOOendr3PZs_lWoetoBzad2TdvDwj7E";

$objDateTime = new DateTime('NOW', new DateTimeZone('America/Chicago'));
$date = $objDateTime->format('Y-m-d\TH:i:sP');

$calendarID = "danieljdrapp%40gmail.com";

// GOOGLE GET URL
// TO get the URL go to https://developers.google.com/google-apps/calendar/v3/reference/events/list#examples
// Enter the CalendarID and execute

$url = "https://www.googleapis.com/calendar/v3/calendars/danieljdrapp%40gmail.com/events?timeMin="
  . $date
  . "&fields=defaultReminders%2Cdescription%2Citems(attachments%2CcolorId%2Ccreated%2Ccreator%2Cdescription%2Cend%2"
  . "ChtmlLink%2Cid%2Clocation%2Corganizer%2CoriginalStartTime%2Csource%2Cstart%2Csummary%2Cupdated)%2Csummary%2CtimeZone%2Cupdated&key="
  . $API_key;

echo file_get_contents($url);

?>
