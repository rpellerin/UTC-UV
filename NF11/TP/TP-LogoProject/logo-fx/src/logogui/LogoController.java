package logogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import logoparsing.LogoLexer;
import logoparsing.LogoParser;
import logoparsing.LogoParser.ProgrammeContext;
import logoparsing.LogoTreeVisitor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.gui.TreeViewer;


public class LogoController {
	private static int W = 900, H = 450;
//	@FXML
//	BorderPane astPane;
	@FXML
	Slider scaleSlider;
	@FXML
	TextArea program, logarea;
	@FXML
	Button run, clrview, clrlog;
	@FXML AnchorPane view;
	@FXML TabPane resultPane;
	@FXML
	SwingNode swingNode;
	
	private JPanel jTreeViewPane = null; // pane for tree and slider
	private JScrollPane jScrollASTPane = null; // panel with AST tree
	private TreeViewer viewer = null;
	Group g;
	public void initialize() {
		createSwingContent();
		Log.getInstance().setLogZone(logarea);
		g = new Group();
		view.getChildren().add(g);
	}
	public void runParser() {
		String prog = program.getText();
		if (prog.length() > 0) {
			ANTLRInputStream str = new ANTLRInputStream(prog);
			LogoLexer lexer = new LogoLexer(str);

			// parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LogoParser parser = new LogoParser(tokens);

			ProgrammeContext tree = parser.programme();
			List<String> rules = Arrays.asList(parser.getRuleNames());
			viewer = new TreeViewer(rules, tree);
			int sliderValue = (int) ((viewer.getScale() - 1.0) * 1000);
			scaleSlider.setValue(sliderValue);
			getJScrollASTPane().setViewportView(viewer);
			
			LogoTreeVisitor visitor = new LogoTreeVisitor();
			visitor.initialize(g);
	           visitor.visit(tree);
		}
	}
   public void clearView() {
	   g.getChildren().clear();
   }
   public void clearLog() {
	   logarea.clear();
   }
	private void createSwingContent() {
		SwingUtilities.invokeLater(() -> swingNode
				.setContent(getjTreeViewPane()));
	}

	public JPanel getjTreeViewPane() {
		if (jTreeViewPane == null) {
			jTreeViewPane = new JPanel();
			jTreeViewPane.setMaximumSize(new Dimension(W,H));
			jTreeViewPane.setLayout(new BorderLayout());
			jTreeViewPane.add(getJScrollASTPane(), BorderLayout.CENTER);
			scaleSlider.setValue(0);
			scaleSlider.valueProperty().addListener((obs, oldv, newval) -> {
				if (viewer != null)
					viewer.setScale((double) newval / 1000.0 + 1.0);
			});

		}
		return jTreeViewPane;
	}

	private JScrollPane getJScrollASTPane() {
		if (jScrollASTPane == null) {
			jScrollASTPane = new JScrollPane();
		}
		return jScrollASTPane;
	}
}
