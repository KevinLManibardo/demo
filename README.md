ASUMPTIONS:
(blablabla)
the results are not ordered in any way, as it was not specified
se cargan los ficheros en hilos separados localizados en X y se llaman Y

To start this project:
- Execute file "run.bat" (If you use Windows") or "run.sh" (if you use Linux)

To use this project:
- Start the project (see "To start this project")
- Open your web browser and navigate to any of this directions:
    - To get all events with the same source:
        - localhost:8000/events/source/{sourceId}
        - e.g. localhost:8000/events/source/203
        - You can find all sources and their IDs listed in the files contained in "/src/main/resources/files/sources"
    - To get all events within defined timestamps:
        - localhost:8000/events/start/{startDate}/end/{endDate}
        - e.g. localhost:8000/events/start/1612347654321/end/1712348003232 (markdown online para poner linkus)
        - The values of "startDate" and "endDate" are epoch timestamps in milliseconds
    - To get all events within defined values:
        - localhost:8000/events/min/{minValue}/max/{maxValue}
        - e.g. localhost:8000/events/min/-345/max/973
        - The values of "minValue" and "maxValue" can range from -1000 to 1000 (integers only) 