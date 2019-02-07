Overview of the animator:

We have a model object Animation.
The sole purpose of the model object is to store and track the data of the animation.
To do this we created multiple objects that allows easy and flexible storing/manipulation of the
data in the animation.

Animation:
Stores the dimensions of the canvas.
Stores the shapes and the motions of the shapes in the animation. To store this efficiently
The shapes are stored in a hashmap, with their name as the key.
The Animation's functionality provides methods for adding and removing shapes. Adding and removing
motions of shapes.

Shape:
A Shape object represents a shape to be drawn. A shape keeps track of all the motions and changes it
goes through during an animation. A shape is comprised of a name, its current state and a list
of its motions. A Shape's name is unique. Shape provides methods for creating and updating shapes.

Motions:
The Motion object is how the model stores the data for a motion.
A motion is comprised of a duration, represented by a start time and end time.
The motion keeps track of any changes or do nothings by storing a start state and end state.
The Motion object provides functionality for detecting changes and updating changes.

State:
The State object keeps track of the state of a shape.
A shape's state is comprised of its x, y coordinate, r, g, b colors and size w, h.

CHANGES FOR ASSIGNMENT6:

Added a Read Only Animation:
The biggest change was creating a read only interface for the model.
The read only interface allows the view to observe the data of the model, but prevents any direct
changes to the model's data. The read only interface prevents any access to actual references to the
model data. The ro interface does allow copies of the model's data to be accessed and manipulated
with. This allows the view to manipulate data without changing the model itself.

Added methods for updating:
To udpdate the current state of shapes and to update the data for the animation, updater methods
were added to the model and objects inside the model.

The View:
The view object's sole purpose is to visualize the data it's given.
The view has room for extension. In future implementations it will allow user interaction, ie
declarative animation. All views are only able to observe the data of the model through a read only
interface. Any manipulation of the data is a derived copy of the data in the model to prevent
mutation.

Print View:
Is mostly just a transfer of the code in the model to a view interface that creates a text
description of the animation.

Svg View:
Translates the data in the model to SVG syntax.
Similar to the Print View in design.

Visual View:
Implements Java Swing to create an animation.

CHANGES FOR ASSIGNMENT7:

Animation:
Added methods for working with keyframes. Specifically a method that adds a keyframe and removes a specific keyframe.
Other than that, no major changes.

Motion and Shapes:
Same idea, added methods that the animation model can call on motions and shapes to modify them.

Read Only Animation:
Blocks off additional functions that can modify the model.

Text View:
before our view was just calling the print methods that were in the model.
The logic for printing has been moved to the view.

Editable Views:
New views that provide the same animation as the visual view, but with the capability of
editing the animation. The main panel for the view has multiple sub panels. There are multiple
components to the edit interface and breaking this up into multiple panels made the layout and
design easier.

One design choice we're not too happy about is the fact that the EditPanel has an IViewListener field.
For our interaction, there is an edit button and when that button's pressed a new editor window opens.
These panels don't exist at the beginning, so we can't call setListener until they exist.
The purpose of this field is to hold on to the listener that cares, so we can pass them to the
new panels. We don't think this tightly couples the view to the controller because it doesn't
depend on the implementation, it just cares that this field is a listener. The field is set when
setListener is called on the panel.





