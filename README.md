I don't like the inheritance approach as much as the private field approach.  In one sense it is "more object oriented" because a `Grass` cell _is-a_ `Cell`, but the code itself ends up worse.  The things I see that I don't like are:

  * **a switch in a nested loop:** The place to choose what type of surface of a cell has to move into the `Grid` constructor.  That puts it inside a nested loop resulting in a triple-nested block of code.  The deeper the nesting, the harder to understand.
  * **fours calls to `super.paint`:** this means that the sub-classes are intimately tied to the super-class.  In this case, adding any `g.setColor` at the top of `Cell.paint` will break this whole setup.  The alternative is to copy-past the code for drawing cells into each sub-class.  That's not much nicer because then we need to remember to change it in three or four places whenever we change it.
  * **we need to write a "nothing" constructor for each subclass:**  The constructors in `Dirt`, `Grass`, `Rocks`, and `Trees` are only there because we are not using the default (zero argument) constructor - such a waste, and just four more chances to introduce bugs.

There is an upside to the inheritance approach that we can take advantage of in the above-and-beyond task though.  The code to decide the surface of a cell is in `Grid`, that means we can see what is in one cell when we decide on the type of the next cell.  We can't do this as easily if the code for making this decision is inside the `Cell` class (that can't see the grid).  However, if we relax the restriction I put on you that you can't change the constructor signature, there is another - perhaps better - solution.

If the `Cell` constructor was `public Cell(int x, int y, int size, Color surface)` then you could decide upon the surface type in `Grid` and still do all the painting within the one `Cell` class.

🤔 This is the first instance of "favour composition over inheritance", something that will become a theme in this course.

# Summary

Overall, I think it's a bad change but there is a half-way solution that is better.