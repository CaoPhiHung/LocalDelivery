package snippet;

public class Snippet {
	 mButton = new JButton("x");
	  UIDefaults def = new UIDefaults();
	  def.put("Button.contentMargins", new Insets(0,0,0,0));
	  mButton.putClientProperty("Nimbus.Overrides", def);
}

