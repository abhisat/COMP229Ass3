# Task 0

Clone this repository (well done!)

# Task 1

Take a look at the two repositories:

  * (A) https://bitbucket.org/farleyknight/ruby-git
  * (B) https://bitbucket.org/kennethendfinger/git-repo

And answer the following questions about them:

  * Who made the last commit to repository A?
       *farleyknight commit 4831ddd that deleted a file.*
  * Who made the first commit to repository A?
       *scott Chacon commit f5baa11 that setup a very basic ruby project.  It has a README, one ruby source file, and a Rakefile for importing libraries.*
  * Who made the first and last commits to repository B?
       *First commit is cf31fe9 by the Android Open Source Project.  It looks to be a dump of an already-existing codebase.  The last commit is e02ac0a by Shawn O. Pearce who disabled clone bundle support (whatever that means).*
  * Are either/both of these projects active at the moment?  🤔 If not, what do you think happened?
       *Neither are active now but repository B was forked 4 times, one of which (https://bitbucket.org/ViolentC/git-repo) has been updated recently.  In fact, ViolentC forked both repositories and then made no changes.  I think we can guess that is one of last year's students experimenting with git :)*
  * 🤔 Which file in each project has had the most activity?
       *This is not a task we can do just with bitbucket - we are going to need advanced git features.  You will need to clone the repository to your computer to do this.  Once  you have cloned it, you will need to use the command line interface of git.  On windows that is available in PowerShell, in MacOS and Linux you should use the Terminal.  The command to get the information we want is `git diff --stat`.  I.e. we ask git to tell us about the diffs but to give us the statistical summary.  We need to know the range of commits to ask about.  In this case we want the full range so we ask for the diffs from the first commit (we need to look that up) to the latest (we can use the shortcut `HEAD`).  The magic incantation that does this is `git diff --stat HEAD..f5baa11a1c82dc42ade5c291e9f061c13b66bc2f` for ruby-git.  This spews a whole pile of stuff on our console and scanning down the list we see that `lib.rb` with 719 deletions is the most active.  If we do the same for git-repo (using the incantation `git diff --stat HEAD..cf31fe9b4fb650b27e19f5d7ee7297e383660caf`) we see `reflection.py` with 1653 insertions is the most active file.*

# Task 2

Setup a new IntelliJ project with a main method that will print the following message to the console when run:

~~~~~
Sheep and Wolves
~~~~~

🤔 Now setup a new bitbucket repository and have this project pushed to that repository.
You will first need to `commit`, then `push`.  Ensure you have setup an appropriate `.gitignore`
file.  The one we have in this repository is a very good start.

# Task 3

Draw a 20 by 20 grid on a 1280x720 window. Have the grid take up the 720x720 square on the left of the window.  Each cell in the grid should be 35 pixels high and wide and the grid should be drawn 10 pixels off the top and left borders of the screen.  To do this, you should use the `Graphics` class from the Java libraries.  Be sure to consult the tips page for this task (it is a link in iLearn).  Without it, you will be very confused.

# ☆ Task 4

Create a 2D array to represent the grid and connect the drawn grid to the array in some way.

**Solution:** I created an array to hold _the top left point_ of each box in the grid.   Then I loop over that array in the `paint` method to draw each box.  They all have the same size, so I didn't need to store that.  Thanks to Java and OO programming, I can put any object in an array and the `Point` object from the `java.awt` library is just what I need.

# Task 5

Modify your program so that mousing over a cell will "highlight" it.  Highlighted cells should be drawn in grey.

**Solution:** A `Canvas` can ask for the mouse position anytime with `getMousePosition()` so we do that and draw the box differently for when the mouse is within it.  We use a helper method, `isWithin` to do the computation for us.  Note that since we now have an animating picture, not a static picture, we need to have the screen drawn repeatedly, not once.  Thus we put the `repaint` request into an infinite loop.

# Task 6

Ensure your program, if it does not already, has a `Cell` class and that your grid array is an array of `Cell` objects.  It should still display as before.  What are reasonable methods and fields for the `Cell` class?  Now create a `Grid` class to subsume your 2D array of `Cell`s.  What fields and methods should this class have?

**Solution:** As you can imagine, we need to introduce the two classes.  `Grid` becomes the home for the 2D array and instead of `Point`s in the array, we have `Cell`s.  This make `Cell` the sensible place to put the `isWithin` method, but because it is owned by the `Cell` it is better to call it `contains` (if you read the lines of code like english they make more sense).  A `Cell` needs to know if the mouse is over it so it can draw itself the right way (highlighted or not) so we pass that information on to the `paint` method from the `Grid` class - who got the mouse position from the `Canvas` in it's `paint` method.  There are many, many ways to organise this little dance but this particular way will work better for us in the future.  You might like to discuss on the forums the pros and cons of your solution compared to this one.


# Task 7

Our `Cell` class is really a specialised rectangle and the Java API already has a `Rectangle` class.  Have `Cell` inherit from `java.awt.Rectangle` (https://docs.oracle.com/javase/8/docs/api/java/awt/Rectangle.html).  It will be good to call `super` in the `Cell` constructor and to use the `contains` method that comes in `Rectangle` instead of your own.  NB:  The `contains` we wrote was graceful when given a `null` pointer for the point, the one from `Rectangle` is not, you will need to "protect" it in some way.
