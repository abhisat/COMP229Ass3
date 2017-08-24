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

# Task 8

Define a `Stage` class that can contain one `Grid` object and many `Character` objects.  There must be three separate characters, each a subclass of a `Character` _interface_ and each must have its own `paint` method.  The `paint` method must take a `Graphics` parameter and draw the character on that graphic.  Have the `paint` method specified in the `Character` interface and have each subclass define it.

Since `Character`s are drawing themselves, they need to know where they are on the screen so each will have a `Cell` field (that is set in the constructor) indicating where on the grid they are.

Have the program start with 1 grid and 3 characters:

  * Sheep (drawn white)
  * Wolf (drawn red)
  * Shepherd (drawn green)

# Task 9

Have a close look at your `Shepherd`, `Sheep` and `Wolf` classes.  If they are anything like mine they are _all the same except for the colour they use_.  This repetition is "a bad thing" because if the same thing is done in three different places, we need to remember that updating one requires us to update all three.

Is there a place that you could put all the common parts?

🤔 Will this work given what you currently have?  If not, what would we need to change?

# Task 10

Draw a picture of the inheritance hierarchy you have created.  You should (loosely) use [UML notation](http://www.csci.csusb.edu/dick/cs201/uml.html) for your diagram.  You are using UML In this case, and all through this course, only for "a rough sketch of an idea".

# Task 11

Start this task from the solution to Task 10.  The abstract `Character` class we were left with had to pick a default colour.  This was an entirely arbitrary choice.  Whenever you see arbitrary default values, you are seeing bad code.  But don't worry, Java 8 has us covered.  Java 8 introduced `Optional` values so that instead of arbitrary choices (or worse - `null`!) you can have an empty value.  [Read up on `Optional` values (just read until "Default Values and Actions")](http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html) and then change the `display` colour in the `Character` class to be an `Optional<Color>` instead of a `Color`.  You will need to make changes in the subclasses as well to support this.

🤔 Does the `Character` class even _need_ to be abstract?  Why or why not?

Your company builds a lot of games like this one and you now have to incorporate your work with the company's game processing code.  We have included a jar of this code in the `\lib` directory and javadoc explaining that code in the `\doc` directory.  In there you will see a `GameBoard` interface that represents things that a game might be played on, like a Chess board or the grid you have created.  You will also see an `GamePiece` interface that represents things that can move around such a game board, like a Chess piece or one of your characters.

## Task 12 - Step One

Have `Grid` implement `GameBoard` and have `Character` implement `GamePiece`.

## Task 12 - Step Two

With that done, you are now able to make use of the `RelativeMove`s provided by the library.  You should add functionality to play a set list of moves automatically.  I.e. when the game starts, it will play some hard-coded moves.  To do this you will need a list of moves to play, something like

~~~~~
private java.util.List<bos.RelativeMove> moves;
~~~~~

in your `Stage` class.

You will also need to have the `paint` method (that runs over and over again) make a move whenever a certain amount of time is up (say 2 seconds).

Note, we are now doing some processing between painting so we should move from the

~~~~~
loop forever {
  paint
}
~~~~~

paint loop to an update-and-paint loop like

~~~~~
loop forever {
  update game state
  paint
}
~~~~~

Something like the following `update` method in `Stage` (assuming you have a `timeOfLastMove` field in `Stage`) will work.

~~~~~
    public void update() {
        if (moves.size() > 0 && timeOfLastMove.plus(Duration.ofSeconds(2)).isBefore(Instant.now())){
            timeOfLastMove = Instant.now();
            moves.remove(0).perform();
        } else if (moves.size() == 0  && timeOfLastMove.plus(Duration.ofSeconds(20)).isBefore(Instant.now())) {
            System.exit(0);
        }
~~~~~

Fill your `moves` object with example moves and see if you can get your program to automatically play the moves you entered.

# Task 13

Currently, the game loop (in `Main.run`) is running as fast as it can.  We fixed the rate of movement at 2-second intervals, but the frame is still painted as quickly as possible.  This just burns CPU cycles and heats up your computer needlessly.  Your task is to "fix" the frame-rate so we are not pointlessly burning CPU power. You can do this by asking the current thread to sleep for a period of time using `Thread.sleep`. We want the frame-rate to be about 50 frames per second, that means we need the loop to take 20ms to complete.

Sleeping a thread throws an `InterruptedException` so you will need to catch that. In fact, we don't care about the thread being interrupted so the catch block should just report the fact it was interrupted, print out a representation (via `toString`) of the exception that was thrown, and continue on as normal.

# Task 14

Add the following method to the `Grid` class

~~~~~
    /**
     * Takes a cell consumer (i.e. a function that has a single `Cell` argument and
     * returns `void`) and applies that consumer to each cell in the grid.
     * @param func The `Cell` to `void` function to apply at each spot.
     */
    public void doToEachCell(Consumer<Cell> func){
      // Your job to add the body
    }
 ~~~~~

 Notice that the method accepts a `Consumer` functional interface.

 Now use this method to turn the `paint` method of the `Grid` class into a single line of code.  I.e. remove the double-nested loop and replace it with a call to `doToEachCell`.

# Task 15

Add two more methods to the `Grid` class

~~~~~
    /** Takes a cell predicate (i.e. a function that has a single `Cell` argument and
     * returns a `boolean` result) and applies that predicate to each cell, returning
     * the first cell it finds for which the predicate is true.
     * @param predicate The `Cell` to `boolean` function to test with
     * @return The first cell (searching row by row, left to right) that is true for the predicate.  Returns `null` if no such cell found.
     */
    public Pair<Integer, Integer> findAmongstCells(Predicate<Cell> predicate){
        // Your job to fill in the body
    }

    /** Takes a cell predicate (i.e. a function that has a single `Cell` argument and
     * returns a `result` and applies that predicate to each cell, returning
     * the first cell it finds for which the predicate is true.  Returns an optional that is full
     * if such a cell is found and an empty optional if there is no such cell.
     * @param predicate The `Cell` to `boolean` function to test with
     * @return The first cell (searching row by row, left to right) that is true for the predicate.  Returns an empty optional if no cell found.
     */
    public Optional<Pair<Integer, Integer>> safeFindAmongstCells(Predicate<Cell> predicate){
        // Your job to fill in the body

    }
~~~~~

Notice that these two accept an argument that is a `Predicate` functional interface, a function that takes something and returns `true` or `false`.  It should be fairly clear how to use `findAmongstCells` in your methods implementing `GameBoard`.  Make that change.

🤔 What would you think if I told you that you can use `safeFindAmongstCell` to turn each of those methods into a single statement?

Here is the solution for `below`:

~~~~~
    @Override
    public Optional<Cell> below(Cell relativeTo) {
        return safeFindAmongstCells((c) -> c == relativeTo).filter((p) -> p.first < 19              )
                                                           .map   ((p) -> cells[p.first+1][p.second]);
    }
~~~~~

Look up the `filter` and `map` methods of the `Optional` class and explain how this implementation of `below` works.

Modify the three similar methods (`above`, `leftOf`, and `rightOf`) to all use the same approach.

Do you like these versions better?  Why or why not?

# Task 16

It is time to start allowing characters in the game to make their own moves.  We call these "AI moves" because we are adding (simple) artificial intelligence to the non-shepherd characters.  There are quite a few changes required to make this work.  Firstly, we will need to use the latest version of the `bos` library.  It supports more types of moves (adding a random move and a non-move) and it requires game boards to be able to compute a path from one place to another.  The `bos.jar` file in this commit has all the additions, copy it into your `lib` folder.  Immediately your `Grid` will have unimplemented methods because the new `bos.GameBoard` has the extra abstract `movesBetween` method.  Thus your first job is to implement this method in your `Grid` class.

The simplest algorithm for doing this is to first compute all the horizontal moves required to get from one place to another.  They will either all be left moves or all right moves (depending on whether the "from" location is to the right or left of the "to" location).  You can use the indices in the grid to calculate exactly how many you need as well.  Once you have done this, repeat the process for vertical moves.  Add all these moves into a list for returning from the `movesBetween` method.

**WARNING:** Be very careful to make sure you know which index (first or second) is representing rows and which is columns in your Grid.  In the sample solution we have made the first co-ordinate the row and the second the column (this will seem backwards to many of you).

Now you need to give all characters the ability to choose a move themselves.  Add `public abstract RelativeMove aiMove(Stage stage);` to the `Character` abstract class.  This will force you to implement that method in each `Character` subclass.  You can choose exactly what move you want them to return to get the behaviour your want out of them.  You can start with having each return a `RandomMove` just so we can test the next step.

Now we have everything we need to move from replaying pre-set moves to having the characters choose their moves.  These changes will all take place in the `update` method of the `Stage` class.  We can take the moves list away entirely and on each "step", which you will recall was happening every two seconds, we ask each character what move it wants to make (with `aiMove`) and then `perform` that move.

Test that you have this working before moving on.

Now we will finesse the stage updating.  The game should end whenever the sheep makes it to the shepherd (the sheep lives!) or the wolf catches the sheep (the sheep dies).  Modify the `update` method to implement this.  You will need an `if` statement or two to check for those conditions and then `System.exit` when they are satisfied.  On the updates where they are not satisfied, the characters make their `aiMove`.

Now we add sensible behaviour to each character.  The behaviours will be:
  * The sheep moves one step along the path towards the shepherd.
  * The wolf moves one step along the path towards the sheep
  * The shepherd remains still.

Make the changes to the various `aiMove` methods to achieve the above.  This is where the `movesBetween` method available on the grid will be useful.  Note, to make this work in my solution, I needed to relax the access modifiers on most of the fields of the `Stage` class from `private` to `protected`.  Is this change reasonable?  What are the consequences?  We ask these questions because any relaxation of access modifiers must be justified in program design terms, not just as a matter of convenience.

# Task 17

So, time to throw a spanner in the works.  It has been decided that any time a sheep steps on a cell that is on the top-left to bottom-right diagonal, it must stop moving.  As if those cells have glue on them or something.  So we want to be able to _change the behaviour of the sheep at run-time_.  Sound familiar?  This will require a strategy pattern.  Change your program so the `aiMove` is not done directly in the method but done via a strategy pattern.  I suggest a `Behaviour` interface for the "top" of the strategy that contains a single method `public RelativeMove chooseMove(Stage stage, Character mover)`.  I also recommend adding a `Behaviour` field to the `Character` class that is set in its constructor.  Now change your program so that when the sheep is on a cell where `x == y` (i.e. that top-left to bottom-right diagonal), its behaviour changes to standing still (not moving).  Your sheep will die more often now.  How about making the shepherd start moving towards the sheep if it gets stuck?

You can have quite a bit of fun with interesting behaviour changes now and the `movesBetween` method is powerful enough to support a number of different movement strategies.

