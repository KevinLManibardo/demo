# ASSUMPTIONS:

 - As it was not specified:
   
   - The results are ordered by event ID.
   
   - The value of "sourceId" appears twice in all responses (is contained both in "sourceId" and in "source.id"), as it was only requested to "add the source data to the event data".
   
   - All files contaning data (be it "sources" or "events") are in CSV format.
   
   - The name of files containing events data are simply named "eventsX" where X is an incremental number.
   
   - The name of files containing sources data are simply named "source_X" where X is a string that defines the "theme" of the source.
   
	   - Also, source names follow arbitrary themes.
   
   - The types required for identifiers, timestamps, and values has been assumed to be "Long".
   
  - Files contaning events and sources data can be found under "/src/main/resources/files"

  
  

# STARTING THIS PROJECT:

- Execute file "run.bat" (If you use Windows") or "run.sh" (If you use Linux)

- OR double click on file "prueba-ariadna-1.0.0.jar"

  
  

# USING THIS PROJECT:

 1. Start the project (see "STARTING THIS PROJECT").

 2. Open your web browser and navigate to any of this directions:

	 - To get all events with the same source:

		 - localhost:8000/events/source/{sourceId}

		 - e.g. [Source URL](http://localhost:8000/events/source/203)

		 - You can find all sources and their IDs listed in the files contained in "/src/main/resources/files/sources"

	 - To get all events within defined timestamps:

		 - localhost:8000/events/start/{startDate}/end/{endDate}

		 - e.g. [Timestamps URL](http://localhost:8000/events/start/1612347654321/end/1712348003232)

		 - The values of "startDate" and "endDate" are epoch timestamps in milliseconds

	 - To get all events within defined values:

		 - localhost:8000/events/min/{minValue}/max/{maxValue}

		 - e.g. [Values URL](localhost:8000/events/min/-345/max/973)

		 - The values of "minValue" and "maxValue" can range from -1000 to 1000 (integers only)