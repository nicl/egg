# egg

A CLI tool to create and report on services.

## Tasks

Run:

    $ egg -h

to see available tasks.

To add a new task:

* add a file under src/egg/tasks/.. with your task definition
* add the task to core.clj

Note, a task:

* accepts a list of options
* may output to standard out as it executes
* outputs a report (see `report.clj` for details)

## License

Copyright © 2015 Nicolas Long

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
