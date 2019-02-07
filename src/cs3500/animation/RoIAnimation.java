package cs3500.animation;

/**
 * A Read only interface for IAnimation. This interface provides only methods that can read data
 * from the model and blocks any methods that could change the model. We are currently passing this
 * to the view in the place of implementing a controller.
 */
public interface RoIAnimation extends IAnimation {


}
