package g.oa;

import java.util.Properties;

import g.oa.util.PropertyUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
	private Stage primaryStage;
	private static Parent ROOT;
	private static Properties cache;
	public static ObservableList<String> TYPE_OBLIST = FXCollections.observableArrayList();
	static {
		cache = PropertyUtil.loadCacheProperties();
		String tol = cache.getProperty("typeOblist");
		if(null!=tol) {
			String[] tols = tol.split(",");
			for(String t:tols)
				TYPE_OBLIST.add(t);
		}else {
			TYPE_OBLIST = FXCollections.observableArrayList("代码", "文档", "其他");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		ROOT = FXMLLoader
				.load(getClass().getClassLoader().getResource("Main.fxml"));
		initUI();
	}

	private void initUI() {
		Scene scene = new Scene(ROOT, 800, 600);
		scene.getStylesheets().add(getClass().getClassLoader()
				.getResource("application.css").toExternalForm());
		primaryStage.setTitle("G's Recorder");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		Image icon = new Image(getClass().getClassLoader()
				.getResource("images/LOGO.gif").toExternalForm(), 140, 140,
				false, false);
		primaryStage.getIcons().add(icon);
		primaryStage.show();

	}

	public static void resetCache() {
		cache = PropertyUtil.loadCacheProperties();
	}

	public static Properties getCache() {
		return cache;
	}
}
