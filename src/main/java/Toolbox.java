import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class Toolbox {
	public static void gblAddComponent(	Container cont,
										GridBagLayout gbl,
										Component c,
										int x, int y,
										int width, int height,
										double weightx, double weighty) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x; gbc.gridy = y;
		gbc.gridwidth = width; gbc.gridheight = height;
		gbc.weightx = weightx; gbc.weighty = weighty;
		gbl.setConstraints(c, gbc);
		cont.add(c);
	}
}
