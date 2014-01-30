babicki-assignment1
==================
By Sasha Babicki

Special File Locations:
UML diagram -> /doc/UML.pdf
Licence -> /LICENCE

Usage:
- Retrieve the project by using 
	git clone https://github.com/sbabicki/babickiassignment1.git
- Run the babickiassignment1 project as an application. 
- The name on the menu will show up as babicki-assignment1


Program Walkthrough: 
Main page - View a list of all Counters
- Add new counter by typing the desired name of the counter at the bottom of the screen. 
  Then press the Add button to submit your changes.
- View options for a specific counter by pressing that counter. 

Select Counter - Update the count and view counter options
- Press the button on the screen to increase the count for that counter.
- Press the menu button at the top right hand side of the screen to view and select counter options.
  Menu options
  	- Home: Return to main page
	- Remove: Deletes the selected counter 
	- Reset: Set the selected counter's count to zero
	*- Rename: Rename the counter. 
	**- Counter statistics: View time aggregation for the selected counter.
	**- Total statistics: View time aggregation for the all existing counters.
  (* and ** options redirect to new page specified below)
  
* Rename Counter - Change the name of a specific counter
- Type in the new name you want for the counter. If you decide not to rename the counter, just submit the old name.
- Press the rename button to submit your changes.

** View Statistics - View the statistics for a counter or all counters
- View all counts aggregated by hour, day, week, and month. 
- Select Home from the menu to return to the main page. 
	
	
Assumptions:
1) All save data from app is created by the app. The sort feature requires this 
because it sorts when each individual counter is updated.

2) Duplicates are not handled. Because the count for the counter is also 
displayed the user is expected to delete or rename duplicates when found, 
and can distinguish by count in most cases.

3) There are no restrictions on the size of counter names. Users are expected 
to keep their entries reasonable. Even if they aren't there shouldn't be  
any problems, this is just a disclaimer in case any undefined behavior is 
caused by exceptionally long strings.

4) Total Statistics are calculated using data from current counters state,
does not include any information about deleted counters or data from before
a counter is reset.
