package cs3500.view;

import java.util.Objects;

import cs3500.adapting.AnimationModelAdapter;
import cs3500.adapting.ViewAdapter;
import cs3500.animation.IAnimation;
import cs3500.animation.RoIAnimation;
import cs3500.provider.AnimationModel;
import cs3500.provider.EditorView;
import cs3500.provider.ImmutablePoint;


/**
 * A factory for producing views.
 */
public class ViewFactory {

  public ViewFactory(){}

  /**
   * Produces the correct view based on the given inputs.
   *
   * @param view desired view.
   * @param file output location.
   * @param speed speed of view if applicable.
   * @param model model.
   * @return
   */
  public IView factoryView(String view, String file, int speed, IAnimation model) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(file);
    Objects.requireNonNull(model);
    RoIAnimation ro = model.toRO();
    IView v;
    switch (view) {
      case "visual":
        v = new VisualView(ro, speed);
        break;
      case "text":
        v = new TextView(file, ro);
        break;
      case "svg":
        v = new SvgView(speed, file, ro);
        break;
      case "edit":
        v = new EditView(ro, speed);
        break;
      case "provider":
        ImmutablePoint loc = new ImmutablePoint(model.getX(), model.getY());
        EditorView editor = new EditorView(loc, model.getWidth(), model.getHeight(), speed);
        AnimationModel anim = new AnimationModelAdapter(model);
        v = new ViewAdapter(editor);
        v.acceptIDsToTypes(anim.getIDsToTypes());
        v.acceptTransformations(anim.getTransformations());
        v.updateLastTick(model.lastTick());
        v.start();
        break;
      default:
        throw new IllegalArgumentException("Unsupported view");
    }
    return v;
  }

}
